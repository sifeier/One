package com.one.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * Created by buke on 15/10/16.
 */
public class FileUtil {

    private static final String ALGORITHM = "PBEWithMD5AndDES";

    private static final int BUFFER_SIZE = 1024 * 8;

    /**
     * 读取文件，返回二进制
     */
    public static byte[] readFile(File sourceFile) {
        FileInputStream input = null;
        BufferedInputStream inputBuffer = null;

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        try {
            input = new FileInputStream(sourceFile);
            inputBuffer = new BufferedInputStream(input);
            byte[] buffer = new byte[BUFFER_SIZE];
            int len = -1;
            while ((len = inputBuffer.read(buffer)) != -1) {
                output.write(buffer, 0, len);
            }
            return output.toByteArray();
        } catch (Exception ex) {
            ex.printStackTrace();
        } catch (Throwable throwable) {

        } finally {
            try {
                if (inputBuffer != null) {
                    inputBuffer.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            try {
                if (null != output) {
                    output.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            try {
                if (null != input) {
                    input.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
        return null;
    }

    public static byte[] zip(byte[] src) {
        try {
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            GZIPOutputStream gzipOutput = new GZIPOutputStream(output);
            gzipOutput.write(src);
            gzipOutput.close();
            return output.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static File zipFile(File input) {
        File target = null;
        try {
            target = new File(input.getAbsolutePath() + ".gz");
            if (target.exists()) {
                target.deleteOnExit();
            }

            boolean create = target.createNewFile();
            if (!create) {
                return input;
            }

            FileOutputStream outputStream = new FileOutputStream(target);
            ZipOutputStream zipOutputStream = new ZipOutputStream(outputStream);

            FileInputStream fileInputStream = new FileInputStream(input);
            byte[] buf = new byte[BUFFER_SIZE];
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream,
                    BUFFER_SIZE);
            ZipEntry zipEntry = new ZipEntry(input.getName());
            zipOutputStream.putNextEntry(zipEntry);

            int len;
            while ((len = bufferedInputStream.read(buf, 0, BUFFER_SIZE)) != -1) {
                zipOutputStream.write(buf, 0, len);
            }

            bufferedInputStream.close();
            zipOutputStream.close();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            return input;
        }
        return target;
    }

}
