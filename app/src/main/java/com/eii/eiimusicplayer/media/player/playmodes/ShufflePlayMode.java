package com.eii.eiimusicplayer.media.player.playmodes;

import com.eii.eiimusicplayer.media.player.SongsPlaying;
import com.eii.eiimusicplayer.media.pojo.Song;

import java.util.Random;

/**
 * Shuffle state for MediaPlayerManager, created on 07/01/2018.
 */

public class ShufflePlayMode implements PlayMode {

    @Override
    public Song getNextSong() {
        Random random = new Random();
        SongsPlaying songsPlaying = SongsPlaying.getInstance();

        int listSize = songsPlaying.getSize();

        if (listSize > 0) {
            int listPosition = random.nextInt(listSize);

            SongsPlaying.getInstance().setCurrentSong(listPosition);
            return SongsPlaying.getInstance().getSong(listPosition);
        }

        return null;

    }

}
