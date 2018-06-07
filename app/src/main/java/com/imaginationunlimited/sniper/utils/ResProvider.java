package com.imaginationunlimited.sniper.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.imaginationunlimited.sniper.MyApplication;


public class ResProvider {
    public static Context application() {
        return MyApplication.getAppContext();
    }

    public static Resources getResources() {
        return application().getResources();
    }

    public static String string(@StringRes int stringId) {
        return getResources().getString(stringId);
    }

    public static String string(@StringRes int stringId, Object... obj) {
        return getResources().getString(stringId, obj);
    }

    @SuppressWarnings("unchecked")
    public static <T extends View> T view(@NonNull View parent, @IdRes int resId) {
        return (T) parent.findViewById(resId);
    }

    public static float dimen(@DimenRes int dimenId) {
        return getResources().getDimension(dimenId);
    }

    public static int dimenI(@DimenRes int dimenId) {
        return Math.round( dimen(dimenId));
    }

    public static int color(@ColorRes int colorId) {
        if (Build.VERSION.SDK_INT >= 23) {
            return getResources().getColor(colorId, null);
        } else {
            return getResources().getColor(colorId);
        }
    }

    public static Drawable drawable(@DrawableRes int drawableId) {
        if (Build.VERSION.SDK_INT >= 23) {
            return getResources().getDrawable(drawableId, null);
        } else {
            return getResources().getDrawable(drawableId);
        }
    }

    public static Bitmap bitmap(@DrawableRes int drawableId) {
        Drawable drawable = drawable(drawableId);
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        } else {
            return null;
        }
    }

    public static View inflate(Activity activity, @LayoutRes int layoutId, ViewGroup parent, boolean addToRoot) {
        return LayoutInflater.from(activity).inflate(layoutId, parent, addToRoot);

    }

    @Deprecated
    public static View inflate(@LayoutRes int layoutId, ViewGroup parent, boolean addToRoot) {
        Context context = parent == null ? application() : parent.getContext();
        return LayoutInflater.from(context).inflate(layoutId, parent, addToRoot);
    }

    public static SharedPreferences sharePreferences(String name) {
        return application().getSharedPreferences(name, Context.MODE_PRIVATE);
    }

}
