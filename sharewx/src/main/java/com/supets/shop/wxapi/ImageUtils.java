package com.supets.shop.wxapi;

import android.graphics.Bitmap;

import java.io.ByteArrayOutputStream;


class ImageUtils {

    static byte[] Bitmap2Bytes(Bitmap bm, int MaxSize) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        byte[] result = null;

        int compressQuality = 100;
        while (true) {
            if (compressQuality <= 0) {
                break;
            }

            bm.compress(Bitmap.CompressFormat.JPEG, compressQuality, output);

            result = output.toByteArray();

            if (compressQuality < 40) {//30
                break;
            }

            if (result.length > MaxSize) {
                compressQuality = compressQuality - 10;
                output.reset();
                continue;
            } else {
                try {
                    output.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
        }

        return result;
    }
}
