package com.intelin.vn.demomvvm;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.CoordinatorLayout;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.TextView;

import com.intelin.vn.demomvvm.something_view.view.TestActivity;
import com.intelin.vn.demomvvm.utils.BottomNavigationViewBehavior;
import com.intelin.vn.demomvvm.utils.CustomConstraint;


import static com.intelin.vn.demomvvm.App.createActivitiesSaved;

public class MainActivity extends BaseActivity {


    private TextView btn, btnBack, btnNext;
    private CustomConstraint constraintLayout;
    private RecyclerView rvView;
    private MainAdapter mainAdapter;
    private int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LogDog.d("MainActivity onCreate");
        initView();
        settingView();
        initSwipe();
        initButton();
    }

    private void initView() {
        btn = findViewById(R.id.btn);
        constraintLayout = new CustomConstraint(this);
        constraintLayout = findViewById(R.id.bottom_nav);
        rvView = findViewById(R.id.rvView);
        mainAdapter = new MainAdapter();
        rvView.setLayoutManager(new LinearLayoutManager(this));
        rvView.setAdapter(mainAdapter);
        btnBack = findViewById(R.id.btnBack);
        btnNext = findViewById(R.id.btnNext);
    }

    private void settingView() {
        CoordinatorLayout.LayoutParams layoutParams = (CoordinatorLayout.LayoutParams) constraintLayout.getLayoutParams();
        layoutParams.setBehavior(new BottomNavigationViewBehavior(this, constraintLayout));

        rvView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int offset = recyclerView.computeVerticalScrollOffset();
                int extent = recyclerView.computeVerticalScrollExtent();
                int range = recyclerView.computeVerticalScrollRange();

                int percentage = (int) (100.0 * offset / (float) (range - extent));
                LogDog.d("Scroll something " + percentage + " - " + range + " - " + extent + " - " + offset);
                constraintLayout.setPageHeight((int) (offset / 10 + 5F));
            }
        });

        mainAdapter.setOnItemClickListener((holder) -> {
            if (holder.itemView.isSelected()) {
                total = total - 1;
                holder.itemView.setSelected(false);
            } else {
                total = total + 1;
                holder.itemView.setSelected(true);
            }
            if (total > 0) {
                slideUp(constraintLayout);
            } else {
                slideDown(constraintLayout);
            }
        });

        btnBack.setOnClickListener(v -> btnBack(btn));

        btnNext.setOnClickListener(v -> btnNext(btn));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogDog.d("MainActivity onResume");
    }

    @Override
    protected void onStart() {
        super.onStart();
        LogDog.d("MainActivity onStart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogDog.d("MainActivity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogDog.d("MainActivity Stop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogDog.d("MainActivity Destroy");
    }

    Paint p = new Paint();

    public void initSwipe() {
        ItemTouchHelper.SimpleCallback simpleItemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();

                if (direction == ItemTouchHelper.LEFT) {
                    mainAdapter.removeItem(position);
                } else {
                }
            }

            @Override
            public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {

                Bitmap icon;
                if (actionState == ItemTouchHelper.ACTION_STATE_SWIPE) {

                    View itemView = viewHolder.itemView;
                    float height = (float) itemView.getBottom() - (float) itemView.getTop();
                    float width = height / 3;

                    if (dX > 0) {
                        p.setColor(Color.parseColor("#388E3C"));
                        RectF background = new RectF((float) itemView.getLeft(), (float) itemView.getTop(), dX, (float) itemView.getBottom());
                        c.drawRect(background, p);
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_background);
                        RectF icon_dest = new RectF((float) itemView.getLeft() + width, (float) itemView.getTop() + width, (float) itemView.getLeft() + 2 * width, (float) itemView.getBottom() - width);
//                        c.drawBitmap(icon, null, icon_dest, p);
                    } else {
                        p.setColor(Color.parseColor("#FF000000"));
                        RectF background = new RectF((float) itemView.getRight() + dX, (float) itemView.getTop(), (float) itemView.getRight(), (float) itemView.getBottom());
                        c.drawRect(background, p);
                        icon = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher_background);
                        RectF icon_dest = new RectF((float) itemView.getRight() - 2 * width, (float) itemView.getTop() + width, (float) itemView.getRight() - width, (float) itemView.getBottom() - width);
//                        c.drawBitmap(icon, null, icon_dest, p);
                    }
                }
                super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
            }
        };
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(simpleItemTouchCallback);
        itemTouchHelper.attachToRecyclerView(rvView);
    }

    public void initButton() {
        swipeLeftRightListeners(btn);
    }

    public int getMaxWidth() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        return width;
    }


    private void slideUp(CustomConstraint child) {
        child.clearAnimation();
        child.animate().translationY(0).setDuration(200);
    }

    private void slideDown(ConstraintLayout child) {
        child.clearAnimation();
        child.animate().translationY(getResources().getDimensionPixelSize(R.dimen.dp50)).setDuration(200);
    }

    private void btnBack(TextView textView) {
        ObjectAnimator animX = ObjectAnimator.ofFloat(textView, "x", 0);
        animX.setDuration(100);
        animX.start();
    }

    private void btnNext(TextView textView) {
        ObjectAnimator animX = ObjectAnimator.ofFloat(textView, "x", 0);
        animX.setDuration(100);
        animX.start();
        LogDog.d("Click");
        createActivitiesSaved().runActivity(TestActivity.class, new Bundle());
    }

    public int totalSelected() {
        return total;
    }
}
