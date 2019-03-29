package com.liuchen.mvpframe.base;

import android.support.v4.app.Fragment;
import android.widget.Toast;

public class BaseFragment extends Fragment implements BaseView {
    @Override
    public void showToast(String msg) {
        Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
    }
}
