package com.intelin.vn.demomvvm.utils;

import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Copyright by Intelin.
 * Creator: Tran Do Gia An
 * Date: 27/03/2019
 * Time: 10:40 AM
 */
public class CustomConstraint extends ConstraintLayout {

    private int pageHeight;

    public void setPageHeight(int pageHeight) {
        this.pageHeight = pageHeight;
        requestLayout();
    }

    public CustomConstraint(Context context) {
        super(context);
    }

    public CustomConstraint(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomConstraint(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int height = 0, desireHeight;
        Log.v("Chart onMeasure w", View.MeasureSpec.toString(widthMeasureSpec));
        Log.v("Chart onMeasure h", View.MeasureSpec.toString(heightMeasureSpec));
        int heightMode = View.MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = View.MeasureSpec.getSize(heightMeasureSpec);

        if (heightMode == View.MeasureSpec.EXACTLY) {
            if (pageHeight <= 131) {
                height = pageHeight;
            } else {
                height = 131;
            }
        } else if (heightMode == View.MeasureSpec.AT_MOST) {
            height = Math.min(pageHeight, heightSize);
        } else {
            height = pageHeight;
        }

        setMeasuredDimension(widthMeasureSpec, height);

//        int desiredHeight = getSuggestedMinimumHeight() + pageHeight;
//        if (pageHeight > 0) {
//            heightMeasureSpec = MeasureSpec.makeMeasureSpec(pageHeight, MeasureSpec.EXACTLY);
//        }
//        setMeasuredDimension(MeasureSpec.getMode(widthMeasureSpec), measureDimension(desiredHeight, heightMeasureSpec));
    }

    private int measureDimension(int desiredSize, int measureSpec) {
        int result;
        int specMode = View.MeasureSpec.getMode(measureSpec);
        int specSize = View.MeasureSpec.getSize(measureSpec);

        if (specMode == View.MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = desiredSize;
            if (specMode == View.MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }

        if (result < desiredSize) {
            Log.e("ChartView", "The view is too small, the content might get cut");
        }
        return result;
    }
}
