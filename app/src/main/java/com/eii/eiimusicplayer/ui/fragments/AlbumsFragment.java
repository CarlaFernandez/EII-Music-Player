package com.eii.eiimusicplayer.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;

import com.eii.eiimusicplayer.R;
import com.eii.eiimusicplayer.media.SongListHelper;
import com.eii.eiimusicplayer.media.pojo.Album;
import com.eii.eiimusicplayer.ui.fragments.adapters.AlbumArrayAdapter;

import java.util.Collections;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class AlbumsFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public AlbumsFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static AlbumsFragment newInstance(int sectionNumber) {
        AlbumsFragment fragment = new AlbumsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_albums, container, false);

        // TODO order
        final List<Album> albums = SongListHelper.getScannedAlbums();
        Collections.sort(
                albums, new NameComparator<Album>()
        );
        GridView gridView = (GridView) rootView.findViewById(R.id.grid_view_albums);
        AlbumArrayAdapter adapter = new AlbumArrayAdapter(getContext(), R.layout.album_grid_item, albums);

        gridView.setAdapter(adapter);

        //TODO onclick
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                try {
//                    Song song = songs.get(position);
//                    SongsPlaying.getInstance().setCurrentPlaylistAndSong(getContext(), songs, position);
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
