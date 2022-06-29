package com.abhibarkadde.coursezila.course;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.abhibarkadde.coursezila.R;
import com.abhibarkadde.coursezila.course.helper.PlaylistAdapter;
import com.abhibarkadde.coursezila.utils.AppConstants;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Arrays;

public class Course extends AppCompatActivity {

    ImageView btnPlayPause;
    RecyclerView playlist;
    TextView txtDescription;
    FrameLayout controller;

    Dialog loadingDialog;

    FirebaseFirestore firestore;
    StorageReference storage;

    SimpleExoPlayerView exoPlayerView;
    SimpleExoPlayer exoPlayer;
    String videoURL = "https://firebasestorage.googleapis.com/v0/b/coursezila.appspot.com/o/Courses%2FCRS%3AANDROID%3AV1%2F1.Installation%20of%20Android%20Studio%2F1.Download%20and%20install.mp4?alt=media&token=717b5e1a-6d35-4723-8152-3855a9af28e0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        initAll();
        listeners();
    }

    private void initAll() {
        // Exo Player
        exoPlayerView = findViewById(R.id.player);

        // Buttons
        btnPlayPause = findViewById(R.id.btn_PlayPause);

        // RecyclerView
        playlist = findViewById(R.id.recyclerView);

        // TextView
        txtDescription = findViewById(R.id.txt_Description);

        // LinearLayout
        controller = findViewById(R.id.controller);

        // Dialog
        loadingDialog = new Dialog(this, R.style.Dialog_rounded);
        {
            loadingDialog.setContentView(R.layout.loading_animation);
            loadingDialog.setCancelable(false);
            loadingDialog.show();
        }

        // Firebase
        {
            firestore = FirebaseFirestore.getInstance();
            storage = FirebaseStorage.getInstance().getReference();
            bindModules();
        }
    }

    @SuppressLint("ResourceType")
    private void listeners() {
        // Exo Player - Bind & Play video
        try {
            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
            exoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector);
            Uri videoUri = Uri.parse(videoURL);
            DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("exoplayer_video");
            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
            MediaSource mediaSource = new ExtractorMediaSource(videoUri, dataSourceFactory, extractorsFactory, null, null);
            exoPlayerView.setPlayer(exoPlayer);
            exoPlayer.prepare(mediaSource);
            exoPlayer.setPlayWhenReady(false);
        } catch (Exception e) {
            Log.e("TAG", "Error : " + e);
        }
    }

    public void showPlayPauseBtn(View view) {
        controller.setVisibility(View.VISIBLE);
        try {
            if (exoPlayer.getPlayWhenReady()) {
                exoPlayer.setPlayWhenReady(false);
                btnPlayPause.setBackground(ContextCompat.getDrawable(Course.this, R.drawable.img_play));
            } else {
                exoPlayer.setPlayWhenReady(true);
                btnPlayPause.setBackground(ContextCompat.getDrawable(Course.this, R.drawable.img_pause));
                new Handler().postDelayed(() -> controller.setVisibility(View.GONE), 1500);
            }
        } catch (Exception exc) {
            Log.d("TAG", exc.getMessage());
        }
    }

    public void changeView(View view) {
        TextView t1 = findViewById(R.id.tx_ch1);
        TextView t2 = findViewById(R.id.tx_ch2);

        TextView txt = (TextView) view;
        if (txt.getText().equals("Description")) {
            txt.setTextColor(Color.parseColor("#FFFFFF"));
            txt.setBackground(ContextCompat.getDrawable(this, R.drawable.label_back));
            t1.setTextColor(Color.parseColor("#808080"));
            t1.setBackground(null);
            txtDescription.setVisibility(View.VISIBLE);
            playlist.setVisibility(View.GONE);
        } else if (txt.getText().equals("Playlist")) {
            txt.setTextColor(Color.parseColor("#FFFFFF"));
            txt.setBackground(ContextCompat.getDrawable(this, R.drawable.label_back));
            t2.setTextColor(Color.parseColor("#808080"));
            t2.setBackground(null);
            txtDescription.setVisibility(View.GONE);
            playlist.setVisibility(View.VISIBLE);
        }
    }

    // Get Modules from Firestore Database
    public void bindModules() {
        try {
            playlist.setLayoutManager(new LinearLayoutManager(this));
            playlist.setAdapter(new PlaylistAdapter(this, Arrays.asList(AppConstants.module)));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            loadingDialog.dismiss();
        }
    }

    public void goBack(View view) {
        finish();
    }
}