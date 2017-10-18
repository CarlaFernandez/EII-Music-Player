package com.eii.eiimusicplayer.songs;

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

//    protected Song(String title, String artist, String album, String albumArtist,
//                String trackNumber, String date, String duration, String genre, Bitmap bmp) {
//        this.title = title;
//        this.artist = artist;
//        this.album = album;
//        this.albumArtist = albumArtist;
//        this.trackNumber = trackNumber;
//        setDate(date);
//        this.duration = duration;
//        this.genre = genre;
//        this.bitmap = bmp;
//    }
//
//
//    public String getTitle() {
//        return title;
//    }
//
//    public String getArtist() {
//        return artist;
//    }
//
//    public String getAlbum() {
//        return album;
//    }
//
//    public String getAlbumArtist() {
//        return albumArtist;
//    }
//
//    public String getTrackNumber() {
//        return trackNumber;
//    }
//
//    public Date getDate() {
//        return date;
//    }
//
//    public String getDuration() {
//        return duration;
//    }
//
//    public String getGenre() {
//        return genre;
//    }
//
//    public Bitmap getBitmap() {
//        return bitmap;
//    }

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
        return displayName + '\n' + artistName;
    }

//    public void setDate(String date) {
//        if (date == SongListHelper.NO_VALUE){
//            this.date = null;
//        }
//        else {
//            this.date = new Date();
//        }
//    }
}

