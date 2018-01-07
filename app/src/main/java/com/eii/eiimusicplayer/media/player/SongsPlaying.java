package com.eii.eiimusicplayer.media.player;

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

    public void setCurrentPlaylistAndSong(List<Song> songs, int position) throws Exception {
        this.playlist = songs;
        this.current = position;

        checkCurrentPosition();
    }

    private void checkCurrentPosition() throws Exception {
        if (current >= playlist.size()) {
            // TODO exception management
            throw new Exception();
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

    public Song getNextSong() {
        if (playlist != null && !playlist.isEmpty()) {
            if (current == playlist.size() - 1) {
                current = 0;
            } else {
                current += 1;
            }
            return playlist.get(current);
        }

        return null;

    }

    public Song getPreviousSong() {
        if (playlist != null && playlist.size() > 1) {
            if (current == 0) {
                current = playlist.size() - 1;
            } else {
                current -= 1;
            }
            return playlist.get(current);
        }

        return null;
    }

    public int getSize() {
        if (playlist == null) return 0;
        else return playlist.size();
    }

    public Song getSong(int listPosition) {
        return playlist.get(listPosition);
    }

    public void setCurrentSong(int currentSong) {
        this.current = currentSong;
    }
}
