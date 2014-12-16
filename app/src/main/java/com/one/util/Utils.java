package com.one.util;

import android.graphics.Bitmap;
import android.os.Build;
import android.os.Looper;

/**
 * Created by sifeier on 14-11-28.
 */
public class Utils {
    private static final String TAG = "Utils";

    private Utils() {} //no instance

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

}
