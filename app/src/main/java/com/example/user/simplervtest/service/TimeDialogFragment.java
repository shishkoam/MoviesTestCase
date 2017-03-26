package com.example.user.simplervtest.service;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.user.simplervtest.R;
import com.example.user.simplervtest.helpers.Consts;
import com.example.user.simplervtest.helpers.MovieManager;

/**
 * Created by User on 13.02.2017 to save dialog during rotation
 */

public class TimeDialogFragment extends DialogFragment implements Consts {
    private Context context;
    private String time;
    private String imageUrl = null;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public Dialog onCreateDialog(Bundle id) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater factory = LayoutInflater.from(context);
        final View view = factory.inflate(R.layout.dialog_view, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.dialog_imageview);


        Glide.with(context).load(Consts.BASE_IMAGE_PATH + imageUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .dontAnimate()
                .into(imageView);

        builder.setTitle(context.getString(R.string.current_time, time))
                .setView(view)
                .setIcon(android.R.drawable.ic_menu_my_calendar)
                .setNeutralButton(R.string.ok, null);

        return builder.create();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TITLE_KEY, time);
        outState.putString(IMAGE_URL, imageUrl);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null) {
            time = savedInstanceState.getString(TITLE_KEY);
            imageUrl = savedInstanceState.getString(IMAGE_URL);
        } else {
            imageUrl = MovieManager.INSTANCE.getRandomImageUrl();
        }
    }
}
