package com.agnext.unification.config;

import org.springframework.stereotype.Component;

@Component
public class ApplicationContext  {
	
	private RequestContext requestContext;

	/**
	 * @return the requestContext
	 */
	public RequestContext getRequestContext() {
		return requestContext;
	}

	/**
	 * @param requestContext the requestContext to set
	 */
	public void setRequestContext(RequestContext requestContext) {
		this.requestContext = requestContext;
	}

}
