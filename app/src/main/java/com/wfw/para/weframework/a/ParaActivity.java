package com.wfw.para.weframework.a;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.wfw.para.weframework.R;
import com.wfw.para.weframework.a.activity.AppBarActivity;
import com.wfw.para.weframework.a.activity.ButtonActivity;
import com.wfw.para.weframework.a.activity.CheckActivity;
import com.wfw.para.weframework.a.activity.CoordinatorLayoutActivity;
import com.wfw.para.weframework.a.activity.EditTextActivity;
import com.wfw.para.weframework.a.activity.SpinnerActivity;
import com.wfw.para.weframework.a.activity.ToolBarActivity;
import com.wfw.para.weframework.a.rxjava.RxJavaActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/11/11.
 */

public class ParaActivity extends Activity {

    @BindView(R.id.button)
    Button button;
    @BindView(R.id.radiobtn)
    Button radiobtn;
    @BindView(R.id.spinner)
    Button spinner;
    @BindView(R.id.edittext)
    Button edittext;
    @BindView(R.id.coordinatorlayout)
    Button coordinatorlayout;
    @BindView(R.id.toolbar)
    Button toolbar;
    @BindView(R.id.appBar1)
    Button appBar1;
    @BindView(R.id.rxjava)
    Button rxjava;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_activity_catalog);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button)
    void button() {
        startActivity(ButtonActivity.class);
    }

    @OnClick(R.id.radiobtn)
    void radiobtn() {
        startActivity(CheckActivity.class);
    }

    @OnClick(R.id.spinner)
    void spinner() {
        startActivity(SpinnerActivity.class);
    }

    @OnClick(R.id.edittext)
    void edittext() {
        startActivity(EditTextActivity.class);
    }

    @OnClick(R.id.coordinatorlayout)
    void coordinatorlayout() {
        startActivity(CoordinatorLayoutActivity.class);
    }

    @OnClick(R.id.toolbar)
    void toolbar() {
        startActivity(ToolBarActivity.class);
    }

    @OnClick(R.id.appBar1)
    void appbar1() {
        startActivity(AppBarActivity.class);
    }

    @OnClick(R.id.rxjava)
    void rxjava() {
        startActivity(RxJavaActivity.class);
    }


    private void startActivity(Class<?> cls) {
        startActivity(new Intent(this, cls));
    }
}
