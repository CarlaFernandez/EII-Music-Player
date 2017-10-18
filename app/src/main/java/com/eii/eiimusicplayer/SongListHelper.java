package com.eii.eiimusicplayer;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carla on 18/10/2017.
 */

public class SongListHelper {
    private static List<Song> songs = new ArrayList<>();

    public static void saveAllSongsFromExternalStorage(ContentResolver contentResolver) {
        String[] allCols = {"*"};
        Uri allSongsURI = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        String where = MediaStore.Audio.Media.IS_MUSIC + " != 0";

        Cursor cursor = contentResolver.query(allSongsURI, allCols, where, null, null);

        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    String displayName = cursor.getString(cursor
                            .getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
                    String extension = displayName.substring(displayName.lastIndexOf("."));

                    if (extension.equalsIgnoreCase(".mp3")) {
                        int songId = cursor.getInt(cursor
                                .getColumnIndex(MediaStore.Audio.Media._ID));

                        String fullPath = cursor.getString(cursor
                                .getColumnIndex(MediaStore.Audio.Media.DATA));

                        String albumName = cursor.getString(cursor
                                .getColumnIndex(MediaStore.Audio.Media.ALBUM));

                        int albumId = cursor.getInt(cursor
                                .getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));

                        String artistName = cursor.getString(cursor
                                .getColumnIndex(MediaStore.Audio.Media.ARTIST));

                        int artistId = cursor.getInt(cursor
                                .getColumnIndex(MediaStore.Audio.Media.ARTIST_ID));

                        Song song = new Song(displayName, extension, songId, fullPath,
                                albumName, albumId, artistName, artistId);
                        songs.add(song);
                    }

                } while (cursor.moveToNext());
            }
            cursor.close();
        }
    }

    public static List<Song> getScannedSongs() {
        Log.i("SONG", "returning " + songs.size() + " songs");
        return songs;
    }
}
