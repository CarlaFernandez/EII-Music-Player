package com.eii.eiimusicplayer.media.pojo;

import android.net.Uri;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carla on 02/11/2017.
 */

public class Album {
    private String title;
    private Artist artist;
    private Uri uriArtwork;
    private List<Song> songs;

    public Album(String albumName) {
        this.title = albumName;
        this.songs = new ArrayList<>();
    }

    public String getTitle() {
        return title;
    }

    public Artist getArtist() {
        return artist;
    }

    public Uri getUriArtwork() {
        return uriArtwork;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public void addSong(Song song) {
        if (uriArtwork == null){
            uriArtwork = song.getUriArtwork();
        }

        if (!songs.contains(song)) {
            this.songs.add(song);
            this.artist = song.getArtist();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Album album = (Album) o;

        if (title != null ? !title.equals(album.title) : album.title != null) return false;
        return artist != null ? artist.equals(album.artist) : album.artist == null;

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (artist != null ? artist.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return title + '\n' +
                artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public void setUriArtwork(Uri uriArtwork) {
        this.uriArtwork = uriArtwork;
    }
}
