package com.abhibarkadde.coursezila.course;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

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
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory;

public class Course extends AppCompatActivity {

    ImageView btnPlayPause;

    SimpleExoPlayerView exoPlayerView;
    SimpleExoPlayer exoPlayer;
//    String videoURL = "https://media.geeksforgeeks.org/wp-content/uploads/20201217163353/Screenrecorder-2020-12-17-16-32-03-350.mp4";
    String videoURL = "https://firebasestorage.googleapis.com/v0/b/coursezila.appspot.com/o/Courses%2FC%3Aadc01373-2a2a-4c4e-94ff-ea79d1a65998%2Fdownload%20and%20install.mp4?alt=media&token=16e4a800-fc0c-4b89-b8e6-821385694eba";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        btnPlayPause = findViewById(R.id.btn_PlayPause);

        exoPlayerView = findViewById(R.id.player);

        listeners();
    }

    @SuppressLint("ResourceType")
    private void listeners() {
        exoPlayerView.setOnHoverListener((view, motionEvent) -> {
            Toast.makeText(this, "HIT - - -1", Toast.LENGTH_SHORT).show();
            btnPlayPause.setVisibility(View.VISIBLE);
            return false;
        });

        exoPlayerView.setOnClickListener(v -> {
            Toast.makeText(this, "HIT1", Toast.LENGTH_SHORT).show();
            btnPlayPause.setVisibility(View.VISIBLE);
        });

        try {
            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
            exoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector);
            Uri videouri = Uri.parse(videoURL);
            DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("exoplayer_video");
            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
            MediaSource mediaSource = new ExtractorMediaSource(videouri, dataSourceFactory, extractorsFactory, null, null);
            exoPlayerView.setPlayer(exoPlayer);
            exoPlayer.prepare(mediaSource);
            exoPlayer.setPlayWhenReady(false);

        } catch (Exception e) {
            Log.e("TAG", "Error : " + e);
        }
    }

    public void showPlayPauseBtn(View view) {
        btnPlayPause.setVisibility(View.VISIBLE);
        try {
            if (exoPlayer.getPlayWhenReady()) {
                exoPlayer.setPlayWhenReady(false);
                btnPlayPause.setBackground(ContextCompat.getDrawable(Course.this, R.drawable.img_play));
            } else {
                exoPlayer.setPlayWhenReady(true);
                btnPlayPause.setBackground(ContextCompat.getDrawable(Course.this, R.drawable.img_pause));
                new Handler().postDelayed(() -> btnPlayPause.setVisibility(View.GONE), 1500);
            }
        } catch (Exception exc) {
            Log.d("TAG", exc.getMessage());
        }
    }
}