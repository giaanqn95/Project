package com.intelin.vn.demomvvm;

import android.app.Application;

import com.intelin.vn.demomvvm.activity.ActivityComponent;
import com.intelin.vn.demomvvm.activity.ActivitySaved;
import com.intelin.vn.demomvvm.activity.DaggerActivityComponent;

/**
 * Copyright by Intelin.
 * Creator: Tran Do Gia An
 * Date: 29/03/2019
 * Time: 5:11 PM
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public static ActivitySaved createActivitiesSaved() {
        ActivityComponent activitiesSaved = DaggerActivityComponent.create();
        return activitiesSaved.getFunction();
    }
}
