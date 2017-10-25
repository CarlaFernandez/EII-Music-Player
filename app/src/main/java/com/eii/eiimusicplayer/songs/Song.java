package com.eii.eiimusicplayer.songs;

import android.graphics.Bitmap;

/**
 * Created by Carla on 18/10/2017.
 */

public class Song {
//    private String title;
//    private String artist;
//    private String album;
//    private String albumArtist;
//    private String trackNumber;
//    private Date date;
//    private String duration;
//    private String genre;
//    private Bitmap bitmap;

    public final static String NO_VALUE = "Unknown";
    private Bitmap cover;
    private String genre;
    private String duration;
    private String date;
    private String track;

    private String displayName;
    private String songName;
    private int id;
    private String fullPath;
    private String albumName;
    private int albumId;
    private String artistName;
    private int artistId;

    protected Song(String displayName, String extension, int id, String fullPath, String albumName,
                   int albumId, String artistName, int artistId) {
        this.displayName = displayName;
        this.songName = displayName.replace(extension, "");
        this.id = id;
        this.fullPath = fullPath;
        this.albumName = albumName;
        this.albumId = albumId;
        this.artistName = artistName;
        this.artistId = artistId;
    }

    public Song(String title, String artist, String album, String trackNumber,
                String date, String duration, String genre, Bitmap bmp, String fullPath) {

        this.displayName = title;
        this.albumName = album == null ? NO_VALUE : album;
        this.artistName = artist == null ? NO_VALUE : artist;
        this.track = trackNumber == null ? NO_VALUE : trackNumber;
        this.date = date == null ? NO_VALUE : date;
        this.duration = duration == null ? NO_VALUE : duration;
        this.genre = genre == null ? NO_VALUE : genre;
        this.cover = bmp;
        this.fullPath = fullPath;
    }


    public String toStringBasic() {
        return displayName + '\n' + artistName;
    }


}

