package fr.wildcodeschool.wildmediaplayerservice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
    }

    protected void  onClickPause(){

    }

    private void onClickStop() {
    }




}
