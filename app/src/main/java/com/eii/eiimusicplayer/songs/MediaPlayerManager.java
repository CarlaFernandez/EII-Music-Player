package com.eii.eiimusicplayer.songs;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.eii.eiimusicplayer.ui.fragments.BottomSheetFragment;

import java.io.IOException;

/**
 * Created by Carla on 26/10/2017.
 */

public class MediaPlayerManager {
    private static MediaPlayerManager manager;
    private MediaPlayer mp;
    private boolean songSet;

    private MediaPlayerManager() {
        mp = new MediaPlayer();
    }

    public static MediaPlayerManager getInstance() {
        if (manager == null) {
            manager = new MediaPlayerManager();
        }
        return manager;
    }

    private void setSong(Context context, Song song) {
        // Manage exception in UI
        try {
            // TODO volver a empezar la reproduccion de una cancion si ya se está reproduciendo?
            mp.reset();
            mp.setAudioStreamType(AudioManager.STREAM_MUSIC);
            mp.setDataSource(context, Uri.parse(song.getFullPath()));
            mp.prepare();
            songSet = true;
        } catch (IOException e) {
            Log.e("ERROR", e.getMessage());
        }
    }

    public void pause() {
        mp.pause();
    }

    public boolean isPlaying() {
        return mp.isPlaying();
    }

    public void playSong(Context context, Song song) {
        if (song == null || context == null){
            // TODO exceptionnnn
        }

        setSong(context, song);
        mp.start();

        // tell the control bar fragment to update the info
        android.support.v4.app.FragmentManager fragmentManager =
                ((AppCompatActivity) context).getSupportFragmentManager();
        BottomSheetFragment playerBar =
                (BottomSheetFragment) fragmentManager.findFragmentByTag(BottomSheetFragment.TAG);
        if (playerBar != null) {
            playerBar.updateSongInfo();
        }
    }

    public boolean hasSongSet() {
        return songSet;
    }
}
