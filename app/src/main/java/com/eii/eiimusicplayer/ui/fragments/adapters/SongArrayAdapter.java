package com.eii.eiimusicplayer.ui.fragments.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.eii.eiimusicplayer.R;
import com.eii.eiimusicplayer.media.pojo.Album;
import com.eii.eiimusicplayer.media.pojo.Song;
import com.eii.eiimusicplayer.ui.utils.ImageUtils;

import java.util.List;

/**
 * Created by Carla on 24/11/2017.
 */

public class SongArrayAdapter extends ArrayAdapter<Song> {

    private final int resource;

    private final Context context;
    private final List<Song> objects;


    public SongArrayAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Song> objects) {
        super(context, resource, objects);
        this.resource = resource;
        this.context = context;
        this.objects = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View retView = convertView;
        if (retView == null) {
            LayoutInflater layout = LayoutInflater.from(getContext());
            retView = layout.inflate(resource, null);
        }

        Song song = objects.get(position);

        TextView songNameTx = (TextView) retView.findViewById(R.id.song_list_song_title);
        TextView albumNameTx = (TextView) retView.findViewById(R.id.song_list_album_title);
        TextView artistNameTx = (TextView) retView.findViewById(R.id.song_list_artist);
        TextView trackNumTx = (TextView) retView.findViewById(R.id.song_list_track_number);

        albumNameTx.setText(song.getAlbum().getTitle());
        songNameTx.setText(song.getTitle());
        artistNameTx.setText(song.getArtist().getName());
        trackNumTx.setText(song.getTrack());

        return retView;


    }
}

