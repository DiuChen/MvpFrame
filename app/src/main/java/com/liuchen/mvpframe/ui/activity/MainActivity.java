package com.liuchen.mvpframe.ui.activity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.liuchen.mvpframe.R;
import com.liuchen.mvpframe.base.BaseActivity;
import com.liuchen.mvpframe.contract.LoginContract;
import com.liuchen.mvpframe.core.bean.LoginBean;
import com.liuchen.mvpframe.presenter.LoginPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements LoginContract.View {
    @BindView(R.id.usernameEt)
    EditText usernameEt;
    @BindView(R.id.passwordEt)
    EditText passwordEt;
    @BindView(R.id.loginBtn)
    Button loginBtn;
    private static final String TAG = "MainActivity";
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        loginPresenter = new LoginPresenter();
        loginPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        loginPresenter.detachView();
    }

    @OnClick(R.id.loginBtn)
    public void onViewClicked() {
        Log.d(TAG, "onViewClicked: 开始请求");
        loginPresenter.login(usernameEt.getText().toString(), passwordEt.getText().toString());
    }

    @Override
    public void onLoginSuccess(LoginBean loginBean) {
        Log.d(TAG, "onLoginSuccess: 登录成功");
        showToast("登录成功");
    }

    @Override
    public void onLoginError(int errorCode) {
        Log.d(TAG, "onLoginError: 服务器异常" + errorCode);
    }

    @Override
    public void onLoginFailure(Throwable e) {
        Log.d(TAG, "onLoginFailure: 网络异常" + e.getMessage());
    }

    @Override
    public void onLoginComplete() {
        Log.d(TAG, "onLoginComplete: 请求结束");
    }
}
