package com.imaginationunlimited.sniper.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by MSC on 2018/6/4.
 * BJ AlphaMobile
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {
    protected void setListeners(View... views) {
        if (views == null) return;

        for (View view : views) {
            view.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView();
        setUpPresenter();
        findViews();
        afterViewFound();
    }

    protected abstract void afterViewFound();

    public abstract void setContentView();

    public abstract void setUpPresenter();

    public abstract void findViews();
}
