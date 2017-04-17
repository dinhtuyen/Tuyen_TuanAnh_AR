package com.qualcomm.ar.pl;

import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.Bitmap.Config;
import android.graphics.Rect;
import android.graphics.YuvImage;
import java.io.ByteArrayOutputStream;

public class ImageTools {
    private static final int CAMERA_IMAGE_FORMAT_LUM = 268439809;
    private static final int CAMERA_IMAGE_FORMAT_NV12 = 268439815;
    private static final int CAMERA_IMAGE_FORMAT_NV21 = 268439817;
    private static final int CAMERA_IMAGE_FORMAT_RGB565 = 268439810;
    private static final String MODULENAME = "ImageTools";

    public static byte[] encodeImage(byte[] pixels, int width, int height, int format, int stride, int quality) {
        if (pixels == null) {
            return null;
        }
        ByteArrayOutputStream encodedBuffer;
        if (format == CAMERA_IMAGE_FORMAT_NV21) {
            YuvImage yuvImage = new YuvImage(pixels, 17, width, height, null);
            encodedBuffer = new ByteArrayOutputStream();
            if (yuvImage.compressToJpeg(new Rect(0, 0, width, height), quality, encodedBuffer)) {
                return encodedBuffer.toByteArray();
            }
            return null;
        } else if (format != CAMERA_IMAGE_FORMAT_LUM) {
            return null;
        } else {
            int numPixels = width * height;
            int[] colors = new int[numPixels];
            for (int p = 0; p < numPixels; p++) {
                colors[p] = (pixels[p] << 24) | 16777215;
            }
            Bitmap bmp = Bitmap.createBitmap(colors, 0, width, width, height, Config.ARGB_8888);
            encodedBuffer = new ByteArrayOutputStream();
            if (bmp.compress(CompressFormat.JPEG, quality, encodedBuffer)) {
                return encodedBuffer.toByteArray();
            }
            return null;
        }
    }
}
