package com.example.networkmodule.constant;

public enum AlarmTypeEnum
{
    SMOKE_FEELING               (0, "烟感"),
    GAS                         (1, "煤气"),
    INFRARED                    (2, "红外"),
    MAGNETOMETER                (3, "门磁"),
    WINDOW_MAGNETIC             (4, "窗磁"),
    EMERGENCY_BUTTON            (5, "紧急按钮"),
    FLOODING                    (6, "水浸"),
    EMERGENCY_ROPE              (7, "紧急绳索"),
    BEDSIDE_BUTTON              (8, "床头按钮"),

    UNKNOWN                     (-1,"未知"),
    ;

    private int type;
    private String info;

    AlarmTypeEnum(int type, String info)
    {

        this.type = type;
        this.info = info;
    }

    public int getType()
    {
        return type;
    }

    public String getInfo()
    {
        return info;
    }

    public static AlarmTypeEnum find(int type)
    {
        for(AlarmTypeEnum i : AlarmTypeEnum.values())
        {
            if(i.type == type)
            {
                return i;
            }
        }
        return UNKNOWN;
    }
}
