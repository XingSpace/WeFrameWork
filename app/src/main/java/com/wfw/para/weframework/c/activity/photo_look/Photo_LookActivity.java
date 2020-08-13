package com.wfw.para.weframework.c.activity.photo_look;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.wfw.para.weframework.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Photo_LookActivity extends FragmentActivity {
    @BindView(R.id.im_ed)
    ImageView imEd;
    @BindView(R.id.textView1)
    TextView textView1;
    @BindView(R.id.ll_back)
    LinearLayout llBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.viewpager)
    ViewPager viewpager;

    /**
     * 图片空间 组
     */
    private ImageView[] ims;

    private String pics;

    //    private List<SimpleDraweeView> IList;
    private List<String> pList;

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.c_activity_photo_look2);
        ButterKnife.bind(this);

        listeners();

        intent = getIntent();

        pList = new ArrayList<String>();
        pList = intent.getStringArrayListExtra("pics");

        PictureSlidePagerAdapter mPagerAdapter = new PictureSlidePagerAdapter(getSupportFragmentManager());

        viewpager.setAdapter(mPagerAdapter);


    }


    void listeners() {

        llBack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                pics = "";
                // 可能图片删除了数据需要修改
                for (int i = 0; i < pList.size(); i++) {
                    pics += pList.get(i) + ",";
                }
                intent = new Intent();
                intent.putExtra("pics", pics);
                setResult(RESULT_OK, intent);
                finish();

            }
        });

        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

//    Bitmap getBitmap(String path) {
//        Bitmap bitmap = (Bitmap) BitmapFactory.decodeFile(path);
//        try {
//            ExifInterface exifInterface;
//            exifInterface = new ExifInterface(path);
//            int result = exifInterface.getAttributeInt(
//                    ExifInterface.TAG_ORIENTATION,
//                    ExifInterface.ORIENTATION_UNDEFINED);
//            int rotate = 0;
//            switch (result) {
//                case ExifInterface.ORIENTATION_ROTATE_90:
//                    rotate = 90;
//                    break;
//                case ExifInterface.ORIENTATION_ROTATE_180:
//                    rotate = 180;
//                    break;
//                case ExifInterface.ORIENTATION_ROTATE_270:
//                    rotate = 270;
//                    break;
//                default:
//                    break;
//            }
//            if (rotate > 0) {
//                Matrix matrix = new Matrix();
//                matrix.setRotate(rotate);
//                Bitmap rotateBitmap = Bitmap.createBitmap(bitmap, 0, 0,
//                        bitmap.getWidth(), bitmap.getHeight(), matrix, true);
//                if (rotateBitmap != null) {
//                    bitmap.recycle();
//                    bitmap = rotateBitmap;
//                }
//            }
//        } catch (IOException e) {
//
//            e.printStackTrace();
//        }
//        return bitmap;
//    }


//    class MyPageAdapter extends PagerAdapter {
//
//        @Override
//        public int getCount() {
//            return pList.size();
//        }
//
//        public MyPageAdapter() {
//            super();
//        }
//
//        @Override
//        public void destroyItem(View container, int position, Object object) {
//            ((ViewPager) container).removeView((View) object);
//        }
//
//        @Override
//        public void destroyItem(ViewGroup container, int position, Object object) {
//            super.destroyItem(container, position, object);
//            container.removeView((View) object);
//        }
//
//        @Override
//        public Object instantiateItem(ViewGroup container, final int position) { // 这个方法用来实例化页卡
//            View view = getLayoutInflater().inflate(R.layout.view_photo, null);
//            SimpleDraweeView im = (SimpleDraweeView) view.findViewById(R.id.im);
//            FrameLayout fl_back = (FrameLayout) view.findViewById(R.id.fl_back);
//            String a = pList.get(position);
//            // File file = new File(a);
//            // if (file.exists()) {
//
//            // im.setImageBitmap(getBitmap(pList.get(position)));
//            float h = MyWindowUtil.getHeight(Photo_LookActivity.this);
//            float w = MyWindowUtil.getWidth(Photo_LookActivity.this);
//            float h1 = h - MyWindowUtil.getTop(Photo_LookActivity.this) - DensityUtils.dp2px(Photo_LookActivity.this, 50f);
//            float w1 = w / h * h1;
//            float height = getBitmapSize(a).getH();
//            float width = getBitmapSize(a).getW();
//            float height1 = 0;
//            float width1 = 0;
//            if ((height / width * w1) > h1) {
//                height1 = height;
//                width1 = width / height * height1;
//            } else {
//                width1 = width;
//                height1 = height / width * width1;
//            }
//
//            Log.e("size", "(" + width1 + "," + height1 + ")");
//            ViewGroup.LayoutParams p1 = im.getLayoutParams();
//            ViewGroup.LayoutParams p2 = fl_back.getLayoutParams();
//
//            p1.width = (int) width1;
//            p1.height = (int) height1;
//            p2.width = (int) w1;
//            p2.height = (int) h1;
//
//            im.setLayoutParams(p1);
//            fl_back.setLayoutParams(p2);
//            try {
//                int ww = im.getLayoutParams().width;
//                int hh = im.getLayoutParams().height;
//                Log.e("size", "(" + ww + "," + hh + ")");
//            } catch (Exception e) {
//
//            }
//            im.setImageURI(Uri.fromFile(new File(a)));
//
//            container.addView(im);
//
//
//            // 添加页卡
//            im.setOnLongClickListener(new View.OnLongClickListener() {
//
//                @Override
//                public boolean onLongClick(View v) {
//                    new AlertDialog.Builder(Photo_LookActivity.this)
//                            .setTitle("警告")
//                            .setMessage("确认删除次图片?")
//                            .setNegativeButton("确定",
//                                    new DialogInterface.OnClickListener() {
//                                        @Override
//                                        public void onClick(
//                                                DialogInterface dialog,
//                                                int which) {
//                                            pList.remove(position);
//                                            notifyDataSetChanged();
//                                            viewPager
//                                                    .setAdapter(new MyPageAdapter());
//                                        }
//                                    })
//                            .setPositiveButton("取消",
//                                    new DialogInterface.OnClickListener() {
//
//                                        @Override
//                                        public void onClick(
//                                                DialogInterface dialog,
//                                                int which) {
//                                            return;
//                                        }
//                                    }).show();
//                    return true;
//                }
//            });
//            return im;
//        }
//
//        @Override
//        public boolean isViewFromObject(View arg0, Object arg1) {
//            return arg0 == arg1;
//        }
//
//    }
//
//    BitmapSize getBitmapSize(String path) {
//        BitmapFactory.Options opts = new BitmapFactory.Options();
//        opts.inJustDecodeBounds = true;
//        BitmapFactory.decodeFile(path, opts);
//        opts.inSampleSize = 1;
//        opts.inJustDecodeBounds = false;
//        Bitmap mBitmap = BitmapFactory.decodeFile(path, opts);
//        int w = opts.outWidth;
//        int h = opts.outHeight;
//        mBitmap = null;
//        return new BitmapSize(w, h);
//    }
//
//    class BitmapSize {
//        int w;
//        int h;
//
//        public BitmapSize(int w, int h) {
//            this.w = w;
//            this.h = h;
//        }
//
//        public int getH() {
//            return h;
//        }
//
//        public void setH(int h) {
//            this.h = h;
//        }
//
//        public int getW() {
//            return w;
//        }
//
//        public void setW(int w) {
//            this.w = w;
//        }
//    }
//

    private class PictureSlidePagerAdapter extends FragmentStatePagerAdapter {
        @Override
        public Object instantiateItem(ViewGroup container, final int position) {

            // 添加页卡
            container.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View v) {
                    new AlertDialog.Builder(Photo_LookActivity.this)
                            .setTitle("警告")
                            .setMessage("确认删除次图片?")
                            .setNegativeButton("确定",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(
                                                DialogInterface dialog,
                                                int which) {
                                            pList.remove(position);
                                            notifyDataSetChanged();

                                        }
                                    })
                            .setPositiveButton("取消",
                                    new DialogInterface.OnClickListener() {

                                        @Override
                                        public void onClick(
                                                DialogInterface dialog,
                                                int which) {
                                            return;
                                        }
                                    }).show();
                    return true;
                }
            });
            return super.instantiateItem(container, position);
        }

        public PictureSlidePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int arg0) {
            PictureSlideFragment ft = PictureSlideFragment.newInstance(arg0);
            String a = pList.get(arg0);
            Bundle bundle = new Bundle();
            bundle.putString("pic", a);
            bundle.putInt("num", arg0 + 1);
            ft.setArguments(bundle);

            return ft;
        }

        @Override
        public int getCount() {
            return pList.size();
        }
    }


}
