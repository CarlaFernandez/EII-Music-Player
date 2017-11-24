package com.eii.eiimusicplayer.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.eii.eiimusicplayer.R;
import com.eii.eiimusicplayer.media.MediaPlayerManager;
import com.eii.eiimusicplayer.media.SongListHelper;
import com.eii.eiimusicplayer.media.SongsPlaying;
import com.eii.eiimusicplayer.media.pojo.Album;
import com.eii.eiimusicplayer.media.pojo.Song;
import com.eii.eiimusicplayer.ui.fragments.adapters.SongArrayAdapter;

import java.util.Collections;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class SongsFragment extends Fragment {

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public SongsFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static SongsFragment newInstance(int sectionNumber) {
        SongsFragment fragment = new SongsFragment();
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
        final List<Song> songs = SongListHelper.getScannedSongs();
        Collections.sort(
                songs, new NameComparator<Song>()
        );
        ListView listView = (ListView) rootView.findViewById(R.id.list_view_songs);
        SongArrayAdapter adapter = new SongArrayAdapter(getContext(), R.layout.song_list_item, songs);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    Song song = songs.get(position);
                    SongsPlaying.getInstance().setCurrentPlaylistAndSong(getContext(), songs, position);
                    MediaPlayerManager.getInstance().playSong(getContext(), song);
                } catch (Exception e) {
                    Log.e("ERROR", "Wrong songPlaying position");
                    Log.e("ERROR", e.getMessage());
                }
            }
        });

        return rootView;
    }
}
