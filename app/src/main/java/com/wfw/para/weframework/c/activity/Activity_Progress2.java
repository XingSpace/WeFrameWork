package com.wfw.para.weframework.c.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ProgressBar;
import android.widget.SeekBar;

import com.wfw.para.weframework.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhao on 2016/11/14.
 */

public class Activity_Progress2 extends AppCompatActivity {



    @BindView(R.id.progressBar11)
    ProgressBar progressBar11;
    @BindView(R.id.progressBar10)
    ProgressBar progressBar10;
    @BindView(R.id.progressBar12)
    ProgressBar progressBar12;
    @BindView(R.id.progressBar13)
    ProgressBar progressBar13;
    @BindView(R.id.progressBar14)
    ProgressBar progressBar14;
    @BindView(R.id.seekbar)
    SeekBar seekbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c_activity_progressbar2);
        ButterKnife.bind(this);
    }
}
