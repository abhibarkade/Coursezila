package com.abhibarkadde.coursezila.course.helper;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.abhibarkadde.coursezila.R;

import java.util.List;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder> {

    List<String> list;
    AppCompatActivity activity;

    public PlaylistAdapter(AppCompatActivity activity, List<String> list) {
        this.list = list;
        this.activity = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.module_top_tile, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.name.setText(list.get(position).split("\\.")[1]);
        holder.dropLayout.setOnClickListener(v -> {
            if (holder.recyclerView.getVisibility() == View.GONE) {
                holder.recyclerView.setVisibility(View.VISIBLE);
                holder.arrow.setBackground(ContextCompat.getDrawable(activity, R.drawable.ic_arrow_down));
            } else {
                holder.recyclerView.setVisibility(View.GONE);
                holder.arrow.setBackground(ContextCompat.getDrawable(activity, R.drawable.ic_arrow_up));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView arrow;
        RecyclerView recyclerView;
        LinearLayout dropLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.txt_moduleName);
            arrow = itemView.findViewById(R.id.txt_arrow);
            recyclerView = itemView.findViewById(R.id.lecture_tile_recycler_view);
            dropLayout = itemView.findViewById(R.id.dropLayout);
        }
    }
}
