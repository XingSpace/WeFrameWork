package com.wfw.para.weframework.c.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串分析,获取工具类
 */
public class StringUtil {
    public static int fragment = 0;

    /**
     * 邮箱验证码判断
     *
     * @param email
     * @return
     */
    public static boolean ifEmail(String email) {
        Pattern pattern = Pattern
                .compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    /**
     * 手机号判断
     *
     * @author zhao
     */
    public static boolean isMobile(String mobile) {
        Pattern p = Pattern.compile("^1(3|4|5|7|8)\\d{9}$");
        Matcher m = p.matcher(mobile);

        return m.matches();
    }

    /**
     * 判断接受字符串是否为空的
     *
     * @param src
     * @return
     */
    public static boolean ifNull(String src) {
        if (null != src && !"null".equals(src) && src.length() > 0) {
            return false;
        }

        return true;
    }

    /**
     * 获取当前时间
     */
    public static String getTime() {
        String time = null;
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss",
                Locale.getDefault());
        time = format.format(date);
        return time;
    }

    /**
     * 获取指定格式的时间
     */
    public static String getTime(String formate) {
        String time = null;
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat(formate,
                Locale.getDefault());
        time = format.format(date);
        return time;
    }


    /**
     * 产生6位随机数
     *
     * @return
     */
    public static String getCheckCode() {
        String s = "000000";
        try {
            int x1 = (int) (Math.random() * 10);
            int x2 = (int) (Math.random() * 10);
            int x3 = (int) (Math.random() * 10);
            int x4 = (int) (Math.random() * 10);
            int x5 = (int) (Math.random() * 10);
            int x6 = (int) (Math.random() * 10);
            s = x1 + "" + x2 + "" + x3 + "" + x4 + "" + x5 + "" + x6;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return s;
    }

    /**
     * 保留两位小数
     *
     * @param num 数字
     * @return 两位小数的数字
     */
    public static String get2pointNum(double num) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#0.00");
        return df.format(num);
    }

    /**
     * 获取Android 手机唯一标识  组合法
     */
    public static String getMobileId(Context context) {
        String IMEI = getMobileIMEI(context);
        String MAC = getMobileWLANMAC(context);
        Log.e("Strings", "IMEI=：" + IMEI + "+MAC=：" + MAC);
        return null;
    }

    /**
     * 获取 IMEI
     * 权限 android.permission.READ_PHONE_STATE
     *
     * @param context 上下文
     * @return IMEI
     */
    public static String getMobileIMEI(Context context) {
        TelephonyManager TelephonyMgr = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return TelephonyMgr.getDeviceId();
    }

    /**
     * 获取 WLAM Mac
     * 权限 android.permission.ACCESS_WIFI_STATE
     *
     * @param context 上下文
     * @return WLAM Mac
     */
    public static String getMobileWLANMAC(Context context) {
        WifiManager wm = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        return wm.getConnectionInfo().getMacAddress();
    }

    /**
     * 获取版本号
     * @return 当前应用的版本号
     */
    public static String getVersion(Context context ) {
        try {
            PackageManager manager = context.getPackageManager();
            PackageInfo info = manager.getPackageInfo(context.getPackageName(), 0);
            String version = info.versionName;
            int code = info.versionCode;
            return ""+ version;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }


}
