package com.example.user.simplervtest.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.user.simplervtest.helpers.Consts;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by User on 25.03.2017
 */

public class TimeService extends Service implements Consts {

    private ExecutorService executorService;

    public void onCreate() {
        super.onCreate();
        executorService = Executors.newFixedThreadPool(2);
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public int onStartCommand(Intent intent, int flags, int startId) {

        MyRun mr = new MyRun(startId);
        executorService.execute(mr);

        return super.onStartCommand(intent, flags, startId);
    }

    public IBinder onBind(Intent arg0) {
        return null;
    }

    private class MyRun implements Runnable {

        private int startId;

        private MyRun(int startId) {
            this.startId = startId;
        }

        public void run() {
            Intent intent = new Intent(BROADCAST_ACTION);
            Calendar calendar = Calendar.getInstance();
            SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
            String formattedDate = dateFormat.format(calendar.getTime());
            intent.putExtra(PARAM_RESULT, formattedDate);
            sendBroadcast(intent);
            stop();
        }

        private void stop() {
            stopSelfResult(startId);
        }
    }
}