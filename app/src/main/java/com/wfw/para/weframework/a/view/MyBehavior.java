package com.wfw.para.weframework.a.view;

import android.content.Context;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

/**
 * Package com.hc.studycoordinatelayout
 * Created by HuaChao on 2016/6/1.
 */
public class MyBehavior extends CoordinatorLayout.Behavior<Button> {
    private int width;

    public MyBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
        DisplayMetrics display = context.getResources().getDisplayMetrics();
        width = display.widthPixels;
    }

    /**
     * 根据逻辑判断rs的取值
     * 返回false表示child不依赖dependency，ture表示依赖
     */
    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, Button child, View dependency) {
//        如果dependency是TempView的实例，说明它就是我们所需要的Dependency
        return dependency instanceof TempView;
    }


    /**
     * 每次dependency位置发生变化，都会执行onDependentViewChanged方法
     * 当dependency发生改变时（位置、宽高等），执行这个函数
     * 返回true表示child的位置或者是宽高要发生改变，否则就返回false
     */
    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, Button btn, View dependency) {

        //根据dependency的位置，设置Button的位置
        int top = dependency.getTop();
        int left = dependency.getLeft();

        int x = width - left - btn.getWidth();
        int y = top;

        setPosition(btn, x, y);
        return true;
    }

    private void setPosition(View v, int x, int y) {
        CoordinatorLayout.MarginLayoutParams layoutParams = (CoordinatorLayout.MarginLayoutParams) v.getLayoutParams();
        layoutParams.leftMargin = x;
        layoutParams.topMargin = y;
        v.setLayoutParams(layoutParams);
    }


}
