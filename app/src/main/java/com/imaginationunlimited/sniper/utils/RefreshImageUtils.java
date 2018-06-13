package com.imaginationunlimited.sniper.utils;

import android.widget.ImageView;
import android.widget.ViewSwitcher;

import com.imaginationunlimited.sniper.widget.SquareImageView;

import java.util.List;

/**
 * Created by gengchunjiang on 2018/6/11.
 */

public class RefreshImageUtils {

    //个人设置中 图片展示相关工具类

    private SquareImageView i1, i2, i3, i4, i5, i6;
    private ImageView add1, add2, add3, add4, add5, add6;
    private ImageView ok1, ok2, ok3, ok4, ok5, ok6;
    private ImageView edit1, edit2, edit3, edit4, edit5, edit6;


    public static void refreshList(List<String> urls, List<SquareImageView> views) {

        for (int i = 0; i < urls.size(); i++) {
            String url = urls.get(i);
            views.get(i);
        }
    }

    public static void setImage() {

    }

    public static void clearImage(List<SquareImageView> views) {
        for (int i = 0; i < views.size(); i++) {
//            views.get(i).setBackground(null);
        }
    }

    public static void changeImage(List<String> urls) {
        for (int i = 0; i < urls.size(); i++) {

        }
    }

}
