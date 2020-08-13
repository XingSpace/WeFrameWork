package com.wfw.para.weframework.a.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;

import com.wfw.para.weframework.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/11/13.
 */

public class AppBarActivity extends AppCompatActivity {
    @BindView(R.id.btn1)
    Button btn1;
    @BindView(R.id.btn2)
    Button btn2;
    @BindView(R.id.btn3)
    Button btn3;
    @BindView(R.id.btn4)
    Button btn4;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_activity_appbarlayout);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn1)
    void btn1() {
        startActivity(AppBarOneActivity.class);
    }

    @OnClick(R.id.btn2)
    void btn2() {
        startActivity(AppBarTwoActivity.class);
    }

    @OnClick(R.id.btn3)
    void btn3() {
        startActivity(AppBarThreeActivity.class);
    }

    @OnClick(R.id.btn4)
    void btn4() {
        startActivity(AppBarFourActivity.class);
    }


    private void startActivity(Class<?> cls) {
        startActivity(new Intent(this, cls));
    }
}
