package com.intelin.vn.demomvvm.retrofit_client;

import android.os.Build;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * Copyright by Intelin.
 * Creator: Tran Do Gia An
 * Date: 22/03/2019
 * Time: 12:34 PM
 */
public class BaseResponse {

    @SerializedName("data")
    private JsonElement data;

    @SerializedName("message")
    private String message;

    @SerializedName("code")
    private String code;

    public String getData() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return Objects.toString(data);
        }
        return data.toString();
    }

    public JsonObject getDataObject() {
        return data.getAsJsonObject();
    }

    public JsonArray getDataArray() {
        return data.getAsJsonArray();
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
