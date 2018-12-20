package fr.wildcodeschool.wildmediaplayerservice.wildplayer;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

import fr.wildcodeschool.wildmediaplayerservice.R;

public class MediaServiceIntent extends IntentService implements WildOnPlayerListener {

    public static final String ACTION_PLAY = "PLAY";
    private static final String ACTION_PAUSE = "PAUSE";
    private static final String ACTION_RESET = "RESET";
    private WildPlayer mPlayer = null;
    static Context AppContext;

    public static Context getAppContext() {
        return AppContext;
    }

    public MediaServiceIntent() {
        super("MediaServiceIntent");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        AppContext = this.getApplicationContext();
        mPlayer = new WildPlayer(this);
        mPlayer.init(R.string.song, this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startid){
        return super.onStartCommand(intent, flags, startid);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
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
            }
        }
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        Toast.makeText(this, "onPrpared", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onCompletion(MediaPlayer mp) {

    }
}