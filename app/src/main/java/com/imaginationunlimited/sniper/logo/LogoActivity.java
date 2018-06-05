package com.imaginationunlimited.sniper.logo;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.imaginationunlimited.sniper.R;
import com.imaginationunlimited.sniper.base.BaseActivity;

/**
 * Created by MSC on 2018/6/4.
 * BJ AlphaMobile
 */
public class LogoActivity extends BaseActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);
    }

    @Override
    protected void afterViewFound() {
//        RESTfulFactory.getInstance().createJson(DataService.class)
//                .login("8613621093593","0897")
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Subscriber<JSONObject>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(JSONObject jsonObject) {
//                        Log.e("msc", jsonObject.toString() + "  ");
//                    }
//                });
    }

    @Override
    public void setContentView() {

    }

    @Override
    public void setUpPresenter() {

    }

    @Override
    public void findViews() {

    }
}
