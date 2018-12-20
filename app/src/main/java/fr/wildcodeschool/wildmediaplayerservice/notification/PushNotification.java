package fr.wildcodeschool.wildmediaplayerservice.notification;

import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;

public class PushNotification<I extends NotificationItem> {
    protected NotificationManager notificationManager;
    protected Context context;
    protected NotificationBuilder<I> notificationBuilder;

    public PushNotification(Context context , NotificationBuilder<I> notificationBuilder) {
        this.notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        this.notificationBuilder = notificationBuilder;
        this.context = context;
    }

    public void show(int id, I notificationItem) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (!channelExist(notificationItem.getChannel().getChannelId()))
                createChannel(this.context, notificationItem.getChannel());
        }
        this.notify(id, notificationItem);
    }

    protected void notify(int id, I notificationItem) {
        this.notificationManager.notify(id,
                this.notificationBuilder.build(this.context, notificationItem));
    }

    public void cancel(int id) {
        this.notificationManager.cancel(id);
    }

    public void cancelAll() {
        this.notificationManager.cancelAll();
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private void createChannel(Context context, NotificationChannel channel) {
        String name = context.getString(channel.getName());
        int importance = NotificationManager.IMPORTANCE_DEFAULT;
        android.app.NotificationChannel notificationChannel
                = new android.app.NotificationChannel(channel.getChannelId(), name, importance);
        notificationChannel.setShowBadge(true);
        this.notificationManager.createNotificationChannel(notificationChannel);
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private boolean channelExist(String channelId) {
        return this.notificationManager.getNotificationChannel(channelId) != null;
    }
}
