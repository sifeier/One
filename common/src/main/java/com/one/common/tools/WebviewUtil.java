package com.one.common.tools;

import android.content.Context;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.WebView;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by buke on 15/7/17.
 */
public class WebViewUtil {

    /**
     * 构建 cookie 字符串
     */
    public static String buildCookieString(String key, String value, String domain) {
        StringBuilder cookieToken = new StringBuilder();
        cookieToken.append(key);
        cookieToken.append(" = ");
        cookieToken.append(value);
        cookieToken.append("; domain = ");
        cookieToken.append(domain);
        cookieToken.append("; path = /");
        return cookieToken.toString();
    }

    /**
     *
     * @param context
     * @param url
     * @param cookies
     * @return
     */
    public static String injectCookie(Context context, String url, Map<String, String> cookies) {
        CookieSyncManager.createInstance(context);
        CookieManager manager = CookieManager.getInstance();
        manager.setAcceptCookie(true);
        String domain = getDomain(url);
        for(Map.Entry<String, String> entry : cookies.entrySet()) {
            String cookie = buildCookieString(entry.getKey(), entry.getValue(), domain);
            manager.setCookie(url, cookie);
        }

        CookieSyncManager.getInstance().sync();
        return manager.getCookie(url);
    }

    /**
     *
     * @param context
     */
    public static void clearCookie(Context context) {
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.removeSessionCookie();
        CookieSyncManager.getInstance().sync();
    }

    /**
     *
     * @param url
     * @return
     */
    public static String getDomain(String url) {
        String content = null;
        if (!TextUtils.isEmpty(url) && (url.contains("http") || url.contains("Http"))) {
            Pattern pattern = Pattern.compile("(?<=//|)((\\w)+\\.)+\\w+");
            Matcher matcher = pattern.matcher(url);
            if (matcher.find()) {
                content = matcher.group();
            }
        }
        return content;
    }

    public static void clear(WebView webView) {
        webView.clearCache(true);
        webView.clearHistory();
    }

}
