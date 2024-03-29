package com.example.networkmodule.util;


import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public class MapUtil {
    private static Logger log = Logger.getLogger("MyConverUtil");

    public static Map<String, Object> PO2Map(Object o) throws Exception{
        Map<String, Object> map = new HashMap<String, Object>();
        Field[] fields = null;
        String clzName = o.getClass().getSimpleName();
        log.info("类："+o.getClass().getName());
        fields = o.getClass().getDeclaredFields();
        log.info("***"+clzName+"转map开始****");
        for (Field field : fields) {
            field.setAccessible(true);
            String proName = field.getName();
            Object proValue = field.get(o);
            map.put(proName.toUpperCase(), proValue);
            log.info("key："+proName+"value:"+proValue);
        }
        log.info("***"+clzName+"转map结束****");
        return map;
    }


    public static Object map2PO(Map<String,Object> map,Object o) throws Exception{
        if (!map.isEmpty()) {
            for (String k : map.keySet()) {
                Object v = "";
                if (!k.isEmpty()) {
                    v = map.get(k);
                }
                Field[] fields = null;
                fields = o.getClass().getDeclaredFields();
                String clzName = o.getClass().getSimpleName();
                log.info("类："+o.getClass().getName());
                log.info("***map转"+clzName+"开始****");
                for (Field field : fields) {
                    int mod = field.getModifiers();
                    if(Modifier.isStatic(mod) || Modifier.isFinal(mod)){
                        continue;
                    }
                    if (field.getName().toUpperCase().equals(k)) {
                        field.setAccessible(true);
                        field.set(o, v);
                        log.info("key："+k+"value:"+v);
                    }

                }
                log.info("***map转"+clzName+"结束****");
            }
        }
        return o;
    }

}
