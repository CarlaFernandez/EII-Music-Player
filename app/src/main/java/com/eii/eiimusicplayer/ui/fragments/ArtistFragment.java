package com.eii.eiimusicplayer.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.eii.eiimusicplayer.R;
import com.eii.eiimusicplayer.media.SongListHelper;
import com.eii.eiimusicplayer.media.pojo.Artist;

import java.util.Collections;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class ArtistFragment extends Fragment {

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public ArtistFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static ArtistFragment newInstance(int sectionNumber) {
        ArtistFragment fragment = new ArtistFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_artists, container, false);

        final List<Artist> artists = SongListHelper.getScannedArtists();
        Collections.sort(artists, new NameComparator<>());
        ListView listView = (ListView) rootView.findViewById(R.id.list_view_artists);

        ArrayAdapter<Artist> adapter = new ArrayAdapter<>
                (rootView.getContext(), android.R.layout.simple_list_item_1, artists);
        listView.setAdapter(adapter);

        // TODO onclick
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                try {
//                    Song song = artists.get(position);
//                    SongsPlaying.getInstance().setCurrentPlaylistAndSong(getContext(), artists, position);
//                    MediaPlayerManager.getInstance().playSong(getContext(), song);
//                } catch (Exception e) {
//                    Log.e("ERROR", "Wrong songPlaying position");
//                    Log.e("ERROR", e.getMessage());
//                }
//            }
//        });

        return rootView;
    }
}
