package com.example.student.musicplayer;

import android.content.Context;
import android.content.Intent;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer song1;


    private Button playButton;
    private Button pauseButton;
    private Button stopButton;
    private Button rewindButton;
    private Button forwardButton;

    private TextView currentTimeView;
    private TextView totalTimeView;
    private TextView title;
    private TextView author;

    private double currentTimeMS;
    private double totalTimeMS;
    private Handler time = new Handler();
    private SeekBar seek;
    private int seekTime = 0;

    Intent thisIntent = getIntent();
    String songId = thisIntent.getStringExtra("songMessage");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        song1 =  MediaPlayer.create(this, Integer.parseInt(songId));


        playButton = (Button) findViewById(R.id.play);
        pauseButton = (Button) findViewById(R.id.pause);
        stopButton = (Button) findViewById(R.id.stop);
        forwardButton = (Button) findViewById(R.id.foward);
        rewindButton = (Button) findViewById(R.id.rewind);

        title = (TextView) findViewById(R.id.songTitle);
        author = (TextView) findViewById(R.id.artistTitle);

        totalTimeMS = song1.getDuration();

        int totalMinutes = (int) (totalTimeMS / 1000 / 60);
        int totalSeconds = ((int) (totalTimeMS / 1000)) % 60;

        totalTimeView = (TextView) findViewById(R.id.totalTime);
        totalTimeView.setText(totalMinutes + " min, " + totalSeconds + " sec");

        MediaMetadataRetriever songInfo = new MediaMetadataRetriever();

        Uri filepath = Uri.parse("android.resource://" + getPackageName() + "/" + songId);
        songInfo.setDataSource(this, filepath);

        String songTitle = songInfo.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
        String songAuthor = songInfo.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);

        title.setText(songTitle);
        author.setText(songAuthor);

        seek = (SeekBar) findViewById(R.id.seeker);
        seek.setMax((int) totalTimeMS);

        time.postDelayed(UpdateSongTime, 100);

        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener()
        {
            @Override
            public void onProgressChanged (SeekBar seekBar,int progress, boolean fromUser){
                seekTime = progress;
            }

            @Override
            public void onStartTrackingTouch (SeekBar seekBar){

            }

            @Override
            public void onStopTrackingTouch (SeekBar seekBar){
                song1.seekTo( seekTime);
                currentTimeMS = seekTime;
            }
        }

        );
    }

    public void playSong(View view) {
        song1.start();

        pauseButton.setEnabled(true);
        stopButton.setEnabled(true);
        playButton.setEnabled(false);

        Context context = getApplicationContext();
        CharSequence text = "A Song Is Playing";
        int duration = Toast.LENGTH_SHORT;
        Toast myMessage = Toast.makeText(context, text, duration);
        myMessage.show();
    }

    public void pauseSong(View view) {
        song1.pause();

        Toast.makeText(getApplicationContext(), "Stopping song.", Toast.LENGTH_SHORT).show();
        stopButton.setEnabled(true);
        pauseButton.setEnabled(false);
        playButton.setEnabled(true);
    }

    public void stopSong(View view) {
        song1.seekTo(0);
        song1.pause();

        playButton.setEnabled(true);
        pauseButton.setEnabled(false);
        stopButton.setEnabled(false);

        Context context = getApplicationContext();
        CharSequence text = "Song stopped.";
        int duration = Toast.LENGTH_SHORT;
        Toast stopMessage = Toast.makeText(context, text, duration);
        stopMessage.show();
    }

    public void rewindSong(View view) {
        song1.seekTo((int) (currentTimeMS - 5000));
    }

    public void forwardSong(View view) {
        song1.seekTo((int) (currentTimeMS + 5000));
    }

    private Runnable UpdateSongTime = new Runnable() {
        public void run() {
            currentTimeMS = song1.getCurrentPosition();

            seek.setProgress((int) currentTimeMS);

            int currentMinutes = (int) (currentTimeMS / 1000 / 60);
            int currentSeconds = ((int) (currentTimeMS / 1000)) % 60;

            currentTimeView = (TextView) findViewById(R.id.currentTime);
            currentTimeView.setText(currentMinutes + " min, " + currentSeconds + " sec");

            if (currentTimeMS > 5000) {
                rewindButton.setEnabled(true);
            } else {
                rewindButton.setEnabled(false);
            }

            if (currentTimeMS < totalTimeMS - 5000) {
                forwardButton.setEnabled(true);
            } else {
                forwardButton.setEnabled(false);
            }

            time.postDelayed(this, 100);
        }
    };

}
