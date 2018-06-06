package com.imaginationunlimited.sniper.logo;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.imaginationunlimited.sniper.MyApplication;
import com.imaginationunlimited.sniper.R;
import com.imaginationunlimited.sniper.base.BaseActivity;
import com.imaginationunlimited.sniper.login.LoginActivity;
import com.imaginationunlimited.sniper.utils.Constant;
import com.imaginationunlimited.sniper.utils.TlsBusiness;
import com.tencent.imsdk.TIMLogLevel;
import com.tencent.imsdk.TIMManager;
import com.tencent.imsdk.TIMSdkConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by MSC on 2018/6/4.
 * BJ AlphaMobile
 */
public class LogoActivity extends BaseActivity {
    public static final String TAG = "TIM_SDK";
    private final int REQUEST_PHONE_PERMISSIONS = 0;

    @Override
    protected void afterViewFound() {
        final List<String> permissionsList = new ArrayList<>();
//        if (ConnectionResult.SUCCESS != GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this)){
//            Toast.makeText(this, getString(R.string.google_service_not_available), Toast.LENGTH_SHORT).show();
////            GoogleApiAvailability.getInstance().getErrorDialog(this, GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this),
////                    GOOGLE_PLAY_RESULT_CODE).show();
//        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if ((checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED))
                permissionsList.add(Manifest.permission.READ_PHONE_STATE);
            if ((checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED))
                permissionsList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (permissionsList.size() == 0) {
                init();
            } else {
                requestPermissions(permissionsList.toArray(new String[permissionsList.size()]), REQUEST_PHONE_PERMISSIONS);
            }
        } else {
            init();
        }
    }

    private void init() {
        TIMSdkConfig config = new TIMSdkConfig(Constant.SDK_APPID);
        config.enableLogPrint(true)
                .setLogLevel(TIMLogLevel.values()[TIMLogLevel.DEBUG.ordinal()]);
        //初始化imsdk
        TIMManager.getInstance().init(MyApplication.getAppContext(), config);
        //禁止服务器自动代替上报已读
        Log.e(TAG, "initIMsdk");

        TlsBusiness.init(MyApplication.getAppContext());
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void setContentView() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_logo);
    }

    @Override
    public void setUpPresenter() {

    }

    @Override
    public void findViews() {

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PHONE_PERMISSIONS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    init();
                } else {
                    Toast.makeText(this, getString(R.string.need_permission), Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }


}
