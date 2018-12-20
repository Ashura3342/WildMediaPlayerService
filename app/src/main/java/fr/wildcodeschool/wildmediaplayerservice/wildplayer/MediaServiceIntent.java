package fr.wildcodeschool.wildmediaplayerservice.wildplayer;

import android.app.IntentService;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import fr.wildcodeschool.wildmediaplayerservice.R;
import fr.wildcodeschool.wildmediaplayerservice.notification.MediaNotificationBuilder;
import fr.wildcodeschool.wildmediaplayerservice.notification.NotificationChannel;
import fr.wildcodeschool.wildmediaplayerservice.notification.NotificationItem;
import fr.wildcodeschool.wildmediaplayerservice.notification.PushNotificationService;

public class MediaServiceIntent extends IntentService implements WildOnPlayerListener {

    public static final String ACTION_PLAY = "PLAY";
    public static final String ACTION_PAUSE = "PAUSE";
    public static final String ACTION_RESET = "RESET";
    private WildPlayer mPlayer = null;
    static Context AppContext;

    public static Context getAppContext() {
        return AppContext;
    }

    private PushNotificationService<NotificationItem> pushNotificationService;
    private NotificationChannel notificationChannel
            = new NotificationChannel("default", R.string.channel_name);

    private NotificationItem mediaNotification
            = new NotificationItem(notificationChannel,
            "Queen-Rhapsody",
            "the best music",
            R.drawable.ic_launcher_background);

    public MediaServiceIntent() {
        super("MediaServiceIntent");
    }


    @Override
    public void onCreate() {
        super.onCreate();
        AppContext = this.getApplicationContext();
        mPlayer = new WildPlayer(this);
        mPlayer.init(R.string.song, this);

        pushNotificationService = new PushNotificationService<>(this,
                new MediaNotificationBuilder<>());

        Intent playIntent = new Intent(this, MediaServiceIntent.class)
                .setAction(ACTION_PLAY);
        Intent pauseIntent = new Intent(this, MediaServiceIntent.class)
                .setAction(ACTION_PAUSE);
        Intent resetIntent = new Intent(this, MediaServiceIntent.class)
                .setAction(ACTION_RESET);

        PendingIntent playPendingIntent = PendingIntent.getService(this,
                0, playIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pausePendingIntent = PendingIntent.getService(this,
                0, pauseIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent resetPendingIntent = PendingIntent.getService(this,
                0, resetIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        mediaNotification.getActions().add(new NotificationCompat.Action(
                android.R.drawable.ic_media_play, "play", playPendingIntent
        ));
        mediaNotification.getActions().add(new NotificationCompat.Action(
                android.R.drawable.ic_media_pause, "pause", pausePendingIntent
        ));
        mediaNotification.getActions().add(new NotificationCompat.Action(
                android.R.drawable.ic_media_rew, "reset", resetPendingIntent
        ));

        pushNotificationService.show(1, mediaNotification);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startid){
        if (null != intent && intent.getAction() != null) {
            switch (intent.getAction()) {
                case ACTION_PLAY:
                    mPlayer.play();
                    break;
                case ACTION_PAUSE:
                    mPlayer.pause();
                    break;
                case ACTION_RESET:
                    mPlayer.reset();
                    break;
                default:
            }
        }
        return Service.START_STICKY;
    }

    @Override
    protected void onHandleIntent(Intent intent) {

    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        Toast.makeText(this, "onPrepared", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }
}
