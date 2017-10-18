package com.eii.eiimusicplayer;

import android.os.Parcelable;

/**
 * Created by Carla on 18/10/2017.
 */

public class Song {
    private String displayName;
    private String songName;
    private int id;
    private String fullPath;
    private String albumName;
    private int albumId;
    private String artistName;
    private int artistId;

    public Song(String displayName, String extension, int id, String fullPath, String albumName,
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

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getDisplayName() {
        return displayName;
    }

    private void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getFullPath() {
        return fullPath;
    }

    private void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public String getAlbumName() {
        return albumName;
    }

    private void setAlbumName(String albumName) {
        this.albumName = albumName;
    }

    public int getAlbumId() {
        return albumId;
    }

    private void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public String getArtistName() {
        return artistName;
    }

    private void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public int getArtistId() {
        return artistId;
    }

    private void setArtistId(int artistId) {
        this.artistId = artistId;
    }

    public String toStringBasic() {
        return songName + '\n' + artistName;
    }
}

