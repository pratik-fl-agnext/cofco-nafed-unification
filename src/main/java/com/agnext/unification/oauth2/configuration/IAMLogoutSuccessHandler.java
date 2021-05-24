package com.agnext.unification.oauth2.configuration;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.agnext.unification.common.CommonUtil;

public class IAMLogoutSuccessHandler implements LogoutSuccessHandler {
	@Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {
        if (authentication != null && authentication.getDetails() != null) {
            try {
            	request.getSession().invalidate();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        response.setStatus(HttpServletResponse.SC_OK);
        StringBuffer redirectUrl = new StringBuffer("/oauth/authorize?response_type=code&client_id=")
        		.append(request.getParameter("client_id"));
        if(!CommonUtil.isEmpty(request.getParameter("bearer")))
        	redirectUrl.append("&bearer=").append(request.getParameter("bearer"));
        response.sendRedirect(redirectUrl.toString());
    }
}
