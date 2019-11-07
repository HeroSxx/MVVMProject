package com.example.networkmodule.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;

import com.umeng.commonsdk.debug.W;

import java.io.File;
import java.net.URISyntaxException;

/**
 * Created by chenliu on 2016/10/18.<br/>
 * 描述：打开手机已安装地图相关工具类
 * </br>
 */
public class OpenLocalMapUtil {

    /**
     * 地图应用是否安装
     *
     * @return
     */
    public static boolean isGdMapInstalled(Context context) {
        return checkApkExist(context, "com.autonavi.minimap");
    }

    public static boolean isBaiduMapInstalled(Context context) {
        return checkApkExist(context, "com.baidu.BaiduMap");
    }

    //打开百度地图app
    public static void openBaiduMapNative(Context mContext, String dlat, String dlon, String desName) {
//        Intent naviIntent = new Intent();
//        naviIntent.setData(android.net.Uri.parse("baidumap://map/geocoder?location=" + latitude + "," + longitude));
//        mContext.startActivity(naviIntent);
        Intent i1 = new Intent();
        // 公交路线规划
        i1.setData(Uri.parse("baidumap://map/direction?destination=name:" + desName + "|latlng:" + dlat + "," + dlon + "&coord_type=bd09ll&sy=3&index=0&target=1&src=andr.baidu.openAPIdemo"));
        mContext.startActivity(i1);
    }

    //打开百度地图app
    public static void openBaiduMapNative(Context mContext, String address) {
        Intent naviIntent = new Intent();
        naviIntent.setData(Uri.parse("baidumap://map/geocoder?src=andr.baidu.openAPIdemo&address=" + address));
        mContext.startActivity(naviIntent);
    }

    /**
     * 打开百度网页版 导航
     *
     * @param activity
     * @param location 起点位置
     * @param content  目的地
     */
    public static void openBaiduBrowserNaviMap(Context activity, String longitude, String latitude, String content) {
        Uri webpage = Uri.parse("http://api.map.baidu.com/marker?location=" +
                latitude + "," + longitude +
                "&title=" + content +
                "&content=" + content +
                "&output=html");
//        http://api.map.baidu.com/direction?origin=latlng:34.264642646862,108.95108518068|name:我家&destination=大雁塔&mode=driving&region=西安&output=html&src=yourCompanyName|yourAppName
//调起百度PC或Web地图，展示"西安市"从（lat:34.264642646862,lng:108.95108518068 ）"我家"到"大雁塔"的驾车路线。
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
        activity.startActivity(webIntent);
    }

    /**
     * 打开百度网页版 导航
     *
     * @param activity
     * @param location 起点位置
     * @param content  目的地
     */
    public static void openBaiduBrowserNaviMap(Context activity, String slongitude, String slatitude, String dla, String dlo, String content) {
        Uri webpage = Uri.parse("http://api.map.baidu.com/marker?destination=" +
                slatitude + "," + slongitude + "&origin=" + dla + "," + dlo +
                "&title=" + content +
                "&content=" + content +
                "&output=html");
        Intent webIntent = new Intent(Intent.ACTION_VIEW, webpage);
        activity.startActivity(webIntent);
    }


    public static boolean checkApkExist(Context context, String packageName) {
        if (TextUtils.isEmpty(packageName))
            return false;
        try {
            ApplicationInfo info = context.getPackageManager()
                    .getApplicationInfo(packageName,
                            PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }

    /**
     * 获取打开百度地图应用uri [http://lbsyun.baidu.com/index.php?title=uri/api/android]
     *
     * @param originLat
     * @param originLon
     * @param desLat
     * @param desLon
     * @return
     */
    public static String getBaiduMapUri(String originLat, String originLon, String originName, String desLat, String desLon, String destination, String src) {
        String uri = "intent://map/direction?origin=latlng:%1$s,%2$s|name:%3$s" +
                "&destination=latlng:%4$s,%5$s|name:%6$s&mode=driving$s&src=%7$s#Intent;" +
                "scheme=bdapp;package=com.baidu.BaiduMap;end";

        return String.format(uri, originLat, originLon, originName, desLat, desLon, destination, src);
    }

    /**
     * 获取打开高德地图应用uri
     */
    public static String getGdMapUri(String appName, String slat, String slon, String sname, String dlat, String dlon, String dname) {
        String uri = "androidamap://route?sourceApplication=%1$s&slat=%2$s&slon=%3$s&sname=%4$s&dlat=%5$s&dlon=%6$s&dname=%7$s&dev=0&m=0&t=2";
        return String.format(uri, appName, slat, slon, sname, dlat, dlon, dname);
    }


    /**
     * 网页版百度地图 有经纬度
     *
     * @param originLat
     * @param originLon
     * @param originName  ->注：必填
     * @param desLat
     * @param desLon
     * @param destination
     * @param appName
     * @return
     */
    public static String getWebBaiduMapUri(String originLat, String originLon, String originName, String desLat, String desLon, String destination, String appName) {
        String uri = "http://api.map.baidu.com/direction?origin=latlng:%1$s,%2$s|name:%3$s" +
                "&destination=latlng:%4$s,%5$s|name:%6$s&mode=driving&output=html" +
                "&src=%8$s";
        return String.format(uri, originLat, originLon, originName, desLat, desLon, destination, appName);
    }


    /**
     * 百度地图定位经纬度转高德经纬度
     *
     * @param bd_lat
     * @param bd_lon
     * @return
     */
    public static double[] bdToGaoDe(double bd_lat, double bd_lon) {
        double[] gd_lat_lon = new double[2];
        double PI = 3.14159265358979324 * 3000.0 / 180.0;
        double x = bd_lon - 0.0065, y = bd_lat - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * PI);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * PI);
        gd_lat_lon[0] = z * Math.cos(theta);
        gd_lat_lon[1] = z * Math.sin(theta);
        return gd_lat_lon;
    }

    /**
     * 高德地图定位经纬度转百度经纬度
     *
     * @param gd_lon
     * @param gd_lat
     * @return
     */
    public static double[] gaoDeToBaidu(double gd_lon, double gd_lat) {
        double[] bd_lat_lon = new double[2];
        double PI = 3.14159265358979324 * 3000.0 / 180.0;
        double x = gd_lon, y = gd_lat;
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * PI);
        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * PI);
        bd_lat_lon[0] = z * Math.cos(theta) + 0.0065;
        bd_lat_lon[1] = z * Math.sin(theta) + 0.006;
        return bd_lat_lon;
    }
}