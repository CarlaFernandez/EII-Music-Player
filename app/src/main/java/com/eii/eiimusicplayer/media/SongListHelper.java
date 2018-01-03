package com.eii.eiimusicplayer.media;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.util.Log;

import com.eii.eiimusicplayer.media.pojo.Album;
import com.eii.eiimusicplayer.media.pojo.Artist;
import com.eii.eiimusicplayer.media.pojo.Song;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Creates Song, Artist, Album and Playlist lists from the device's storage
 */

public class SongListHelper {
    private static List<Song> scannedSongs = new ArrayList<>();
    private static List<Song> currentSongList = new ArrayList<>();
    private static List<Album> scannedAlbums = new ArrayList<>();
    private static List<Artist> scannedArtists = new ArrayList<>();
    ;

    // TODO exception management
    public static void saveAllSongsFromExternalStorage(ContentResolver contentResolver) throws IOException {
        if (!isExternalStorageReadable()) {
            throw new IOException("External storage is not readable");
        }

        String[] allCols = {"*"};
        Uri allSongsURI = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String where = MediaStore.Audio.Media.IS_MUSIC + " != 0";

        Cursor cursor = contentResolver.query(allSongsURI, allCols, where, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    String fullPath = cursor.getString(cursor
                            .getColumnIndex(MediaStore.Audio.Media.DATA));
                    String extension = fullPath.substring(fullPath.lastIndexOf("."));

                    if (extension.equalsIgnoreCase(".mp3")) {

                        Song song = createSongWithMediaStore(cursor);

                        if (song != null) {
                            scannedSongs.add(song);
                        } else {
                            String displayName = cursor.getString(cursor
                                    .getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                            Log.e("ERROR", displayName);
                        }
                    }
                }
                while (cursor.moveToNext());

                cursor.close();
            }
        }
        currentSongList.addAll(scannedSongs);
        mapAlbumsAndArtists();

    }

    /* Checks if external storage is available to at least read */

    private static boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }


    @NonNull
    private static Song createSongWithMediaStore(Cursor cursor) {
        String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
        String albumName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
        String artistName = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
        long duration = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
        String trackString = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TRACK));
        String date = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.YEAR));
        String fullPath = cursor.getString(cursor
                .getColumnIndex(MediaStore.Audio.Media.DATA));
        int albumIdIndex = cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM_ID);
        int albumId = cursor.getInt(albumIdIndex);

        Uri sArtworkUri = Uri.parse("content://media/external/audio/albumart");
        Uri uriArtwork = ContentUris.withAppendedId(sArtworkUri, albumId);

        int trackNumber;
        if (trackString.length() == 4) {
            trackNumber = Integer.parseInt(trackString.substring(1));
        } else {
            trackNumber = Integer.parseInt(trackString);
        }

        return new Song(title, artistName, albumName, String.valueOf(trackNumber),
                date, String.valueOf(duration), fullPath, uriArtwork);
    }

    private static void mapAlbumsAndArtists() {

        for (Song song : scannedSongs) {

            Album album = song.getAlbum();
            Artist artist = song.getArtist();
            album.setArtist(artist);
            album.setUriArtwork(song.getUriArtwork());

            if (!scannedAlbums.contains(album)) {
                album.addSong(song);

                if (scannedArtists.contains(artist)) {
                    artist = findArtist(artist.getName());
                }

                album.setArtist(artist);
                artist.addAlbum(album);

                if (!scannedArtists.contains(artist)) {
                    scannedArtists.add(artist);
                }

                scannedAlbums.add(album);

            } else {
                Album albumAlreadySeen = findAlbum(album.getTitle());
                if (albumAlreadySeen != null) {
                    albumAlreadySeen.addSong(song);
                }
            }
        }
    }

    private static Artist findArtist(String name) {
        for (Artist a : getScannedArtists()) {
            if (a.getName().equalsIgnoreCase(name)) {
                return a;
            }
        }
        return null;
    }

    private static Album findAlbum(String title) {
        for (Album a : scannedAlbums) {
            if (a.getTitle().equalsIgnoreCase(title)) {
                return a;
            }
        }
        return null;
    }

    public static List<Song> getScannedSongs() {
        Log.i("SONG", "returning " + scannedSongs.size() + " scannedSongs");
        return currentSongList;
    }

    public static List<Album> getScannedAlbums() {
        Log.i("ALBUM", "returning " + scannedAlbums.size() + " scannedAlbums");
        return scannedAlbums;
    }

    public static List<Artist> getScannedArtists() {
        Log.i("ARTIST", "returning " + scannedArtists.size() + " scannedArtists");
        return scannedArtists;
    }
}