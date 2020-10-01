package com.automobilegt.musicallibrarystructure.model;

import androidx.annotation.Nullable;

import com.automobilegt.musicallibrarystructure.R;

import java.io.Serializable;
import java.util.ArrayList;

public class Album implements Serializable {
    private String mAlbumName;
    private String mArtistName;
    private ArrayList<Song> mListSongs;
    private int mAlbumImage;

    public Album(){}
    public Album(String albumName ,@Nullable String artistName, ArrayList<Song> listSongs ,@Nullable Integer albumImage) {

        mAlbumName = albumName;
        mListSongs = listSongs;

        if(artistName == null){
            mArtistName = "Unknown";
        }else {
            mArtistName = artistName;
        }

        if (albumImage == null){
            mAlbumImage = R.drawable.ic_music_song;
        }else {
            mAlbumImage = albumImage;
        }
    }

    public String getAlbumName() {
        return mAlbumName;
    }

    public String getArtistName() {
        return mArtistName;
    }

    public ArrayList<Song> getListSongs() { return mListSongs; }

    public int getAlbumImage() {
        return mAlbumImage;
    }
}
