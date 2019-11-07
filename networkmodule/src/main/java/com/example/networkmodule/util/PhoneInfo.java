package com.example.networkmodule.util;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.telephony.TelephonyManager;

import static com.example.networkmodule.util.StringUtils.TODO;


public class PhoneInfo {
    private TelephonyManager telephonemanager;
    private String IMSI;
    private Context ctx;

    /**
     * 获取手机国际识别码IMEI
     */
    public PhoneInfo(Context context) {
        ctx = context;
        telephonemanager = (TelephonyManager) context
                .getSystemService(Context.TELEPHONY_SERVICE);
    }

    /**
     * 获取手机号码
     */
    public String getNativePhoneNumber() {

        String nativephonenumber = null;
        if (ActivityCompat.checkSelfPermission(ctx, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(ctx, Manifest.permission.READ_PHONE_NUMBERS) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(ctx, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return "";
        }
        nativephonenumber = telephonemanager.getLine1Number();

        return nativephonenumber;
    }

    /**
     * 获取手机服务商信息
     */
    public String getProvidersName() {
        String providerName = null;
        try {
            if (ActivityCompat.checkSelfPermission(ctx, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return "";
            }
            IMSI = telephonemanager.getSubscriberId();
            //IMSI前面三位460是国家号码，其次的两位是运营商代号，00、02是中国移动，01是联通，03是电信。
            System.out.print("IMSI是：" + IMSI);
            if (IMSI.startsWith("46000") || IMSI.startsWith("46002")) {
                providerName = "中国移动";
            } else if (IMSI.startsWith("46001")) {
                providerName = "中国联通";
            } else if (IMSI.startsWith("46003")) {
                providerName = "中国电信";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return providerName;

    }

    /**
     * 获取手机信息
     */
    public String getPhoneInfo() {

        TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Context.TELEPHONY_SERVICE);
        StringBuilder sb = new StringBuilder();

        if (ActivityCompat.checkSelfPermission(ctx, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return TODO;
        }
        sb.append("\nDeviceID(IMEI)" + tm.getDeviceId());
        sb.append("\nDeviceSoftwareVersion:" + tm.getDeviceSoftwareVersion());
        sb.append("\ngetLine1Number:" + tm.getLine1Number());
        sb.append("\nNetworkCountryIso:" + tm.getNetworkCountryIso());
        sb.append("\nNetworkOperator:" + tm.getNetworkOperator());
        sb.append("\nNetworkOperatorName:" + tm.getNetworkOperatorName());
        sb.append("\nNetworkType:" + tm.getNetworkType());
        sb.append("\nPhoneType:" + tm.getPhoneType());
        sb.append("\nSimCountryIso:" + tm.getSimCountryIso());
        sb.append("\nSimOperator:" + tm.getSimOperator());
        sb.append("\nSimOperatorName:" + tm.getSimOperatorName());
        sb.append("\nSimSerialNumber:" + tm.getSimSerialNumber());
        sb.append("\ngetSimState:" + tm.getSimState());
        sb.append("\nSubscriberId:" + tm.getSubscriberId());
        sb.append("\nVoiceMailNumber:" + tm.getVoiceMailNumber());

        return sb.toString();

    }
}