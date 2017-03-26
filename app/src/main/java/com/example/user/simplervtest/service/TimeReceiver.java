package com.example.user.simplervtest.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.user.simplervtest.R;
import com.example.user.simplervtest.helpers.Consts;
import com.example.user.simplervtest.helpers.MovieManager;
import com.example.user.simplervtest.helpers.Utils;

/**
 * Created by User on 25.03.2017
 */

public class TimeReceiver extends BroadcastReceiver implements Consts {

    @Override
    public void onReceive(Context context, Intent intent) {

        String result = intent.getStringExtra(PARAM_RESULT);

        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater factory = LayoutInflater.from(context);
        final View view = factory.inflate(R.layout.dialog_view, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.dialog_imageview);

        String internetUrl = MovieManager.INSTANCE.getRandomImageUrl();

        Glide.with(context).load(Consts.BASE_IMAGE_PATH + internetUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
                .dontAnimate()
                .into(imageView);

        builder.setTitle(context.getString(R.string.current_time, result))
                .setView(view)
                .setIcon(android.R.drawable.ic_menu_my_calendar)
                .setNeutralButton(R.string.ok, (dialog, id) -> dialog.cancel());
        AlertDialog alert = builder.create();
        alert.show();
    }
}
