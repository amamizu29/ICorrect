package com.dkstudio.icorrect.utils;

/**
 * String Utils
 */
public class StringUtils
{
    public static boolean isEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    public static boolean isEmpty(CharSequence s) {
        return s == null || s.length() == 0;
    }

    public static boolean isNumeric(String s) {
        return s.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }
}
