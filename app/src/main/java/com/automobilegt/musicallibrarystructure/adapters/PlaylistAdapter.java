package com.automobilegt.musicallibrarystructure.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.automobilegt.musicallibrarystructure.R;
import com.automobilegt.musicallibrarystructure.interfaces.ListItemClickListener;
import com.automobilegt.musicallibrarystructure.model.Song;

import java.util.ArrayList;

public class PlaylistAdapter extends RecyclerView.Adapter<PlaylistAdapter.ViewHolder> implements ListItemClickListener {

    private ArrayList<Song> mListSongs;
    Context mContext;
    final private ListItemClickListener mOnClickListener;

    public PlaylistAdapter(ArrayList<Song> listSongs, ListItemClickListener onClickListener) {
        mListSongs = listSongs;
        mOnClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.rv_song_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.adapterPosition(position);
        Song mSong = mListSongs.get(position);
        holder.songTitle.setText(mSong.getTitle());
        holder.songImage.setImageResource(mSong.getSongImage());
        holder.addToPlaylist.setImageResource(R.drawable.ic_remove_from_playlist);
        String duration_artist = mSong.getDuration() + " " + mSong.getArtist();
        holder.duration.setText(duration_artist);
    }

    @Override
    public int getItemCount() {
        return mListSongs.size();
    }

    @Override
    public void onListItemClick(View view, int clickedItemIndex) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView songImage;
        TextView songTitle;
        TextView duration;
        ImageView addToPlaylist;
        int position;
        public ViewHolder(View itemView) {
            super(itemView);
            songTitle = itemView.findViewById(R.id.rv_row_song_title);
            songImage = itemView.findViewById(R.id.rv_row_song_image);
            duration = itemView.findViewById(R.id.rv_row_song_duration_artist);
            addToPlaylist = itemView.findViewById(R.id.rv_row_add_to_playlist);
            songTitle.setOnClickListener(this);
            addToPlaylist.setOnClickListener(this);
            duration.setOnClickListener(this);
            songImage.setOnClickListener(this);
        }

        public void adapterPosition(int songIndex){
            position = songIndex;
        }

        @Override
        public void onClick(View view) {
            mOnClickListener.onListItemClick(view, position);
        }
    }
}
