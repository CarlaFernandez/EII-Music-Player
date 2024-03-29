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
import com.eii.eiimusicplayer.ui.utils.ImageUtils;

import java.util.List;

/**
 * Created by Manuel on 01/11/2017.
 */

public class AlbumArrayAdapter extends ArrayAdapter<Album> {

    private final int resource;

    private final Context context;
    private final List<Album> objects;


    public AlbumArrayAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Album> objects) {
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

        Album album = objects.get(position);

        TextView albumNameTx = (TextView) retView.findViewById(R.id.album_grid_album_name);
        TextView artistNameTx = (TextView) retView.findViewById(R.id.album_grid_album_artist);

        ImageView imageView = (ImageView) retView.findViewById(R.id.album_grid_album_image);
        ImageUtils.setImageOrPlaceholderFit(getContext(), imageView, album.getUriArtwork());

        albumNameTx.setText(album.getTitle());
        artistNameTx.setText(album.getArtist().getName());

        return retView;


    }
}
