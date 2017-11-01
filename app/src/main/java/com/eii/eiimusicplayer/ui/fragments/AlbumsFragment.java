package com.eii.eiimusicplayer.ui.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;

import com.eii.eiimusicplayer.R;
import com.eii.eiimusicplayer.songs.MediaPlayerManager;
import com.eii.eiimusicplayer.songs.Song;
import com.eii.eiimusicplayer.songs.SongListHelper;
import com.eii.eiimusicplayer.songs.SongsPlaying;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class AlbumsFragment extends Fragment {
    private HashMap<String, List<Song>> songsInAlbum;
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public AlbumsFragment() {
        songsInAlbum = new HashMap<>();
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

        // TODO load this in memory?? HashMap maybe?
        // TODO order
        final List<Song> songs = SongListHelper.getScannedSongs();
        mapSongsToAlbums(songs);

        GridView gridView = (GridView) rootView.findViewById(R.id.grid_view_albums);
        AlbumArrayAdapter adapter = new AlbumArrayAdapter(getContext(), R.layout.album_grid_item, (String[]) songsInAlbum.keySet().toArray());

        gridView.setAdapter(adapter);

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

    private void mapSongsToAlbums(List<Song> songs) {
        for (Song song : songs) {
            String album = song.getAlbum();
            if (songsInAlbum.containsKey(album)) {
                List<Song> songsForCurrentAlbum = songsInAlbum.get(album);
                songsForCurrentAlbum.add(song);
                songsInAlbum.put(album, songsForCurrentAlbum);
            } else {
                List<Song> songsForCurrentAlbum = new ArrayList<>();
                songsForCurrentAlbum.add(song);
                songsInAlbum.put(album, songsForCurrentAlbum);
            }
        }
    }
}
