package com.eii.eiimusicplayer.media.player.playmodes;

import com.eii.eiimusicplayer.media.pojo.Song;

/**
 * Interface for play mode state for MediaPlayerManager, created on 07/01/2018.
 */

public interface PlayMode {
    Song getNextSong();
}
