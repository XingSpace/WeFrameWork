package com.wfw.para.weframework.c;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.wfw.para.weframework.R;
import com.wfw.para.weframework.c.activity.Activity_Navigation;
import com.wfw.para.weframework.c.activity.Activity_Progress;
import com.wfw.para.weframework.c.activity.Activity_Progress2;
import com.wfw.para.weframework.c.activity.zxing.Activity_QRTest;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2016/11/11.
 */

public class CActivity extends Activity {


    @BindView(R.id.button1)
    Button button1;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.button3)
    Button button3;
    @BindView(R.id.button4)
    Button button4;
    @BindView(R.id.button5)
    Button button5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c_activity_main);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button1:
                startActivity(Activity_Progress.class);
                break;
            case R.id.button2:
                startActivity(Activity_Progress2.class);
                break;
            case R.id.button3:
                startActivity(Activity_QRTest.class);
                break;
            case R.id.button4:
                startActivity(Activity_Navigation.class);
                break;
            case R.id.button5:
                break;
        }
    }

    private void startActivity(Class<?> cls) {
        startActivity(new Intent(this, cls));
    }
}
