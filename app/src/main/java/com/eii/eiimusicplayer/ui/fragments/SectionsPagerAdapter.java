package com.eii.eiimusicplayer.ui.fragments;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.eii.eiimusicplayer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private Context applicationContext;
    private List<String> tabNames;

    public SectionsPagerAdapter(FragmentManager fm, Context applicationContext) {
        super(fm);
        this.applicationContext = applicationContext;
        tabNames = new ArrayList<String>();

        tabNames.add(applicationContext.getString(R.string.tabArtists));
        tabNames.add(applicationContext.getString(R.string.tabAlbums));
        tabNames.add(applicationContext.getString(R.string.tabPlaylists));
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).
        return PlaceholderFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return tabNames.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabNames.get(position);
    }
}
