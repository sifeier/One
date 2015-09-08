package com.one.common.tools;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.graphics.PixelFormat;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Driver;

/**
 * Created by buke on 15/9/7.
 */
public class ImageUtil {

    /**
     * 通过路径获取输入流
     */
    public static InputStream getRequest(String path) throws Exception {
        URL url = new URL(path);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        if (null != connection) {
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            if (connection.getResponseCode() == 200) {
                return connection.getInputStream();
            }
        }
        return null;
    }

    /**
     * 从输入流中读取二进制
     */
    public static byte[] readInputStream(InputStream inputStream) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[8192];
        int len = 0;

        while ((len = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, len);
        }

        outputStream.close();
        inputStream.close();
        return outputStream.toByteArray();
    }

    /**
     * 把一个路径转化为Drawable对象
     */
    public static Drawable loadImageFromUrl(String path) {
        URL url;
        InputStream inputStream = null;
        try {
            url = new URL(path);
            inputStream = (InputStream) url.getContent();
        } catch (MalformedURLException mex) {
            mex.printStackTrace();
        } catch (IOException iex) {
            iex.printStackTrace();
        }
        return Drawable.createFromStream(inputStream, "src");
    }

    /**
     * 把一个路径转化为Drawable对象
     */
    public static Drawable getDrawableFromUrl(String url) throws Exception {
        return Drawable.createFromStream(getRequest(url), null);
    }

    /**
     * 从二进制数组中得到位图
     */
    public static Bitmap byte2Bitmap(byte[] byteArray) {
        if (byteArray.length > 0) {
            return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        }
        return null;
    }

    /**
     * 从二进制中获取 Drawable 对象
     */
    public static Drawable byte2Drawable(byte[] byteArray) {
        if (byteArray.length > 0) {
            ByteArrayInputStream inputStream = new ByteArrayInputStream(byteArray);
            Drawable.createFromStream(inputStream, null);
        }
        return null;
    }

    /**
     * 位图转化为二进制数组
     *
     * @param bitmap 输入位图
     * @return 二进制数组 或 null
     */
    public static byte[] bitmap2Bytes(Bitmap bitmap) {
        if (bitmap != null) {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            return outputStream.toByteArray();
        }
        return null;
    }

    /**
     * 将 drawable 对象 转为 Bitmap 对象
     * @param drawable
     * @return
     */
    public static Bitmap drawable2Bitmap(Drawable drawable) {
        if(drawable == null) {
            return null;
        }

        Bitmap bitmap = Bitmap.createBitmap(
                drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(),
                drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565);

        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0,
                drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight());

        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * 图片加灰色滤镜，返回灰度图片
     *
     * @param bitmap
     * @return
     */
    public static Bitmap toGrayScale(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        Bitmap bmpGrayScale = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);

        Canvas canvas = new Canvas(bmpGrayScale);
        Paint paint = new Paint();

        ColorMatrix matrix =  new ColorMatrix();
        matrix.setSaturation(0);
        ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
        paint.setColorFilter(filter);
        canvas.drawBitmap(bitmap, width, height, paint);

        return bmpGrayScale;
    }

    /**
     * 把位图变成圆角位图
     *
     * @param bitmap
     * @param pixels
     * @return
     */
    public static Bitmap toRoundCorner(Bitmap bitmap, final int pixels) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        RectF rectF = new RectF(rect);

        final int color = 0xff424242;
        final Paint paint = new Paint();
        paint.setColor(color);
        paint.setAntiAlias(true);

        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawRoundRect(rectF, pixels, pixels, paint);

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);

        return output;

    }


    /**
     * 转换为带圆角的 BitmapDrawable
     *
     * @param context        上下文， 获取Resource
     * @param bitmapDrawable 原始BitmapDrawable
     * @param pixels         圆角像素
     * @return 带圆角的BitmapDrawable
     */
    public static BitmapDrawable toRoundCorner(Context context, BitmapDrawable bitmapDrawable,
            int pixels) {
        Bitmap bitmap = bitmapDrawable.getBitmap();
        Bitmap outputBitmap = toRoundCorner(bitmap, pixels);
        bitmapDrawable = new BitmapDrawable(context.getResources(), outputBitmap);
        return bitmapDrawable;
    }

    /**
     * 生成带水印的图片
     *
     * @param src  底图
     * @param mark 水印图片
     * @return 带水印的图片
     */
    public static Bitmap combineBitmap(Bitmap src, Bitmap mark) {
        if (src == null) {
            return null;
        }

        int w = src.getWidth();
        int h = src.getHeight();

        int mw = mark.getWidth();
        int mh = mark.getHeight();

        Bitmap output = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);

        canvas.drawBitmap(src, 0, 0, null);
        canvas.drawBitmap(mark, w - mw + 5, h - mh + 5, null);
        canvas.save(Canvas.ALL_SAVE_FLAG);  //保存，用来保存Canvas的状态。save之后，可以调用Canvas的平移、放缩、旋转、错切、裁剪等操作
        canvas.restore(); //存储，用来恢复Canvas之前保存的状态。防止save后对Canvas执行的操作对后续的绘制有影响。

        return output;
    }
}
