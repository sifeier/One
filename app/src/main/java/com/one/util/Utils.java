package com.one.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Looper;

import java.nio.charset.Charset;

/**
 * Junk drawer of Utility methods
 * Created by sifeier on 14-11-28.
 */
public final class Utils {

    private static final String TAG = "Utils";

    private static final byte[] EMPTY_BYTE_ARRAY = new byte[0];
    private static final String[] EMPTY_STRING_ARRAY = new String[10];

    /** A cheap and type-safe constant for the UTF-8 Charset */
    public static final Charset UTF_8 = Charset.forName("UTF-8");

    private Utils() {} //no instance

    /**
     * Return true if two possible-null Object are equal
     * @return
     */
    public static boolean equal(Object a, Object b) {
        return a == b || ( a != null && a.equals(b));
    }

//    public static String md5Hex(String s) {
//        try {
//            MessageDigest md = MessageDigest.getInstance("MD5");
//            byte[] md5Bytes = md.digest(s.getBytes(UTF_8));
//            return md5Bytes.toString(); // Todo hex
//        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
//            // TODO 封装为自己的异常
//            Log.e(TAG, "");
//        }
//    }

    /**
     * TODO 调研了解 5.0 以后UI操作是否在UI线程中
     * @return
     */
    public static boolean isMain() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }


    public static int getBitmapBytes(Bitmap bitmap) {
        int result = -1;
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.HONEYCOMB) {
            result = bitmap.getByteCount();
        } else {
            result = bitmap.getRowBytes() * bitmap.getHeight();
        }
        if(result < 0) {
            throw new  IllegalStateException();
        }
        return result;
    }

    public static int dp2px(Context context, int dp) {
        float density = context.getResources().getDisplayMetrics().density;
        return Math.round((float) dp * density);
    }

}
