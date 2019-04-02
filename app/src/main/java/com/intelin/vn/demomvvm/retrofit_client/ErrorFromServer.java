package com.intelin.vn.demomvvm.retrofit_client;

/**
 * Copyright by Intelin.
 * Creator: Tran Do Gia An
 * Date: 22/03/2019
 * Time: 10:39 AM
 */
public interface ErrorFromServer {

    void ErrorResponse(String message);

    void ResponseSuccess(String body);
}
