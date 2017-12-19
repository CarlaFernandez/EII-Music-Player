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

public class MediaPlayerManager extends MediaPlayer {
    private static MediaPlayerManager manager;
    private boolean songSet;

    private MediaPlayerManager() {

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
            reset();
            setAudioStreamType(AudioManager.STREAM_MUSIC);
            setDataSource(context, Uri.parse(song.getFullPath()));
            prepare();
            songSet = true;
        } catch (IOException e) {
            Log.e("ERROR", e.getMessage());
        }
    }

    private void playSong(Context context, Song song) {
        if (song == null || context == null) {
            // TODO exceptionnnn
            return;
        }

        setSong(context, song);
        start();

        // tell the control bar fragment to update the info
        android.support.v4.app.FragmentManager fragmentManager =
                ((AppCompatActivity) context).getSupportFragmentManager();
        BottomSheetFragment playerBar =
                (BottomSheetFragment) fragmentManager.findFragmentByTag(BottomSheetFragment.TAG);
        if (playerBar != null) {
            playerBar.updateSongInfo();
        }
    }

    public void playNextSong(Context context) {
        Song next = SongsPlaying.getInstance().getNextSong();
        playSong(context, next);
    }

    public void playPreviousSong(Context context) {
        Song previous = SongsPlaying.getInstance().getPreviousSong();
        playSong(context, previous);
    }

    public boolean hasSongSet() {
        return songSet;
    }

}
