package com.intelin.vn.demomvvm.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Copyright by Intelin.
 * Creator: Tran Do Gia An
 * Date: 02/04/2019
 * Time: 1:47 PM
 */
@SuppressLint("AppCompatCustomView")
public class CustomMyEditText extends EditText {

    public CustomMyEditText(Context context) {
        super(context);
    }

    public CustomMyEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomMyEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public CustomMyEditText(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


}
