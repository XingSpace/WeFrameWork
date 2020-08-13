package com.wfw.para.weframework.c.desktop;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.RemoteViews;

import com.wfw.para.weframework.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Implementation of App Widget functionality.
 */
public class C_LedClock extends AppWidgetProvider {
    private Timer timer = new Timer();
    private AppWidgetManager appWidgetManager;
    private Context context;
    private final int myNum = 0x123;

    private int[] digits = new int[]{
            R.drawable.c_n0,
            R.drawable.c_n1,
            R.drawable.c_n2,
            R.drawable.c_n3,
            R.drawable.c_n4,
            R.drawable.c_n5,
            R.drawable.c_n6,
            R.drawable.c_n7,
            R.drawable.c_n8,
            R.drawable.c_n9
    };
    private int[] digitsViews = new int[]{
            R.id.im_1,
            R.id.im_2,
            R.id.im_3,
            R.id.im_4,
            R.id.im_5,
            R.id.im_6,
    };
    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        this.appWidgetManager = appWidgetManager;
        this.context = context;
        Log.e("AAA", "update");
        //定义计时器
        timer = new Timer();
        //启动 周期性调度
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.sendEmptyMessage(myNum);
            }
        }, 0, 1000);
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

            if (msg.what == myNum) {
                RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.c_led_clock);
                //定义SimpleDateFormat对象
                SimpleDateFormat format = new SimpleDateFormat("HHmmss");
                //将时间转为String
                String timeNow = format.format(new Date());
                for (int i = 0; i < timeNow.length(); i++) {
                    //将第一个数字字符串转换为数字
                    int num = timeNow.charAt(i) - 48;
                    //将i图片设置为相应的
                    views.setImageViewResource(digitsViews[i], digits[num]);
                }
                //将AppWidgetProvider的子类包装成ComponentName对象
                ComponentName componentName = new ComponentName(context, C_LedClock.class);
                appWidgetManager.updateAppWidget(componentName, views);
            }
            super.handleMessage(msg);
        }
    };

    @Override
    public void onEnabled(Context context) {
        // Enter relevant functionality for when the first widget is created
    }

    @Override
    public void onDisabled(Context context) {
        // Enter relevant functionality for when the last widget is disabled
    }
}

