package com.wfw.para.weframework.b.Activity;

import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;

import com.wfw.para.weframework.R;
import com.wfw.para.weframework.b.View.MarqueeTextView;

/**
 * Created by wangxing on 16/11/19.
 */

public class ActivityMTV extends ActivityBase implements View.OnClickListener,SeekBar.OnSeekBarChangeListener{

    private MarqueeTextView mtv;

    private EditText editText;

    private Button button,red,blue,white,black;

    private SeekBar seekBar,seekBar1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.b_activity_mtv);
    }

    @Override
    public void findViews() {
        mtv = (MarqueeTextView)findViewById(R.id.mtv);
        editText = (EditText)findViewById(R.id.edit);
        button = (Button)findViewById(R.id.button);
        red = (Button) findViewById(R.id.red);
        blue = (Button) findViewById(R.id.blue);
        white = (Button) findViewById(R.id.white);
        black = (Button) findViewById(R.id.black);
        seekBar = (SeekBar) findViewById(R.id.seekbar);
        seekBar1 = (SeekBar) findViewById(R.id.seekbar1);
        red.setOnClickListener(this);
        blue.setOnClickListener(this);
        white.setOnClickListener(this);
        black.setOnClickListener(this);
        button.setOnClickListener(this);
        seekBar.setOnSeekBarChangeListener(this);
        seekBar1.setOnSeekBarChangeListener(this);
    }

    @Override
    public void init() {
        setTitle("测试跑马灯");
        seekBar.setMax(255);
        seekBar.setProgress(90);
        seekBar1.setMax(10);
        seekBar1.setProgress(4);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.button:
                mtv.setText(editText.getText().toString());
                break;

            case R.id.red:
                mtv.setTextColor(Color.RED);
                break;

            case R.id.blue:
                mtv.setTextColor(Color.BLUE);
                break;

            case R.id.black:
                mtv.setTextColor(Color.BLACK);
                break;

            case R.id.white:
                mtv.setTextColor(Color.WHITE);
                break;
        }
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        switch (seekBar.getId()){
            case R.id.seekbar:
                mtv.setPaddingAlpha(progress);
                break;

            case R.id.seekbar1:
                mtv.setTextSize(10+3*progress);
                break;
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
