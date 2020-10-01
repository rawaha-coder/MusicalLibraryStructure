package com.automobilegt.musicallibrarystructure.model;

import androidx.annotation.Nullable;

import com.automobilegt.musicallibrarystructure.R;

import java.io.Serializable;
import java.util.ArrayList;

public class Artist implements Serializable {

    private String mArtistName;
    private ArrayList<Song> mListSongs;
    private int mNumberOfAlbums;
    private int mArtistImage;

    public Artist(){}
    public Artist(String artistName, ArrayList<Song> listSongs, int numberOfAlbums, @Nullable Integer artistImage) {
        mArtistName = artistName;
        mListSongs = listSongs;
        mNumberOfAlbums = numberOfAlbums;
        if (artistImage == null){
            mArtistImage = R.drawable.ic_music_song;
        }else {
            mArtistImage= artistImage;
        }
    }

    public String getArtistName() {
        return mArtistName;
    }

    public ArrayList<Song> getListSongs() { return mListSongs; }

    public int getNumberOfAlbums() { return mNumberOfAlbums; }

    public int getArtistImage() {
        return mArtistImage;
    }
}

