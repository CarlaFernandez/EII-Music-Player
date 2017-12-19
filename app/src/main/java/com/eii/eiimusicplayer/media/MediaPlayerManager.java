package com.eii.eiimusicplayer.media;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.eii.eiimusicplayer.media.pojo.Song;
import com.eii.eiimusicplayer.ui.fragments.BottomSheetFragment;

import java.io.IOException;

/**
 * Media Player wrapper
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

    public void play() { mp.start(); }

    public boolean isPlaying() {
        return mp.isPlaying();
    }

    public void playSong(Context context, Song song) {
        if (song == null || context == null){
            // TODO exceptionnnn
            return;
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

    public int getDuration() {
        return mp.getDuration();
    }

    public int getCurrrentPosition() {
        return mp.getCurrentPosition();
    }

    public void seekTo(int progress) {
        mp.seekTo(progress);
    }

}
