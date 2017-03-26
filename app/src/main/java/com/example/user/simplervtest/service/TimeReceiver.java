package com.example.user.simplervtest.service;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;

import com.example.user.simplervtest.helpers.Consts;

/**
 * Created by User on 25.03.2017
 */

public class TimeReceiver extends BroadcastReceiver implements Consts {

    private AppCompatActivity activity;

    public TimeReceiver(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        String result = intent.getStringExtra(PARAM_RESULT);
        TimeDialogFragment dialogFragment = new TimeDialogFragment();
        dialogFragment.setTime(result);
        dialogFragment.show(activity.getSupportFragmentManager(), "");
    }
}
