package com.imaginationunlimited.sniper;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;
import android.text.TextUtils;

import java.io.UnsupportedEncodingException;
import java.util.Locale;
import java.util.UUID;

/**
 * Created by MSC on 2018/6/4.
 * BJ AlphaMobile
 */
public class MyApplication extends Application {
    public static MyApplication THIS;

    public static MyApplication getInstance() {
        return THIS;
    }

    public static boolean isDebugMode() {
        return true;
//        return BuildConfig.DEVELOPER_MODE;
    }

    public static Context getAppContext() {
        return THIS.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        THIS = this;
    }

    /**
     * versionCode
     *
     * @return versionCode
     */
    public int getVersionCode() {
        // 获取packagemanager的实例
        PackageManager packageManager = getPackageManager();
        // getPackageName()是你当前类的包名，0代表是获取版本信息
        PackageInfo packInfo;
        try {
            packInfo = packageManager.getPackageInfo(getPackageName(), 0);
            int code = packInfo.versionCode;
            return code;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * uuID
     *
     * @return uuID
     */
    public String getUuid() {

        return "G-" + getDeviceId().toString();
    }

    public String getLanguage() {
        Locale locale;
        if (Build.VERSION.SDK_INT >= 24) {
            locale = getResources().getConfiguration().getLocales().get(0);
        } else {
            locale = getResources().getConfiguration().locale;

        }
        return locale.getLanguage();
    }

    public UUID uuid = null;
    protected static final String PREFS_FILE = "device_id.xml";
    protected static final String PREFS_DEVICE_ID = "device_id";

    public UUID getDeviceId() {
        if (uuid == null) {
            final SharedPreferences prefs = getSharedPreferences(PREFS_FILE, 0);
            final String id = prefs.getString(PREFS_DEVICE_ID, null);
            if (id != null) {
                // Use the ids previously computed and stored in the prefs file
                uuid = UUID.fromString(id);
            } else {
                final String androidId = Settings.Secure.getString(getContentResolver(), Settings.Secure.ANDROID_ID);
                // Use the Android ID unless it's broken, in which case fallback on deviceId,
                // unless it's not available, then fallback on a random number which we store
                // to a prefs file
                try {
                    if (!"9774d56d682e549c".equals(androidId)) {
                        try {
                            uuid = UUID.nameUUIDFromBytes(androidId.getBytes("utf8"));
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    } else {
                        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                            final String deviceId = ((TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE)).getDeviceId();
                            uuid = deviceId != null ? UUID.nameUUIDFromBytes(deviceId.getBytes("utf8")) : UUID.fromString("");
                        }
                    }

                } catch (UnsupportedEncodingException e) {
                    uuid = UUID.fromString("");
                }
                // Write the value out to the prefs file
                prefs.edit().putString(PREFS_DEVICE_ID, uuid.toString()).apply();
            }
        }
        return uuid;
    }

    public static String COUNTRY_CODE = "";//cache

    /**
     * check country
     *
     * @return COUNTRY_CODE
     */
    public String checkUsUser() {
        if (!TextUtils.isEmpty(COUNTRY_CODE)) {
            return COUNTRY_CODE;
        }
        TelephonyManager manager = (TelephonyManager) this.getSystemService(TELEPHONY_SERVICE);
        String country;
        if (manager.getPhoneType() != TelephonyManager.PHONE_TYPE_CDMA) {
            country = manager.getNetworkCountryIso();
            if (country != null && country.length() == 2) {
                return COUNTRY_CODE = country.toLowerCase(Locale.US);
            }
        }

        country = manager.getSimCountryIso();
        if (country != null && country.length() == 2) {
            return COUNTRY_CODE = country.toLowerCase(Locale.US);
        }

        Locale locale;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            locale = getResources().getConfiguration().getLocales().get(0);
        } else {
            locale = getResources().getConfiguration().locale;
        }

        if (locale != null && locale.getCountry() != null) {
            return COUNTRY_CODE = locale.getCountry().toLowerCase(Locale.US);
        }

        return COUNTRY_CODE = "";
    }
}
