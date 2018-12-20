package fr.wildcodeschool.wildmediaplayerservice.notification;

import android.app.Notification;
import android.content.Context;

public interface NotificationBuilder<T extends NotificationItem> {
    Notification build(Context context, T item);
}
