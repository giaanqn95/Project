package com.intelin.vn.demomvvm.retrofit_client;

/**
 * Copyright by Intelin.
 * Creator: Tran Do Gia An
 * Date: 25/03/2019
 * Time: 11:20 AM
 */
public class CallRequestService {

    public static CallRequestService instance;

    public static CallRequestService getInstance() {
        if (null == instance) {
            synchronized (CallRequestService.class) {
                if (null == instance) {
                    instance = new CallRequestService();
                }
            }
        }
        return instance;
    }

    private int keyRequest;

    public int getKeyRequest() {
        return keyRequest;
    }

    public CallRequestService setKeyRequest(int keyRequest) {
        this.keyRequest = keyRequest;
        return this;
    }

    private RequestMethod requestMethod;

    public CallRequestService setRequestMethod(RequestMethod requestMethod) {
        this.requestMethod = requestMethod;
        return this;
    }

    public void enqueue(int keyRequest) {
        switch (keyRequest) {
            case 0:
//                getMethod("abc");
                requestMethod.getMethod("/public/news");
                break;

            default:
//                postMethod("xyz");
                requestMethod.putMethod("abc");
                break;
        }
    }
}
