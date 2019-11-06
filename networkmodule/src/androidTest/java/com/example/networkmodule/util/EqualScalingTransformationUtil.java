package com.example.networkmodule.util;

import android.graphics.Bitmap;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.request.target.ImageViewTarget;


/**
 * 按照固定宽度等比例缩放图片
 *
 * @author 金石
 */
public class EqualScalingTransformationUtil extends ImageViewTarget<Bitmap> {
    private ImageView target;

    public EqualScalingTransformationUtil(ImageView target) {
        super(target);
        this.target = target;
    }

    @Override
    protected void setResource(Bitmap resource) {
        if (resource == null || view == null || target == null) {
            return;
        }
        view.setImageBitmap(resource);
        //获取原图的宽高
        int width = resource.getWidth();
        int height = resource.getHeight();
        //获取imageView的宽
        int imageViewWidth = target.getWidth();
        //计算缩放比例
        float sy = (float) (imageViewWidth * 0.1) / (float) (width * 0.1);
        //计算图片等比例放大后的高
        int imageViewHeight = (int) (height * sy);
        ViewGroup.LayoutParams params = target.getLayoutParams();
        params.height = imageViewHeight;
        target.setLayoutParams(params);
    }


}
