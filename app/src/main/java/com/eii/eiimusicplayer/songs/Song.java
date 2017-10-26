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
    private String duration;
    private String date;
    private String track;

    private String title;
    private String fullPath;
    private String album;
    private String artist;

    public Song(String title, String artist, String album, String trackNumber,
                String date, String duration, Bitmap bmp, String fullPath) {

        this.title = title;
        this.album = album == null ? NO_VALUE : album;
        this.artist = artist == null ? NO_VALUE : artist;
        this.track = trackNumber == null ? NO_VALUE : trackNumber;
        this.date = date == null ? NO_VALUE : date;
        this.duration = duration == null ? NO_VALUE : duration;
        this.cover = bmp;
        this.fullPath = fullPath;
    }

    public Bitmap getCover() {
        return cover;
    }

    public String getDuration() {
        return duration;
    }

    public String getDate() {
        return date;
    }

    public String getTrack() {
        return track;
    }

    public String getTitle() {
        return title;
    }

    public String getAlbum() {
        return album;
    }

    public String getArtist() {
        return artist;
    }

    public String getFullPath() {
        return fullPath;
    }

    public String toStringBasic() {
        return title + '\n' + artist + '\n' + album + '\n' + date
                +'\n' + track +'\n' + duration;
    }


    public String toStringOther() {
        return title;
    }

    public String toString(){
        return title + '\n' + artist + '\n' + album + '\n' + date
                +'\n' + track +'\n' + duration;
    }

}

