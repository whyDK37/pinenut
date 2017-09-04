package java8.pojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Album implements Performance {

  private String name;

  private List<Track> tracks;

  private List<Artist> artists;

  public Album(String name, List<Track> tracks, List<Artist> artists) {
    Objects.requireNonNull(name);
    Objects.requireNonNull(tracks);
    Objects.requireNonNull(artists);
    this.name = name;
    this.tracks = new ArrayList<>(tracks);
    this.artists = new ArrayList<>(artists);
  }

  @Override
  public String toString() {
    return "Album{" +
            "artists=" + artists +
            ", name='" + name + '\'' +
            ", tracks=" + tracks +
            '}';
  }

  public List<Artist> getArtists() {
    return artists;
  }

  public void setArtists(List<Artist> artists) {
    this.artists = artists;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public List<Track> getTracks() {
    return tracks;
  }

  public void setTracks(List<Track> tracks) {
    this.tracks = tracks;
  }
}