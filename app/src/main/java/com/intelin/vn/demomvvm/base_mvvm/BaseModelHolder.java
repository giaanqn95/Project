package com.intelin.vn.demomvvm.base_mvvm;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


/**
 * Copyright by Intelin.
 * Creator: Tran Do Gia An
 * Date: 22/03/2019
 * Time: 5:30 PM
 */
public class BaseModelHolder<VM> extends Activity {

    private VM mViewModel;

    public BaseModelHolder() { }

    public static <M> BaseModelHolder createContainer(@NonNull M viewModel) {
        BaseModelHolder<M> viewModelContainer = new BaseModelHolder<>();
        viewModelContainer.setViewModel(viewModel);
        return viewModelContainer;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable public VM getViewmodel() {
        return mViewModel;
    }

    public void setViewModel(@NonNull VM viewModel) {
        mViewModel = viewModel;
    }
}