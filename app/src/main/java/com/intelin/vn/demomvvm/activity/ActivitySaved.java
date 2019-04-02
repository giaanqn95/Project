package com.intelin.vn.demomvvm.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.intelin.vn.demomvvm.LogDog;
import com.intelin.vn.demomvvm.MainActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

/**
 * Copyright by Intelin.
 * Creator: Tran Do Gia An
 * Date: 01/04/2019
 * Time: 1:52 PM
 */
public class ActivitySaved {

    @Inject
    public HashMap<String, Class> savedActivity = new HashMap<>();

    @Inject
    public List<String> keyActivity = new ArrayList<>();

    @Inject
    public Activity stateActivity;

    public Activity getStateActivity() {
        return stateActivity;
    }

    //Set activity state
    public ActivitySaved setStateActivity(Activity stateActivity) {
        this.stateActivity = stateActivity;
        return this;
    }

    public void runActivity(Class activity, Bundle bundle) {
        LogDog.i("Run Activity");
        if (!savedActivity.containsKey(activity.getName())) {
            savedActivity.put(activity.getName(), activity);
            keyActivity.add(activity.getName());
        }
        LogDog.d(activity.getName());
        Intent intent = new Intent(stateActivity, savedActivity.get(activity.getName()));
        stateActivity.startActivity(intent);
        stateActivity.finish();
    }

    public void backActivity(Bundle bundle) {
        if (keyActivity.size() > 2) {
            LogDog.d("Back Activity ");
            runActivity(MainActivity.class, bundle);
            savedActivity.remove(this.getClass().getName());
            keyActivity.remove(keyActivity.size() - 1);
            LogDog.d(keyActivity.get(keyActivity.size() - 1));
            runActivity(savedActivity.get(keyActivity.get(keyActivity.size() - 1)), null);
        } else {
            LogDog.d("No more activity to back");
            return;
        }
    }
}
