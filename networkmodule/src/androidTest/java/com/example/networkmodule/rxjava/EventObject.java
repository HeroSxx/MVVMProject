package com.example.networkmodule.rxjava;

public class EventObject {
    public static final int TOKEN_OUT_DATA = 0;
    public static final int BACK_HOME = 1;
    public static final int GO_COURSE = 2;
    public static final int GO_ARTICLE = 3;
    public static final int GO_TEACHER = 4;
    public static final int REFRESH_KEYWORDS = 5;
    public static final int OUT_LOGIN = 6;
    public static final int COLLECT_CANCEL = 7;
    public static final int NETWORK_DISCONNECT = 8;
    public static final int SEVER_ERROR = 9;
    public static final int SEVER_RECOVER = 10;
    public static final int NETWORK_RECOVER = 11;
    public static final int PAY_CANCEL = 12;
    public static final int PAY_SUCCESS = 13;
    public static final int PAY_ERROR = 14;
    public static final int PAY_FINISH = 15;
    public static final int CHANGE_GRADE = 16;
    public static final int OTHER_LOGIN = 17;
    public static final int GO_COURSE_FROM_OTHER_MAIN_PAGE = 18;
    public static final int LOGIN_AFTER_MAIN_ACTIVITY_CREATE = 19;
    public static final int CHANGE_AREA = 20;
    public static final int UPDATE_UNREAD_MSG_COUNT = 21;
    public static final int CHANGE_SELECT_CLASS_KIND = 22;
    public static final int COURSE_FRAGMENT_RESET_VIEW = 23;
    public static final int SEARCH_TO_COURSE = 24;
    public static final int CHANGE_SELECT_CLASS_TERM = 25;
    public static final int GET_HUANXIN_LOGIN_INFO = 26;
    public static final int CHOOSE_GRADE = 27;
    public static final int CHOOSE_OTHER_PAGE = 28;
    public static final int LOCATION_CHANGE = 29;
    public static final int SHIFT_COURSE_COMPLETE = 30;
    public static final int HAS_COUPON_SHOW = 31;
    //关闭APP（不退出账号）
    public static final int FINISH_APP = 32;
    /**
     * 开小差页面点击刷新试试，主页四个fragment进行刷新
     */
    public static final int REFRESH_MAIN_PAGE = 33;
    public static final int CHANGE_SELECT_CLASS_TYPE = 34;
    public static final int GET_LOCATION = 35;
    /**
     * 关闭目标课程的相关课程页
     */
    public static final int FINISH_SHIFT_TARGET_CLASS_PAGE = 36;
    public static final int HIDE_TITLE_RIGHT = 37;
    /**
     * 预约考试成功
     */
    public static final int EXAM_APPOINTMENT_SUCCESS = 38;
    /**
     * 已读购买说明
     */
    public static final int READ_BUY_INTRODUCTION = 39;
    private int type;
    private String msg;

    public EventObject(int type, String msg) {
        this.type = type;
        this.msg = msg;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "EventObject{" +
                "type=" + type +
                ", msg='" + msg + '\'' +
                '}';
    }
}
