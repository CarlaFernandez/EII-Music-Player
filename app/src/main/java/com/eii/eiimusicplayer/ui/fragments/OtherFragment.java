package com.eii.eiimusicplayer.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.eii.eiimusicplayer.R;
import com.eii.eiimusicplayer.songs.Song;
import com.eii.eiimusicplayer.songs.SongListHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class OtherFragment extends Fragment {

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public OtherFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static OtherFragment newInstance(int sectionNumber) {
        OtherFragment fragment = new OtherFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_songs, container, false);

        // TODO load this in memory?? HashMap maybe?
        // TODO order
        List<Song> songs = SongListHelper.getScannedSongs();
        ListView listView = (ListView) rootView.findViewById(R.id.list_view_songs);

        List<String> songsListed = new ArrayList<>();
        for (Song s : songs){
            songsListed.add(s.toStringOther());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>
                (rootView.getContext(), android.R.layout.simple_list_item_1, songsListed);
        listView.setAdapter(adapter);

        return rootView;
    }
}
