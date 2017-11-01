package com.eii.eiimusicplayer.ui.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.eii.eiimusicplayer.R;
import com.eii.eiimusicplayer.songs.Song;

import java.util.List;

import static android.widget.GridView.*;

public class ImageAdapter extends BaseAdapter {

    private final List<Song> songs;
    private Context mContext;

    public ImageAdapter(Context c, List<Song> songs) {
        mContext = c;
        this.songs = songs;
    }

    public int getCount() {
        return mThumbIds.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        View retView = null;
        //ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
//            imageView = new ImageView(mContext);
//            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
//            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
//            imageView.setPadding(8, 8, 8, 8);

            LayoutInflater layout = LayoutInflater.from(mContext);
            retView = layout.inflate(R.layout.album_grid_item, parent, false);
            retView.setLayoutParams(new GridView.LayoutParams(85, 85));
            Song song = songs.get(position);
            TextView albumName = (TextView) retView.findViewById(R.id.album_name);
            TextView artistName = (TextView) retView.findViewById(R.id.artist_name);

            albumName.setText(song.getAlbum());
            artistName.setText(song.getArtist());

        } else {
            retView = convertView;
        }

//        if (position < 4) {
//            imageView.setImageResource(mThumbIds[position]);
//        } else {
//            imageView.setImageResource(R.drawable.album_cover_test_2);
//        }

        return retView;
    }

    // references to our images
    private Integer[] mThumbIds = {
            R.drawable.album_cover_test_1, R.drawable.album_cover_test_1,
            R.drawable.album_cover_test_1, R.drawable.album_cover_test_1
    };
}