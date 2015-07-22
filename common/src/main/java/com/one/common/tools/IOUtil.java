package com.one.common.tools;

import org.w3c.dom.ProcessingInstruction;

import android.media.MediaCodec;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by buke on 15/7/22.
 */
public class IOUtil {

    private static final String TAG = "IOUtil";

    private static final int BUFFER_SIZE = 1024 * 8;

    private IOUtil() {
    }

    public static void copyStream(InputStream is, OutputStream os) throws IOException {
        byte[] buffer = new byte[BUFFER_SIZE];
        while (true) {
            int count = is.read(buffer, 0, BUFFER_SIZE);
            if (-1 == count) {
                return;
            }
            os.write(buffer, 0, count);
        }
    }


    public static void closeSilently(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException ex) {

        }
    }

    public static byte[] convertInputStreamToByteArray(InputStream is) {
        BufferedInputStream bis;
        if (is instanceof BufferedInputStream) {
            bis = (BufferedInputStream) is;
        } else {
            bis = new BufferedInputStream(is);
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            copyStream(bis, bos);
            return bos.toByteArray();
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return null;
    }


}
