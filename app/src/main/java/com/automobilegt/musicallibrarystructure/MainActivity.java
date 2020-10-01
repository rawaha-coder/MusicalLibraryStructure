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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.automobilegt.musicallibrarystructure.adapters.SongListAdapter;
import com.automobilegt.musicallibrarystructure.controller.Controller;
import com.automobilegt.musicallibrarystructure.data.Data;
import com.automobilegt.musicallibrarystructure.interfaces.ListItemClickListener;
import com.automobilegt.musicallibrarystructure.model.Song;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ListItemClickListener {

    public static ArrayList<Song> savedPlaylistSong;
    private TextView tvPlayingSongTitle;
    private int playSongIndex;
    private ArrayList<Song> mListSongs;
    ImageButton playImageButton;
    public boolean playingState = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle(R.string.all_songs);

        if (savedPlaylistSong == null) {
            savedPlaylistSong = new ArrayList<>();
        }
        playSongIndex = 0;
        mListSongs = new ArrayList<>();
        mListSongs = Data.listSongs();

        RecyclerView playlistRecyclerView = findViewById(R.id.rv_songs);
        SongListAdapter songListAdapter = new SongListAdapter(mListSongs, this);
        playlistRecyclerView.setAdapter(songListAdapter);
        playlistRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button btnAllSongList = findViewById(R.id.btn_all_songs);
        Button btnPlaylist = findViewById(R.id.btn_playlist);
        Button btnArtistList = findViewById(R.id.btn_list_artists);
        Button btnAlbumList = findViewById(R.id.btn_list_albums);
        tvPlayingSongTitle = findViewById(R.id.tv_now_playing_song);

        btnAllSongList.setBackgroundColor(getResources().getColor(R.color.colorButtonEnabled));
        btnAllSongList.setTextColor(getResources().getColor(R.color.colorButtonTextColor));

        btnPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, PlaylistActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnAlbumList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AlbumsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnArtistList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ArtistsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        playImageButton = findViewById(R.id.ib_play_song);
        playImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!playingState) {
                    Controller.playSong(mListSongs.get(playSongIndex), MainActivity.this, tvPlayingSongTitle);
                    playImageButton.setImageResource(R.drawable.ic_pause_song);
                    playingState = true;
                }else {
                    playImageButton.setImageResource(R.drawable.ic_play_music);
                    playingState = false;
                }
            }
        });

        tvPlayingSongTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NowPlayingActivity.class);
                intent.putExtra("listSongs", mListSongs);
                intent.putExtra("PlaySongIndex", playSongIndex);
                intent.putExtra("playingState", playingState);
                startActivity(intent);
                finish();
            }
        });
        ImageView playingSongImage = findViewById(R.id.iv_now_playing_song);
        playingSongImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NowPlayingActivity.class);
                intent.putExtra("listSongs", mListSongs);
                intent.putExtra("PlaySongIndex", playSongIndex);
                intent.putExtra("playingState", playingState);
                startActivity(intent);
                finish();
            }
        });

    }

    @Override
    public void onListItemClick(View view, int clickedItemIndex) {
        playSongIndex = clickedItemIndex;
        if (view.getId() == R.id.rv_row_song_title || view.getId() == R.id.rv_row_song_duration_artist || view.getId() == R.id.rv_row_song_image) {
            Controller.playSong(mListSongs.get(playSongIndex), MainActivity.this, tvPlayingSongTitle);
            playImageButton.setImageResource(R.drawable.ic_pause_song);
            playingState = true;
        } else if (view.getId() == R.id.rv_row_add_to_playlist) {
            Controller.addToPlaylist(mListSongs.get(playSongIndex), MainActivity.this);
        }
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