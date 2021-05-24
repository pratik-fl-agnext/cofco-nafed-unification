
package com.agnext.unification.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ServerContext {

    @Autowired
    private ApplicationContext context;

    public RequestContext getRequestContext() {
        return context.getBean(RequestContext.class);
    }

    public <T> T getBean(final Class<T> beanClass) {
        return context.getBean(beanClass);
    }
}
