package com.eii.eiimusicplayer.songs;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.net.Uri;
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
        int counter = 0;

        Cursor cursor = contentResolver.query(allSongsURI, allCols, where, null, null);

        // TODO quitar la limitacion del cursor para que se carguen TODAS las canciones
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    String fullPath = cursor.getString(cursor
                            .getColumnIndex(MediaStore.Audio.Media.DATA));
                    String extension = fullPath.substring(fullPath.lastIndexOf("."));

                    if (extension.equalsIgnoreCase(".mp3")) {
//                        String displayName = cursor.getString(cursor
//                                .getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));
//
//                        int songId = cursor.getInt(cursor
//                                .getColumnIndex(MediaStore.Audio.Media._ID));
//
//
//                        String albumName = cursor.getString(cursor
//                                .getColumnIndex(MediaStore.Audio.Media.ALBUM));
//
//                        int albumId = cursor.getInt(cursor
//                                .getColumnIndex(MediaStore.Audio.Media.ALBUM_ID));
//
//                        String artistName = cursor.getString(cursor
//                                .getColumnIndex(MediaStore.Audio.Media.ARTIST));
//
//                        int artistId = cursor.getInt(cursor
//                                .getColumnIndex(MediaStore.Audio.Media.ARTIST_ID));
//
//                        Song song = new Song(displayName, extension, songId, fullPath,
//                                albumName, albumId, artistName, artistId);

                        Song song = createSongWithMetaData(fullPath);
                        if (song != null) {
                            songs.add(song);
                            counter += 1;
                        }

                    }

                }
                while (cursor.moveToNext() && counter < 200);

                cursor.close();
            }

        }
    }
//    }

    private static Song createSongWithMetaData(String fullPath) {
        MediaMetadataRetriever mmr = new MediaMetadataRetriever();
        mmr.setDataSource(fullPath);
        Song song = null;

        String title = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE);
        if (title != null) {
            String artist = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST);
            String album = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM);
            String trackNumber = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_CD_TRACK_NUMBER);
            String date = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DATE);
            String duration = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION);
            String genre = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_GENRE);

//            byte[] artwork = mmr.getEmbeddedPicture();
//            Bitmap bmp = null;
//            if (artwork != null) {
//                bmp = BitmapFactory.decodeByteArray(artwork, 0, artwork.length);
//            }

            song = new Song(title, artist, album, trackNumber, date, duration, genre, null, fullPath);
        }

        mmr.release();
        return song;
    }

    public static List<Song> getScannedSongs() {
        Log.i("SONG", "returning " + songs.size() + " songs");
        return songs;
    }
}
