package com.imaginationunlimited.sniper.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLDecoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * 类型之间的转换
 *
 * @author jiaxu
 */
public class TypeChange {

    private TypeChange() {
    }

    private static TypeChange mInstance = null;

    public static TypeChange getInstance() {
        if (mInstance == null) {
            mInstance = new TypeChange();
        }
        return mInstance;
    }


    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * byte[] → Bitmap
     *
     * @param b
     * @return
     */
    public Bitmap Bytes2Bimap(byte[] b) {
        if (b != null && b.length != 0) {
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        } else {
            return null;
        }
    }

    /**
     * Bitmap → byte[]
     *
     * @param bm
     * @return
     */
    public byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        if (bm == null) {
            return null;
        }
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }


    /**
     * 把输入流转成字符串
     *
     * @param is
     * @return
     * @throws IOException
     */
    public String readInputStream(InputStream is) throws IOException {
        String result = null;
        if (is == null)
            return null;
        ByteArrayOutputStream bout = new ByteArrayOutputStream();
        int len = 0;
        byte[] buf = new byte[1024];
        while ((len = is.read(buf)) != -1) {
            bout.write(buf, 0, len);
        }
        result = new String(bout.toByteArray());
        is.close();
        bout.close();
        return URLDecoder.decode(result, "utf-8");
    }

    /**
     * Editable类型数据转成String,如果Editable数据为空返回"",而不是NULL
     *
     * @param editable
     * @return
     */

    public static String editable2String(Editable editable) {
        String result = "";
        if (editable != null) {
            result = editable.toString();
        }
        return result;
    }

    /**
     * CharSequence类型数据转成String,如果CharSequence数据为空返回"",而不是NULL
     *
     * @param charSequence
     * @return
     */
    public static String charSequence2String(CharSequence charSequence) {
        String result = "";
        if (charSequence != null) {
            result = charSequence.toString();
        }
        return result;
    }

    /**
     * Object类型数据转成String,如果Object数据为空返回"",而不是NULL
     *
     * @param object
     * @return
     */

    public static String object2String(Object object) {
        String result = "";
        if (object != null) {
            result = object.toString();
        }
        return result;
    }

    /**
     * Object类型数据转成Integer,如果Object数据为空返回"0",而不是NULL
     *
     * @param object
     * @return
     */

    public static Integer object2Integer(Object object) {
        int result = 0;
        if (object != null && !TextUtils.isEmpty(object.toString())) {
            try {
                result = (int) Float.parseFloat(object.toString());
            } catch (Exception e) {
                result = 0;
            }
        }
        return result;
    }

    /**
     * Object类型数据转成Float,如果Object数据为空返回"0",而不是NULL
     *
     * @param object
     * @return
     */

    public static float object2Float(Object object) {
        float result = 0f;
        if (object != null && !TextUtils.isEmpty(object.toString())) {
            try {
                result = Float.parseFloat(object.toString());
            } catch (Exception e) {
                result = 0f;
            }
        }
        return result;
    }

    /**
     * 获取圆角位图的方法
     *
     * @param bitmap 需要转化成圆角的位图
     * @param pixels 圆角的度数，数值越大，圆角越大
     * @return 处理后的圆角位图
     */
    public Bitmap toRoundCorner(Bitmap bitmap, int pixels) {
        if (bitmap == null) {
            return null;
        }
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Config.ARGB_4444);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = pixels;
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    /**
     * 将字符串转化为颜色
     *
     * @param strColor #ffffff or 255,255,255
     * @return color
     */
    public int toColor(String strColor) {
        if (strColor == null) return Color.parseColor("#ffffffff");
        if (strColor.charAt(0) == '#') {
            int color = 0;
            try {
                color = Color.parseColor(strColor);
            } catch (Exception e) {
                e.toString();
                color = Color.parseColor("#ffffffff");
            }
//            return Color.parseColor(strColor);
            return color;
        } else {
            String[] strList = strColor.split(",");
            if (strList.length != 3) return Color.parseColor("#ffffffff");
            int r = Integer.valueOf(strList[0]);
            int g = Integer.valueOf(strList[1]);
            int b = Integer.valueOf(strList[2]);
            return Color.rgb(r, g, b);
        }
    }

    /**
     * 根据字符串获取字体
     *
     * @param fontFile 字体文件绝对地址
     * @param fontName 字体名称
     * @return 字体文件
     */
    public Typeface toTypeface(String fontFile, String fontName) {
        Typeface typeface = null;
        if (fontName != null) {
            typeface = Typeface.create(fontName, Typeface.NORMAL);
        }
        if (typeface == null) {
            try {
                //读取字体文件
                typeface = Typeface.createFromFile(fontFile);
            } catch (Exception e) {
                Log.i(getClass().getSimpleName(), "--->" + e);
            }
        }
        return typeface;
    }

    /**
     * 从string中提取一维数组
     */
    public int[] getOneDimenIntFromString(String str) {
        if (str == null) return null;
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        String[] strings = str.split(",");
        int[] ints = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            Matcher m = p.matcher(strings[i]);
            ints[i] = Integer.valueOf(m.replaceAll("").trim().toString());
        }
        return ints;
    }

    /**
     * 获取二维数组
     *
     * @param str 字符串表示的二维数组
     */
    public int[][] getTwoDimenIntFromString(String str) {
        if (str == null) return null;
        int[][] ints = new int[2][2];

        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);

        String[] strings_one = str.split("\\},");
        for (int i = 0; i < strings_one.length; i++) {
            String[] strings_two = strings_one[i].split(",");
            for (int j = 0; j < strings_two.length; j++) {
                Matcher m = p.matcher(strings_two[j]);
                ints[i][j] = Integer.valueOf(m.replaceAll("").trim().toString());
            }
        }
        return ints;
    }

}
