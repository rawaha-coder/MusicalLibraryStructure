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
import com.automobilegt.musicallibrarystructure.model.Album;

import java.util.ArrayList;

public class AlbumListAdapter extends RecyclerView.Adapter<AlbumListAdapter.ViewHolder> implements ListItemClickListener {

    private ArrayList<Album> mAlbumList;
    final private ListItemClickListener mOnClickListener;

    public AlbumListAdapter(ArrayList<Album> albumList, ListItemClickListener onClickListener) {
        mAlbumList = albumList;
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
        Album  album = mAlbumList.get(position);
        holder.mAlbumName.setText(album.getAlbumName());
        holder.mAlbumImage.setImageResource(album.getAlbumImage());
        String artist_song = album.getArtistName() + ", " + album.getListSongs().size() + " song";
        holder.mArtistSong.setText(artist_song);
    }

    @Override
    public int getItemCount() {
        return mAlbumList.size();
    }

    @Override
    public void onListItemClick(View view, int clickedItemIndex) {

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView mAlbumImage;
        TextView mAlbumName;
        TextView mArtistSong;
        int position;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mAlbumName = itemView.findViewById(R.id.rv_row_album_name);
            mAlbumImage = itemView.findViewById(R.id.rv_row_album_image);
            mArtistSong = itemView.findViewById(R.id.rv_row_artist_size);
            itemView.setOnClickListener(this);
        }

        public void adapterPosition(int index){
            position = index;
        }

        @Override
        public void onClick(View view) {
            mOnClickListener.onListItemClick(view, position);
        }
    }
}

