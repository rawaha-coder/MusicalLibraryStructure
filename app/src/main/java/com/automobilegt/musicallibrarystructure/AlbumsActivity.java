package com.automobilegt.musicallibrarystructure;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.automobilegt.musicallibrarystructure.adapters.AlbumListAdapter;
import com.automobilegt.musicallibrarystructure.data.Data;
import com.automobilegt.musicallibrarystructure.interfaces.ListItemClickListener;
import com.automobilegt.musicallibrarystructure.model.Album;

import java.util.ArrayList;

public class AlbumsActivity extends AppCompatActivity implements ListItemClickListener {

    private ArrayList<Album> mListAlbums;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_albums);
        setTitle(R.string.albums);
        mListAlbums = new ArrayList<>();
        mListAlbums = Data.getAlbum(Data.listSongs());

        RecyclerView albumListRecyclerView = findViewById(R.id.rv_albums);
        albumListRecyclerView.setHasFixedSize(true);
        AlbumListAdapter albumAdapter = new AlbumListAdapter(mListAlbums, this);
        albumListRecyclerView.setAdapter(albumAdapter);
        albumListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button btnAllSongList = findViewById(R.id.btn_all_songs);
        Button btnPlaylist = findViewById(R.id.btn_playlist);
        Button btnArtistList = findViewById(R.id.btn_list_artists);
        Button btnAlbumList = findViewById(R.id.btn_list_albums);

        btnAlbumList.setBackgroundColor(getResources().getColor(R.color.colorButtonEnabled));
        btnAlbumList.setTextColor(getResources().getColor(R.color.colorButtonTextColor));

        btnPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlbumsActivity.this, PlaylistActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnAllSongList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlbumsActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnArtistList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlbumsActivity.this, ArtistsActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onListItemClick(View view, int clickedItemIndex) {
        Intent intent = new Intent(AlbumsActivity.this, SelectedSongsActivity.class);
        intent.putExtra("listSongs", mListAlbums.get(clickedItemIndex).getListSongs());
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.leave_app).setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        }).setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}