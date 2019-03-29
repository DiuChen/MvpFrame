package com.liuchen.mvpframe.core.http;

import com.liuchen.mvpframe.core.bean.LoginBean;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
    String BASE_URL = "https://www.wanandroid.com/";//测试域名

    //登录
    @FormUrlEncoded
    @POST("user/login")
    Observable<LoginBean> login(@Field("username") String username,
                                @Field("password") String password);
}
