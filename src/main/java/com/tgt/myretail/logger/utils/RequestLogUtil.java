package com.tgt.myretail.logger.utils;

import com.tgt.myretail.logger.log.enums.EnumLoggingKeys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

public class RequestLogUtil {
    private static final Set<String> EXCLUDED_HEADERS = new HashSet<>(Arrays.asList("cookie", "authorization"));
    private final static Logger LOGGER = LoggerFactory.getLogger(RequestLogUtil.class);

    public static void writeRequestToLog(HttpServletRequest request) {
        StringBuilder builder = new StringBuilder();
        MDC.put(EnumLoggingKeys.TRANSACTION.getValue(), UUID.randomUUID().toString());

        builder.append(EnumLoggingKeys.HEADERS.getValue()).append("=").append(getRequestHeaders(request)).append(" ");
        builder.append(EnumLoggingKeys.REQUESTURI.getValue()).append("=").append(request.getRequestURI()).append(" ");
        builder.append(EnumLoggingKeys.QUERYPARAMS.getValue()).append("=").append(getQueryStringParams(request.getQueryString())).append(" ");
        builder.append(EnumLoggingKeys.METHOD.getValue()).append("=").append(request.getMethod()).append(" ");
        builder.append(EnumLoggingKeys.PAYLOAD.getValue()).append("=").append(readRequestPayload(request));
        LOGGER.info(builder.toString());
        MDC.clear();
    }

    private static HttpHeaders getRequestHeaders(HttpServletRequest request) {
        if (request != null) {
            HttpHeaders headers = new HttpHeaders();
            Enumeration<String> names = request.getHeaderNames();
            while (names.hasMoreElements()) {
                String name = names.nextElement();
                if (!EXCLUDED_HEADERS.contains(name.toLowerCase())) {
                    headers.put(name, Collections.singletonList(request.getHeader(name)));
                }
            }
            return headers;
        }

        return new HttpHeaders();
    }

    private static String readRequestPayload(final HttpServletRequest request) {
        String payloadData = null;
        ContentCachingRequestWrapper contentCachingRequestWrapper = WebUtils.getNativeRequest(request, ContentCachingRequestWrapper.class);
        if (contentCachingRequestWrapper != null) {
            byte[] buf = contentCachingRequestWrapper.getContentAsByteArray();
            if (buf.length > 0) {
                payloadData = new String(buf, 0, buf.length);
            }
        }
        return payloadData;
    }

    private static String getQueryStringParams(String uriQueryParams) {
        String formattedQueryParams = null;
        if (uriQueryParams != null) {
            String params[] = uriQueryParams.split("&");
            Map<String, String> map = new HashMap<>();

            for (String p : params) {
                String pair[] = p.trim().split("=");
                String key = pair[0];
                String value = pair[1];
                map.put(key, addOrAppend(map.get(key), value));
            }

            formattedQueryParams = map.entrySet()
                    .stream()
                    .map(entry -> entry.getKey() + "=" + entry.getValue())
                    .collect(Collectors.joining(", "));
        }

        return "{" + formattedQueryParams + "}";
    }

    private static String addOrAppend(String root, String value) {
        StringBuilder builder = new StringBuilder();
        if (root == null) {
            return builder.append(value).toString();
        }
        return builder.append(root).append(",").append(value).toString();
    }
}
