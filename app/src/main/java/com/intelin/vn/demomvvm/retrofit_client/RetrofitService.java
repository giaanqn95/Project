package com.intelin.vn.demomvvm.retrofit_client;

import android.support.annotation.NonNull;
import android.util.Log;

import com.intelin.vn.demomvvm.exception.CustomException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Copyright by Intelin.
 * Creator: Tran Do Gia An
 * Date: 22/03/2019
 * Time: 10:36 AM
 */
public class RetrofitService implements RequestMethod {

    private static RetrofitService instance = new RetrofitService();

    private ErrorFromServer errorFromServer;
    private String responseBody;
    private ApiInterface apiInterface;

    public static RetrofitService getInstance() {
        if (null == instance) {
            synchronized (RetrofitService.class) {
                if (null == instance) {
                    instance = new RetrofitService();
                }
            }
        }
        return instance;
    }

    public RetrofitService() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
        CallRequestService.getInstance().setRequestMethod(this);
    }

    public void setErrorFromServer(ErrorFromServer errorFromServer) {
        this.errorFromServer = errorFromServer;
    }

    public String getResponeBody() {
        return responseBody;
    }

    public RetrofitService setResponseBody(String responseBody) {
        this.responseBody = responseBody;
        Log.d("AAAAAAA", responseBody);
        return this;
    }

    @Override
    public void getMethod(@NonNull String url) {
        apiInterface.get(url).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                try {
                    BaseResponse baseResponse = response.body();
                    switch (baseResponse.getCode()) {
                        case "200":
                            errorFromServer.ResponseSuccess(JSON.encode(response.body().getDataArray()));
                            break;
                        default:
                            errorFromServer.ErrorResponse(response.message());
                            break;
                    }
                } catch (NullPointerException e) {
                    errorFromServer.ErrorResponse(e.getMessage());
                    throw new NullPointerException("Null or Error" + e.getMessage() + " cause " + e.getCause());

                } catch (CustomException.EncodingException e) {
                    errorFromServer.ErrorResponse(e.getMessage());
                    throw new CustomException.EncodingException("Decode Error by " + e.getMessage() + " cause " + e.getCause());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                errorFromServer.ErrorResponse(t.getMessage());
            }
        });
    }

    @Override
    public void postMethod(@NonNull String url) {
        apiInterface.post(url).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                BaseResponse baseResponse = response.body();
                try {
                    switch (baseResponse.getCode()) {
                        case "200":
                            errorFromServer.ResponseSuccess(JSON.encode(response.body().getDataArray()));
                            break;
                        default:
                            errorFromServer.ErrorResponse(response.message());
                            break;
                    }
                } catch (NullPointerException e) {
                    errorFromServer.ErrorResponse(e.getMessage());
                    throw new CustomException.EncodingException("Null or Error" + e.getMessage() + " cause " + e.getCause());

                } catch (CustomException.EncodingException e) {
                    errorFromServer.ErrorResponse(e.getMessage());
                    throw new CustomException.EncodingException("Decode Error by " + e.getMessage() + " cause " + e.getCause());
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                errorFromServer.ErrorResponse(t.getMessage());
            }
        });
    }

    @Override
    public void putMethod(@NonNull String url) {
        apiInterface.put(url).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.isSuccessful()) {
                    BaseResponse baseResponse = response.body();
                    try {
                        switch (baseResponse.getCode()) {
                            case "200":
                                errorFromServer.ResponseSuccess(JSON.encode(response.body().getDataArray()));
                                break;
                            default:
                                errorFromServer.ErrorResponse(response.message());
                                break;
                        }
                    } catch (NullPointerException e) {
                        errorFromServer.ErrorResponse(e.getMessage());
                        throw new CustomException.EncodingException("Null or Error" + e.getMessage() + " cause " + e.getCause());

                    } catch (CustomException.EncodingException e) {
                        errorFromServer.ErrorResponse(e.getMessage());
                        throw new CustomException.EncodingException("Decode Error by " + e.getMessage() + " cause " + e.getCause());
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                errorFromServer.ErrorResponse(t.getMessage());
            }
        });
    }

    @Override
    public void deleteMethod(@NonNull String url) {
        apiInterface.delete(url).enqueue(new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                if (response.isSuccessful()) {
                    BaseResponse baseResponse = response.body();
                    try {
                        switch (baseResponse.getCode()) {
                            case "200":
                                errorFromServer.ResponseSuccess(JSON.encode(response.body().getDataArray()));
                                break;
                            default:
                                errorFromServer.ErrorResponse(response.message());
                                break;
                        }
                    } catch (NullPointerException e) {
                        errorFromServer.ErrorResponse(e.getMessage());
                        throw new CustomException.EncodingException("Null or Error" + e.getMessage() + " cause " + e.getCause());
                    } catch (CustomException.EncodingException e) {
                        errorFromServer.ErrorResponse(e.getMessage());
                        throw new CustomException.EncodingException("Decode Error by " + e.getMessage() + " cause " + e.getCause());
                    }
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                errorFromServer.ErrorResponse(t.getMessage());
            }
        });
    }
}
