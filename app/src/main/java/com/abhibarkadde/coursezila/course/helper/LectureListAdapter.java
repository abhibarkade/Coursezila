package com.abhibarkadde.coursezila.course.helper;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.abhibarkadde.coursezila.R;
import com.abhibarkadde.coursezila.course.FullScreenPlayer;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LectureListAdapter extends RecyclerView.Adapter<LectureListAdapter.ViewHolder> {

    AppCompatActivity activity;
    List<String> list;

    public LectureListAdapter(AppCompatActivity activity, List<String> list) {
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_lecture_tile, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(list.get(position).split("~")[0]);
        holder.root.setOnClickListener(v -> activity.startActivity(new Intent(activity, FullScreenPlayer.class)
                .putExtra("Url", list.get(position).split("~")[1])));

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());
        executor.execute(() -> {
            try {
                String url = list.get(position).split("~")[1].trim();
                MediaPlayer mp = MediaPlayer.create(activity, Uri.parse(url));
                int duration = mp.getDuration();
                mp.release();
                holder.time.setText(String.format("%d min %d sec",
                        TimeUnit.MILLISECONDS.toMinutes(duration),
                        TimeUnit.MILLISECONDS.toSeconds(duration) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration))
                ));
                handler.post(() -> {
                    //Toast.makeText(activity, "HIT", Toast.LENGTH_SHORT).show();
                    // update the result to the UI thread
                    // or any operation you want to perform on UI thread. It is similar to onPostExecute() of AsyncTask
                });
            } catch (Exception e) {
                e.printStackTrace();
                handler.post(() -> {

                });
            }

        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout root;
        TextView name, time;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.txt_LectureName);
            time = itemView.findViewById(R.id.tx_LectureTime);
            root = itemView.findViewById(R.id.root);
        }
    }
}
