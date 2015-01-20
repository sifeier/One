package com.one.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.renderscript.Allocation;
import android.renderscript.Element;
import android.renderscript.RenderScript;
import android.renderscript.ScriptIntrinsicBlur;

import java.lang.annotation.Target;

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

    @TargetApi(17)
    public static Bitmap blurBitmap(Context context, Bitmap bitmap){

        //Let's create an empty bitmap with the same size of the bitmap we want to blur
        Bitmap outBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);

        //Instantiate a new Renderscript
        RenderScript rs = RenderScript.create(context);

        //Create an Intrinsic Blur Script using the Renderscript
        ScriptIntrinsicBlur blurScript = ScriptIntrinsicBlur.create(rs, Element.U8_4(rs));

        //Create the Allocations (in/out) with the Renderscript and the in/out bitmaps
        Allocation allIn = Allocation.createFromBitmap(rs, bitmap);
        Allocation allOut = Allocation.createFromBitmap(rs, outBitmap);

        //Set the radius of the blur
        blurScript.setRadius(25.f);

        //Perform the Renderscript
        blurScript.setInput(allIn);
        blurScript.forEach(allOut);

        //Copy the final bitmap created by the out Allocation to the outBitmap
        allOut.copyTo(outBitmap);

        //recycle the original bitmap
        bitmap.recycle();

        //After finishing everything, we destroy the Renderscript.
        rs.destroy();

        return outBitmap;

    }


}
