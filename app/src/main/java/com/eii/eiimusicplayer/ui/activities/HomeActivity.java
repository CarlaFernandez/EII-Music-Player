package com.eii.eiimusicplayer.ui.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.eii.eiimusicplayer.R;
import com.eii.eiimusicplayer.media.SongListHelper;
import com.eii.eiimusicplayer.ui.fragments.BottomSheetFragment;
import com.eii.eiimusicplayer.ui.fragments.adapters.SectionsPagerAdapter;

import java.io.IOException;

public class HomeActivity extends AppCompatActivity implements BottomSheetFragment.OnFragmentInteractionListener {
    public static final int MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE = 1;

    // TODO cambiar este valor cuando hayamos hecho el reproductor
    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded bottomSheetFragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private BottomSheetFragment bottomSheetFragment;
    public static boolean permissions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Create the adapter that will return a bottomSheetFragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(),
                getApplicationContext());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE},
                MY_PERMISSIONS_REQUEST_READ_EXTERNAL_STORAGE);

        bottomSheetFragment = new BottomSheetFragment();
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.bottomSheet_container, bottomSheetFragment, BottomSheetFragment.TAG)
                .commit();
    }

    public static boolean hasPermissions() {
        return permissions;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {

        if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            try {
                SongListHelper.saveAllSongsFromExternalStorage(getContentResolver());
                HomeActivity.permissions = true;
            } catch (IOException e) {
                Toast.makeText(HomeActivity.this, "Cannot read external storage",
                        Toast.LENGTH_SHORT).show();
                HomeActivity.permissions = false;
            }

        } else {
            Toast.makeText(HomeActivity.this, "Permission denied to read your External Storage",
                    Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
