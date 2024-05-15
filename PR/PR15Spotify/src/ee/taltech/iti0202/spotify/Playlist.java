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
        boolean found = false;
        for (Song s : songs) {
            if (s.title().equalsIgnoreCase(song.title()) && s.artist().equalsIgnoreCase(song.artist())) {
                found = true;
                break;
            }
        }
        if (!found) {
            songs.add(song);
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
        int duration = 0;
        for (Song song : songs) {
            duration += song.duration();
        }

        int hours = duration / 3600;
        int minutes = duration % 3600 / 60;
        int seconds = duration % 60;

        if (duration == 0) {
            return "0s";
        }

        StringBuilder durationString = new StringBuilder();
        if (hours > 0 && seconds <= 0 && minutes <= 0) {
            durationString.append(hours).append("h");
        } else if (hours > 0) {
            durationString.append(hours).append("h ");
        }

        if (minutes > 0 && seconds <= 0) {
            durationString.append(minutes).append("m");
        } else if (minutes > 0){
            durationString.append(minutes).append("m ");
        }

        if (seconds > 0) {
            durationString.append(seconds).append("s");
        }
        return durationString.toString();
    }

    public List<Song> getSongs() {
        return songs;
    }

    public String getName() {
        return name;
    }

}