package com.wfw.para.weframework.a.activity;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import com.wfw.para.weframework.R;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/11/13.
 */

public class AppBarOneActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_activity_appbarlayout_1);
        ButterKnife.bind(this);
    }
}
