package com.intelin.vn.demomvvm.retrofit_client;


import android.support.annotation.NonNull;

/**
 * Copyright by Intelin.
 * Creator: Tran Do Gia An
 * Date: 25/03/2019
 * Time: 11:14 AM
 */
public interface RequestMethod {

    void getMethod(@NonNull String url);

    void postMethod(@NonNull String url);

    void putMethod(@NonNull String url);

    void deleteMethod(@NonNull String url);
}
