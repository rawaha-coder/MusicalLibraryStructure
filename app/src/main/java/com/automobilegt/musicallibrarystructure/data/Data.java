package com.automobilegt.musicallibrarystructure.data;

import com.automobilegt.musicallibrarystructure.model.Album;
import com.automobilegt.musicallibrarystructure.model.Artist;
import com.automobilegt.musicallibrarystructure.model.Song;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Data {

    //This method is just for help illustration the function of the app.
    public static ArrayList<Song> listSongs(){
        ArrayList<Song> songs = new ArrayList<>();
        for (int i = 0; i < 50 ; i++){
            double duration = 05.43;
            String artist;
            String album;
            if (i < 10){
                artist = "Artist 1";
                album = "Rabat";
            }else if(i < 20){
                artist = "Artist 2";
                album = "Tokyo";
            }else if(i < 30){
                artist = "Artist 3";
                album = "Paris";
            }else if(i < 40){
                artist = "Artist 1";
                album = "Roma";
            }else {
                artist = "Artist 2";
                album = "London";
            }

            songs.add(new Song("Song #" + i, artist, album, duration, null));
        }
        return songs;
    }

    public static ArrayList<Album> getAlbum(ArrayList<Song> songArrayList){
        ArrayList<Album> albumArrayList = new ArrayList<>();
        Set<String> setAlbum = new HashSet();
        for (Song song : songArrayList){
            setAlbum.add(song.getAlbum());
        }
        for (String albumName : setAlbum) {
            Album album = new Album();
            ArrayList<Song> listSong = new ArrayList<>();
            for (Song song : songArrayList) {
                if (albumName.equals(song.getAlbum())) {
                    listSong.add(song);
                    album = new Album(song.getAlbum(), song.getArtist(), listSong, null);
                }
            }
            albumArrayList.add(album);
        }
        return albumArrayList;
    }

    public static ArrayList<Artist> getArtist(ArrayList<Song> songList){
        ArrayList<Artist> artistArrayList = new ArrayList<>();
        Set<String> setArtist = new HashSet();
        for (Song song : songList){
            setArtist.add(song.getArtist());
        }
        for (String artistName : setArtist) {
            Artist artist = new Artist();
            ArrayList<Song> listSong = new ArrayList<>();
            ArrayList<Album> listAlbum;

            for (Song song : songList) {
                if (artistName.equals(song.getArtist())) {
                    listSong.add(song);
                    listAlbum = getAlbum(listSong);
                    artist = new Artist(song.getArtist(), listSong, listAlbum.size(), null);
                }
            }

            artistArrayList.add(artist);
        }
        return artistArrayList;
    }
}
