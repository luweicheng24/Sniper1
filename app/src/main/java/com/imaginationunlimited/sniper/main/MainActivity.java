package com.imaginationunlimited.sniper.main;

import android.view.View;

import com.imaginationunlimited.sniper.R;
import com.imaginationunlimited.sniper.base.BaseActivity;
import com.imaginationunlimited.sniper.main.presenter.IMainPresenter;
import com.imaginationunlimited.sniper.main.presenter.IMainPresenterCompl;
import com.imaginationunlimited.sniper.main.viewInterface.IViewListener;

public class MainActivity extends BaseActivity implements IViewListener {
    private IMainPresenter mainPresenter;
    private View mSearchButton, mChatButton, mPersonalButton;

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_main);
    }

    @Override
    public void setUpPresenter() {
        mainPresenter = new IMainPresenterCompl(this);
    }

    @Override
    public void findViews() {
        mSearchButton = findViewById(R.id.main_button_search);
        mChatButton = findViewById(R.id.main_button_chat);
        mPersonalButton = findViewById(R.id.main_button_personal);
    }

    @Override
    protected void afterViewFound() {
        setListeners(mSearchButton, mChatButton, mPersonalButton);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.main_button_search:
                mainPresenter.showSearchFragment();
                break;
            case R.id.main_button_chat:
                mainPresenter.showChatFragment();
                break;
            case R.id.main_button_personal:
                mainPresenter.showPersonalFragment();
                break;
        }
    }

    @Override
    public void showSearchFragment() {

    }

    @Override
    public void showChatFragment() {

    }

    @Override
    public void showPersonalFragment() {

    }
}
