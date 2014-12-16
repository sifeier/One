package com.one.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;

/**
 * Created by sifeier on 14-12-12.
 */
public class BitmapUtils {

    public static int mOffsetX = 0;
    public static int mOffsetY = 0;

    public static Bitmap combineBitmap(Bitmap bgBitmap, Bitmap upLayerbitmap) {
        Bitmap combinedBitmap = Bitmap.createBitmap(bgBitmap.getWidth(), bgBitmap.getHeight(), bgBitmap.getConfig());
        Canvas canvas = new Canvas(combinedBitmap);
        canvas.drawBitmap(bgBitmap, new Matrix(), null);

        // 计算上层Bitmap在底图Bitmap中的坐标
        int positionX = (int) (bgBitmap.getWidth()/2 - upLayerbitmap.getWidth());
        int positionY = (int) (bgBitmap.getHeight() - upLayerbitmap.getHeight() * 3);

        // 计算上层Bitmap在底图Bitmap中的size
        int upLayerWidth = (int) (upLayerbitmap.getWidth()*2);
        int upLayerHeight = (int) (upLayerbitmap.getHeight()*2);

        // 缩放上层Bitmap
        Bitmap scaledUpLayerBitmap = Bitmap.createScaledBitmap(upLayerbitmap, upLayerWidth, upLayerHeight, true);

        // 合并上层Bitmap到底图Bitmap
        canvas.drawBitmap(scaledUpLayerBitmap, positionX, positionY, null);

        return combinedBitmap;
    }


}
