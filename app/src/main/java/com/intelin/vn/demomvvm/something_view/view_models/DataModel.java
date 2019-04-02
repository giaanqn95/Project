package com.intelin.vn.demomvvm.something_view.view_models;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.text.TextUtils;
import android.util.Patterns;

import com.intelin.vn.demomvvm.BR;
import com.intelin.vn.demomvvm.something_view.filter.ManFilter;


/**
 * Copyright by Intelin.
 * Creator: Tran Do Gia An
 * Date: 22/03/2019
 * Time: 9:28 AM
 */
public class DataModel extends BaseObservable {
    private ManFilter user;


    private String successMessage = "Get data successful";
    private String errorMessage = "Map data error";

    @Bindable
    private String toastMessage = null;


    public String getToastMessage() {
        return toastMessage;
    }


    private DataModel setToastMessage(String toastMessage) {
        this.toastMessage = toastMessage;
        notifyPropertyChanged(BR.toastMessage);
        return this;
    }


    public DataModel setName(String name) {
        user.setName(name);
        notifyPropertyChanged(BR.name);
        return this;
    }

    @Bindable
    public String getName() {
        return user.getName();
    }

    @Bindable
    public String getAction() {
        return user.getAction();
    }

    public DataModel setAction(String action) {
        user.setAction(action);
        notifyPropertyChanged(BR.action);
        return this;
    }

    public DataModel() {
        user = new ManFilter("", "");
    }

    public void onGetData() {
        if (isInputDataValid()) {
            setToastMessage(successMessage);
        } else {
            setToastMessage(errorMessage);
        }
    }

    public boolean isInputDataValid() {
        return !TextUtils.isEmpty(getName()) && Patterns.EMAIL_ADDRESS.matcher(getName()).matches() && getAction().length() > 5;
    }

}
