package com.intelin.vn.demomvvm.activity;

import dagger.Module;
import dagger.Provides;

/**
 * Copyright by Intelin.
 * Creator: Tran Do Gia An
 * Date: 01/04/2019
 * Time: 1:55 PM
 */

@Module
public class ActivityModule {

    private static volatile ActivitySaved instance;

    public static ActivitySaved getInstance() {
        if (instance == null) {
            synchronized (ActivitySaved.class) {
                if (instance == null) {
                    instance = new ActivitySaved();
                }
            }
        }
        return instance;
    }

    @Provides
    public ActivitySaved saved() {
        return getInstance();
    }
}
