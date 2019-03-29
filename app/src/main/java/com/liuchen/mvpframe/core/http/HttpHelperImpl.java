package com.liuchen.mvpframe.core.http;

import android.util.Log;

import com.liuchen.mvpframe.core.bean.LoginBean;
import com.liuchen.mvpframe.util.UnicodeUtil;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class HttpHelperImpl implements HttpHelper {
    private static final String TAG = "HttpHelperImpl";
    private static HttpHelperImpl httpHelper;
    private ApiService apiService;

    private HttpHelperImpl() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                Log.d(TAG, "log: " + UnicodeUtil.unicodeToString(message));
            }
        });
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClientBuilder.addInterceptor(interceptor);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .client(httpClientBuilder.build())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())//用于Gson解析
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//适配RxJava2
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public static HttpHelperImpl getInstance() {
        if (httpHelper == null) {
            httpHelper = new HttpHelperImpl();
        }
        return httpHelper;
    }

    @Override
    public void login(String username, String password, Observer<LoginBean> loginBeanObserver) {
        apiService.login(username, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(loginBeanObserver);
    }
}
