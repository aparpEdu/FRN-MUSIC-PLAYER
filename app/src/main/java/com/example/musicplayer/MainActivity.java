package com.example.musicplayer;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    ImageView songImage;
    Button playButton;
    Button skipPreviousButton;
    Button skipNextButton;
    boolean isPlaying = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        songImage = findViewById(R.id.imageView);
        playButton = findViewById(R.id.button);
        skipPreviousButton = findViewById(R.id.button2);
        skipNextButton = findViewById(R.id.button3);
        playButton.setOnClickListener(view -> {
            if (isPlaying) {
                playButton.setBackgroundResource(R.drawable.button_play);
                isPlaying = false;
            } else  {
                playButton.setBackgroundResource(R.drawable.button_pause);
                isPlaying = true;
            }
        });
    }

}