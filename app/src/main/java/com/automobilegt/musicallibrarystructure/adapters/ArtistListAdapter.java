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
import com.automobilegt.musicallibrarystructure.model.Artist;

import java.util.ArrayList;

public class ArtistListAdapter extends RecyclerView.Adapter<ArtistListAdapter.ViewHolder> implements ListItemClickListener {

    private ArrayList<Artist> mArtistList;
    final private ListItemClickListener mOnClickListener;

    public ArtistListAdapter(ArrayList<Artist> artistList, ListItemClickListener onClickListener) {
        mArtistList = artistList;
        mOnClickListener = onClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.rv_item_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.adapterPosition(position);
        Artist artist = mArtistList.get(position);
        holder.mArtistName.setText(artist.getArtistName());
        holder.mArtistImage.setImageResource(artist.getArtistImage());
        String album_song = artist.getNumberOfAlbums() + " Album, " + artist.getListSongs().size() + " songs";
        holder.mArtistSongs.setText(album_song);
    }

    @Override
    public int getItemCount() {
        return mArtistList.size();
    }

    @Override
    public void onListItemClick(View view, int clickedItemIndex) {

    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView mArtistImage;
        TextView mArtistName;
        TextView mArtistSongs;
        int position;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mArtistName = itemView.findViewById(R.id.rv_row_album_name);
            mArtistImage = itemView.findViewById(R.id.rv_row_album_image);
            mArtistSongs = itemView.findViewById(R.id.rv_row_artist_size);
            itemView.setOnClickListener(this);
        }

        public void adapterPosition(int index) {
            position = index;
        }

        @Override
        public void onClick(View view) {
            mOnClickListener.onListItemClick(view, position);
        }
    }
}
