package com.eii.eiimusicplayer.media.comparators;

import com.eii.eiimusicplayer.media.pojo.Album;
import com.eii.eiimusicplayer.media.pojo.Artist;
import com.eii.eiimusicplayer.media.pojo.Song;

/**
 * Name comparator for Artist, Song and Album titles
 */

public class NameComparator<T> implements java.util.Comparator<T> {
    @Override
    public int compare(T o1, T o2) {
        if (o1 instanceof Artist && o2 instanceof Artist) {
            Artist a1 = (Artist) o1;
            Artist a2 = (Artist) o2;
            return a1.getName().compareToIgnoreCase(a2.getName());
        } else if (o1 instanceof Album && o2 instanceof Album) {
            Album a1 = (Album) o1;
            Album a2 = (Album) o2;
            return a1.getTitle().compareToIgnoreCase(a2.getTitle());
        } else if (o1 instanceof Song && o2 instanceof Song) {
            Song a1 = (Song) o1;
            Song a2 = (Song) o2;
            return a1.getTitle().compareToIgnoreCase(a2.getTitle());
        } else return 0;
    }
}
