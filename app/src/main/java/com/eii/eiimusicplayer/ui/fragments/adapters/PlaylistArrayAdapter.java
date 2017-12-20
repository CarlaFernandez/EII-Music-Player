package com.eii.eiimusicplayer.ui.fragments.adapters;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.eii.eiimusicplayer.R;
import com.eii.eiimusicplayer.media.pojo.Artist;

import java.util.List;

/**
 * Created by Carla on 04/12/2017.
 */

public class PlaylistArrayAdapter extends ArrayAdapter<Artist> {

    private final int resource;

    private final Context context;
    private final List<Artist> objects;


    public PlaylistArrayAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Artist> objects) {
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

        Artist artist = objects.get(position);

        TextView txName = (TextView) retView.findViewById(R.id.artist_list_artist_name);
        TextView txAlbums = (TextView) retView.findViewById(R.id.artist_list_artist_numAlbums);

        txName.setText(artist.getName());
        int numAlbums = artist.getAlbums().size();
        txAlbums.setText(numAlbums == 1 ? numAlbums + " " + getContext().getResources().getString(R.string.album)
                : numAlbums + " " + getContext().getResources().getString(R.string.albums));

        return retView;


    }
}
