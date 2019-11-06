package com.example.networkmodule.util;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

//import com.goldstone.goldstone_android.R;

import static android.content.Context.NOTIFICATION_SERVICE;

public class NotificationUtil {
    private static final NotificationUtil ourInstance = new NotificationUtil();

    public static NotificationUtil getInstance() {
        return ourInstance;
    }

    private NotificationUtil() {
        LogUtils.e("===NotificationUtil oncreate");
    }

    private void createChannel(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "chat";
            String channelName = "聊天消息";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            createNotificationChannel(channelId, channelName, importance, context);

            channelId = "subscribe";
            channelName = "订阅消息";
            importance = NotificationManager.IMPORTANCE_DEFAULT;
            createNotificationChannel(channelId, channelName, importance, context);
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    private void createNotificationChannel(String channelId, String channelName, int importance, Context context) {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        channel.setShowBadge(true);
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(
                NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
    }

    public void sendChatMsg(Context context, String msgTitle, String msgContent) {
        createChannel(context);
        NotificationManager manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = manager.getNotificationChannel("chat");
            if (channel.getImportance() == NotificationManager.IMPORTANCE_NONE) {
                Intent intent = new Intent(Settings.ACTION_CHANNEL_NOTIFICATION_SETTINGS);
                intent.putExtra(Settings.EXTRA_APP_PACKAGE, context.getPackageName());
                intent.putExtra(Settings.EXTRA_CHANNEL_ID, channel.getId());
                context.startActivity(intent);
                Toast.makeText(context, "请手动将通知打开", Toast.LENGTH_SHORT).show();
            }
        }
        Intent intent = new Intent("notification_clicked");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notification = new NotificationCompat.Builder(context, "chat")
                .setContentTitle(msgTitle)
                .setContentText(msgContent)
                .setWhen(System.currentTimeMillis())
//                .setSmallIcon(R.mipmap.android_icon)
//                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.android_icon))
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                //通知栏显示
                .setVisibility(Notification.VISIBILITY_PUBLIC)
                .setFullScreenIntent(pendingIntent, false)
                .build();
//        使用相同的消息id,可保证通知栏只显示最新消息，不会堆积，避免用户反感
        manager.notify(1, notification);
    }

    public void sendSubscribeMsg(Context context, String msgTitle, String msgContent) {
        createChannel(context);
        NotificationManager manager = (NotificationManager) context.getSystemService(NOTIFICATION_SERVICE);
        Notification notification = new NotificationCompat.Builder(context, "subscribe")
                .setContentTitle(msgTitle)
                .setContentText(msgContent)
                .setWhen(System.currentTimeMillis())
//                .setSmallIcon(R.mipmap.android_icon)
//                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.android_icon))
                .setAutoCancel(true)
                .setNumber(2)
                .build();
        manager.notify(2, notification);
    }
}
