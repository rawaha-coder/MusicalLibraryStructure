package com.automobilegt.musicallibrarystructure.controller;

import android.content.Context;
import android.widget.TextView;
import android.widget.Toast;

import com.automobilegt.musicallibrarystructure.MainActivity;
import com.automobilegt.musicallibrarystructure.R;
import com.automobilegt.musicallibrarystructure.model.Song;

public class Controller {

    public static void playSong(Song song, Context context, TextView playingSongTitle){
        playingSongTitle.setText(song.getTitle());
        Toast mToast = new Toast(context);
        if (mToast != null){
            mToast.cancel();
        }
        String textMessage = context.getResources().getString(R.string.now_playing_message)  + song.getTitle();
        mToast = Toast.makeText(context, textMessage, Toast.LENGTH_SHORT);
        mToast.show();
    }

    public static void addToPlaylist(Song song, Context context){
        if (!MainActivity.savedPlaylistSong.contains(song)){
            MainActivity.savedPlaylistSong.add(song);
            Toast.makeText(context, song.getTitle() +context.getString(R.string.addToPlaylist) , Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, R.string.existInPlaylist, Toast.LENGTH_SHORT).show();
        }

    }

    public static void removeFromPlaylist(Song song, Context context){
        if (MainActivity.savedPlaylistSong.contains(song)){
            MainActivity.savedPlaylistSong.remove(song);
            Toast.makeText(context, song.getTitle() +context.getString(R.string.removeFromPlaylist), Toast.LENGTH_SHORT).show();
        }
    }
}
