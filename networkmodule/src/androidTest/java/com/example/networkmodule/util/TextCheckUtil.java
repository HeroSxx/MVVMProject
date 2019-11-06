package com.example.networkmodule.util;

import android.content.Context;
import android.text.TextUtils;
import android.widget.EditText;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextCheckUtil {
    /**
     * 验证手机格式
     */
    public static boolean isMobileNumber(String mobiles) {
        /*
         * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
         * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
         * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
         */
        if (mobiles == null) {
            return false;
        }
        String telRegex = "[1][0123456789]\\d{9}";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(mobiles))
            return false;
        else
            return mobiles.matches(telRegex);
    }

    /**
     * 规则1：至少包含大小写字母及数字中的一种
     *
     * @param str
     * @return
     */
    public static boolean isLetterOrDigit(String str) {
        boolean isLetterOrDigit = false;//定义一个boolean值，用来表示是否包含字母或数字
        for (int i = 0; i < str.length(); i++) {
            if (Character.isLetterOrDigit(str.charAt(i))) {   //用char包装类中的判断数字的方法判断每一个字符
                isLetterOrDigit = true;
            } else {
                isLetterOrDigit = false;
            }
        }
        String regex = "^[a-zA-Z0-9]+$";
        boolean isRight = isLetterOrDigit && str.matches(regex);
        return isRight;
    }

//    各种字符的unicode编码的范围：
//     汉字：[0x4e00,0x9fa5]（或十进制[19968,40869]）
//      数字：[0x30,0x39]（或十进制[48, 57]）
//      小写字母：[0x61,0x7a]（或十进制[97, 122]）
//      大写字母：[0x41,0x5a]（或十进制[65, 90]）

    public static boolean isLetterDigitOrChinese(String str) {
        String regex = "^[a-z0-9A-Z\u4e00-\u9fa5]+$";
        return str.matches(regex);
    }

    //    是否是字母数字下划线
    public static Boolean isDigitLetter_(String str) {
        Boolean bl = false;
        //首先,使用Pattern解释要使用的正则表达式，其中^表是字符串的开始，$表示字符串的结尾。
        Pattern pt = Pattern.compile("^[0-9a-zA-Z_]+$");
        //然后使用Matcher来对比目标字符串与上面解释得结果
        Matcher mt = pt.matcher(str);
        //如果能够匹配则返回true。实际上还有一种方法mt.find()，某些时候，可能不是比对单一的一个字符串，
        //可能是一组，那如果只要求其中一个字符串符合要求就可以用find方法了.
        if (mt.matches()) {
            bl = true;
        }
        return bl;

    }

    /**
     * 规则2：至少包含大小写字母及数字中的两种
     * 是否包含
     *
     * @param str
     * @return
     */
    public static boolean isLetterDigit(String str) {
        boolean isDigit = false;//定义一个boolean值，用来表示是否包含数字
        boolean isLetter = false;//定义一个boolean值，用来表示是否包含字母
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {   //用char包装类中的判断数字的方法判断每一个字符
                isDigit = true;
            } else if (Character.isLetter(str.charAt(i))) {  //用char包装类中的判断字母的方法判断每一个字符
                isLetter = true;
            }
        }
        String regex = "^[a-zA-Z0-9]+$";
        boolean isRight = isDigit && isLetter && str.matches(regex);
        return isRight;
    }

    /**
     * 规则3：必须同时包含大小写字母及数字
     * 是否包含
     *
     * @param str
     * @return
     */
    public static boolean isContainAll(String str) {
        boolean isDigit = false;//定义一个boolean值，用来表示是否包含数字
        boolean isLowerCase = false;//定义一个boolean值，用来表示是否包含字母
        boolean isUpperCase = false;
        for (int i = 0; i < str.length(); i++) {
            if (Character.isDigit(str.charAt(i))) {   //用char包装类中的判断数字的方法判断每一个字符
                isDigit = true;
            } else if (Character.isLowerCase(str.charAt(i))) {  //用char包装类中的判断字母的方法判断每一个字符
                isLowerCase = true;
            } else if (Character.isUpperCase(str.charAt(i))) {
                isUpperCase = true;
            }
        }
        String regex = "^[a-zA-Z0-9]+$";
        boolean isRight = isDigit && isLowerCase && isUpperCase && str.matches(regex);
        return isRight;
    }

    /**
     * 判断EditText输入的数字、中文还是字母方法
     */
    public static boolean isNumber(String txt) {
        Pattern p = Pattern.compile("[0-9]*");
        Matcher m = p.matcher(txt);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }

    }

    //校验是否是字母
    public static boolean isLetter(String txt) {
        Pattern p = Pattern.compile("[a-zA-Z]");
        Matcher m = p.matcher(txt);

        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    //校验是否是中文
    public static boolean isContainChinese(String str) {
        Pattern p = Pattern.compile("[\u4e00-\u9fa5]");
        Matcher m = p.matcher(str);
        if (m.find()) {
            return true;
        }
        return false;
    }

}
