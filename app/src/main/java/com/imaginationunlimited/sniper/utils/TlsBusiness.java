package com.imaginationunlimited.sniper.utils;

import android.content.Context;

/**
 * 初始化tls登录模块
 */
public class TlsBusiness {


    private TlsBusiness(){}

    public static void init(Context context){
        TLSConfiguration.setSdkAppid(Constant.SDK_APPID);
        TLSConfiguration.setAccountType(Constant.ACCOUNT_TYPE);
        TLSConfiguration.setTimeout(8000);
        TLSService.getInstance().initTlsSdk(context);
    }

    public static void logout(String id){
        TLSService.getInstance().clearUserInfo(id);

    }
}
