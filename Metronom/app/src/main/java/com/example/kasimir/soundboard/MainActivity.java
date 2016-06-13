package com.example.kasimir.soundboard;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    MediaPlayer mediaPlayer; //Definition eines neuen MediaPlayers

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void buttonVibrate(View view){

        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE); //Vibrator

        if (v.hasVibrator()) {              //Gerät kann vibrieren
            Log.v("Kann vibrieren", "YES");
            v.vibrate(500);                 //Vibrator vibriert für 500ms
        } else {
            Log.v("Kann vibrieren", "NO");
        }
    }

    public void buttonCowbell(View view){
        mediaPlayer = MediaPlayer.create(this, R.raw.cowbell); //Aufrufen von 'cowbell'
        playSound();
    }

    public void buttonCow(View view){
        mediaPlayer = MediaPlayer.create(this, R.raw.cow);
        playSound();

    }

    public void playSound(){
        mediaPlayer.start();                                                         //Start Sound

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){  //Warten auf Ende
            public void onCompletion(MediaPlayer mediaPlayer) {                      //Sound Ende
                mediaPlayer.release();                                               //Zurücksetzen MediaPlayer
            }});
    }
}
