package fr.wildcodeschool.wildmediaplayerservice.notification;

import android.app.Service;

public class PushNotificationService<I extends NotificationItem>
        extends PushNotification<I> {

    protected Service service;

    public PushNotificationService(Service service, NotificationBuilder<I> notificationBuilder) {
        super(service.getApplicationContext(), notificationBuilder);
        this.service = service;
    }

    @Override
    protected void notify(int id, I notificationItem) {
        this.service.startForeground(id,
                this.notificationBuilder.build(this.context, notificationItem));
    }

    @Override
    @Deprecated
    public void cancel(int id) {
        super.cancel(id);
    }

    @Override
    public void cancelAll() {
        this.service.stopForeground(true);
    }
}
