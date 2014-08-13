package com.one.util;

/**
 * 序列化回调，这里的回调不一定是在UI线程
 */
public interface SerializeCallback {
    public void onComplete(Object data);
    public void onFailed();
}