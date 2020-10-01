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

import com.automobilegt.musicallibrarystructure.adapters.ArtistListAdapter;
import com.automobilegt.musicallibrarystructure.data.Data;
import com.automobilegt.musicallibrarystructure.interfaces.ListItemClickListener;
import com.automobilegt.musicallibrarystructure.model.Artist;

import java.util.ArrayList;

public class ArtistsActivity extends AppCompatActivity implements ListItemClickListener {

    private ArrayList<Artist> mListArtists;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artists);
        setTitle(R.string.artists);
        mListArtists = new ArrayList<>();
        mListArtists = Data.getArtist(Data.listSongs());

        RecyclerView artistListRecyclerView = findViewById(R.id.rv_artists);
        artistListRecyclerView.setHasFixedSize(true);
        ArtistListAdapter artistListAdapter = new ArtistListAdapter(mListArtists, this);
        artistListRecyclerView.setAdapter(artistListAdapter);
        artistListRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button btnAllSongList = findViewById(R.id.btn_all_songs);
        Button btnPlaylist = findViewById(R.id.btn_playlist);
        Button btnArtistList = findViewById(R.id.btn_list_artists);
        Button btnAlbumList = findViewById(R.id.btn_list_albums);

        btnArtistList.setBackgroundColor(getResources().getColor(R.color.colorButtonEnabled));
        btnArtistList.setTextColor(getResources().getColor(R.color.colorButtonTextColor));

        btnPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ArtistsActivity.this, PlaylistActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnAllSongList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ArtistsActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnAlbumList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ArtistsActivity.this, AlbumsActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onListItemClick(View view, int clickedItemIndex) {
        Intent intent = new Intent(ArtistsActivity.this, SelectedSongsActivity.class);
        intent.putExtra("listSongs", mListArtists.get(clickedItemIndex).getListSongs());
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