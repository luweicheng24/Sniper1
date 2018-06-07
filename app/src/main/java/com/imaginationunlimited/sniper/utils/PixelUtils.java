package com.imaginationunlimited.sniper.utils;


/**
 * Created by somehui on 16/7/7.
 */
public class PixelUtils {
    private static final float DEFAULT = 2;
    private static float density = -1;

    public static int getPxByDp(float dp) {
        check();
        if (density > 0) {
            return (int) Math.ceil(density * dp);
        } else {
            return (int) (dp * DEFAULT);
        }
    }

    public static int getDpFromPx(int px) {
        check();
        if (density > 0) {
            return (int) Math.ceil(px / density);
        } else {
            return (int) (px / DEFAULT);
        }
    }

    public static void check() {
        if (density <= 0) {
            try {
                density = ResProvider.getResources().getDisplayMetrics().density;
            } catch (Exception e) {
                density = 2;
            }
        }
    }

    public static float getDP() {
        check();
        if (density <= 0) {
            return DEFAULT;
        } else {
            return density;
        }
    }
}
