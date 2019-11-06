package com.example.networkmodule.util;

import android.content.Context;
import android.content.SharedPreferences;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.basemodule.di.qualifier.ApplicationContext;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.http.HEAD;

/**
 * class_name: SPUtils
 * package_name: com.i5d5.basemodule.Utils
 * author: lijun
 * time: 2017/4/7 14:35
 */
@Singleton
public class SPUtils {
    /**
     * ；临时文件管理类
     */
    public final String FILE_NAME = "AppData";
    public static final String FIRST_OPEN = "isFirstOpen";//是否第一次打开
    public static final String LOGIN = "isLogin";//是否登录
    public static final String USER_NAME = "userName";
    public static final String HEAD_PATH = "headPath";
    public static final String PHONE_NUMBER = "phone_number";//手机号码
    public static final String CLASS_GRADE_INDEX = "classGradeIndex ";//年级
    public static final String EDUCATIONAL_TYPE = "educationalType ";//学年制
    public static final String SEX = "sex ";//性别 男0 女1
    public static final String CITY_NAME = "cityName ";//选择城市县级市以及区级地区
    public static final String ARTICLE_LABEL = "articleList";//文章标签
    public static final String CITY_ID = "cityId";//城市区域id;
    public static final String HUANXIN_NAME = "huanxinName";//环信用户名
    public static final String HUANXIN_PW = "huanxinPW";//环信密码
    public static final String FIRST_CLOSE_HOMEPAGE_TIP_DATE = "firstCloseHomePageTipDate";
    public static final String LAST_LOCATION_CHANGE_TIME = "locationChangeTime";
    public static final String LAST_OUT_OF_RANGE = "outOfRangeTime";
    public static final String JPUSH_INIT = "jPushInit";
    public static final String PROVINCE = "province";

    private SharedPreferences preferences;
    private SharedPreferences.Editor edit;
    private Context context;

    @Inject
    public SPUtils(@ApplicationContext Context context) {
        this.context = context;
        preferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        edit = preferences.edit();
    }


    //设置学年制
    public String getEducationalType() {
        return preferences.getString(EDUCATIONAL_TYPE, "");
    }

    public void setEducationalType(String educationalType) {
        edit.putString(EDUCATIONAL_TYPE, educationalType).commit();
    }

    public void setLastLocationChangeDate(String time) {
        edit.putString(LAST_LOCATION_CHANGE_TIME, time).commit();
    }

    public String getLastLocationChangeDate() {
        return preferences.getString(LAST_LOCATION_CHANGE_TIME, "");
    }

    //超出服务范围弹窗时间节点，一天内只弹一次显示弹窗
    public void setLastOutOfRange(String time) {
        edit.putString(LAST_OUT_OF_RANGE, time).commit();
    }

    public String getLastOutOfRange() {
        return preferences.getString(LAST_OUT_OF_RANGE, "");
    }

    //设置环信用户名
    public void setHuanxinName(String name) {
        edit.putString(HUANXIN_NAME, name).commit();

    }

    public String getHuanxinName() {
        return preferences.getString(HUANXIN_NAME, "");
    }

    //设置环信用户密码
    public void setHuanxinPW(String pw) {
        edit.putString(HUANXIN_PW, pw).commit();

    }

    public String getHuanxinPW() {
        return preferences.getString(HUANXIN_PW, "");
    }

    //    设置年级
    public String getClassGradeIndex() {
        return preferences.getString(CLASS_GRADE_INDEX, "");
    }

    public void setClassGradeIndex(String gradeIndex) {
        edit.putString(CLASS_GRADE_INDEX, gradeIndex).commit();
    }

    //    设置城市Id
    public String getCityId() {
        return preferences.getString(CITY_ID, "");
    }

    public void setCityId(String cityId) {
        edit.putString(CITY_ID, cityId).commit();
    }

    //设置文章标签
    public String getArticleLabel() {
        return preferences.getString(ARTICLE_LABEL, "");
    }

    public void setArticleLabel(String articleLabel) {
        edit.putString(ARTICLE_LABEL, articleLabel).commit();
    }

    //设置地区
    public String getCityName() {
        return preferences.getString(CITY_NAME, "");
    }

    public void setCityName(String cityName) {
        edit.putString(CITY_NAME, cityName).commit();
    }

    //设置省名称
    public String getProviceName() {
        return preferences.getString(PROVINCE, "");
    }

    public void setProvinceName(String provenceName) {
        edit.putString(PROVINCE, provenceName).commit();
    }

    //极光推送是否初始化成功
    public void setIsJPushInit(boolean jPushInit) {
        edit.putBoolean(JPUSH_INIT, jPushInit).commit();
    }

    public boolean getIsJPushInit() {
        return preferences.getBoolean(JPUSH_INIT, true);
    }

    //第一次打开
    public void setIsFirstOpen(boolean first) {
        edit.putBoolean(FIRST_OPEN, first).commit();
    }

    public boolean getIsFirstOpen() {
        return preferences.getBoolean(FIRST_OPEN, true);
    }

    //设置是否使用GPS定位的经纬度查询课程
    public void setIsLogin(boolean login) {
        edit.putBoolean(LOGIN, login).commit();
    }

    public boolean getIsLogin() {
        return preferences.getBoolean(LOGIN, false);
    }

    //用户姓名
    public void setUsName(String name) {
        edit.putString(USER_NAME, name).commit();
    }

    public String getUsName() {
        return preferences.getString(USER_NAME, "");
    }

    //用户头像地址
    public void setHeadPath(String author) {
        edit.putString(HEAD_PATH, author).commit();
    }

    public String getHeadPath() {
        return preferences.getString(HEAD_PATH, "");
    }

    //手机号
    public void setPhoneNumber(String phoneNumber) {
        edit.putString(PHONE_NUMBER, phoneNumber).commit();
    }

    public String getPhoneNumber() {
        return preferences.getString(PHONE_NUMBER, "");
    }

    //退出用户清空数据
    public void clearData() {
        preferences.edit().clear().commit();
    }


    //第一次关闭弹窗，七天内不再提示
    public void setHomePageTipFirstClosedDays(String firstCloseDays) {
        edit.putString(FIRST_CLOSE_HOMEPAGE_TIP_DATE, firstCloseDays).commit();
    }

    public String getHomePageTipFirstClosedDays() {
        return preferences.getString(FIRST_CLOSE_HOMEPAGE_TIP_DATE, "0");
    }

    public String getStringValue(String key) {
        return preferences.getString(key, "");
    }

    public void setStringValue(String key, String value) {
        edit.putString(key, value).commit();
    }
}
