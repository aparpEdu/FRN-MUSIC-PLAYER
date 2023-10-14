package com.example.musicplayer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.musicplayer.player.PlayerManager;
import com.example.musicplayer.song.Song;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    ImageView songImage;
    Button playButton, skipPreviousButton, skipNextButton;
    TextView songPlayingText, authorsText, currentSongTime;
    SeekBar seekBar;
    MediaPlayer mediaPlayer;
    Handler handler = new Handler();
    PlayerManager playerManager = PlayerManager.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        songImage = findViewById(R.id.imageView);
        playButton = findViewById(R.id.button);
        skipPreviousButton = findViewById(R.id.button2);
        skipNextButton = findViewById(R.id.button3);
        songPlayingText = findViewById(R.id.textView);
        currentSongTime = findViewById(R.id.time);
        authorsText = findViewById(R.id.textView2);
        seekBar = findViewById(R.id.seekBar);

        mediaPlayer = MediaPlayer.create(getApplicationContext(),
                PlayerManager.getInstance().getSongsToPlay().get(0).getId());

        playButton.setOnClickListener(view -> playSong());
        setSongInfo();

        skipNextButton.setOnClickListener(view ->{
            mediaPlayer.stop();
            if(playerManager.getCurrentSongIndex() < playerManager.getSongsToPlay().size() - 1) {
                mediaPlayer = MediaPlayer.create(getApplicationContext(),
                        PlayerManager.getInstance()
                                .getSongsToPlay().get(playerManager.getCurrentSongIndex() + 1).getId());
                playerManager.setCurrentSongIndex(playerManager.getCurrentSongIndex() + 1);
            } else{
                mediaPlayer = MediaPlayer.create(getApplicationContext(),
                        PlayerManager.getInstance()
                                .getSongsToPlay().get(0).getId());
                playerManager.setCurrentSongIndex(0);
            }
            playSong();
            setSongInfo();
        });


        skipPreviousButton.setOnClickListener(view -> {
            mediaPlayer.stop();
            if(playerManager.getCurrentSongIndex() > 0){

                mediaPlayer = MediaPlayer.create(getApplicationContext(),
                        PlayerManager.getInstance()
                                .getSongsToPlay().get(playerManager.getCurrentSongIndex() - 1).getId());
                playerManager.setCurrentSongIndex(playerManager.getCurrentSongIndex() - 1);

            } else{
                mediaPlayer = MediaPlayer.create(getApplicationContext(),
                        PlayerManager.getInstance()
                                .getSongsToPlay().get(playerManager.getSongsToPlay().size() - 1).getId());
                playerManager.setCurrentSongIndex(playerManager.getSongsToPlay().size() - 1);
            }

            playSong();
            setSongInfo();
        });
    }


    private void playSong() {

        playerManager.setFinalTime(mediaPlayer.getDuration());
        playerManager.setStartTime(mediaPlayer.getCurrentPosition());
        seekBar.setClickable(false);

        if (playerManager.getOneTimeOnly() == 0) {
            seekBar.setMax((int) playerManager.getFinalTime());
            playerManager.setOneTimeOnly(1);
        }

        long totalDuration = mediaPlayer.getDuration();
        String totalTime = String.format(Locale.getDefault(), "%d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(totalDuration),
                TimeUnit.MILLISECONDS.toSeconds(totalDuration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(totalDuration)));

        long startTime = (long) playerManager.getStartTime();
        long minutes = TimeUnit.MILLISECONDS.toMinutes(startTime);
        long seconds = TimeUnit.MILLISECONDS.toSeconds(startTime) - TimeUnit.MINUTES.toSeconds(minutes);

        String formattedTime = String.format(Locale.getDefault(), "%d:%02d", minutes, seconds);

        String fraction = formattedTime + " / " + totalTime;

        currentSongTime.setText(fraction);

        seekBar.setProgress((int) playerManager.getStartTime());
        handler.postDelayed(updateSongTIme, 100);
        changeState();

    }

    private final Runnable updateSongTIme = new Runnable() {
        @Override
        public void run() {
            playerManager.setStartTime(mediaPlayer.getCurrentPosition());
            long startTime = (long) playerManager.getStartTime();
            long minutes = TimeUnit.MILLISECONDS.toMinutes(startTime);
            long seconds = TimeUnit.MILLISECONDS.toSeconds(startTime) - TimeUnit.MINUTES.toSeconds(minutes);

            long totalDuration = mediaPlayer.getDuration();
            String totalTime = String.format(Locale.getDefault(), "%d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(totalDuration),
                    TimeUnit.MILLISECONDS.toSeconds(totalDuration) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(totalDuration)));

            String formattedTime = String.format(Locale.getDefault(), "%d:%02d", minutes, seconds);

            String fraction = formattedTime + " / " + totalTime;

            currentSongTime.setText(fraction);

            seekBar.setProgress((int) playerManager.getStartTime());
            handler.postDelayed(this, 100);
        }
    };

    private void changeState(){
        if (mediaPlayer.isPlaying()) {
            playButton.setBackgroundResource(R.drawable.button_play);
            mediaPlayer.pause();
        } else {
            playButton.setBackgroundResource(R.drawable.button_pause);
            mediaPlayer.start();
        }
    }

    private void setSongInfo(){
        Song songToPlay = playerManager.getSongsToPlay().get(playerManager.getCurrentSongIndex());
        songPlayingText.setText(songToPlay.getName());
        authorsText.setText(songToPlay.getAuthors());
    }


}