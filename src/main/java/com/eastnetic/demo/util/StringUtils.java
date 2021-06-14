package com.eastnetic.demo.util;

public class StringUtils {
    public static boolean isNullOrEmpty(String str){
        return str == null || str.isEmpty();
    }

    public static String nullSafeTrim(String str) {
        return str == null ? null : str.trim();
    }
}
