package com.imaginationunlimited.sniper.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by gengchunjiang on 2018/6/11.
 */

public class ImageGroupView extends ViewGroup {
    public ImageGroupView(Context context) {
        super(context);
    }

    public ImageGroupView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImageGroupView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        int childCount = this.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = this.getChildAt(i);
            LayoutParams lParams = (LayoutParams) child.getLayoutParams();
//            child.layout(lParams.left, lParams.top, lParams.left + childWidth,
//                    lParams.top + childHeight);
        }

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int childCount = this.getChildCount();
        for (int i = 0; i < childCount; i++){
            View child = this.getChildAt(i);
            this.measureChild(child,widthMeasureSpec,heightMeasureSpec);
            int cw = child.getMeasuredWidth();

        }
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }
}
