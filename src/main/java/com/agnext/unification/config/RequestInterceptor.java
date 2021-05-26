package com.agnext.unification.config;

import java.nio.file.AccessDeniedException;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.agnext.jwt.service.TokenService;
import com.agnext.jwt.service.TokenStore;
import com.agnext.unification.common.Constants;
import com.agnext.unification.entity.nafed.DcmDevice;
import com.agnext.unification.entity.nafed.UserEntity;
import com.agnext.unification.repository.nafed.DeviceRepository;
import com.agnext.unification.repository.nafed.StateManagerOperatorRepository;
import com.agnext.unification.repository.nafed.UserRepository;


@Component
public class RequestInterceptor implements HandlerInterceptor {

  private static final Log logger = LogFactory.getLog(RequestInterceptor.class);
  private static final String CORRELATION_ID = "correlation_id";


  @Autowired
  private ServerContext serverContext;

  @Autowired
  ApplicationContext context;
  
  @Autowired
  DeviceRepository deviceRepo;
  
  @Autowired
  StateManagerOperatorRepository smoRepo;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    logger.info("Incoming Request URI: " + request.getRequestURI());
    //handle DCM/SCM Requests
    String correlationId = request.getParameter(CORRELATION_ID);
    correlationId = correlationId == null ? UUID.randomUUID().toString() : correlationId;
    if (!byPassRequest(request)) {
      logger.debug("Request Needs Authentication");
     	TokenStore tokenStore = TokenService.validateToken(fetchToken(request.getHeader(Constants.AUTHORIZATION)));
     	if (tokenStore != null) {
        // Validate permissions
       logger.debug("Token Store is valid, validating permissions and updating request context.");
        validatePermissions(tokenStore, handler);
        updateRequestContext(correlationId, request, tokenStore,response);
      }else{
      	logger.debug("Executing IAM Authentication");
				return validateAndSaveRequest(request, response);
			}
    }
    return true;
  }

  private void updateRequestContext(final String correlationId, final HttpServletRequest request,
      final TokenStore tokenStore, HttpServletResponse response) throws Exception {
    
    RequestContext requestContext = serverContext.getRequestContext();
    logger.debug("request Context before Updation : " + requestContext);
    if(requestContext != null){
      requestContext.setCorrelationId(tokenStore.getUserName() + "_" + correlationId);
      requestContext.setUserId(tokenStore.getUserId());
      requestContext.setUserName(tokenStore.getUserName());
      requestContext.setPermissions(tokenStore.getPermissions());
      requestContext.setRoles(tokenStore.getRoles());
      requestContext.setCustomerId(tokenStore.getCustomerId());
      requestContext.setCustomerName(tokenStore.getCustomerName());
      requestContext.setCustomerType(tokenStore.getCustomerType());
      requestContext.setCustomerUuid(tokenStore.getCustomerUuid());
      requestContext.setRoles(tokenStore.getRoles());
      requestContext.setUserHierarchy(tokenStore.getUserHierarchy());
      requestContext.setUserUuid(tokenStore.getUserUuid());
      requestContext.setUserEmail(tokenStore.getUserEmail());
      requestContext.setUserMobile(tokenStore.getUserMobile());
      requestContext.setStateAdmin(getStateAdmin(requestContext));
      requestContext.setRequestURL(request.getHeader("origin"));
      setDeviceDetails(requestContext,tokenStore);
      try {
        requestContext.setAccessToken(fetchToken(request.getHeader("Authorization")));
      } catch (AccessDeniedException e) {
        e.printStackTrace();
      }
      context.setRequestContext(requestContext);
    }else{
      validateRequest(request,response);
    }


    MDC.put(CORRELATION_ID, requestContext.getCorrelationId());
  }


  private boolean validateAndSaveRequest(HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    return validateRequest(request, response);
  }

  private String fetchToken(String token) throws AccessDeniedException {
    if (token == null) {
      throw new AccessDeniedException("Token can't be empty");
    }
    return token.replace(Constants.BEARER + " ", "");
  }

  @Autowired
  UserRepository userRepository;

  private boolean validateRequest(HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    logger.info(
        "Validate request for : " + SecurityContextHolder.getContext().getAuthentication().getName()
            + " for resource : " + request.getRequestURI()
            + (request.getQueryString() != null ? "/" + request.getQueryString() : ""));

    UserEntity user = userRepository
        .findByUserEmail(SecurityContextHolder.getContext().getAuthentication().getName());
    setRequestContext(user, request);
    return true;
  }

  private boolean byPassRequestDCMSCM(HttpServletRequest request) {
    String oauthUrls[] = {"api/token", "error"};
    String reqUri = request.getRequestURI();
    for (String allowedUrl : oauthUrls) {
			if (reqUri.contains(allowedUrl)) {
				return true;
			}
    }
    return false;
  }


  private boolean byPassRequest(HttpServletRequest request) {
    String oauthUrls[] = {"/login", "/oauth/authorize", "/oauth/token", "/oauth/check_token",
        "/secure/two_factor_authentication", "/token/validate", "/error", "/images", "/fonts",
        "/css", "/js","/arya/token","arya/token","/error","tragnext/submit","/bypassed-requests"};
    String reqUri = request.getRequestURI();

    for (String allowedUrl : oauthUrls) {
			if (reqUri.contains(allowedUrl)) {
				return true;
			}
    }
    return false;
  }

  private void validatePermissions(TokenStore tokenStore, Object handler)
      throws AccessDeniedException {
    if (handler instanceof HandlerMethod) {
      HandlerMethod handlerMethod = (HandlerMethod) handler;
      Permissions permissions = handlerMethod.getMethod().getAnnotation(Permissions.class);
      if (permissions != null) {
        validateAndPopulatePermisssion(tokenStore, permissions);
      }
    }
  }

  private void validateAndPopulatePermisssion(final TokenStore tokenStore,
      final Permissions permissions)
      throws AccessDeniedException {
    if (!permissions.checkObjectAccessPermissions()) {
      if (!hasPermissions(tokenStore, permissions.values())) {
        logger.warn("Access Denied. User(id: " + tokenStore.getUserId()
            + ") not have the permission to perform this operation. Permissions required "
            + permissions.values());
        throw new AccessDeniedException("You are not having permission");
      }
    }
  }

  private boolean hasPermissions(final TokenStore tokenStore, final String... permissions) {
    boolean hasPermission = true;
    Set<String> availablePermissions = tokenStore.getPermissions();
    if (!availablePermissions.isEmpty()) {
      for (String permission : permissions) {
        if (!availablePermissions.contains(permission)) {
          hasPermission = false;
          break;
        }
      }
    } else {
      hasPermission = false;
    }
    return hasPermission;
  }

  private void setRequestContext(UserEntity user, HttpServletRequest request) {
    RequestContext requestContext = new RequestContext();
    requestContext.setUserUuid(user.getUserUuid());
    requestContext.setUserId(user.getUserId());
    requestContext.setCustomerUuid(user.getCustomer().getCustomerUuid());
    requestContext.setCustomerId(user.getCustomer().getCustomerId());
    requestContext.setCustomerType(user.getCustomer().getCustomerType().getCustomerType());
    try {
      requestContext.setAccessToken(fetchToken(request.getHeader("Authorization")));
    } catch (AccessDeniedException e) {
      e.printStackTrace();
    }
    context.setRequestContext(requestContext);
  }

  /*
   * private String fecthAccessToken(String token) throws Exception{
   * if(!CommonUtil.isEmpty(token)) { return
   * token.replace(Constants.RequestParams.HEADER_AUTHORIZATION_BEARER,
   * "").trim(); } throw new
   * IMException(Constants.ErrorCode.MISSING_AUTHORIZATION,
   * Constants.ErrorMessage.MISSING_AUTHORIZATION); }
   */

  @Override
  public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
      ModelAndView modelAndView) throws Exception {
    // TODO Auto-generated method stub
    HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
  }

  @Override
  public void afterCompletion(HttpServletRequest request, HttpServletResponse response,
      Object handler, Exception ex)
      throws Exception {
    // TODO Auto-generated method stub
    HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
  }

	private void setDeviceDetails(RequestContext requestContext, TokenStore tokenStore) {
		if (requestContext.getRoles().contains("operator")) {
			if(tokenStore !=null && tokenStore.getCustomerId() !=null && tokenStore.getUserId() !=null) {
			DcmDevice device = deviceRepo.findDetailsByCustomerIdAndUserIdAndDcmStatusId(tokenStore.getCustomerId(),
					tokenStore.getUserId(), Constants.STATUS.ACTIVE.getId());
			if (device != null) {
				requestContext.setDeviceId(device.getId());
				requestContext.setDeviceSerialNumber(device.getSerialNumber());
			}
			}
		}

	}
	
	private Long getStateAdmin(RequestContext requestContext) {
		logger.info(" Inside the getStateAdmin Method :  ");
		logger.info(" Request Context : " + requestContext);
		logger.info(" Operator's Id : " + requestContext.getUserId() + " , Operator's Email : "
				+ requestContext.getUserEmail());
		Long stateManagerId=null;
		
		if(requestContext.getRoles().contains("state_admin")){
			
			stateManagerId = requestContext.getUserId();
			 requestContext.setUserId(null);
//		stateManagerId = smoRepo.findStateManagerByOperatorId(context.getRequestContext().getUserId());
		logger.info(" State Manager's Id : " + stateManagerId);
		}
		return stateManagerId;
	}
	
}
