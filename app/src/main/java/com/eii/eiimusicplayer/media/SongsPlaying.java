package com.eii.eiimusicplayer.media;

import android.content.Context;
import android.util.Log;

import com.eii.eiimusicplayer.media.pojo.Song;

import java.util.List;

/**
 * Class that holds the current playlist and song
 */

public class SongsPlaying {
    private static SongsPlaying songsPlaying;

    private List<Song> playlist;
    private int current;

    private SongsPlaying() {

    }

    public static SongsPlaying getInstance() {
        if (songsPlaying == null) {
            songsPlaying = new SongsPlaying();
        }
        return songsPlaying;
    }

    public void setCurrentPlaylistAndSong(Context context, List<Song> songs, int position) throws Exception {
        this.playlist = songs;
        this.current = position;

        checkCurrentPosition();

        Log.i("PLAYLIST", "playlist size " + playlist.size());
        Log.i("PLAYLIST", "current" + current);
    }

    private void checkCurrentPosition() throws Exception {
        if (current >= playlist.size()) {
            // TODO exception management
            throw new Exception();
        }
    }

    public void setCurrentSong(int position) throws Exception {
        this.current = position;
        checkCurrentPosition();
    }

    //TODO this should be placed in MediaPlayerManager
    public void playNextSong(Context context) {
        if (playlist != null && !playlist.isEmpty()) {
            if (current == playlist.size() - 1) {
                current = 0;
            } else {
                current += 1;
            }
            Song next = playlist.get(current);

            Log.i("PLAYLIST", String.valueOf(current));
            MediaPlayerManager.getInstance().playSong(context, next);
        }
    }

    public void playPreviousSong(Context context) {
        // TODO guardar ultima lista y puntero
        if (playlist != null && playlist.size() > 1) {
            if (current == 0) {
                current = playlist.size() - 1;
            } else {
                current -= 1;
            }
            Song previous = playlist.get(current);
            Log.i("PLAYLIST", String.valueOf(current));
            MediaPlayerManager.getInstance().playSong(context, previous);
        }
    }

    public Song getSongPlaying() {
        //TODO exceptions??
        if (playlist != null && !playlist.isEmpty()) {
            return playlist.get(current);
        } else {
            return null;
        }
    }
}
