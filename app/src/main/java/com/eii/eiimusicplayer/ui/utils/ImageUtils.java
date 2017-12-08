package com.eii.eiimusicplayer.ui.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;

import com.eii.eiimusicplayer.R;
import com.eii.eiimusicplayer.ui.activities.HomeActivity;

import java.io.IOException;

/**
 * Created by Carla on 24/11/2017.
 */

public class ImageUtils {

    public static void setImageOrPlaceholder(Context context, ImageView view, Uri uri) {
        Bitmap placeholder = BitmapFactory.decodeResource(context.getResources(),
                R.drawable.album_artwork_placeholder_detail);

        try {
            Bitmap cover = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
            view.setImageBitmap(cover);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } catch (IOException | NullPointerException e) {
            view.setImageBitmap(placeholder);
            view.setScaleType(ImageView.ScaleType.CENTER_CROP);
            e.printStackTrace();
        }
    }

    public static void setImageOrPlaceholderFit(Context context, ImageView view, Uri uri) {
        Bitmap placeholder = null;
        try {
            placeholder = BitmapFactory.decodeResource(context.getResources(),
                    R.drawable.album_artwork_placeholder_detail);
            if (HomeActivity.hasPermissions()) {
                Bitmap cover = MediaStore.Images.Media.getBitmap(context.getContentResolver(), uri);
                view.setImageBitmap(cover);
                view.setScaleType(ImageView.ScaleType.FIT_CENTER);
            }
            else throw new IOException();

        } catch (IOException | NullPointerException e) {
            view.setImageBitmap(placeholder);
            view.setScaleType(ImageView.ScaleType.FIT_CENTER);
            Log.e("Imagen", "Imagen no encontrada");
        }
    }

}
