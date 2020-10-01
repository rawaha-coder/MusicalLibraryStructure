package com.automobilegt.musicallibrarystructure;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.automobilegt.musicallibrarystructure.controller.Controller;
import com.automobilegt.musicallibrarystructure.model.Song;

import java.util.ArrayList;

public class NowPlayingActivity extends AppCompatActivity {
    private TextView playingSongTitle;
    private ArrayList<Song> songArrayList;
    private int playSongIndex;
    public boolean playingState = false;
    ImageButton playImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_now_playing);
        setTitle(R.string.playing_song);

        Intent intent = getIntent();
        songArrayList = (ArrayList<Song>) intent.getSerializableExtra("listSongs");
        playSongIndex = intent.getIntExtra("PlaySongIndex", 0);
        playingState = intent.getBooleanExtra("playingState", false);
        playingSongTitle = findViewById(R.id.tv_now_playing_song);
        playImageButton = findViewById(R.id.ib_play_song);

        if (songArrayList.size() > 0){
            if (playingState){
                Controller.playSong(songArrayList.get(playSongIndex), NowPlayingActivity.this, playingSongTitle);
                playImageButton.setImageResource(R.drawable.ic_pause_song);
                playingState = true;
            }else {
                playImageButton.setImageResource(R.drawable.ic_play_music);
                playingState = false;
            }
        }
        playImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (songArrayList.size() > 0){
                    if (!playingState){
                        Controller.playSong(songArrayList.get(playSongIndex), NowPlayingActivity.this, playingSongTitle);
                        playImageButton.setImageResource(R.drawable.ic_pause_song);
                        playingState = true;
                    }else {
                        playImageButton.setImageResource(R.drawable.ic_play_music);
                        playingState = false;
                    }
                }
            }
        });

        ImageButton forwardImageButton = findViewById(R.id.ib_fast_forward);
        forwardImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (playSongIndex < (songArrayList.size() - 1)) {
                    Controller.playSong(songArrayList.get(playSongIndex + 1), NowPlayingActivity.this, playingSongTitle);
                    playImageButton.setImageResource(R.drawable.ic_pause_song);
                    playingState = true;
                    playSongIndex++;
                }
            }
        });

        ImageButton rewindImageButton = findViewById(R.id.ib_fast_rewind);
        rewindImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (playSongIndex > 0) {
                    Controller.playSong(songArrayList.get(playSongIndex - 1), NowPlayingActivity.this, playingSongTitle);
                    playImageButton.setImageResource(R.drawable.ic_pause_song);
                    playingState = true;
                    playSongIndex--;
                }
            }
        });

        Button btnAllSongs = findViewById(R.id.btn_all_songs);
        Button btnPlaylist = findViewById(R.id.btn_playlist);
        Button btnArtistslist = findViewById(R.id.btn_list_artists);
        Button btnAlbumsList = findViewById(R.id.btn_list_albums);

        btnAllSongs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NowPlayingActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnPlaylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NowPlayingActivity.this, PlaylistActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnAlbumsList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NowPlayingActivity.this, AlbumsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnArtistslist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NowPlayingActivity.this, ArtistsActivity.class);
                startActivity(intent);
                finish();
            }
        });
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