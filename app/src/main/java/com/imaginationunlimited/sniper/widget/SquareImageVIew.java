package com.imaginationunlimited.sniper.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by gengchunjiang on 2018/6/8.
 */

public class SquareImageVIew extends ImageView {
    public SquareImageVIew(Context context) {
        super(context);
    }

    public SquareImageVIew(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareImageVIew(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    //传入参数widthMeasureSpec、heightMeasureSpec
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}
