package com.abhibarkadde.coursezila.asynctasks;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.concurrent.TimeUnit;

public class CheckVideoLength extends AsyncTaskLoader<String> {
    String url;

    public CheckVideoLength(@NonNull Context context, String url) {
        super(context);
    }

    @SuppressLint("DefaultLocale")
    @Nullable
    @Override
    public String loadInBackground() {
        MediaPlayer mp = MediaPlayer.create(getContext(), Uri.parse(url.trim()));
        int duration = mp.getDuration();
        mp.release();
        return String.format("%d min %d sec",
                TimeUnit.MILLISECONDS.toMinutes(duration),
                TimeUnit.MILLISECONDS.toSeconds(duration) -
                        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration))
        );
    }
}
