package com.eii.eiimusicplayer.ui.fragments;

import android.content.Context;
import android.support.design.widget.TabLayout;
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
        tabNames.add(applicationContext.getString(R.string.tabSongs));
        tabNames.add(applicationContext.getString(R.string.tabPlaylists));

    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a SongsFragment (defined as a static inner class below).
        if (position == 2) {
            return SongsFragment.newInstance(position + 1);
        }
        else return OtherFragment.newInstance(position + 1);
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
