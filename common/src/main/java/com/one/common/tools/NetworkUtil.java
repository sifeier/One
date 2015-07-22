package com.one.common.tools;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.io.IOException;

/**
 * Created by buke on 15/7/22.
 */
public class NetworkUtil {

    private static final String TAG = "NetworkUtil";

    public static final String NET_GPRS = "gprs";

    public static final String NET_4G = "4g";

    public static final String NET_3G = "3g";

    public static final String NET_2G = "2g";

    public static final String NET_OTHER = "other";

    public static HttpEntity sendHE(HttpClient client, String url) {
        Log.i(TAG, "url: " + url);
        try {
            HttpRequestBase base = new HttpGet(url);
            HttpResponse response = client.execute(base);
            int statusCode = response.getStatusLine().getStatusCode();
            HttpEntity entity = response.getEntity();
            if (null != entity) {
                if (statusCode == 200) {
                    return entity;
                } else {
                    entity.consumeContent();
                }
            }
        } catch (IllegalStateException iex) {
            Log.e(TAG, iex.toString());
        } catch (IOException ioex) {
            Log.e(TAG, ioex.toString());
        }
        return null;
    }

    public static boolean isNetworkAvailable(Context context) {
        boolean netStatus = false;

        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (null != networkInfo) {
            netStatus = networkInfo.isAvailable();
        }
        return netStatus;
    }

    public static boolean isWifi(Context context) {
        boolean is = false;

        ConnectivityManager manager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (null != networkInfo && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            is = true;
        }
        return is;
    }

    public static boolean isSupport3G(Context context) {
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        switch (telephonyManager.getNetworkType()) {
            case TelephonyManager.NETWORK_TYPE_1xRTT:
                return false;
            case TelephonyManager.NETWORK_TYPE_CDMA:
                return false;
            case TelephonyManager.NETWORK_TYPE_EDGE:
                return false;
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
                return true;
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
                return true;
            case TelephonyManager.NETWORK_TYPE_GPRS:
                return false;
            case TelephonyManager.NETWORK_TYPE_HSDPA:
                return true;
            case TelephonyManager.NETWORK_TYPE_HSPA:
                return true;
            case TelephonyManager.NETWORK_TYPE_HSUPA:
                return true;
            case TelephonyManager.NETWORK_TYPE_UMTS:
                return true;
            case TelephonyManager.NETWORK_TYPE_EHRPD:
                return true;
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
                return true;
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                return true;
            case TelephonyManager.NETWORK_TYPE_IDEN:
                return true;
            case TelephonyManager.NETWORK_TYPE_LTE:
                return true;
            default:
                return false;
        }
    }





}
