package com.example.networkmodule.util;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 * Date:2018/9/29
 * Time:19:47
 * author:lijun
 */

public class FileUtil {

    /**
     * 删除文件，可以是文件或文件夹
     *
     * @param fileName 要删除的文件名
     * @return 删除成功返回true，否则返回false
     */
    public static boolean delete(String fileName) {
        File file = new File(fileName);
        if (!file.exists()) {
            System.out.println("删除文件失败:" + fileName + "不存在！");
            return false;
        } else {
            if (file.isFile())
                return deleteFile(fileName);
            else
                return deleteDirectory(fileName);
        }
    }

    /**
     * 删除单个文件
     *
     * @param fileName 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                System.out.println("删除单个文件" + fileName + "成功！");
                return true;
            } else {
                System.out.println("删除单个文件" + fileName + "失败！");
                return false;
            }
        } else {
            System.out.println("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }

    /**
     * 删除目录及目录下的文件
     *
     * @param dir 要删除的目录的文件路径
     * @return 目录删除成功返回true，否则返回false
     */
    public static boolean deleteDirectory(String dir) {
        // 如果dir不以文件分隔符结尾，自动添加文件分隔符
        if (!dir.endsWith(File.separator))
            dir = dir + File.separator;
        File dirFile = new File(dir);
        // 如果dir对应的文件不存在，或者不是一个目录，则退出
        if ((!dirFile.exists()) || (!dirFile.isDirectory())) {
            System.out.println("删除目录失败：" + dir + "不存在！");
            return false;
        }
        boolean flag = true;
        // 删除文件夹中的所有文件包括子目录
        File[] files = dirFile.listFiles();
        for (int i = 0; i < files.length; i++) {
            // 删除子文件
            if (files[i].isFile()) {
                flag = FileUtil.deleteFile(files[i].getAbsolutePath());
                if (!flag)
                    break;
            }
            // 删除子目录
            else if (files[i].isDirectory()) {
                flag = FileUtil.deleteDirectory(files[i]
                        .getAbsolutePath());
                if (!flag)
                    break;
            }
        }
        if (!flag) {
            System.out.println("删除目录失败！");
            return false;
        }
        // 删除当前目录
        if (dirFile.delete()) {
            System.out.println("删除目录" + dir + "成功！");
            return true;
        } else {
            return false;
        }
    }
    //获取网络视频第一帧
    public static Bitmap getNetVideoBitmap(String videoUrl) {
        Bitmap bitmap = null;

        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        try {
            //根据url获取缩略图
            retriever.setDataSource(videoUrl, new HashMap());
            //获得第一帧图片
            bitmap = retriever.getFrameAtTime();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } finally {
            retriever.release();
        }
        return bitmap;
    }
    /**
     * 保存图片并通知相册
     *
     * @param context
     * @param bitmap
     */
    public static String saveBitmap(Context context, Bitmap bitmap, String imgName) {
        final String imgFileDir = Environment.getExternalStorageDirectory() + File.separator + String.valueOf(System.currentTimeMillis());
        final String fileName = imgFileDir + imgName + ".jpg";
        File filePic = null;
        Log.e("====", "路径：" + fileName);
        try {
            filePic = new File(fileName);
            if (!filePic.exists()) {
                filePic.getParentFile().mkdirs();
                filePic.createNewFile();
                FileOutputStream fos = new FileOutputStream(filePic);
                //不压缩
//                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
                //压缩至100kb
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                int options = 80;
                bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
                while (baos.toByteArray().length / 1024 > 100) {
                    baos.reset();
                    options -= 10;
                    bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
                }
                fos.write(baos.toByteArray());

                fos.flush();
                fos.close();
                MediaStore.Images.Media.insertImage(context.getContentResolver(), filePic.getAbsolutePath(), fileName, null);
                // 第三步：最后通知图库更新
                context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + filePic)));
            } else {

            }
            Log.e("wh", "读取：" + filePic.getAbsolutePath());

//            Toast.makeText(context, "保存成功，可去相册中查看", Toast.LENGTH_SHORT).show();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return fileName;
    }

    /**
     * 读取文件里面的内容
     *
     * @return
     */
    public static String getFile(String fileName) {
        try {
            // 创建文件
            File file = new File(Environment.getExternalStorageDirectory(), fileName);
            // 创建FileInputStream对象
            FileInputStream fis = new FileInputStream(file);
            // 创建字节数组 每次缓冲1M
            byte[] b = new byte[1024];
            int len = 0;// 一次读取1024字节大小，没有数据后返回-1.
            // 创建ByteArrayOutputStream对象
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            // 一次读取1024个字节，然后往字符输出流中写读取的字节数
            while ((len = fis.read(b)) != -1) {
                baos.write(b, 0, len);
            }
            // 将读取的字节总数生成字节数组
            byte[] data = baos.toByteArray();
            // 关闭字节输出流
            baos.close();
            // 关闭文件输入流
            fis.close();
            // 返回字符串对象
            return new String(data);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 删除已存储的文件
     */
    public static void deletefile(String fileName) {
        try {
            // 找到文件所在的路径并删除该文件
            File file = new File(Environment.getExternalStorageDirectory(), fileName);
            file.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static final String SD_PATH = "/sdcard/dskqxt/pic/";
    private static final String IN_PATH = "/dskqxt/pic/";


    /**
     * 获取SD卡路径
     *
     * @return SD卡路径，SD卡如不存在，返回null
     */
    public static String getSDPath() {
        File sdDir = null;
        boolean sdCardExist = Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
        if (sdCardExist) {
            sdDir = Environment.getExternalStorageDirectory();
        } else {
            return null;
        }
        return sdDir.toString();

    }


    /**
     * 将文件转换为base64字符串
     *
     * @param filePath 文件路径
     * @return
     */
    public static String file2Base64(String filePath) {
        File file = new File(filePath);
        FileInputStream inputFile;
        try {
            inputFile = new FileInputStream(file);
            byte[] buffer = new byte[(int) file.length()];
            inputFile.read(buffer);
            inputFile.close();
            return Base64.encodeToString(buffer, Base64.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /**
     * 文件转为base64并输出到文件
     *
     * @param filePath
     * @return
     */
    public static void file2Base64File(String filePath, String toFile) {
        String base64 = file2Base64(filePath);
        Log.d("base64", base64);
        try {
            FileOutputStream outputStream = new FileOutputStream(toFile);
            outputStream.write(base64.getBytes("utf-8"));
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 把字符串Base64加密并返回加密后的字符串
     *
     * @param str 需要加密的字符串
     * @return 加密后的字符串
     */
    public static String getBase64String(String str) {
        return Base64.encodeToString(str.getBytes(), Base64.NO_WRAP);
    }


}
