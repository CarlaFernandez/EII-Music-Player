package com.eii.eiimusicplayer.media;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.ImageView;

import com.eii.eiimusicplayer.media.pojo.Album;
import com.eii.eiimusicplayer.media.pojo.Artist;
import com.eii.eiimusicplayer.media.pojo.Song;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carla on 18/10/2017.
 */

public class SongListHelper {
    private static List<Song> scannedSongs = new ArrayList<>();
    private static List<Song> currentSongList = new ArrayList<>();
    private static List<Album> scannedAlbums = new ArrayList<>();
    private static List<Artist> scannedArtists = new ArrayList<>();
    ;

    // TODO exception management
    public static void saveAllSongsFromExternalStorage(ContentResolver contentResolver) {
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

        // TODO Add album cover. Didn't delete "fullPath" parameter, just in case we need it to retrieve cover
        // TODO better "Unknown" management?
        return new Song(title, artistName, albumName, String.valueOf(trackNumber),
                date, String.valueOf(duration), fullPath, uriArtwork);
    }

    private static void mapAlbumsAndArtists() {
        for (Song song : scannedSongs) {
            Album album = song.getAlbum();
            Artist artist = song.getArtist();

            album.addSong(song);
            artist.addAlbum(album);

            if (!scannedAlbums.contains(album)) {
                scannedAlbums.add(album);
            }
            if (!scannedArtists.contains(artist)) {
                scannedArtists.add(artist);
            }
        }
    }

    public static List<Song> getScannedSongs() {
        Log.i("SONG", "returning " + scannedSongs.size() + " scannedSongs");
        // TODO order
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