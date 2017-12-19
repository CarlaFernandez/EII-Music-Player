package com.eii.eiimusicplayer.media;

import com.eii.eiimusicplayer.media.pojo.Song;

import java.util.List;

/**
 * Class that holds the current playlist and song
 */

public class SongsPlaying {
    private static SongsPlaying songsPlaying;

    private List<Song> playlist;
    private int current;
    private Song nextSong;

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

    Song getNextSong() {
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

    Song getPreviousSong() {
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
}
