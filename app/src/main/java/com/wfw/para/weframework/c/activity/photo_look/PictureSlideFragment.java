package com.wfw.para.weframework.c.activity.photo_look;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wfw.para.weframework.R;
import com.wfw.para.weframework.c.util.MyWindowUtil;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by zhao on 2016/11/17.
 */

public class PictureSlideFragment extends Fragment {
    @BindView(R.id.im)
    SimpleDraweeView im;
    @BindView(R.id.fl_back)
    FrameLayout flBack;
    @BindView(R.id.tv_num)
    TextView tvNum;

    private int mIndex;
    private String pic;
    private int num;

    private float h;
    private float w;

    private float height;
    private float width;
    private float height1 = 0;
    private float width1 = 0;
    private int i = 1;


    private boolean ifbig = false;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.c_view_photo, container, false);

        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        pic = bundle.getString("pic");
        num = bundle.getInt("num", 1);
        tvNum.setText(num + "");


        h = MyWindowUtil.getHeight(getActivity());
        w = MyWindowUtil.getWidth(getActivity());

        height = getBitmapSize(pic).getH();
        width = getBitmapSize(pic).getW();
        height1 = 0;
        width1 = 0;

        // im.setImageBitmap(getBitmap(pList.get(position)));
        ifbig = false;
        if ((height / width * w) > h) {
            height1 = height;
            width1 = width / height * height1;
        } else {
            width1 = width;
            height1 = height / width * width1;
        }

        Log.e("size", "(" + width1 + "," + height1 + ")");
        ViewGroup.LayoutParams p1 = im.getLayoutParams();
        p1.width = (int) width1;
        p1.height = (int) height1;
        im.setLayoutParams(p1);
        im.setImageURI(Uri.fromFile(new File(pic)));

        im.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //实现双击 切换效果
                i++;
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1500);
                            i = 1;
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                if (i > 2) {
                    if (ifbig) {
                        ifbig = false;
                        if ((height / width * w) > h) {
                            height1 = height;
                            width1 = width / height * height1;
                        } else {
                            width1 = width;
                            height1 = height / width * width1;
                        }

                        Log.e("size", "(" + width1 + "," + height1 + ")");
                        ViewGroup.LayoutParams p1 = im.getLayoutParams();
                        p1.width = (int) width1;
                        p1.height = (int) height1;
                        im.setLayoutParams(p1);
                        im.setImageURI(Uri.fromFile(new File(pic)));
                        i = 1;
                    } else if (!ifbig) {
                        ifbig = true;
                        if ((height / width * w > h)) {
                            height1 = h;
                            width1 = width / height * height1;
                        } else {
                            width1 = w;
                            height1 = height / width * width1;
                        }

                        //  Log.e("size", "(" + width1 + "," + height1 + ")");
                        ViewGroup.LayoutParams p1 = im.getLayoutParams();
                        p1.width = (int) width1;
                        p1.height = (int) height1;
                        im.setLayoutParams(p1);
                        im.setImageURI(Uri.fromFile(new File(pic)));
                        i = 1;
                    }
                }


            }
        });


    }

    BitmapSize getBitmapSize(String path) {
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, opts);
        opts.inSampleSize = 1;
        opts.inJustDecodeBounds = false;
        Bitmap mBitmap = BitmapFactory.decodeFile(path, opts);
        int w = opts.outWidth;
        int h = opts.outHeight;
        mBitmap = null;
        return new BitmapSize(w, h);
    }

    class BitmapSize {
        int w;
        int h;

        public BitmapSize(int w, int h) {
            this.w = w;
            this.h = h;
        }

        public int getH() {
            return h;
        }

        public void setH(int h) {
            this.h = h;
        }

        public int getW() {
            return w;
        }

        public void setW(int w) {
            this.w = w;
        }
    }

    public static PictureSlideFragment newInstance(int index) {
        PictureSlideFragment f = new PictureSlideFragment();

        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);

        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        mIndex = getArguments() != null ? getArguments().getInt("index") : 1;

    }


}
