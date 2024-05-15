package ee.taltech.iti0202.spotify;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Playlist {
    private final String name;
    private final List<Song> songs;

    public Playlist(String name) {
        this.name = name;
        this.songs = new ArrayList<>();
    }

    public void addSong(Song song) {
        if (!this.songs.contains(song)) {
            this.songs.add(song);
        }
    }

    public void removeSong(Song song) {
        this.songs.remove(song);
    }

    public List<Song> getSongsByArtist(String artist) {
        List<Song> result = new ArrayList<>();
        for (Song song : this.songs) {
            if (song.artist().equalsIgnoreCase(artist)) {
                result.add(song);
            }
        }
        return result;
    }

    public List<Song> sortSongsAlphabetically() {
        return songs.stream().sorted().collect(Collectors.toList());
    }

    public Song getLongestSong() {
        return songs.stream().max(Comparator.comparing(Song::duration)).orElse(null);
    }

    public String getDurationOfPlaylist() {
        // TODO
        return "";
    }

    public List<Song> getSongs() {
        return songs;
    }

    public String getName() {
        return name;
    }

}
