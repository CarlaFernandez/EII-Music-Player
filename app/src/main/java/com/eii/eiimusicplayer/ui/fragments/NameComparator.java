package com.eii.eiimusicplayer.ui.fragments;

import com.eii.eiimusicplayer.media.pojo.Album;
import com.eii.eiimusicplayer.media.pojo.Artist;

/**
 * Created by Carla on 02/11/2017.
 */

class NameComparator<T> implements java.util.Comparator<T> {
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
        } else return 0;
    }
}