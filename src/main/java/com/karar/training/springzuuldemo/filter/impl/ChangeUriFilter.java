package com.karar.training.springzuuldemo.filter.impl;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.zuul.filters.ProxyRequestHelper;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;

import javax.servlet.http.HttpServletRequest;

public class ChangeUriFilter extends ZuulFilter {

    @Autowired
    private ProxyRequestHelper helper;

    private final String REQUEST_URI_KEY = "requestURI";
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        // it is important to set order bigger than PRE_DECORATION_FILTER_ORDER cuz context provided after pre decoration
        // in case change order to before pre decoration uri changing block is not gonna work
        return FilterConstants.PRE_DECORATION_FILTER_ORDER+1;
    }

    @Override
    public boolean shouldFilter() {
        return true;
    }

    @Override
    public Object run() {
        RequestContext ctx = RequestContext.getCurrentContext();
        HttpServletRequest request = ctx.getRequest();

        Object originalRequestPath = helper.buildZuulRequestURI(request);

        // After pre decoration Zuul proxy has matched the context root (in our case it is services) and has removed from the uri
        String modifiedRequestPath = "/api/hello";
        ctx.put(REQUEST_URI_KEY, modifiedRequestPath);

        return null;
    }
}
