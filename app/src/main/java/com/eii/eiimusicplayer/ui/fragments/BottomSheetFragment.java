package com.eii.eiimusicplayer.ui.fragments;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.eii.eiimusicplayer.R;
import com.eii.eiimusicplayer.media.MediaPlayerManager;
import com.eii.eiimusicplayer.media.SongsPlaying;
import com.eii.eiimusicplayer.ui.utils.ImageUtils;

import java.io.File;
import java.io.IOException;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BottomSheetFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BottomSheetFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BottomSheetFragment extends Fragment {
    public static final String TAG = "BOTTOM_SHEET_FRAGMENT";
    private static MediaPlayerManager mp;
    private TextView songName;
    private TextView artistName;
    private ImageButton playPause;
    private ImageView image;


    private OnFragmentInteractionListener mListener;
    private BottomSheetBehavior<View> bottomSheetBehavior;

    public BottomSheetFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_bottom_sheet, container, false);

        songName = (TextView) v.findViewById(R.id.song_name);
        artistName = (TextView) v.findViewById(R.id.artist_name);
        playPause = (ImageButton) v.findViewById(R.id.play_pause_button);

        image = (ImageView) v.findViewById(R.id.imageView);
        ImageUtils.setImageOrPlaceholder(super.getContext(), image, null);

        View bottomSheet = v.findViewById(R.id.bottomSheet);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);

        mp = MediaPlayerManager.getInstance();

        createOnClickListeners(v);

        return v;
    }

    private void createOnClickListeners(View v) {
        v.findViewById(R.id.repInfoLayout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expandSongControls();
            }
        });

        v.findViewById(R.id.prev_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playPrevious();
            }
        });

        v.findViewById(R.id.next_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playNext();
            }
        });

        v.findViewById(R.id.play_pause_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playPause();
            }
        });
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }

    public void setImagePlay() {
        playPause.setImageDrawable(getResources()
                .getDrawable(R.drawable.ic_play_arrow_white_24px));
    }

    public void setImagePause() {
        playPause.setImageDrawable(getResources()
                .getDrawable(R.drawable.ic_pause_white_24px));
    }

    public void updateSongInfo() {
        songName.setText(SongsPlaying.getInstance().getSongPlaying().getTitle());
        artistName.setText(SongsPlaying.getInstance().getSongPlaying().getArtist().getName());
        Uri uri = SongsPlaying.getInstance().getSongPlaying().getAlbum().getUriArtwork();

        ImageUtils.setImageOrPlaceholder(super.getContext(), image, uri);

        if (mp.isPlaying()) {
            setImagePause();
        }
    }

    public void expandSongControls() {
        if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        } else {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
    }

    public void playPause() {
        if (mp.isPlaying()) {
            mp.pause();
            setImagePlay();
        }
        else if (mp.hasSongSet()){
            mp.play();
            setImagePause();
        }
    }

    public void playNext() {
        Log.i("SONG", "Next");
        SongsPlaying.getInstance().playNextSong();
        updateSongInfo();
    }

    public void playPrevious() {
        Log.i("SONG", "Previous");
        SongsPlaying.getInstance().playPreviousSong();
        updateSongInfo();
    }
}
