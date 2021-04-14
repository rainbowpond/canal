package com.tuhu.mario;

import org.apache.commons.lang.StringUtils;

import java.util.Properties;

/**
 * @Author: jianglei
 */
public class MarioUtils {
    public static String getProperty(Properties properties, String key) {
        key = StringUtils.trim(key);
        String value = System.getProperty(key);

        if (value == null) {
            value = System.getenv(key);
        }

        if (value == null) {
            value = properties.getProperty(key);
        }

        return StringUtils.trim(value);
    }
}
