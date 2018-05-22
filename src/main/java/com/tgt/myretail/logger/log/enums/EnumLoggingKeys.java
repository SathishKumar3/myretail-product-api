package com.tgt.myretail.logger.log.enums;

public enum EnumLoggingKeys {

    TRANSACTION("transactionId"),
    HTTPSTATUS("httpStatus"),
    EXCEPTION("exception"),
    RESPONSE("response"),
    REQUESTJSON("requestJson"),
    RESPONSEJSON("responseJson"),
    REQUESTURI("requestURI"),
    HEADERS("headers"),
    METHOD("method"),
    PAYLOAD("payload"),
    QUERYPARAMS("queryParams");

    private String value;

    EnumLoggingKeys(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
