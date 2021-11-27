package com.example.cuoiia.view.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.cuoiia.OnActionCallBack;

public abstract class BaseFragment<T extends ViewModel> extends Fragment {
    protected T mModel;
    protected View rootView;
    protected Context mContext;
    protected OnActionCallBack callBack;

    public void setCallBack(OnActionCallBack callBack){
        this.callBack = callBack;
    }

    @Override
    public final void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mContext=context;
    }

    public final <K extends View> K findViewById(int id) {
        return rootView.findViewById(id);
    }



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(getLayoutId(), container, false);
        mModel = new ViewModelProvider(requireActivity()).get(getClassViewModel());
        initViews();
        return rootView;
    }

    protected abstract void initViews();

    protected abstract Class<T> getClassViewModel();

    protected abstract int getLayoutId();



}
