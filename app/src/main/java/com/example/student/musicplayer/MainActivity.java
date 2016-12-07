package com.example.student.musicplayer;

import android.content.Context;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    private MediaPlayer song1;
    private MediaPlayer song2;
    private MediaPlayer song3;
    private MediaPlayer song4;
    private MediaPlayer song5;
    private MediaPlayer song6;
    private MediaPlayer song7;
    private MediaPlayer song8;

    private Button playButton;
    private Button pauseButton;
    private Button stopButton;
    private Button rewindButton;
    private Button forwardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        song1 = MediaPlayer.create(this, R.raw.bass-walker);
        song2 = MediaPlayer.create(this, R.raw.dispersion-relation);
        song3 = MediaPlayer.create(this, R.raw.george-street);
        song4 = MediaPlayer.create(this, R.raw.in-your-arms);
        song5 = MediaPlayer.create(this, R.raw.local-forecast);
        song6 = MediaPlayer.create(this, R.raw.off-to-osoka);
        song7 = MediaPlayer.create(this, R.raw.sidewalk-shuffle);
        song8 = MediaPlayer.create(this, R.raw.spy-glass);

        playButton = (Button)findViewById(R.id.view4);
        pauseButton = (Button)findViewById(R.id.view9);
        stopButton = (Button)findViewById(R.id.view8);
        forwardButton = (Button)findViewById(R.id.view5);
        rewindButton = (Button)findViewById(R.id.view7);

    }

    public void playSong(View view) {
        song1.start();

        pauseButton.setEnabled(true);
        playButton.setEnabled(false);

        Context context = getApplicationContext();
        CharSequence text = "A Song Is Playing";
        int duration = Toast.LENGTH_SHORT;
        Toast myMessage= Toast.makeText(context, text, duration);
        myMessage.show();
    }
}
