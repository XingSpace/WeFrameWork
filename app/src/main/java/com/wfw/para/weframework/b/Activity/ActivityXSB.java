package com.wfw.para.weframework.b.Activity;

import android.os.Bundle;
import android.widget.TextView;

import com.wfw.para.weframework.R;
import com.wfw.para.weframework.b.View.XSeekBar;

/**
 * Created by wangxing on 16/11/23.
 */

public class ActivityXSB extends ActivityBase implements XSeekBar.OnXSeekBarListener{

    private XSeekBar xSeekBar1,xSeekBar2;

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b_activity_wsb);
    }

    @Override
    public void findViews() {
        xSeekBar1 = (XSeekBar) findViewById(R.id.xsb1);
        xSeekBar2 = (XSeekBar) findViewById(R.id.xsb2);
        textView = (TextView) findViewById(R.id.text);
    }

    @Override
    public void init() {
        xSeekBar1.setOnSeekBarListener(this);
        xSeekBar2.setOnSeekBarListener(this);
    }

    @Override
    public void onProgressChange(XSeekBar xSeekBar, int progress, boolean isUser) {
        textView.setText(progress+"");
    }
}
