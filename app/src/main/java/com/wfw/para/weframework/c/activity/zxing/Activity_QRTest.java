package com.wfw.para.weframework.c.activity.zxing;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.wfw.para.weframework.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 二维码 扫描测试
 * Created by zhao on 2016/12/17.
 */

public class Activity_QRTest extends AppCompatActivity {
    @BindView(R.id.im_QR)
    ImageView imQR;
    @BindView(R.id.btn_getQRCode)
    Button btnGetQRCode;
    @BindView(R.id.tv_code_info)
    TextView tvCodeInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.c_qr_test);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_getQRCode)
    public void onClick() {
        doGetPermission(1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (null != data) {
            /**
             * 处理二维码扫描结果
             */
            IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (scanResult != null) {
                String re = scanResult.getContents();
                String formatName = scanResult.getFormatName();
                tvCodeInfo.setText("扫描类型:" + formatName + "\n" + "扫描结果:" + re);
                String path = scanResult.getBarcodeImagePath();
                Bitmap bitmap = BitmapFactory.decodeFile(path);
                imQR.setImageBitmap(bitmap);

            }
        } else {

        }

    }

    void doGetPermission(int requestCode) {
        //6.0权限 动态请求
        // 1、获取手机相机：
        if (
                ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, requestCode);
        } else {
            IntentIntegrator integrator = new IntentIntegrator(Activity_QRTest.this);
            integrator.setCaptureActivity(CaptureActivityAnyOrientation.class);
            integrator.setOrientationLocked(false);
            integrator.setBarcodeImageEnabled(true);
            integrator.setPrompt("请将二维码置入框内");
            integrator.initiateScan();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        doGetPermission(requestCode);
    }
}
