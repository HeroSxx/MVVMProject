package com.example.networkmodule.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kukugtu on 2018/5/7 0007 14:56.
 * 正则表达式工具类
 *
 */
public class RegExpUtil {

    public RegExpUtil() {
        // TODO Auto-generated constructor stub
    }

    /**
     * 判断是否是邮件格式
     *
     * @param text 待判断字符串
     * @return 是否为邮箱格式
     */
    public static boolean isEmail(String text) {
        String str = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+\\.[a-zA-Z0-9_-]+$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(text);
        return m.matches();
    }


    /**
     * 输入小于20个字符
     *
     * @param text
     * @return
     */
    public static boolean isCorrectChar(String text) {
        String str = "^.{1,20}$";
        Pattern p = Pattern.compile(str);
        Matcher m = p.matcher(text);
        return m.matches();
    }


    /**
     * 判断是否是手机号格式
     *
     * @param text 待判断字符串
     * @return 是否为手机号码格式
     */
    public static boolean isPhoneNumber(String text) {
        String telRegex = "^1[3|4|5|7|8|][0-9]{9}";
        Pattern p = Pattern.compile(telRegex);
        Matcher m = p.matcher(text);
        return m.matches();
    }

    /**
     * 判断是否是数字
     *
     * @param text 待判断字符串
     * @return 是否为数字
     */
    public static boolean isNumber(String text) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher match = pattern.matcher(text);
        return match.matches();
    }

    /**
     * 判断是否包含特殊字符
     *
     * @param text 判断字符串
     * @return 是否包含特殊字 true 表示包含特殊字符
     */
    public static boolean isFormatText(String text) {
        final String REG_STR = "[`~@#$%^&*+=|{}';',\\[\\].<>/~！@#%……&*（）——+|{}【】]";
        Pattern pattern = Pattern.compile(REG_STR);
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }

    /**
     * 检测是否有emoji表情
     *
     * @param source
     * @return
     */
    public static boolean containsEmoji(String source) {
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (!isEmojiCharacter(codePoint)) { //如果不能匹配,则该字符是Emoji表情
                return true;
            }
        }
        return false;
    }

    /**
     * 判断是否是Emoji
     *
     * @param codePoint 比较的单个字符
     * @return
     */
    private static boolean isEmojiCharacter(char codePoint) {
        return (codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) ||
                (codePoint == 0xD) || ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) ||
                ((codePoint >= 0xE000) && (codePoint <= 0xFFFD)) || ((codePoint >= 0x10000)
                && (codePoint <= 0x10FFFF));
    }

}
