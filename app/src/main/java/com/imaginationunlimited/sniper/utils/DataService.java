package com.imaginationunlimited.sniper.utils;


import com.imaginationunlimited.sniper.model.UserInfoFromService;

import org.json.JSONObject;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * -------------------_ooOoo_
 * ------------------o8888888o
 * ------------------88" . "88
 * ------------------(| -_- |)
 * ------------------O\  =  /O
 * ---------------____/`---'\____
 * -------------.'  \\|     |//  `.
 * ------------/  \\|||  :  |||//  \
 * -----------/  _||||| -:- |||||-  \
 * -----------|   | \\\  -  /// |   |
 * -----------| \_|  ''\---/''  |   |
 * -----------\  .-\__  `-`  ___/-. /
 * ---------___`. .'  /--.--\  `. . __
 * ------."" '<  `.___\_<|>_/___.'  >'"".
 * -----| | :  `- \`.;`\ _ /`;.`/ - ` : | |
 * -----\  \ `-.   \_ __\ /__ _/   .-` /  /
 * ======`-.____`-.___\_____/___.-`____.-'======
 * -------------------`=---='
 * ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
 * -----------佛祖保佑       永无BUG
 * <p>
 * Created by MSC on 2017/9/8.
 * E-Mail : mengshuangchun@163.com.
 */
public interface DataService {

//    @GET("manly/getMaterials")
//    Observable<HttpResponse<MaterialsListInfoEntity>>
//    getSuggestedMaterials(@Query("type") String typesWith_);
//
//    @GET("manly/getHomeListNew")
//    Observable<HttpResponse<List<HomeBannerEntity>>>
//    getHomeBannerList();

    @GET("login")
    Observable<JSONObject>
    login(@Query("phone") String phoneNumber);

    @GET("login")
    Observable<HttpResponse<UserInfoFromService>>
    login(@Query("phone") String phoneNumber, @Query("code") String code);

    //配置列表
    @GET("getHomeList")
    Observable<JSONObject>
    getHomeJson(@Query("version") int version);

    @GET("recommend")
    Observable<JSONObject>
    getHomeItemList(@Query("token") String token);
}
