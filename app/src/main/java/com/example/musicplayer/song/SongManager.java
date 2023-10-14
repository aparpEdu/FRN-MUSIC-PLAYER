package com.example.musicplayer.song;

import com.example.musicplayer.R;

import java.util.ArrayList;
import java.util.List;

public class SongManager {
    public static List<Song> addSongs(){
        List<Song> songs = new ArrayList<>();
        songs.add(new Song("Real Talk", "Farona feat. IVKATA 2K", R.raw.ivkata_frn));
        songs.add(new Song("Amazing", "Farona feat. moneykena & genshow", R.raw.amazing));
        songs.add(new Song("Hi B@$TCH", "Farona", R.raw.hi_bitch));
        return songs;
    }
}
