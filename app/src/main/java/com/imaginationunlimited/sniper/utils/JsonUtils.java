package com.imaginationunlimited.sniper.utils;

import android.text.TextUtils;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by msc on 2015/9/17.
 * author : MSC.
 * E-Mail : mengshuangchun@163.com.
 */
public class JsonUtils {

    public static <T> List<T> getTypeList(Class<T> clazz, String json) {
        List<T> list = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(json);

            int code = jsonObject.getInt("code");
            String msg = jsonObject.getString("message");

            JSONArray data = jsonObject.getJSONArray("data");

            for (int i = 0; i < data.length(); i++) {
                JSONObject object = data.getJSONObject(i);
                try {
                    String s = object.toString();
                    Gson gson = new Gson();
                    T gif = gson.fromJson(s, clazz);
                    list.add(gif);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static <T> ArrayList<T> getTypeListFromData(Type type, JSONObject jsonObject) {
        ArrayList<T> list = null;
        try {
            if (jsonObject == null || TextUtils.isEmpty(jsonObject.getString("data"))) {
                return list;
            }

//            Type listType = new TypeToken<ArrayList<T>>() {
//            }.getType();
            Gson gson = new Gson();
            list = gson.fromJson(jsonObject.getString("data"), type);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return list;
    }

}
