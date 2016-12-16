package com.example.student.musicplayer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SongPicker extends AppCompatActivity {

    private Button pickSong1;
    private Button pickSong2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_picker);

        pickSong1 = (Button) findViewById(R.id.pick1);
        pickSong2 = (Button) findViewById(R.id.pick2);
    }

    public void pickSong(View view) {
        Intent launchSongPlayer = new Intent(this, MainActivity.class);
        startActivity(launchSongPlayer);

        String message = String.valueOf(R.raw.dispersion_relation);
        launchSongPlayer.putExtra("songMessage", message);
    }

    public void pickSong2(View view) {
        Intent launchSongPlayer = new Intent(this, MainActivity.class);
        startActivity(launchSongPlayer);

        String message = String.valueOf(R.raw.local_forecast);
        launchSongPlayer.putExtra("songMessage", message);
    }
}
