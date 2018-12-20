package fr.wildcodeschool.wildmediaplayerservice.notification;

import android.app.Notification;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

public class MediaNotificationBuilder<I extends NotificationItem>
        implements NotificationBuilder<I> {

    @Override
    public Notification build(Context context, I item) {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, item.getChannel().getChannelId())
                .setContentTitle(item.getTitle())
                .setSmallIcon(item.getSmallIcon())
                .setContentText(item.getDescription())
                .setAutoCancel(true)
                .setDefaults(NotificationCompat.DEFAULT_ALL)
                .setBadgeIconType(NotificationCompat.BADGE_ICON_SMALL);

        for(NotificationCompat.Action action : item.getActions()) {
            builder.addAction(action);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            if (item.getActions().size() > 0)
                builder.setStyle(new android.support.v4.media.app.NotificationCompat.MediaStyle()
                        .setShowActionsInCompactView(0)
            );
        }
        return builder.build();
    }
}
