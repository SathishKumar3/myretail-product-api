package com.tgt.myretail.logger.interceptors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.tgt.myretail.logger.log.enums.EnumLoggingKeys;
import com.tgt.myretail.logger.utils.LogUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class ResponseInterceptor implements ResponseBodyAdvice<Object> {

    private final static Logger LOGGER = LoggerFactory.getLogger(ResponseInterceptor.class);

    /**
     * @param returnType
     * @param converterType
     * @return
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {

        return true;
    }

    /**
     *
     * @param body
     * @param returnType
     * @param selectedContentType
     * @param selectedConverterType
     * @param request
     * @param response
     * @return
     */

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {

        StringBuilder builder = new StringBuilder();
        HttpServletResponse servletResponse = ((ServletServerHttpResponse) response).getServletResponse();
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();

        try {
            String jsonResponse = ow.writeValueAsString(body);
            builder.append(LogUtil.formatKeyValue(EnumLoggingKeys.HTTPSTATUS, String.valueOf(servletResponse.getStatus()), false));
            builder.append(LogUtil.formatKeyValue(EnumLoggingKeys.RESPONSEJSON, jsonResponse, false));
            builder.append("\n");
            writeWithLogLevel(servletResponse.getStatus(), builder.toString());
        } catch (Exception e) {
            LOGGER.error(EnumLoggingKeys.EXCEPTION + "=" + e.getStackTrace().toString());
        }

        return body;
    }

    private void writeWithLogLevel(Integer status, String logString) {
        if (status >= 200 && status < 300) {
            LOGGER.info(logString);
        } else if (status >= 400 && status < 500) {
            LOGGER.warn(logString);
        } else {
            LOGGER.error(logString);
        }
    }
}
