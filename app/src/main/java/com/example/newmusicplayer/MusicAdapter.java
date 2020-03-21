package com.example.newmusicplayer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.MusicViewHolder> {
    private List<Data> list = Collections.emptyList();


    public MusicAdapter(List<Data> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.details_row, parent, false);
        MusicViewHolder vh = new MusicViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MusicViewHolder holder, int position) {
        holder.songImgView.setImageBitmap(list.get(position).bmp);
        holder.songDescView.setText(list.get(position).desc);
        holder.songNameView.setText(list.get(position).name);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public static class MusicViewHolder extends RecyclerView.ViewHolder {
        public TextView songNameView, songDescView;
        public ImageView songImgView;

        public MusicViewHolder(@NonNull View itemView) {
            super(itemView);
            songNameView = itemView.findViewById(R.id.title);
            songDescView = itemView.findViewById(R.id.description);
            songImgView = itemView.findViewById(R.id.imageView);
        }

    }


}