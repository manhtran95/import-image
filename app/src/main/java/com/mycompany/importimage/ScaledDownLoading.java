package com.mycompany.importimage;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;

public class ScaledDownLoading {
    public ScaledDownLoading() {
        super();
    }

    public static Bitmap decodeSampledBitmapFromFile(String imageDecodableString,
                                                     int reqWidth, int reqHeight) {

        final BitmapFactory.Options options = checkDimentions(imageDecodableString);
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        return getBitmap(imageDecodableString, options);

    }

    private static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        inSampleSize = getInSampleSize(reqWidth, reqHeight, height, width, inSampleSize);

        return inSampleSize;
    }

    private static int getInSampleSize(int reqWidth, int reqHeight, int height, int width, int inSampleSize) {
        if (height > reqHeight || width > reqWidth) {

            final int halfHeight = height / 2;
            final int halfWidth = width / 2;

            while (((halfHeight / inSampleSize) > reqHeight)
                    && ((halfWidth / inSampleSize) > reqWidth)) {
                inSampleSize *= 2;
            }
        }
        return inSampleSize;
    }

    // Decode bitmap with inSampleSize set
    private static Bitmap getBitmap(String imageDecodableString, BitmapFactory.Options options) {
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(imageDecodableString, options);
    }

    // decode with inJustDecodeBounds=true to check dimensions
    @NonNull
    private static BitmapFactory.Options checkDimentions(String imageDecodableString) {
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(imageDecodableString, options);
        return options;
    }
}
