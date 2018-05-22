package com.tgt.myretail.logger.interceptors;

import com.tgt.myretail.logger.utils.RequestLogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class RequestInterceptor extends HandlerInterceptorAdapter {
    private final static Logger LOGGER = LoggerFactory.getLogger(RequestInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        HttpServletRequest requestCacheWrapperObject = null;
        try {
            requestCacheWrapperObject = new ContentCachingRequestWrapper(request);
            requestCacheWrapperObject.getParameterMap();
        } catch (Exception exception) {
            LOGGER.error("STACKTRACE: " + exception.getStackTrace().toString());
        } finally {
            RequestLogUtil.writeRequestToLog(requestCacheWrapperObject);
        }

        return true;
    }
}