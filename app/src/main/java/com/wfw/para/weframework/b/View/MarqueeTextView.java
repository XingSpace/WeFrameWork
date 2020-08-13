package com.wfw.para.weframework.b.View;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import androidx.core.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.wfw.para.weframework.R;

/**
 * Created by wangxing on 16/11/19.
 * 跑马灯效果的TextView。。。暂时还不能应用实战(还欠缺setXXX等等方法
 * 但已经初步测试完毕。。。计划于2016-11-22前上线
 */

public class MarqueeTextView extends View {

    private AttributeSet attributeSet;

    private Paint mPaint, Ppaint;

    private Rect rect = new Rect();//本文本域的一个矩形对象

    //以下是设置文本的参照值
    private int mSize;//字体大小
    private String mText;//要显示的文本
    private int mColor;//文本颜色
    private int mPColor;//padding边距的颜色,默认是白色
    private int mPAlpha;//padding边距的透明度,默认不透明
    private int mWidth,mHeight;//整个文本框的长宽
    private float mTextX = 0;//被绘制文本的X轴起始位
    private float mSpeed;//文字滚动的速度,默认为1,数值越大滚动越快

    //以下三个为默认参数(如果没有输入任何数据
    private int DEFAULT_SIZE = 16;
    private String DEFAULT_TEXT = "";
    private int DEFAULT_COLOR = ContextCompat.getColor(getContext(),R.color.black);
    private int DEFAULT_COLOR_WHITE = ContextCompat.getColor(getContext(),R.color.white);

    private Handler handler;
    private Runnable runnable;

    public MarqueeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.attributeSet = attrs;
        init();
    }

    public MarqueeTextView(Context context) {
        super(context);
        init();
    }

    public void init(){
        if(attributeSet!=null){
            TypedArray typedArray = getContext().obtainStyledAttributes(attributeSet, R.styleable.MarqueeTextView);

            mSize = (int) typedArray.getDimension(R.styleable.MarqueeTextView_textSize,sp2px(DEFAULT_SIZE));

            mText = TextUtils.isEmpty(typedArray.getString(R.styleable.MarqueeTextView_text)) ? DEFAULT_TEXT : typedArray.getString(R.styleable.MarqueeTextView_text);
            //如果这里没有在xml中配置text属性,那么这里就使用默认属性

            mColor = typedArray.getColor(R.styleable.MarqueeTextView_textColor,DEFAULT_COLOR);

            mPColor = typedArray.getColor(R.styleable.MarqueeTextView_paddingColor,DEFAULT_COLOR_WHITE);

            mPAlpha = typedArray.getInteger(R.styleable.MarqueeTextView_paddingAlpha,0);

            mSpeed = typedArray.getFloat(R.styleable.MarqueeTextView_speed,1f);

            mPaint = new Paint();
            mPaint.setAntiAlias(true);
            mPaint.setColor(mColor);
            mPaint.setTextSize(mSize);
            mPaint.getTextBounds(mText,0, mText.length(),rect);//这里是第一次刷新rect的数据
//            float[] f = new float[mText.length()];
//            mPaint.getTextWidths(mText,f);
//
//            for (float temp:f){
//                mWidth += (int)Math.ceil(temp);
//                //将每个字符所需的宽度加起来。。。就是整个字符串的长度
//                //Math.ceil将小数部分进一
//            }
//            mHeight = rect.height();
//            //楼上部分将需要绘制的字符串长宽都测量出来


            handler = new Handler();

            runnable = new Runnable(){
                @Override
                public void run() {
                    moveText();
                    handler.postDelayed(this,3);
                }
            };
            handler.postDelayed(runnable,1);//在测量好宽高才开启线程
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        onDrawText(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 这里要计算一下控件的实际大小，然后调用setMeasuredDimension来设置
        mWidth = this.getMeasuredSize(widthMeasureSpec, true);
        mHeight = this.getMeasuredSize(rect.height(), false);//这里把文本域的高度设置为本View的最终高度
        setMeasuredDimension(mWidth,mHeight);
    }

    /**
     * 在关联的activity关闭之后,相应的移除线程,以免占用cpu
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        handler.removeCallbacks(runnable);//结束线程
    }

    /**
     * 每被线程执行一次,对应的更新数据
     */
    private synchronized void moveText(){
        if (mTextX > -rect.width()){
            mTextX-=mSpeed;//每一次移动一个单位距离
        }else {
            mTextX = mWidth-1-getPaddingRight();//减掉getPaddingRight是为了适配padding的边距属性
        }
        requestLayout();
        invalidate();
    }

    private void onDrawText(Canvas canvas){
        mPaint.setColor(mColor);
        mPaint.setTextSize(mSize);
        mPaint.getTextBounds(mText,0, mText.length(),rect);
        //这里是在每一次绘制之前刷新。。。(防止再跑马灯效果中,字符串发生改变,造成跑灯效果错乱
        //楼下部分则开始绘制
        canvas.drawText(mText,mTextX + getPaddingLeft(), Math.abs(rect.top) + getPaddingTop(),mPaint);
        //本句的第三个参数尚不明确,填这个参数就能使字符串居中

        //以下为了解决padding参数造成的边框效果
        Ppaint = new Paint();
        Ppaint.setColor(mPColor);
        Ppaint.setAlpha(mPAlpha);
        canvas.drawRect(0,0,mWidth,getPaddingTop(), Ppaint);
        canvas.drawRect(0,getPaddingTop(),getPaddingLeft(),mHeight-getPaddingBottom(), Ppaint);
        canvas.drawRect(mWidth-getPaddingRight(),getPaddingTop(),mWidth,mHeight-getPaddingBottom(), Ppaint);
        canvas.drawRect(0,mHeight-getPaddingBottom(),mWidth,mHeight, Ppaint);

    }

    /**
     * 每一次移动的距离,默认为1,传入的数值越大,滚动越快
     * @param speed
     */
    public void setSpeed(float speed){
        this.mSpeed = speed;
    }

    /**
     * 传入一个0~255之间的数,代表透明度,默认为0,表示完全透明
     * @param alpha
     */
    public void setPaddingAlpha(int alpha){
        this.mPAlpha = alpha;
    }

    /**
     * 设置padding边距的颜色,默认为白色
     * @param color
     */
    public void setPaddingColor(int color){
        this.mPColor = color;
    }

    /**
     * 设置文本颜色
     * @param color
     */
    public void setTextColor(int color){
        this.mColor = color;
    }

    /**
     * 改变字体大小(以sp为单位
     * @param size
     */
    public void setTextSize(int size){
        this.mSize = sp2px(size);
    }

    /**
     * 设置需要滚动的文本
     * @param text
     */
    public void setText(String text){
        this.mText = text;
    }

    /**
     * 将sp值转换为px值
     */
    private int sp2px(float spValue) {
        final float fontScale = getContext().getResources().getDisplayMetrics().scaledDensity;
        return (int) (spValue * fontScale + 0.5f);
    }

    /**
     * 计算控件的实际大小
     * @param length onMeasure方法的参数，widthMeasureSpec或者heightMeasureSpec
     * @param isWidth 是宽度还是高度
     * @return int 计算后的实际大小
     */
    private int getMeasuredSize(int length, boolean isWidth){
        // 模式
        int specMode = MeasureSpec.getMode(length);
        // 尺寸
        int specSize = MeasureSpec.getSize(length);
        // 计算所得的实际尺寸，要被返回
        int retSize = 0;
        // 得到两侧的padding（留边）
        int padding = (isWidth? getPaddingLeft()+getPaddingRight():getPaddingTop()+getPaddingBottom());


        // 对不同的指定模式进行判断
        if(specMode==MeasureSpec.EXACTLY){  // 显式指定大小，如40dp或fill_parent
            retSize = specSize;
        }else{                              // 如使用wrap_content
            retSize = (isWidth? rect.width()+padding : rect.height()+padding);
            if(specMode==MeasureSpec.AT_MOST){
                retSize = Math.min(retSize, specSize);
            }
        }

        return retSize;
    }

}
