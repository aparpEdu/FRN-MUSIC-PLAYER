package com.example.musicplayer.song;

public class Song {
    private final String name;
    private final String authors;
    private final int id;

    public Song(String name, String authors, int id) {
        this.name = name;
        this.authors = authors;
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public String getAuthors() {
        return authors;
    }

    public int getId() {
        return id;
    }
}
