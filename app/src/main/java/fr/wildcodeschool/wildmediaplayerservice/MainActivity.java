package fr.wildcodeschool.wildmediaplayerservice;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import fr.wildcodeschool.wildmediaplayerservice.notification.MediaNotificationBuilder;
import fr.wildcodeschool.wildmediaplayerservice.notification.NotificationChannel;
import fr.wildcodeschool.wildmediaplayerservice.notification.NotificationItem;
import fr.wildcodeschool.wildmediaplayerservice.notification.PushNotification;
import fr.wildcodeschool.wildmediaplayerservice.wildplayer.MediaServiceIntent;

import static fr.wildcodeschool.wildmediaplayerservice.wildplayer.MediaServiceIntent.ACTION_PLAY;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button playButton =  findViewById(R.id.button_play);
        Button pauseButton =  findViewById(R.id.button_pause);
        Button stopButton =  findViewById(R.id.button_stop);
        playButton.setOnClickListener(this);
        pauseButton.setOnClickListener(this);
        stopButton.setOnClickListener(this);
        Intent service = new Intent(this, MediaServiceIntent.class);
        startService(service);

        /*NotificationItem notificationItem = new NotificationItem(new NotificationChannel("Default", R.string.channel_name ),
                                            "Title",
                                            "Description",
                                            R.drawable.ic_launcher_background);
        PushNotification<NotificationItem> pushNotification = new PushNotification<>(this, new MediaNotificationBuilder<NotificationItem>());
        pushNotification.show(1, notificationItem);*/
    }

    @Override
    public void onClick(View v) {
        int id= v.getId();

        switch (id){
            case R.id.button_play : onClickPlay(); break;
            case R.id.button_pause : onClickPause(); break;
            case R.id.button_stop : onClickStop(); break;
            default: throw new IllegalArgumentException(String.format(getString(R.string.bad_id_exception_text) , id) );
        }
    }

    private void onClickPlay() {
        Intent i = new Intent(this, MediaServiceIntent.class);
        i.setAction(ACTION_PLAY);
        startService(i);
    }

    protected void  onClickPause(){

    }

    private void onClickStop() {
    }




}
