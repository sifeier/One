package com.one.util;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Created by sifeier on 15/4/10.
 */
public final class ITools {

    public static void sleepIgnoreInterrupt(long time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public static boolean copyFile(File src, File target) {
        boolean isOk = false;
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            if (!target.exists() && !target.createNewFile()) {
                return isOk;
            }
            inputStream = new FileInputStream(src);
            outputStream = new FileOutputStream(target);
            byte[] buffer = new byte[8 * 1024];
            int count;
            while ((count = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, count);
            }
            isOk = true;
        } catch (IOException ioe) {
            ioe.printStackTrace();
            isOk = false;
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }

            try {
                if (outputStream != null) {
                    outputStream.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return isOk;
    }

    /**
     * 先移位取出 rgb，再加上alpha 值域
     */
    public static int getNewAlphaColor(int color, int alpha) {
        int r = color >> 16 & 0xFF;
        int g = color >> 8 & 0xFF;
        int b = color & 0xFF;
        return alpha << 24 | r << 16 | g << 8 | b;
    }

    /**
     *
     * This Device's SN
     * @return
     */
    public static String getSerialNumber() {
        String serialNumber = Build.SERIAL;
        if (serialNumber == null || serialNumber.length() == 0 || serialNumber
                .contains("unknown")) {
            String[] keys = new String[]{"ro.boot.serialno", "ro.serialno"};
            for (String key : keys) {
                try {
                    Method systemProperties_get = Class.forName("android.os.SystemProperties")
                            .getMethod("get", String.class);
                    serialNumber = (String) systemProperties_get.invoke(null, key);
                    if (serialNumber != null && serialNumber.length() >= 0 && serialNumber
                            .contains("unknown")) {
                        break;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        return serialNumber;
    }  //m end

    /**
     *
     * Equipment is started for the first time the generated number
     * Are potential "9774d56d682e549c"
     *
     * @param ctx
     * @return
     */
    public static String getAndroidID(Context ctx) {
        return Settings.System.getString(ctx.getContentResolver(), Settings.Secure.ANDROID_ID);
    }
}
