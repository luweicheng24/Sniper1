package com.imaginationunlimited.sniper.personal;

import android.view.View;

import com.imaginationunlimited.sniper.R;
import com.imaginationunlimited.sniper.base.BaseActivity;

/**
 * Created by MSC on 2018/6/4.
 * BJ AlphaMobile
 */
public class PersonalActivity extends BaseActivity {
    View tv_mention_complete;
    @Override
    protected void afterViewFound() {

    }

    @Override
    public void setContentView() {
            setContentView(R.layout.activity_setting);
    }

    @Override
    public void setUpPresenter() {

    }

    @Override
    public void findViews() {
        tv_mention_complete = findViewById(R.id.tv_mention_complete);
    }
    public void hide(View view){
        tv_mention_complete.setVisibility(View.INVISIBLE);
    }
}
