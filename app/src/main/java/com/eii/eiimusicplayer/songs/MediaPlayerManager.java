package com.eii.eiimusicplayer.songs;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;

import java.io.IOException;

/**
 * Created by Carla on 26/10/2017.
 */

public class MediaPlayerManager {
    private static MediaPlayerManager manager;
    private MediaPlayer mp;

    private MediaPlayerManager() {
        mp = new MediaPlayer();
        mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
    }

    public static MediaPlayerManager getInstance() {
        if (manager == null) {
            manager = new MediaPlayerManager();
        }
        return manager;
    }

    public void setSong(Context context, Song song) {
        // Manage exception in UI
        try {
            // TODO volver a empezar la reproduccion de una cancion si ya se está reproduciendo?
            mp.reset();
            mp.setDataSource(context, Uri.parse(song.getFullPath()));
            mp.prepare();
        } catch (IOException e) {
            Log.e("ERROR", e.getMessage());
        }
    }

    public void play() {
        mp.start();
    }

    public void pause(){
        mp.pause();
    }

    public boolean isPlaying(){
        return mp.isPlaying();
    }

    public void playSong(Context context, Song song) {
        setSong(context, song);
        play();
    }
}
