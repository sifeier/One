package com.one.util;

import com.demo.common.logger.Log;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.List;

/**
 * Created by buke on 15/10/19.
 */
public class SystemUtil {

    private static String TAG = "SystemUtils";

    /**
     * 判断当前的应用是否在前台运行
     */
    public static boolean isRunningForeground(Context context, String packageName) {
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> taskInfos = activityManager.getRunningTasks(1);
        if (null != taskInfos && taskInfos.size() > 0) {
            Log.e(TAG, String.format("当前位于堆栈顶层的应用包名是： %s",
                    taskInfos.get(0).topActivity.getPackageName()));
            return packageName.equals(taskInfos.get(0).topActivity.getPackageName());
        }
        return false;
    }

    /**
     * 获取当前进程的名字
     */
    public static String getCurProcessName(Context context) {
        int pid = android.os.Process.myPid();
        ActivityManager activityManager = (ActivityManager) context
                .getSystemService(Context.ACTIVITY_SERVICE);
        if (null == activityManager) {
            return null;
        }
        List<ActivityManager.RunningAppProcessInfo> processInfos = activityManager
                .getRunningAppProcesses();
        if (processInfos == null && processInfos.isEmpty()) {
            return null;
        }

        for (ActivityManager.RunningAppProcessInfo appProcessInfo : processInfos) {
            if (appProcessInfo.pid == pid) {
                return appProcessInfo.processName;
            }
        }
        return null;
    }

    /**
     * 判断某应用是否安装
     *
     * @param packageName 应用名称
     */
    public static boolean isAppInstalled(Context context, String packageName) {
        try {
            context.getPackageManager().getPackageInfo(packageName, 0);
            return true;
        } catch (PackageManager.NameNotFoundException ex) {
            return false;
        }
    }

    /**
     * 获取当前应用的版本名称，对应 manifest文件中的 android:versionName
     */
    public static String getAppVersionName(Context context, String packageName) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            if (null != packageInfo) {
                return packageInfo.versionName;
            }
            return "";
        } catch (PackageManager.NameNotFoundException ex) {
            return "";
        }
    }


}
