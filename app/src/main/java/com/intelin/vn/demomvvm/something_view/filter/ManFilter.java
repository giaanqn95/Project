package com.intelin.vn.demomvvm.something_view.filter;


import java.lang.reflect.Field;

/**
 * Copyright by Intelin.
 * Creator: Tran Do Gia An
 * Date: 22/03/2019
 * Time: 9:56 AM
 */
public class ManFilter {

    private String name;

    private String action;

    public ManFilter(String name, String action) {
        this.name = name;
        this.action = action;
    }

    public ManFilter() {

    }

    public String getName() {
        return name;
    }

    public ManFilter setName(String name) {
        this.name = name;
        return this;
    }

    public String getAction() {
        return action;
    }

    public ManFilter setAction(String action) {
        this.action = action;
        return this;
    }

    private ManFilter checkValue(ManFilter manFilter) throws IllegalArgumentException, IllegalAccessException {
        for (Field field : getClass().getDeclaredFields()) {
            if (field.get(manFilter) == null) {
                throw new NullPointerException("Properties " + field.getName() + " is null");
            }
        }
        return manFilter;
    }
}
