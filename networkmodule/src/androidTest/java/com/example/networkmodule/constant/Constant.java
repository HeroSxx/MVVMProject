package com.example.networkmodule.constant;

import com.basemodule.util.FileUtil;

import java.io.File;

/**
 * class_name: Constant
 * package_name: com.basemodule
 * author: lijun
 * time: 2018/7/23 16:42
 */

public class Constant {
    /*测试服务器地址*/

//    public static final String BASE_URL = "http://10.10.23.222:8005/";
    public static final String BASE_URL = "http://10.10.18.191:8080/";
    public static final String OFFICIAL_UPDATE_URL = "http://stuapp.jinshiedu.net/anonymity/stuappVersion/selectByVersionNumByAndriod/{code}";
    public static final String TEST_UPDATE_URL = "http://10.10.18.191:8080/stuapp_web/anonymity/stuappVersion/selectByVersionNumByAndriod/{code}";

    //外部缓存路径
    public static final String EXTRENAL_FILEPATH = FileUtil.getSDPath() + File.separator + "GoldStone";

    //外部视频录制存放路径
    public static final String EXTRENAL_FILEPATH_VIDEO = FileUtil.getSDPath() + File.separator + "GoldStone" + File.separator + "video";
    //外部图片存放路径
    public static final String EXTRENAL_FILEPATH_IMAGE = FileUtil.getSDPath() + File.separator + "GoldStone" + File.separator + "image";
    //外部视频封面存放路径
    public static final String EXTRENAL_FILEPATH_VIDEOIMAGE = FileUtil.getSDPath() + File.separator + "GoldStone" + File.separator + "bmsave" + File.separator;
    //外部音频录制存放路径
    public static final String EXTRENAL_FILEPATH_AUDIO = FileUtil.getSDPath() + File.separator + "GoldStone" + File.separator + "audio";
    //外部网络音频存放路径
    public static final String EXTRENAL_FILEPATH_NETAUDIO = FileUtil.getSDPath() + File.separator + "GoldStone" + File.separator + "netaudio";
    //外部大图存储
    public static final String EXTRENAL_FILEPATH_BIG_IMAGE = FileUtil.getSDPath() + File.separator + "GoldStone" + File.separator + "bigimage";


}
