package com.example.user.simplervtest.activity;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.example.user.simplervtest.BR;
import com.example.user.simplervtest.R;
import com.example.user.simplervtest.helpers.Consts;
import com.example.user.simplervtest.model.Movie;
import com.example.user.simplervtest.service.TimeReceiver;

public class DetailsActivity extends AppCompatActivity implements Consts {

    private BroadcastReceiver br;
    private static final int LAYOUT_ACTIVITY = R.layout.activity_details;
    private Movie movie;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ViewDataBinding binding = DataBindingUtil.setContentView(this, LAYOUT_ACTIVITY);
        if (savedInstanceState == null) {
            movie = (Movie) getIntent().getSerializableExtra(CURR_MOVIE);
        } else {
            movie = (Movie) savedInstanceState.getSerializable(CURR_MOVIE);
        }
        binding.setVariable(BR.movie, getIntent().getSerializableExtra(CURR_MOVIE));

        //register receiver
        br = new TimeReceiver();
        IntentFilter intFilt = new IntentFilter(BROADCAST_ACTION);
        registerReceiver(br, intFilt);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // unregister BroadcastReceiver
        unregisterReceiver(br);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(CURR_MOVIE, movie);
    }
}
