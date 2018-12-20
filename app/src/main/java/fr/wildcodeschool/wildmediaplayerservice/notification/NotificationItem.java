package fr.wildcodeschool.wildmediaplayerservice.notification;

import android.content.Context;
import android.support.v4.app.NotificationCompat;

import java.util.ArrayList;
import java.util.List;

public class NotificationItem {
    private NotificationChannel channel;
    private String title;
    private String description;
    private int smallIcon;
    private List<NotificationCompat.Action> actions;

    public NotificationItem(Context context,
                            NotificationChannel notificationChannel,
                            int title, int description, int smallIcon) {
        this(notificationChannel,
                context.getString(title),
                context.getString(description),
                smallIcon);
    }

    public NotificationItem(NotificationChannel channel,
                            String title, String description,
                            int smallIcon) {
        this.channel = channel;
        this.title = title;
        this.description = description;
        this.smallIcon = smallIcon;
        this.actions = new ArrayList<>();
    }

    public NotificationChannel getChannel() {
        return channel;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getSmallIcon() {
        return smallIcon;
    }

    public List<NotificationCompat.Action> getActions() {
        return actions;
    }
}
