package com.wfw.para.weframework;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;

import com.wfw.para.weframework.a.ParaActivity;
import com.wfw.para.weframework.b.BActivity;
import com.wfw.para.weframework.c.CActivity;
import com.wfw.para.weframework.d.DActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.btna)
    Button btna;
    @BindView(R.id.btnb)
    Button btnb;
    @BindView(R.id.btnc)
    Button btnc;
    @BindView(R.id.btnd)
    Button btnd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btna)
    void btna() {
        startActivity(new Intent(this, ParaActivity.class));
    }

    @OnClick(R.id.btnb)
    void btnb() {
        startActivity(new Intent(this, BActivity.class));
    }

    @OnClick(R.id.btnc)
    void btnc() {
        startActivity(new Intent(this, CActivity.class));
    }

    @OnClick(R.id.btnd)
    void btnd() {
        startActivity(new Intent(this, DActivity.class));
    }
}
