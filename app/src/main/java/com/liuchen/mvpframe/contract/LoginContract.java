package com.liuchen.mvpframe.contract;

import com.liuchen.mvpframe.base.BaseView;
import com.liuchen.mvpframe.core.bean.LoginBean;

public interface LoginContract {
    interface View extends BaseView {
        void onLoginSuccess(LoginBean loginBean);

        void onLoginError(int errorCode);

        void onLoginFailure(Throwable e);

        void onLoginComplete();
    }

    interface Presenter {
        void login(String username, String password);
    }
}
