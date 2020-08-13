package com.wfw.para.weframework.b;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import com.wfw.para.weframework.R;
import com.wfw.para.weframework.b.Activity.ActivityHP;
import com.wfw.para.weframework.b.Activity.ActivityMTV;
import com.wfw.para.weframework.b.Activity.ActivitySD;
import com.wfw.para.weframework.b.Activity.ActivityXSB;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/11/11.
 */

public class BActivity extends Activity {

    @BindView(R.id.button6)
    Button button;

    @BindView(R.id.button7)
    Button button1;

    @BindView(R.id.button8)
    Button button2;

    @BindView(R.id.button9)
    Button button3;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b_activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button6)
    void hpOnClick(){
        startActivity(new Intent(this,ActivityHP.class));
    }

    @OnClick(R.id.button7)
    void sdOnClick(){
        startActivity(new Intent(this, ActivitySD.class));
    }

    @OnClick(R.id.button8)
    void mtvOnClick() {
        startActivity(new Intent(this, ActivityMTV.class));
    }

    @OnClick(R.id.button9)
    void wsbOnClick(){
        startActivity(new Intent(this, ActivityXSB.class));
    }
}
