package com.eii.eiimusicplayer.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eii.eiimusicplayer.R;
import com.eii.eiimusicplayer.media.SongListHelper;
import com.eii.eiimusicplayer.media.comparators.NameComparator;
import com.eii.eiimusicplayer.media.pojo.Playlist;

import java.util.Collections;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class PlaylistFragment extends Fragment {

    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";
    private static final String ARGS_TAG = "TAG";
    private View rootView;

    public PlaylistFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static PlaylistFragment newInstance(int sectionNumber, String tag) {
        PlaylistFragment fragment = new PlaylistFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        args.putString(ARGS_TAG, tag);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.rootView = inflater.inflate(R.layout.fragment_playlists, container, false);

        final List<Playlist> playlists = SongListHelper.getScannedPlaylists();
        Collections.sort(
                playlists, new NameComparator<Playlist>()
        );

        buildViewWithArtists(playlists);

        return rootView;
    }

    private void buildViewWithArtists(final List<Playlist> playlists) {
//        Bundle bundle = getArguments();
//        final int sectionNumber = bundle.getInt(ARG_SECTION_NUMBER);
//        final String tag = bundle.getString(ARGS_TAG);
//
//        ListView listView = (ListView) rootView.findViewById(R.id.list_view_artists);
//        ArtistArrayAdapter adapter = new ArtistArrayAdapter(getContext(), R.layout.artist_list_item, artists);
//
//        listView.setAdapter(adapter);
//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                try {
//                    Artist artist = artists.get(position);
//                    AlbumsFragment newFragment = AlbumsFragment.newInstance(sectionNumber, "artist_album");
//                    newFragment.setAlbums(artist.getAlbums());
//                    getActivity().getSupportFragmentManager().beginTransaction()
//                            .replace(rootView.getId(), newFragment, tag)
//                            .addToBackStack(null).commit();
//                } catch (Exception e) {
//
//                }
//            }
//        });
    }
}
