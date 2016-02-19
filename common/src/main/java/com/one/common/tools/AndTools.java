package com.one.common.tools;

import android.content.Context;
import android.content.res.Resources;

/**
 * Created by buke on 16/1/26.
 */
public class AndTools {

    /**
     * 获取状态栏高度
     */
    public static int getStatusBarHeight(Context context) {
        Resources res = context.getResources();
        int result = 0;
        int resourceId = res.getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = res.getDimensionPixelSize(resourceId);
        }
        return result;
    }

}
