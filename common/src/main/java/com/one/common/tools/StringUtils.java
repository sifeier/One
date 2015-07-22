package com.one.common.tools;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by buke on 15/7/22.
 */
public class StringUtils {

    private static final String TAG = "StringUtils";

    public static boolean isBlank(String str) {
        return (null == str || str.trim().length() == 0);
    }

    private static final Pattern sChinesePattern = Pattern.compile("^[\u4E00-\u9FA5]+$");

    public static boolean isChineseName(String str) {
        Matcher m = sChinesePattern.matcher(str);
        return m.matches();
    }

    private static final String CHAR_POUND_SIGN = "#";

    public static final String getAlpha(String str) {
        if (isBlank(str)) {
            return CHAR_POUND_SIGN;
        }

        char ch = str.charAt(0);
        if ((ch >= 97 && ch <= 122) || (ch >= 65 && ch <= 90)) {
            if (ch >= 97) {
                ch = Character.toUpperCase(ch);
            }
            return String.valueOf(ch);
        } else {
            return CHAR_POUND_SIGN;
        }
    }

    public static final boolean isMobileNo(String str) {
        Pattern p = Pattern.compile("^((13[0-9])|(15[^4,\\D])|(14[7])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(str);
        return m.matches();
    }

}
