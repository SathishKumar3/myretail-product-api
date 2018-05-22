package com.tgt.myretail.logger.utils;

import com.tgt.myretail.logger.log.enums.EnumLoggingKeys;

public class LogUtil {

    public static String formatKeyValue(EnumLoggingKeys enumLoggingKeys, String values, boolean delimitter) {
        if (delimitter) {
            return " " + enumLoggingKeys.getValue() + "='" + values + "'";
        }
        return " " + enumLoggingKeys.getValue() + "=" + values;
    }

}
