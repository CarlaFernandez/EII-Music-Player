package com.eii.eiimusicplayer;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.view.View;

/**
 * Created by Carla on 24/09/2017.
 */

public class MusicPlayerBSBehavior extends BottomSheetBehavior {
    private static final int MIN_PEEK_HEIGHT = 225;
    private View bottomSheet;
    private BottomSheetBehavior bottomSheetBehavior;

    public MusicPlayerBSBehavior(View bottomSheet) {
        this.bottomSheet = bottomSheet;
        this.bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet);



    }


}
