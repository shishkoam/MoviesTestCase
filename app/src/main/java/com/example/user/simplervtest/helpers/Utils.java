package com.example.user.simplervtest.helpers;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;


/**
 * Created by User on 25.03.2017
 */

public class Utils {

    public static void loadImageIntoView(Context context, ImageView targetImageView, String internetUrl){

        Glide.with(context).load(Consts.BASE_IMAGE_PATH + internetUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(targetImageView);
    }
}
