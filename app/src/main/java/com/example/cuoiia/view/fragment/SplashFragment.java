package com.example.cuoiia.view.fragment;

import android.os.Handler;
import com.example.cuoiia.R;
import com.example.cuoiia.viewmodel.SplashViewModel;

public class SplashFragment extends BaseFragment<SplashViewModel>{

    public static final String KEY_SHOW_HOME = "KEY_SHOW_HOME";

    @Override
    protected void initViews() {
        new Handler().postDelayed(this::gotoMainFragment, 2000);
    }

    @Override
    protected Class getClassViewModel() {
        return SplashViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.splash_fragment;
    }

    private void gotoMainFragment() {
        callBack.onCallBack(KEY_SHOW_HOME);
    }
}
