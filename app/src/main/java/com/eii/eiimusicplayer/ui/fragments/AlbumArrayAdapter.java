package com.eii.eiimusicplayer.ui.fragments;


import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.eii.eiimusicplayer.R;
import com.eii.eiimusicplayer.songs.Song;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manuel on 01/11/2017.
 */

public class AlbumArrayAdapter extends ArrayAdapter<String> {

    private final int resource;

    private final Context context;
    private final String[] objects;

    public AlbumArrayAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull String[] objects) {
        super(context, resource, objects);
        this.resource = resource;
        this.objects = objects;
        this.context = context;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View retView = convertView;
        if (retView == null) {
            LayoutInflater layout = LayoutInflater.from(getContext());
            retView = layout.inflate(resource, null);
        }

        String albumName = objects[position];
        TextView albumNameTx = (TextView) retView.findViewById(R.id.album_name);
        TextView artistNameTx = (TextView) retView.findViewById(R.id.album_artist);
        albumNameTx.setText(albumName);
        return retView;


    }
}
