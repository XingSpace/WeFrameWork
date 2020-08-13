package com.wfw.para.weframework.c.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.wfw.para.weframework.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhao on 2016/11/14.
 */

public class Activity_Progress extends AppCompatActivity {
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.progressBar2)
    ProgressBar progressBar2;
    @BindView(R.id.progressBar3)
    ProgressBar progressBar3;
    @BindView(R.id.progressBar4)
    ProgressBar progressBar4;
    @BindView(R.id.progressBar5)
    ProgressBar progressBar5;
    @BindView(R.id.progressBar6)
    ProgressBar progressBar6;
    @BindView(R.id.progressBar7)
    ProgressBar progressBar7;
    @BindView(R.id.progressBar8)
    ProgressBar progressBar8;
    @BindView(R.id.progressBar9)
    ProgressBar progressBar9;
    @BindView(R.id.progressBar10)
    ProgressBar progressBar10;
    @BindView(R.id.progressBar11)
    ProgressBar progressBar11;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c_activity_progressbar);
        ButterKnife.bind(this);
    }
}
