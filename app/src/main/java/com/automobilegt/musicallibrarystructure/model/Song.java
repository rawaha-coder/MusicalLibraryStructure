package com.automobilegt.musicallibrarystructure.model;

import androidx.annotation.Nullable;

import com.automobilegt.musicallibrarystructure.R;

import java.io.Serializable;

public class Song implements Serializable {
    private String mTitle;
    private String mArtist;
    private String mAlbum;
    private double mDuration;
    private int mSongImage;

    public Song() {
    }

    public Song(String title, @Nullable String artist, @Nullable String album, double duration, @Nullable Integer albumImage) {
        mTitle = title;
        mDuration = duration;
        if (artist == null) {
            mArtist = "Unknown";
        } else {
            mArtist = artist;
        }
        if (artist == null) {
            mAlbum = "Unknown";
        } else {
            mAlbum = album;
        }
        if (albumImage == null) {
            mSongImage = R.drawable.ic_music_song;
        } else {
            mSongImage = albumImage;
        }
    }

    public String getTitle() {
        return mTitle;
    }

    public String getArtist() {
        return mArtist;
    }

    public String getAlbum() {
        return mAlbum;
    }

    public double getDuration() {
        return mDuration;
    }

    public int getSongImage() {
        return mSongImage;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        assert obj != null;
        return (this.mTitle.equals(((Song) obj).mTitle) &&
                this.mArtist.equals(((Song) obj).mArtist) &&
                this.mAlbum.equals(((Song) obj).mAlbum) &&
                this.mDuration == ((Song) obj).mDuration);
    }
}
