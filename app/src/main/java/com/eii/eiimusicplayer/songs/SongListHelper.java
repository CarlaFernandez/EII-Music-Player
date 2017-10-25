package com.eii.eiimusicplayer.songs;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carla on 18/10/2017.
 */

public class SongListHelper {
    private static List<Song> songs = new ArrayList<>();

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
                            songs.add(song);
                        } else {
                            String displayName = cursor.getString(cursor
                                    .getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                            Log.i("ERROR", displayName);
                        }
                    }
                }
                while (cursor.moveToNext());

                cursor.close();
            }
        }
    }

    @NonNull
    private static Song createSongWithMediaStore(Cursor cursor) {
        String title = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TITLE));
        String album = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ALBUM));
        String artist = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.ARTIST));
        long duration = cursor.getLong(cursor.getColumnIndex(MediaStore.Audio.Media.DURATION));
        String trackString = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.TRACK));
        String date = cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.YEAR));
        int trackNumber;
        if (trackString.length() == 4) {
            trackNumber = Integer.parseInt(trackString.substring(1));
        } else {
            trackNumber = Integer.parseInt(trackString);
        }

        // TODO Add album cover. Didn't delete "fullPath" parameter, just in case we need it to retrieve cover
        // TODO better "Unknown" management?
        return new Song(title, artist, album, String.valueOf(trackNumber),
                date, String.valueOf(duration), null, null);
    }

    public static List<Song> getScannedSongs() {
        Log.i("SONG", "returning " + songs.size() + " songs");
        return songs;
    }
}