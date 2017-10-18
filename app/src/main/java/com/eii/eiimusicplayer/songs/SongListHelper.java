package com.eii.eiimusicplayer.songs;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;
import android.provider.MediaStore;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Carla on 18/10/2017.
 */

public class SongListHelper {
//    public final static String NO_VALUE = "Unknown";
    private static List<Song> songs = new ArrayList<>();

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
                        String displayName = cursor.getString(cursor
                                .getColumnIndex(MediaStore.Audio.Media.DISPLAY_NAME));

                        int songId = cursor.getInt(cursor
                                .getColumnIndex(MediaStore.Audio.Media._ID));


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

                }
                while (cursor.moveToNext());

                cursor.close();
            }
        }
    }
//    }

//    private static Song createSongWithMetaData(String fullPath) {
//        FFmpegMediaMetadataRetriever mmr = new FFmpegMediaMetadataRetriever();
//        mmr.setDataSource(fullPath);
//        Song song = null;
//
//        String title = mmr.extractMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_TITLE);
//        if (title != null) {
//
//            String artist = mmr.extractMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_ARTIST);
//            String album = mmr.extractMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_ALBUM);
//            String albumArtist = mmr.extractMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_ALBUM_ARTIST);
//            String trackNumber = mmr.extractMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_TRACK);
//            String date = mmr.extractMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_DATE);
//            String duration = mmr.extractMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_DURATION);
//            String genre = mmr.extractMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_GENRE);
//
//            String tweakedArtist = artist == null ? NO_VALUE : artist;
//            String tweakedAlbum = album == null ? NO_VALUE : album;
//            String tweakedAlbumArtist = albumArtist == null ? NO_VALUE : albumArtist;
//            String tweakedTrack = trackNumber == null ? NO_VALUE : trackNumber;
//            String tweakedDate = date == null ? NO_VALUE : date;
//            String tweakedDuration = duration == null ? NO_VALUE : duration;
//            String tweakedGenre = genre == null ? NO_VALUE : genre;
//
//            byte[] artwork = mmr.getEmbeddedPicture();
//            Bitmap bmp = null;
//            if (artwork != null) {
//                bmp = BitmapFactory.decodeByteArray(artwork, 0, artwork.length);
//            }
//
//            song = new Song(title, tweakedArtist, tweakedAlbum, tweakedAlbumArtist,
//                    tweakedTrack, tweakedDate, tweakedDuration, tweakedGenre, bmp);
//
//        }
//
//        mmr.release();
//
//        return song;
//    }

    public static List<Song> getScannedSongs() {
        Log.i("SONG", "returning " + songs.size() + " songs");
        return songs;
    }
}
