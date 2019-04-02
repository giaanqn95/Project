package com.intelin.vn.demomvvm;

import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.intelin.vn.demomvvm.retrofit_client.ErrorFromServer;
import com.intelin.vn.demomvvm.retrofit_client.RetrofitService;

import static com.intelin.vn.demomvvm.App.createActivitiesSaved;

/**
 * Copyright by Intelin.
 * Creator: Tran Do Gia An
 * Date: 22/03/2019
 * Time: 9:30 AM
 */
public class BaseActivity extends AppCompatActivity implements ErrorFromServer {

    public RetrofitService retrofitService;
    private Dialog mDialog;
    private int heightKeyBoard;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        retrofitService = RetrofitService.getInstance();
        retrofitService.setErrorFromServer(this);
        createActivitiesSaved().setStateActivity(this);
    }

    public void back() {

    }

    @Override
    public void ErrorResponse(String message) {

    }

    @Override
    public void ResponseSuccess(String body) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 500:
                Toast.makeText(this, "Có vài lỗi trong hệ thống vui lòng thử lại sau", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        createActivitiesSaved().backActivity(new Bundle());
    }

    public void swipeLeftRightListeners(View view) {
        view.setOnTouchListener(new View.OnTouchListener() {
            float dx = 0, x = 0;
            float startX = 0, upX = 0;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                TextView textView = (TextView) view;
                int action = motionEvent.getAction();
                if (action == MotionEvent.ACTION_DOWN) {
                    x = motionEvent.getRawX();
                    dx = view.getX() - x;
                    startX = motionEvent.getRawX() + dx;
                    LogDog.d("ACTION_DOWN " + startX);
                    return true;

                } else if (action == MotionEvent.ACTION_MOVE) {
                    float newX = motionEvent.getRawX() + dx;
                    view.animate().x(newX).setDuration(0).start();
                    return true;

                } else if (action == MotionEvent.ACTION_UP) {
                    x = motionEvent.getRawX();
                    dx = view.getX() - x;
                    upX = motionEvent.getRawX() + dx;
                    LogDog.d("ACTION_UP " + upX);
                    float totalRange = startX + upX;

                    //Swipe Left qualification with default value
                    if (totalRange < 0 && totalRange < -getResources().getDimensionPixelSize(R.dimen.dp100)) {
                        ObjectAnimator animX = ObjectAnimator.ofFloat(textView, "x", -getResources().getDimensionPixelSize(R.dimen.dp100));
                        animX.setDuration(100);
                        animX.start();

                        //Swipe Right qualification with default value
                    } else if (totalRange > 0 && totalRange > getResources().getDimensionPixelSize(R.dimen.dp100)) {
                        ObjectAnimator animX = ObjectAnimator.ofFloat(textView, "x", getResources().getDimensionPixelSize(R.dimen.dp100));
                        animX.setDuration(100);
                        animX.start();

                        //Swipe Left qualification without default value
                    } else if (totalRange < 0 && totalRange > -getResources().getDimensionPixelSize(R.dimen.dp100)) {
                        ObjectAnimator animX = ObjectAnimator.ofFloat(textView, "x", 0);
                        animX.setDuration(100);
                        animX.start();
                        //Swipe Right qualification without default value
                    } else if (totalRange > 0 && totalRange < getResources().getDimensionPixelSize(R.dimen.dp100)) {
                        ObjectAnimator animX = ObjectAnimator.ofFloat(textView, "x", 0);
                        animX.setDuration(100);
                        animX.start();
                    }
                    return true;
                }
                return true;
            }
        });
    }

    public void dialogEditText(TextView et, Context context) {
        final Dialog dialog = new Dialog(context, R.style.MyDialog);
        View view = LayoutInflater.from(context).inflate(R.layout.component_editext, null);
        dialog.setContentView(view);
        dialog.getWindow().setGravity(Gravity.BOTTOM);
        dialog.getWindow().setWindowAnimations(R.style.Animation_Design_BottomSheetDialog);

        EditText etDemo = view.findViewById(R.id.etDemo);
        ImageView ivDelete = view.findViewById(R.id.ivDelete);

        ivDelete.setVisibility(et.length() > 0 ? View.VISIBLE : View.GONE);
        showIMM(etDemo);
        etDemo.setText(et.getText().toString());
        etDemo.setSelection(et.length());

        etDemo.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                et.setText(etDemo.getText().toString());
                dialog.dismiss();
            }
            return false;
        });

        etDemo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ivDelete.setVisibility(s.length() > 0 ? View.VISIBLE : View.GONE);
            }
        });

        ivDelete.setOnClickListener(v -> etDemo.setText(""));

        mDialog = dialog;
        mDialog.show();
        dialog.setCanceledOnTouchOutside(true);
    }

    public int getHeightIMM(View viewMain) {
        viewMain.getViewTreeObserver().addOnGlobalLayoutListener(() -> {
            int height = viewMain.getHeight();
            Log.w("Foo", String.format("layout height: %d", height));
            Rect r = new Rect();
            viewMain.getWindowVisibleDisplayFrame(r);
            int visible = r.bottom - r.top;
            Log.w("Foo", String.format("visible height: %d", visible));
            Log.w("Foo", String.format("keyboard height: %d", height - visible));
            heightKeyBoard = height - visible;
        });
        return heightKeyBoard;
    }

    public void showIMM(EditText e) {
        e.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(e, InputMethodManager.SHOW_IMPLICIT);
        e.setSelection(e.length());

    }
}
