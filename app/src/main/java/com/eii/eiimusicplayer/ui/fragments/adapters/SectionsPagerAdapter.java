package com.eii.eiimusicplayer.ui.fragments.adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.eii.eiimusicplayer.R;
import com.eii.eiimusicplayer.ui.fragments.AlbumsFragment;
import com.eii.eiimusicplayer.ui.fragments.ArtistFragment;
import com.eii.eiimusicplayer.ui.fragments.OtherFragment;
import com.eii.eiimusicplayer.ui.fragments.SongsFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private Context applicationContext;
    private List<String> tabNames;
    private FragmentManager mFragmentManager;


    public SectionsPagerAdapter(FragmentManager fm, Context applicationContext) {
        super(fm);
        this.mFragmentManager = fm;
        this.applicationContext = applicationContext;
        tabNames = new ArrayList<>();

        tabNames.add(applicationContext.getString(R.string.tabArtists));
        tabNames.add(applicationContext.getString(R.string.tabAlbums));
        tabNames.add(applicationContext.getString(R.string.tabSongs));
        tabNames.add(applicationContext.getString(R.string.tabPlaylists));

    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return ArtistFragment.newInstance(position, "artist_artist");
            case 1:
                return AlbumsFragment.newInstance(position, "album_album");
            case 2:
                return SongsFragment.newInstance(position);
            default:
                return OtherFragment.newInstance(position);

        }
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
