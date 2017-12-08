package com.eii.eiimusicplayer.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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
    private View rootView;
    private List<Album> albums;

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
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {

        this.rootView = inflater.inflate(R.layout.fragment_albums, container, false);

        if (this.albums == null) {
            this.albums = SongListHelper.getScannedAlbums();
        }

        Collections.sort(
                albums, new NameComparator<Album>()
        );

        buildViewWithAlbums();

        return rootView;
    }

    private void buildViewWithAlbums() {
        GridView gridView = (GridView) rootView.findViewById(R.id.grid_view_albums);
        AlbumArrayAdapter adapter = new AlbumArrayAdapter(getContext(), R.layout.album_grid_item, albums);

        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                try {
                    Album album = albums.get(position);
                    SongsFragment newFragment = new SongsFragment();
                    newFragment.setSongs(album.getSongs());
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(rootView.getId(), newFragment, "SONGS_FROM_ALBUM")
                            .addToBackStack(null).commit();

                } catch (Exception e) {

                }
            }
        });
    }

    public void setAlbums(List<Album> albums) {
        this.albums = albums;
        Log.i("FRAGMENT", "Setting albums from artist");
    }
}
