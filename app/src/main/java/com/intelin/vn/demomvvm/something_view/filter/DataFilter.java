package com.intelin.vn.demomvvm.something_view.filter;

import com.intelin.vn.demomvvm.retrofit_client.JSON;
import com.intelin.vn.demomvvm.something_view.model.Man;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Copyright by Intelin.
 * Creator: Tran Do Gia An
 * Date: 22/03/2019
 * Time: 9:55 AM
 */
public class DataFilter {

    private List<ManFilter> manList;

    public List<ManFilter> getManList() {
        return manList;
    }

    public DataFilter setManList(List<ManFilter> manList) {
        this.manList = manList;
        return this;
    }

    public ArrayList<Man> getScheduleList(String data) {
        try {
            ArrayList<Man> result = new ArrayList<>();
            Man[] arr = new Man[0];

            arr = JSON.decode(data, Man[].class);

            if (arr != null)
                result.addAll(Arrays.asList(arr));
            return result;
        } catch (Exception e) {
            return new ArrayList<Man>();
        }
    }
}
