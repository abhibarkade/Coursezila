package com.abhibarkadde.coursezila.course;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.abhibarkadde.coursezila.R;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.extractor.ExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

public class FullScreenPlayer extends AppCompatActivity {

    ImageView setting;

    SimpleExoPlayerView exoPlayerView;
    SimpleExoPlayer exoPlayer;
    String videoURL = "https://firebasestorage.googleapis.com/v0/b/coursezila.appspot.com/o/Courses%2FCRS%3AANDROID%3AV1%2F1.Installation%20of%20Android%20Studio%2F1.Download%20and%20install.mp4?alt=media&token=717b5e1a-6d35-4723-8152-3855a9af28e0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_player);

        setting = findViewById(R.id.im_setting);
        exoPlayerView = findViewById(R.id.player);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setting.setOnClickListener(v -> showOptions());

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

    private void showOptions() {
        int[] modes = {
                AspectRatioFrameLayout.RESIZE_MODE_FIT,
                AspectRatioFrameLayout.RESIZE_MODE_FILL,
                AspectRatioFrameLayout.RESIZE_MODE_FIXED_WIDTH,
                AspectRatioFrameLayout.RESIZE_MODE_FIXED_HEIGHT
        };
        String[] options = {"Fit", "Fill", "Fit Width", "Fit Height"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Select Video mode");
        builder.setItems(options, (dialogInterface, i) -> {
            dialogInterface.dismiss();
            exoPlayerView.setResizeMode(modes[i]);
        });
        builder.show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            exoPlayer.setPlayWhenReady(false);
        } catch (Exception e) {
            Log.d("TAG", e.getMessage());
        }
    }

    public void close(View view) {
        finish();
    }
}