package fr.wildcodeschool.wildmediaplayerservice.wildplayer;

import android.media.MediaPlayer;

public interface WildOnPlayerListener {
  void onPrepared(MediaPlayer mp);
  void onCompletion(MediaPlayer mp);
}
