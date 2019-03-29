package com.liuchen.mvpframe.presenter;

import com.liuchen.mvpframe.base.BasePresenter;
import com.liuchen.mvpframe.contract.LoginContract;
import com.liuchen.mvpframe.core.bean.LoginBean;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    @Override
    public void login(String username, String password) {
        if (!isViewAttached()) return;
        getDataManager().login(username, password, new Observer<LoginBean>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(LoginBean loginBean) {
                if (loginBean.getErrorCode() == 0) {
                    getView().onLoginSuccess(loginBean);
                } else {
                    getView().onLoginError(loginBean.getErrorCode());
                }
            }

            @Override
            public void onError(Throwable e) {
                getView().onLoginFailure(e);
                getView().onLoginComplete();
            }

            @Override
            public void onComplete() {
                getView().onLoginComplete();
            }
        });
    }
}
