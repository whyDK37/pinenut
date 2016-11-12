package java8;

import java8.pojo.Album;
import java8.pojo.Artist;
import java8.pojo.Track;

import java.util.ArrayList;
import java.util.List;


/**
 * @author zhangxu
 */
public class AlbumBuilder {

    public static List<Album> getAlbums() {
        List<Album> albums = new ArrayList<>();
        albums.add(getAlbum1());
        albums.add(getAlbum2());
        return albums;
    }

    public static Album getAlbum1() {
        Album album = new Album("Cold Play", getTracks1(), getArtists1());
        return album;
    }

    public static Album getAlbum2() {
        Album album = new Album("1989", getTracks2(), getArtists2());
        return album;
    }

    public static List<Artist> getArtists1() {
        List<Artist> artists = new ArrayList<>();
        artists.add(new Artist("Chris", "UK"));
        artists.add(new Artist("Guy", "United Kingdom"));
        artists.add(new Artist("Jonny", "Wales"));
        artists.add(new Artist("Will", "England"));
        return artists;
    }

    public static List<Track> getTracks1() {
        List<Track> tracks = new ArrayList<>();
        tracks.add(new Track("Always In My Head", 10));
        tracks.add(new Track("Magic", 30));
        tracks.add(new Track("True Love", 50));
        tracks.add(new Track("Midnight", 70));
        tracks.add(new Track("A Sky Full of Stars", 80));
        return tracks;
    }

    public static List<Artist> getArtists2() {
        List<Artist> artists = new ArrayList<>();
        artists.add(new Artist("Taylor Swift", "USA"));
        return artists;
    }

    public static List<Track> getTracks2() {
        List<Track> tracks = new ArrayList<>();
        tracks.add(new Track("Welcome To New York", 10));
        tracks.add(new Track("Blank Space", 30));
        tracks.add(new Track("Style", 90));
        return tracks;
    }

}
