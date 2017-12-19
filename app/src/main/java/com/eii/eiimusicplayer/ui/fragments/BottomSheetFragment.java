package com.eii.eiimusicplayer.ui.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.BottomSheetBehavior;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.eii.eiimusicplayer.R;
import com.eii.eiimusicplayer.media.MediaPlayerManager;
import com.eii.eiimusicplayer.media.SongsPlaying;
import com.eii.eiimusicplayer.ui.utils.ImageUtils;

import java.util.concurrent.TimeUnit;

public class BottomSheetFragment extends Fragment {
    public static final String TAG = "BOTTOM_SHEET_FRAGMENT";
    private static final int THRESHOLD_TO_SEEK_NEXT = 2;
    private static MediaPlayerManager mp;
    private TextView songName;
    private TextView artistName;
    private TextView currentDurationTx;
    private ImageButton playPause;
    private ImageButton loopButton;
    private ImageView image;
    private SeekBar seekBar;
    private Handler seekTimeHandler;


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

        seekTimeHandler = new Handler();

        songName = (TextView) v.findViewById(R.id.song_name);
        artistName = (TextView) v.findViewById(R.id.artist_name);
        currentDurationTx = (TextView) v.findViewById(R.id.duration_current);
        playPause = (ImageButton) v.findViewById(R.id.play_pause_button);
        loopButton = (ImageButton) v.findViewById(R.id.repeat_button);

        image = (ImageView) v.findViewById(R.id.imageView);
        ImageUtils.setImageOrPlaceholder(super.getContext(), image, null);

        View bottomSheet = v.findViewById(R.id.bottomSheet);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);

        seekBar = (SeekBar) v.findViewById(R.id.rep_bar);

        mp = MediaPlayerManager.getInstance();

        createOnClickListeners(v);

        mUpdateTimeTask.run();

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

        createSeekBarListeners();

    }

    private void createSeekBarListeners() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (fromUser) {
                    if (seekBar.getMax() - progress < THRESHOLD_TO_SEEK_NEXT) {
                        playNext();
                    } else {
                        mp.seekTo(progress);
                    }
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

    }

    private Runnable mUpdateTimeTask = new Runnable() {
        private static final long SEEK_UPDATE_TIME = 1000;

        public void run() {
            int currentDuration = mp.getCurrrentPosition();
            @SuppressLint("DefaultLocale") String timeCurrent = String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toMinutes(currentDuration),
                    TimeUnit.MILLISECONDS.toSeconds(currentDuration) -
                            TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(currentDuration))
            );
            currentDurationTx.setText(timeCurrent);

            seekBar.setProgress(currentDuration);

            seekTimeHandler.postDelayed(this, SEEK_UPDATE_TIME);

            // TODO call onCompletion for MediaPlayerManager
            if (mp.isPlaying() &&
                    currentDuration >= mp.getDuration() - SEEK_UPDATE_TIME) {
                playNext();
            }
        }
    };


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

    private void setImagePlay() {
        playPause.setImageDrawable(getResources()
                .getDrawable(R.drawable.ic_play_arrow_white_24px));
    }

    private void setImagePause() {
        playPause.setImageDrawable(getResources()
                .getDrawable(R.drawable.ic_pause_white_24px));
    }

    public void updateSongInfo() {
        try {
            songName.setText(SongsPlaying.getInstance().getSongPlaying().getTitle());
            artistName.setText(SongsPlaying.getInstance().getSongPlaying().getArtist().getName());
            Uri uri = SongsPlaying.getInstance().getSongPlaying().getAlbum().getUriArtwork();

            seekBar.setMax(MediaPlayerManager.getInstance().getDuration());
            seekBar.setProgress(0);

            ImageUtils.setImageOrPlaceholder(super.getContext(), image, uri);

            if (mp.isPlaying()) {
                setImagePause();
            }
        } catch (Exception e) {

        }
    }

    private void expandSongControls() {
        if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        } else {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
    }

    private void playPause() {
        if (mp.isPlaying()) {
            mp.pause();
            setImagePlay();
        } else if (mp.hasSongSet()) {
            mp.play();
            setImagePause();
        }
    }

    private void playNext() {
        Log.i("SONG", "Next");
        SongsPlaying.getInstance().playNextSong(getContext());
        updateSongInfo();
    }

    private void playPrevious() {
        Log.i("SONG", "Previous");
        SongsPlaying.getInstance().playPreviousSong(getContext());
        updateSongInfo();
    }
}
