package com.example.user.simplervtest.helpers;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.user.simplervtest.service.TimeService;


/**
 * Created by User on 25.03.2017
 */

public class Utils implements Consts{

    public static void loadImageIntoView(Context context, ImageView targetImageView, String internetUrl){

        Glide.with(context).load(Consts.BASE_IMAGE_PATH + internetUrl)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(targetImageView);
    }


    public static void scheduleService(Context context, Intent intent, int repeatTime) {
        PendingIntent pendingIntent = PendingIntent.getService(context, REQUEST_CODE, intent, 0);
        long firstTime = SystemClock.elapsedRealtime();
        firstTime += SECOND;//start 1 seconds after first register.

        // Schedule the alarm!
        AlarmManager am = (AlarmManager) context.getSystemService(Activity.ALARM_SERVICE);
        am.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP, firstTime,
                repeatTime, pendingIntent);
    }
}
