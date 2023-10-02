package com.example.musicplayer;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    ImageView songImage;
    Button playButton;
    boolean isPlaying = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        songImage = findViewById(R.id.imageView);
        playButton = findViewById(R.id.button);
        playButton.setBackgroundResource(R.drawable.button_press);
        playButton.setOnClickListener(view -> {
            if (isPlaying) {
                playButton.setBackgroundResource(R.drawable.button_press);
                isPlaying = false;
            } else  {
                playButton.setBackgroundResource(R.drawable.baseline_pause_24);
                isPlaying = true;
            }
        });
    }

}