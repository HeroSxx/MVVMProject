package com.example.networkmodule.util;

import android.util.Log;

import java.lang.reflect.Method;
import java.util.ArrayList;

/**
 * class_name: LogUtils
 * package_name: com.basemodule.Utils
 * author: lijun
 * time: 2018/8/2 9:14
 */
public class LogUtils {
    public static boolean mLogEnable = true;
    public static final String PREFIX = "LogUtils--";

    private static String mClassname;
    private static ArrayList<String> mMethods;

    static {
        mClassname = LogUtils.class.getName();
        mMethods = new ArrayList<>();

        Method[] ms = LogUtils.class.getDeclaredMethods();
        for (Method m : ms) {
            mMethods.add(m.getName());
        }
    }

    public static void init(boolean logEnable) {
        mLogEnable = logEnable;
    }

    public static void d(String tag, String msg) {
        if (mLogEnable) {
            longDebugLogPrint(tag, getMsgWithLineNumber(msg));

        }
    }

    public static void e(String tag, String msg) {
        if (mLogEnable) {
            longErrorLogPrint(tag, getMsgWithLineNumber(msg));
        }
    }

    public static void i(String tag, String msg) {
        if (mLogEnable) {
            Log.i(tag, getMsgWithLineNumber(msg));
        }
    }

    public static void w(String tag, String msg) {
        if (mLogEnable) {
            Log.w(tag, getMsgWithLineNumber(msg));
        }
    }

    public static void v(String tag, String msg) {
        if (mLogEnable) {
            Log.v(tag, getMsgWithLineNumber(msg));
        }
    }


    public static void d(String msg) {
        if (mLogEnable) {
            String[] content = getMsgAndTagWithLineNumber(msg);
            longDebugLogPrint(content[0], content[1]);
        }
    }

    public static void e(String msg) {
        if (mLogEnable) {
            String[] content = getMsgAndTagWithLineNumber(msg);
            longErrorLogPrint(content[0], content[1]);
        }
    }

    public static void i(String msg) {
        if (mLogEnable) {
            String[] content = getMsgAndTagWithLineNumber(msg);
            longDebugLogPrint(content[0], content[1]);
        }
    }

    public static void i() {
        if (mLogEnable) {
            String[] content = getMsgAndTagWithLineNumber("");
            longDebugLogPrint(content[0], content[1]);
        }
    }

    public static void w(String msg) {
        if (mLogEnable) {
            String[] content = getMsgAndTagWithLineNumber(msg);
            longDebugLogPrint(content[0], content[1]);
        }
    }

    public static void v(String msg) {
        if (mLogEnable) {
            String[] content = getMsgAndTagWithLineNumber(msg);
            longDebugLogPrint(content[0], content[1]);
        }
    }

    public static String getMsgWithLineNumber(String msg) {
        try {
            for (StackTraceElement st : (new Throwable()).getStackTrace()) {
                if (mClassname.equals(st.getClassName()) || mMethods.contains(st.getMethodName())) {
                    continue;
                } else {
                    int b = st.getClassName().lastIndexOf(".") + 1;
                    String message = new StringBuilder(st.getClassName().substring(b)).append("->").append(st.getMethodName())
                            .append("():").append(st.getLineNumber()).append("行  ").append(msg).toString();
                    return message;
                }

            }
        } catch (Exception e) {

        }
        return msg;
    }

    public static String[] getMsgAndTagWithLineNumber(String msg) {
        try {
            for (StackTraceElement st : (new Throwable()).getStackTrace()) {
                if (mClassname.equals(st.getClassName()) || mMethods.contains(st.getMethodName())) {
                    continue;
                } else {
                    int b = st.getClassName().lastIndexOf(".") + 1;
                    String TAG = PREFIX + st.getClassName().substring(b);
                    String message = st.getMethodName() + "():" + st.getLineNumber() + "->" + msg;
                    String[] content = new String[]{TAG, message};
                    return content;
                }

            }
        } catch (Exception e) {

        }
        return new String[]{"universal tag", msg};
    }

    /**
     * 截断输出日志
     *
     * @param msg
     */
    public static void longErrorLogPrint(String tag, String msg) {
        if (tag == null || tag.length() == 0
                || msg == null || msg.length() == 0)
            return;

        int segmentSize = 3 * 1024;
        long length = msg.length();
        if (length <= segmentSize) {// 长度小于等于限制直接打印
            Log.e(tag, msg);
        } else {
            while (msg.length() > segmentSize) {// 循环分段打印日志
                String logContent = msg.substring(0, segmentSize);
                msg = msg.replace(logContent, "");
                Log.e(tag, logContent);
            }
            Log.e(tag, msg);// 打印剩余日志
        }
    }

    /**
     * 截断输出日志
     *
     * @param msg
     */
    public static void longDebugLogPrint(String tag, String msg) {
        if (tag == null || tag.length() == 0
                || msg == null || msg.length() == 0)
            return;

        int segmentSize = 3 * 1024;
        long length = msg.length();
        if (length <= segmentSize) {// 长度小于等于限制直接打印
            Log.e(tag, msg);
        } else {
            while (msg.length() > segmentSize) {// 循环分段打印日志
                String logContent = msg.substring(0, segmentSize);
                msg = msg.replace(logContent, "");
                Log.e(tag, logContent);
            }
            Log.e(tag, msg);// 打印剩余日志
        }
    }
}
