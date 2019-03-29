package com.liuchen.mvpframe.core.http;

import com.liuchen.mvpframe.core.bean.LoginBean;

import io.reactivex.Observer;

public interface HttpHelper {
    void login(String username, String password, Observer<LoginBean> loginBeanObserver);
}
