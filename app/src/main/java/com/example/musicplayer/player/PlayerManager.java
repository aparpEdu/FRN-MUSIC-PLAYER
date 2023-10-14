package com.example.musicplayer.player;

import com.example.musicplayer.song.Song;
import com.example.musicplayer.song.SongManager;

import java.util.List;

public class PlayerManager {

    private static PlayerManager instance = null;
    private final List<Song> songsToPlay;
    private int currentSongIndex = 0;
    private double startTime = 0;
    private double finalTime = 0;
    private double oneTimeOnly = 0;




    private PlayerManager(){
       this.songsToPlay =  SongManager.addSongs();
    }

    public static PlayerManager getInstance(){
        if(instance == null){
           instance = new PlayerManager();
        }
        return instance;
    }


    public int getCurrentSongIndex() {
        return currentSongIndex;
    }

    public void setCurrentSongIndex(int currentSongIndex) {
        this.currentSongIndex = currentSongIndex;
    }

    public double getStartTime() {
        return startTime;
    }

    public double getFinalTime() {
        return finalTime;
    }

    public double getOneTimeOnly() {
        return oneTimeOnly;
    }

    public void setStartTime(double startTime) {
        this.startTime = startTime;
    }

    public void setFinalTime(double finalTime) {
        this.finalTime = finalTime;
    }

    public void setOneTimeOnly(double oneTimeOnly) {
        this.oneTimeOnly = oneTimeOnly;
    }

    public List<Song> getSongsToPlay() {
        return songsToPlay;
    }
}
