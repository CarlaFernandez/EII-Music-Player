package com.eii.eiimusicplayer.media.pojo;

/**
 * Created by Carla on 18/10/2017.
 */

public class Song {
    public final static String NO_VALUE = "Unknown";
    private String duration;
    private String date;
    private String track;

    private String title;
    private String fullPath;
    private Album album;
    private Artist artist;

    public Song(String title, String artist, String album, String trackNumber,
                String date, String duration, String fullPath) {

        this.title = title;
        this.album = album == null ? new Album(NO_VALUE) : new Album(album);
        this.artist = artist == null ? new Artist(NO_VALUE) : new Artist(artist);
        this.track = trackNumber == null ? NO_VALUE : trackNumber;
        this.date = date == null ? NO_VALUE : date;
        this.duration = duration == null ? NO_VALUE : duration;
        this.fullPath = fullPath;
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

    public Album getAlbum() {
        return album;
    }

    public Artist getArtist() {
        return artist;
    }

    public String getFullPath() {
        return fullPath;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Song song = (Song) o;

        if (title != null ? !title.equals(song.title) : song.title != null) return false;
        if (album != null ? !album.equals(song.album) : song.album != null) return false;
        return artist != null ? artist.equals(song.artist) : song.artist == null;

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (album != null ? album.hashCode() : 0);
        result = 31 * result + (artist != null ? artist.hashCode() : 0);
        return result;
    }

    public String toStringOther() {
        return title;
    }

    public String toString(){
        return title + '\n' + artist + '\n' + album + '\n' + date
                +'\n' + track +'\n' + duration;
    }

}

