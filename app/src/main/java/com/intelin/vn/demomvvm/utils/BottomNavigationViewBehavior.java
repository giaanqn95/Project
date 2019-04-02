package com.intelin.vn.demomvvm.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.view.View;

import com.intelin.vn.demomvvm.R;


/**
 * Copyright by Intelin.
 * Creator: Tran Do Gia An
 * Date: 27/03/2019
 * Time: 9:15 AM
 */
public class BottomNavigationViewBehavior extends CoordinatorLayout.Behavior<CustomConstraint> {

    private int height;
    private Context context;
    private CustomConstraint customConstraint;

    public BottomNavigationViewBehavior(Context context, CustomConstraint customConstraint) {
        this.context = context;
        this.customConstraint = customConstraint;
    }

    @Override
    public boolean onLayoutChild(CoordinatorLayout parent, CustomConstraint child, int layoutDirection) {
        height = child.getHeight();
        return super.onLayoutChild(parent, child, layoutDirection);
    }

    @Override
    public boolean onStartNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, CustomConstraint child,
                                       @NonNull View directTargetChild, @NonNull View target,
                                       int axes, int type) {
        return axes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedScroll(@NonNull CoordinatorLayout coordinatorLayout, @NonNull CustomConstraint child,
                               @NonNull View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed,
                               @ViewCompat.NestedScrollType int type) {
//        LogDog.d("Scroll something " + dxConsumed + " - " + dyConsumed + " - " + dxUnconsumed + " - " + dyUnconsumed);
//        if (dyConsumed > 0) {
//            slideDown(child);
//        } else if (dyConsumed < 0) {
//            slideUp(child);
//        }
//        customConstraint.setPageHeight(dyConsumed);
    }


    private void slideUp(ConstraintLayout child) {
        child.clearAnimation();
        child.animate().translationY(0).setDuration(200);
    }

    private void slideDown(ConstraintLayout child) {
        child.clearAnimation();
        child.animate().translationY(context.getResources().getDimensionPixelSize(R.dimen.dp50)).setDuration(200);
    }
}