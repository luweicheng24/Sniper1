package com.imaginationunlimited.sniper.login;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.imaginationunlimited.sniper.R;
import com.imaginationunlimited.sniper.base.BaseActivity;
import com.imaginationunlimited.sniper.main.MainActivity;
import com.imaginationunlimited.sniper.model.UserInfo;
import com.imaginationunlimited.sniper.utils.DataService;
import com.imaginationunlimited.sniper.utils.RESTfulFactory;
import com.imaginationunlimited.sniper.utils.TLSService;
import com.imaginationunlimited.sniper.utils.Util;
import com.tencent.imsdk.TIMCallBack;
import com.tencent.imsdk.TIMConnListener;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMUserConfig;
import com.tencent.imsdk.TIMUserStatusListener;

import org.json.JSONObject;

import java.util.Random;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import tencent.tls.platform.TLSErrInfo;
import tencent.tls.platform.TLSPwdLoginListener;
import tencent.tls.platform.TLSUserInfo;

/**
 * Created by MSC on 2018/6/4.
 * BJ AlphaMobile
 */
public class LoginActivity extends BaseActivity {
    private static final String TAG = "TIM_SDK";
    private EditText mUserName, mVerificationCode;

    @Override
    protected void afterViewFound() {

    }

    @Override
    public void setContentView() {
        setContentView(R.layout.activity_login);
    }

    @Override
    public void setUpPresenter() {

    }

    @Override
    public void findViews() {
        mUserName = findViewById(R.id.user_name);
        mVerificationCode = findViewById(R.id.verification_code);
    }

    public void getVerification(View view) {
        RESTfulFactory.getInstance().createJson(DataService.class)
                .login(mUserName.getText().toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<JSONObject>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(JSONObject jsonObject) {
                        Log.e("msc", jsonObject.toString() + "  ");

                    }
                });
    }

    public void login(View view) {
        TLSService.getInstance().getLoginHelper().TLSPwdLogin("1517unimserver", "00000000".getBytes(), new TLSPwdLoginListener() {
            @Override
            public void OnPwdLoginSuccess(TLSUserInfo userInfo) {
                Util.showToast(LoginActivity.this, "登录成功");
                TLSService.getInstance().setLastErrno(0);

                String id = TLSService.getInstance().getLastUserIdentifier();
                UserInfo.getInstance().setId(id);
                Log.e("msc", "TLSService.getInstance().getUserSig(id) = " + TLSService.getInstance().getUserSig(id));
                UserInfo.getInstance().setUserSig(TLSService.getInstance().getUserSig(id));

                //登录之前要初始化群和好友关系链缓存
                TIMUserConfig userConfig = new TIMUserConfig();
                userConfig.setUserStatusListener(new TIMUserStatusListener() {
                    @Override
                    public void onForceOffline() {
                        Log.d(TAG, "receive force offline message");

                    }

                    @Override
                    public void onUserSigExpired() {
                        //票据过期，需要重新登录
                        Log.e(TAG, "票据过期，需要重新登录");
                    }
                })
                        .setConnectionListener(new TIMConnListener() {
                            @Override
                            public void onConnected() {
                                Log.i(TAG, "onConnected");
                            }

                            @Override
                            public void onDisconnected(int code, String desc) {
                                Log.i(TAG, "onDisconnected");
                            }

                            @Override
                            public void onWifiNeedAuth(String name) {
                                Log.i(TAG, "onWifiNeedAuth");
                            }
                        });

                //设置刷新监听
//                RefreshEvent.getInstance().init(userConfig);
//                userConfig = FriendshipEvent.getInstance().init(userConfig);
//                userConfig = GroupEvent.getInstance().init(userConfig);
//                userConfig = MessageEvent.getInstance().init(userConfig);
                TIMManager.getInstance().setUserConfig(userConfig);

                TIMManager.getInstance().login(
                        "1517unimserver",
                        TLSService.getInstance().getUserSig(id),                    //用户帐号签名，由私钥加密获得，具体请参考文档
                        new TIMCallBack() {
                            @Override
                            public void onError(int i, String s) {
                                Log.e(TAG, "onError\n" + s);
                            }

                            @Override
                            public void onSuccess() {
                                Log.e(TAG, "onSuccess");
                                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            }
                        });
            }

            @Override
            public void OnPwdLoginReaskImgcodeSuccess(byte[] picData) {

            }

            @Override
            public void OnPwdLoginNeedImgcode(byte[] picData, TLSErrInfo errInfo) {

            }

            @Override
            public void OnPwdLoginFail(TLSErrInfo errInfo) {
                TLSService.getInstance().setLastErrno(-1);
                Util.notOK(LoginActivity.this, errInfo);
            }

            @Override
            public void OnPwdLoginTimeout(TLSErrInfo errInfo) {
                TLSService.getInstance().setLastErrno(-1);
                Util.notOK(LoginActivity.this, errInfo);
            }
        });
    }

    public void register(View view) {
        RESTfulFactory.getInstance().createJson(DataService.class)
                .login(mUserName.getText().toString(), mVerificationCode.getText().toString())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<JSONObject>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(JSONObject jsonObject) {
                        Log.e("msc", jsonObject.toString() + "  ");

//                        String mUserName = this.mUserName.getText().toString() + "unimserver";
//                        String pwd = getPwd();
//                        Log.e("msc", "pwd = " + pwd);
//                        TLSService.getInstance().initAccountRegisterService(LoginActivity.this, mUserName, pwd);
                    }
                });
    }

    public String getPwd() {
        String pwd = this.mUserName.getText().toString();
        Random random = new Random();
        for (int i = 0; i < 4; i++) {

            pwd = pwd + String.valueOf(random.nextInt(10));
        }
        return pwd;
    }
}
