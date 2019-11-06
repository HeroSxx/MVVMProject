package com.example.networkmodule.util;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.provider.Settings;
import io.reactivex.functions.Consumer;

public class GPSUtil {
//    需要权限
//    <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
//<uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
//<uses-permission android:name="android.permission.ACCESS_WIFI_STATE" />
//<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE" />
//<uses-permission android:name="android.permission.CHANGE_WIFI_STATE" />
//<uses-permission android:name="android.permission.INTERNET" />

    /**
     * 先请求权限，然后在判断
     * 判断GPS是否开启，GPS或者AGPS开启一个就认为是开启的
     *
     * @param context
     * @return true 表示开启
     */

    public static final boolean isOPen(final Activity context) {
        LocationManager locationManager
                = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        // 通过GPS卫星定位，定位级别可以精确到街（通过24颗卫星定位，在室外和空旷的地方定位准确、速度快）
        boolean gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
//        // 通过WLAN或移动网络(3G/2G)确定的位置（也称作AGPS，辅助GPS定位。主要用于在室内或遮盖物（建筑群或茂密的深林等）密集的地方定位）
//        boolean network = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        if (gps) {
            return true;
        }
        return false;
    }
//    private int GPS_REQUEST_CODE = 10;
//    private void goToNearbySchool() {
//        RxPermissions rxPermissions = new RxPermissions(getActivity());
//        rxPermissions.request(Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION).subscribe(
//                new Consumer<Boolean>() {
//                    @Override
//                    public void accept(Boolean aBoolean) throws Exception {
//                        if (aBoolean) {
//                            if (GPSUtil.isOPen(getActivity())) {
//                                toastUtils.showShort("GPS已开启");
//                                StartActivityUtil.startNearbySchoolActivity(getActivity());
//                            } else {
//                                toastUtils.showShort("GPS未开启，无法定位");
//                                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
//                                startActivityForResult(intent, GPS_REQUEST_CODE);
//                            }
//                        } else {
//                            toastUtils.showShort("未获取位置权限，无法显示附近校区");
//                        }
//                    }
//                }
//        );
//    }
//@Override
//public void onActivityResult(int requestCode, int resultCode, Intent data) {
//    super.onActivityResult(requestCode, resultCode, data);
//    if (requestCode == GPS_REQUEST_CODE) {
//        if (GPSUtil.isOPen(getActivity())) {
//            toastUtils.showShort("GPS已开启");
//            StartActivityUtil.startNearbySchoolActivity(getActivity());
//        } else {
//            toastUtils.showShort("GPS未开启，无法定位");
//        }
//    }
//}

}
