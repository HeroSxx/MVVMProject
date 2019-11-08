package com.example.networkmodule.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.example.networkmodule.R;

public class GlideUtils {

    /**
     * 为避免因为缓存造成的裁剪后的圆形图片被其他图片控件使用出现bug,
     * 改用自定义view实现的ImageView,RoundImageView
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadCircleImage(Context context, String url, ImageView imageView, int defaultImage,
                                       int widthDp, int heightDp) {
//按照设计屏幕尺寸宽度（360dp）1080px,屏幕宽度适配加载图片
        int realWidth = ScreenUtils.getScreenWidth(context);
        int scaling = (realWidth / 1080) * 2;
        int loadWidth = scaling * widthDp;
        int loadHeight = scaling * heightDp;
//        LogUtils.e("====imageSize", "scaling:" + scaling + " hw:" + loadHeight + " " + loadWidth);
        Glide.with(context).applyDefaultRequestOptions(new RequestOptions().error(defaultImage).placeholder(R.mipmap.rectangle_white))
                .load(url).diskCacheStrategy(DiskCacheStrategy.ALL).override(loadWidth, loadHeight)
                .into(imageView);
    }


    /**
     * 加载矩形
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadRectangleImage(Context context, String url, ImageView imageView, int defaultImage,
                                          int widthDp, int heightDp) {
        //按照设计屏幕尺寸宽度（360dp）1080px,屏幕宽度适配加载图片，仅限本项目特殊情况使用（上传图片无尺寸与大小上限）
        int realWidth = ScreenUtils.getScreenWidth(context);
        int scaling = (realWidth / 1080) * 2;
        int loadWidth = scaling * widthDp;
        int loadHeight = scaling * heightDp;
//        LogUtils.e("====imageSize", "scaling:" + scaling + " hw:" + loadHeight + " " + loadWidth);
        Glide.with(context).
                applyDefaultRequestOptions(new RequestOptions().error(defaultImage).placeholder(R.mipmap.rectangle_white))
                .load(url).diskCacheStrategy(DiskCacheStrategy.ALL)
                .skipMemoryCache(true)
                .override(loadWidth, loadHeight)
                .into(imageView);
    }

    /**
     * 加载矩形无占位图等
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadRectangleImage(Context context, String url, ImageView imageView
            , int widthDp, int heightDp) {
        //按照设计屏幕尺寸宽度（360dp）1080px,屏幕宽度适配加载图片，仅限本项目特殊情况使用（上传图片无尺寸与大小上限）
        int realWidth = ScreenUtils.getScreenWidth(context);
        int scaling = (realWidth / 1080) * 2;
        int loadWidth = scaling * widthDp;
        int loadHeight = scaling * heightDp;
        Glide.with(context).load(url).diskCacheStrategy(DiskCacheStrategy.ALL).
                override(loadWidth, loadHeight).
                skipMemoryCache(true).into(imageView);
    }

    /**
     * 加载圆角矩形图片
     *
     * @param corner
     * @param url
     * @param imageView
     */
    public static void loadRectangleImageWithRoundCorner(Context context, String url, ImageView imageView, int corner, int defaultImage
            , int widthDp, int heightDp) {
        //按照设计屏幕尺寸宽度（360dp）1080px,屏幕宽度适配加载图片，仅限本项目特殊情况使用（上传图片无尺寸与大小上限）

        int realWidth = ScreenUtils.getScreenWidth(context);
        int scaling = (realWidth / 1080) * 2;
        int loadWidth = scaling * widthDp;
        int loadHeight = scaling * heightDp;

        Glide.with(context).applyDefaultRequestOptions(new RequestOptions().error(defaultImage).placeholder(R.mipmap.rectangle_white).transform(new RoundedCorners(corner)))
                .load(url).diskCacheStrategy(DiskCacheStrategy.ALL).
                override(loadWidth, loadHeight).into(imageView);
    }

    /**
     * 加载圆角矩形图片,根据设计宽度等比例缩放
     *
     * @param corner
     * @param url
     * @param imageView
     */
    public static void loadNetRectangleImageWithRoundCornerEqualScaling(Context context, String url, ImageView imageView, int defaultImage, int corner) {
        Glide.with(context).applyDefaultRequestOptions(new RequestOptions().error(defaultImage).placeholder(R.mipmap.rectangle_white).transform(new RoundedCorners(corner)))
                .asBitmap().load(url).diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(new EqualScalingTransformationUtil(imageView));
    }

    /**
     * 加载本地圆角矩形图片,根据设计宽度等比例缩放
     *
     * @param corner
     * @param imageView
     */
    public static void loadLocalRectangleImageWithRoundCornerEqualScaling(Context context, ImageView imageView, int imageId, int corner) {

        Glide.with(context).applyDefaultRequestOptions(new RequestOptions().transform(new RoundedCorners(corner)))
                .asBitmap().load(imageId)
                .into(new EqualScalingTransformationUtil(imageView));
    }

    /**
     * 加载直角矩形图片,根据设计宽度等比例缩放
     *
     * @param url
     * @param imageView
     */
    public static void loadNetRectangleImageEqualScaling(Context context, String url, ImageView imageView, int defaultImage) {

        Glide.with(context).applyDefaultRequestOptions(new RequestOptions().error(defaultImage).placeholder(R.mipmap.rectangle_white))
                .asBitmap().load(url).diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(new EqualScalingTransformationUtil(imageView));
    }

    /**
     * 加载本地直角矩形图片,根据设计宽度等比例缩放
     *
     * @param
     * @param imageView
     */
    public static void loadLocalRectangleImageEqualScaling(Context context, ImageView imageView, int imageId) {

        Glide.with(context).asBitmap().load(imageId)
                .into(new EqualScalingTransformationUtil(imageView));
    }
}