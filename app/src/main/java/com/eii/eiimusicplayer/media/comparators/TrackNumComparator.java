package com.eii.eiimusicplayer.media.comparators;

import com.eii.eiimusicplayer.media.pojo.Song;

/**
 * Comparator for the song track number
 */

public class TrackNumComparator<T> implements java.util.Comparator<T> {
    @Override
    public int compare(T o1, T o2) {
        if (o1 instanceof Song && o2 instanceof Song) {
            Song a1 = (Song) o1;
            Song a2 = (Song) o2;
            return (Integer.parseInt(a1.getTrack()) < Integer.parseInt(a2.getTrack()))? -1 : 1;
        } else {
            return 0;
        }
    }
}
