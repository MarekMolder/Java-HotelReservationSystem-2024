package ee.taltech.iti0202.spotify;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class User {

    private final List<Playlist> playlists;

    public User() {
        this.playlists = new ArrayList<>();
    }

    public void addPlaylist(Playlist playlist) {
       if (!playlists.contains(playlist)) {
           playlists.add(playlist);
       }
    }

    public void addSongToPlaylist(Song song, Playlist playlist) {
        if (playlists.contains(playlist)) {
            playlist.addSong(song);
        }
    }

    public List<Song> getPlaylist(Playlist playlist) {
        if (!playlists.contains(playlist)) {
            throw new NoSuchElementException();
        }
        return new ArrayList<>(playlist.getSongs());
    }

    public List<Playlist> getPlaylists() {
        return playlists;
    }
}