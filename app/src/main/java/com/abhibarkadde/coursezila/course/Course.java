package com.abhibarkadde.coursezila.course;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.abhibarkadde.coursezila.R;
import com.abhibarkadde.coursezila.course.helper.PlaylistAdapter;
import com.abhibarkadde.coursezila.dialogs.ShowMessagePrompt;
import com.abhibarkadde.coursezila.profile.UserDetails;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.Arrays;
import java.util.Objects;

public class Course extends AppCompatActivity {

    RecyclerView playlist;
    TextView txtDescription;
    MaterialTextView txtEnrollNow;

    Dialog loadingDialog;

    MaterialCardView enrollNow;
    MaterialToolbar toolbar;

    FirebaseFirestore firestore;
    FirebaseUser user;
    StorageReference storage;

    SimpleExoPlayerView exoPlayerView;
    SimpleExoPlayer exoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        initAll();
        listeners();
    }

    @SuppressLint("RestrictedApi")
    private void initAll() {
        // Exo Player
        exoPlayerView = findViewById(R.id.player);

        // Buttons
        //btnPlayPause = findViewById(R.id.btn_PlayPause);

        // RecyclerView
        playlist = findViewById(R.id.recyclerView);

        // TextView
        txtDescription = findViewById(R.id.txt_Description);
        txtEnrollNow = findViewById(R.id.txt_EnrollNow);

        // Enroll Part
        enrollNow = findViewById(R.id.registrationBlock);

        // LinearLayout
        //controller = findViewById(R.id.controller);

        // Toolbar
        toolbar = findViewById(R.id.course_toolbar);
        {
            toolbar.setTitle("Android app Development");
            toolbar.setTitleCentered(true);
            setSupportActionBar(toolbar);
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

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

    private void checkUserCourseDetails() {
        user = FirebaseAuth.getInstance().getCurrentUser();
        firestore.collection("Users")
                .document(user.getEmail().split("@")[0])
                .get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        boolean flg = false;
                        UserDetails userDetails = task.getResult().toObject(UserDetails.class);
                        for (String str : userDetails.getEnrolledIn().split("~")) {
                            if (str.equals("CRS:ANDROID:V1")) {
                                flg = true;
                                break;
                            }
                        }
                        Toast.makeText(Course.this, "" + flg, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @SuppressLint("ResourceType")
    private void listeners() {
        // Exo Player - Bind & Play video
        try {
            BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
            TrackSelector trackSelector = new DefaultTrackSelector(new AdaptiveTrackSelection.Factory(bandwidthMeter));
            exoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector);
            Uri videoUri = Uri.parse(AppConstants.intro);
            DefaultHttpDataSourceFactory dataSourceFactory = new DefaultHttpDataSourceFactory("exoplayer_video");
            ExtractorsFactory extractorsFactory = new DefaultExtractorsFactory();
            MediaSource mediaSource = new ExtractorMediaSource(videoUri, dataSourceFactory, extractorsFactory, null, null);
            exoPlayerView.setPlayer(exoPlayer);
            exoPlayer.prepare(mediaSource);
            exoPlayer.setPlayWhenReady(false);
        } catch (Exception e) {
            Log.e("TAG", "Error : " + e);
        }

        // Enroll for Course
        txtEnrollNow.setOnClickListener(v -> ShowMessagePrompt.showRegistrationPrompt(this,
                "Register for Android App Development",
                "Enter Coupon to get started with it",
                firestore, user));
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

    @Override
    protected void onPause() {
        super.onPause();
        try {
            exoPlayer.setPlayWhenReady(false);
        } catch (Exception e) {
            Log.d("TAG", e.getMessage());
        }
    }
}