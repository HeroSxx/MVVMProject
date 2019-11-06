package com.example.networkmodule.util;

import android.view.View;

/**
 * 点击间隔
 * class_name: OnDelayCliclListener
 * package_name: com.basemodule.Utils
 * author: lijun
 * time: 2018/7/25 16:32
 */
public abstract class OnDelayCliclListener implements View.OnClickListener {
    private static long lastTime;

    public abstract void singleClick(View v);

    private long delay;

    public OnDelayCliclListener(long delay) {
        this.delay = delay;
    }

    public OnDelayCliclListener() {
        this.delay = 500;
    }

    @Override
    public void onClick(View v) {
        if (onMoreClick(v)) {
            return;
        }
        singleClick(v);
    }

    public boolean onMoreClick(View v) {
        boolean flag = false;
        long time = System.currentTimeMillis() - lastTime;
        if (time < delay) {
            flag = true;
        }
        lastTime = System.currentTimeMillis();
        return flag;
    }
}
