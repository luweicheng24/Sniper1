package com.imaginationunlimited.sniper.main.presenter;

import com.imaginationunlimited.sniper.main.viewInterface.IViewListener;

/**
 * Created by MSC on 2018/6/4.
 * BJ AlphaMobile
 */
public class IMainPresenterCompl implements IMainPresenter {


    private final IViewListener listener;

    public IMainPresenterCompl(IViewListener listener) {
        this.listener = listener;
        initialise();
    }

    private void initialise() {
        //TODO initialise user info data

    }

    @Override
    public void showChatFragment() {
        listener.showChatFragment();
    }

    @Override
    public void showPersonalFragment() {
        listener.showPersonalFragment();
    }

    @Override
    public void showSearchFragment() {
        listener.showSearchFragment();
    }
}
