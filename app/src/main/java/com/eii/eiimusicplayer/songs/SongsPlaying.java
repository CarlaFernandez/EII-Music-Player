package com.eii.eiimusicplayer.songs;

import android.content.Context;
import android.util.Log;

import java.util.List;

/**
 * Created by Carla on 26/10/2017.
 */

public class SongsPlaying {
    private static SongsPlaying songsPlaying;

    private List<Song> playlist;
    private int current;
    private Context context;

    private SongsPlaying() {

    }

    public static SongsPlaying getInstance() {
        if (songsPlaying == null) {
            songsPlaying = new SongsPlaying();
        }
        return songsPlaying;
    }

    public void setCurrentPlaylistAndSong(Context context, List<Song> songs, int position) throws Exception {
        this.context = context;
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

    public void playNextSong() {
        if (playlist != null) {
            if (current == playlist.size() - 1){
                current = 0;
            }
            else{
                current += 1;
            }
            Song next = playlist.get(current);

            Log.i("PLAYLIST", String.valueOf(current));
            MediaPlayerManager.getInstance().playSong(context, next);
        }
    }

    public void playPreviousSong() {
        // TODO guardar ultima lista y puntero
        if (playlist != null) {
            if (current == 0){
                current = playlist.size() - 1;
            }
            else{
                current -= 1;
            }
            Song previous = playlist.get(current);
            Log.i("PLAYLIST", String.valueOf(current));
            MediaPlayerManager.getInstance().playSong(context, previous);
        }
    }

    public Song getSongPlaying(){
        //TODO exceptions??
        return playlist.get(current);
    }
}
