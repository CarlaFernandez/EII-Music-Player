package com.eii.eiimusicplayer.media.player.playmodes;

import com.eii.eiimusicplayer.media.player.SongsPlaying;
import com.eii.eiimusicplayer.media.pojo.Song;

/**
 * Loop state for MediaPlayerManager, created on 07/01/2018.
 */

public class LoopPlayMode implements PlayMode {
    @Override
    public Song getNextSong() {
        return SongsPlaying.getInstance().getSongPlaying();
    }

}
