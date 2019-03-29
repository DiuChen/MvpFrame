package com.liuchen.mvpframe.core;

import com.liuchen.mvpframe.core.bean.LoginBean;
import com.liuchen.mvpframe.core.http.HttpHelper;
import com.liuchen.mvpframe.core.http.HttpHelperImpl;

import io.reactivex.Observer;

public class DataManager implements HttpHelper {
    private static DataManager dataManager;
    private HttpHelperImpl httpHelper;

    private DataManager() {
        httpHelper = HttpHelperImpl.getInstance();
    }

    public static DataManager getInstance() {
        if (dataManager == null) {
            dataManager = new DataManager();
        }
        return dataManager;
    }

    @Override
    public void login(String username, String password, Observer<LoginBean> loginBeanObserver) {
        httpHelper.login(username, password, loginBeanObserver);
    }
}
