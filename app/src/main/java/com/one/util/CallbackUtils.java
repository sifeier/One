package com.one.util;

import android.os.Handler;
import android.os.Looper;


/**
 * 如果Callback有UI操作，必须在主线程执行，可以用此工具类
 * <p/>
 * Created by hugozhu on 7/29/14.
 */
public class CallbackUtils {
    public static void runOnUiThread(Runnable runnable) {
        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
            runnable.run();
        } else {
            new Handler(Looper.getMainLooper()).post(runnable);
        }
    }

    public static SerializeCallback runOnUiThread(final SerializeCallback callback) {
        return new SerializeCallback() {
            @Override
            public void onComplete(final Object data) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callback.onComplete(data);
                    }
                });
            }

            @Override
            public void onFailed() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        callback.onFailed();
                    }
                });
            }
        };
    }
}
