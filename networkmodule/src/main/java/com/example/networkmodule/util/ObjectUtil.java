package com.example.networkmodule.util;


import java.lang.reflect.Field;

public class ObjectUtil {
    public static boolean checkObjectIsNull(Object model) {
        try {
            for (Field field : model.getClass().getDeclaredFields()) {
                field.setAccessible(true);
//                LogUtils.e("=====checkObjectIsNull====" + field.getName() + ":" + field.get(model));
                if (!field.getName().equals("serialVersionUID")) {
                    if (field.get(model) != null && field.get(model).toString() != null) {
                        if (field.get(model).toString().length() > 0) {
                            return false;
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return true;
    }

    public static boolean checkObjectSomeValueIsNull(Object model, String valueName) {
        try {
            for (Field field : model.getClass().getDeclaredFields()) {
                field.setAccessible(true);
//                LogUtils.e("=====checkObjectIsNull====" + field.getName() + ":" + field.get(model));
                if (!field.getName().equals("serialVersionUID")&&!field.getName().equals(valueName)) {
                    if (field.get(model) != null && field.get(model).toString() != null) {
                        if (field.get(model).toString().length() > 0) {
                            return false;
                        }
                    }
                }
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return true;
    }
}
