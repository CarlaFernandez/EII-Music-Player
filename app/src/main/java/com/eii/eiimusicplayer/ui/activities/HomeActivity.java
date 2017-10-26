package com.eii.eiimusicplayer.ui.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.eii.eiimusicplayer.R;
import com.eii.eiimusicplayer.songs.MediaPlayerManager;
import com.eii.eiimusicplayer.songs.SongListHelper;
import com.eii.eiimusicplayer.songs.SongsPlaying;
import com.eii.eiimusicplayer.ui.fragments.SectionsPagerAdapter;

public class HomeActivity extends AppCompatActivity {
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;

    // TODO cambiar este valor cuando hayamos hecho el reproductor
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private BottomSheetBehavior bottomSheetBehavior;
    private static MediaPlayerManager mp;
    private static TextView songName;
    private static TextView artistName;
    private static ImageButton playPause;
    private static Resources resources;

//    private View bottomSheet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        songName = (TextView) findViewById(R.id.song_name);
        artistName = (TextView) findViewById(R.id.artist_name);
        playPause = (ImageButton)findViewById(R.id.play_pause_button);

        resources = getResources();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),
                getApplicationContext());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

        mp = MediaPlayerManager.getInstance();

    }

    // Las vistas ya estan creadas
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        View bottomSheet = findViewById(R.id.bottomSheet);
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);

    }

    public void playPause(View view) {
        if (mp.isPlaying()) {
            mp.pause();
            playPause.setImageDrawable(getResources()
                    .getDrawable(R.drawable.ic_play_arrow_white_24px));
        } else {
            mp.play();
            playPause.setImageDrawable(getResources()
                    .getDrawable(R.drawable.ic_pause_white_24px));
        }
        // TODO cambiar imagen del boton
    }

    public static void updateSongInfo() {
        songName.setText(SongsPlaying.getInstance().getSongPlaying().getTitle());
        artistName.setText(SongsPlaying.getInstance().getSongPlaying().getArtist());
        if (mp.isPlaying()) {
            playPause.setImageDrawable(resources
                    .getDrawable(R.drawable.ic_pause_white_24px));
        }
    }

    public void playNext(View view) {
        Log.i("SONG", "Next");
        SongsPlaying.getInstance().playNextSong();
        updateSongInfo();
    }

    public void playPrevious(View view) {
        Log.i("SONG", "Previous");
        SongsPlaying.getInstance().playPreviousSong();
        updateSongInfo();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            Toast.makeText(HomeActivity.this, "Permission OK", Toast.LENGTH_SHORT).show();
            SongListHelper.saveAllSongsFromExternalStorage(getContentResolver());
        } else {
            Toast.makeText(HomeActivity.this, "Permission denied to read your External Storage",
                    Toast.LENGTH_SHORT).show();
        }
        return;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void expandSongControls(View view) {
        if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_COLLAPSED) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        } else {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
    }

}
