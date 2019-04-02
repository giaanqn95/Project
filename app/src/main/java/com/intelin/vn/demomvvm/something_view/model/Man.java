package com.intelin.vn.demomvvm.something_view.model;

import com.google.gson.annotations.SerializedName;

/**
 * Copyright by Intelin.
 * Creator: Tran Do Gia An
 * Date: 22/03/2019
 * Time: 9:38 AM
 */
public class Man {

    @SerializedName("name")
    private String name;

    @SerializedName("action")
    private String action;

    public Man(String name, String action) {
        this.name = name;
        this.action = action;
    }

    public Man() {

    }

    public String getName() {
        return name;
    }

    public Man setName(String name) {
        this.name = name;
        return this;
    }

    public String getAction() {
        return action;
    }

    public Man setAction(String action) {
        this.action = action;
        return this;
    }
}
