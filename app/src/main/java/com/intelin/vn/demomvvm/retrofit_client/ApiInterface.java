package com.intelin.vn.demomvvm.retrofit_client;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Url;

/**
 * Copyright by Intelin.
 * Creator: Tran Do Gia An
 * Date: 22/03/2019
 * Time: 10:33 AM
 */
public interface ApiInterface {

    @GET
    Call<BaseResponse> get(@Url String url);

    @POST
    Call<BaseResponse> post(@Url String url);

    @PUT
    Call<BaseResponse> put(@Url String url);

    @DELETE
    Call<BaseResponse> delete(@Url String url);
}
