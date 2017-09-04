package java8.pojo;

public class Track {

  private final String name;

  private final int length;

  public Track(String name, int length) {
    this.name = name;
    this.length = length;
  }

  @Override
  public String toString() {
    return "Track{" +
            "length=" + length +
            ", name='" + name + '\'' +
            '}';
  }

  public int getLength() {
    return length;
  }

  public String getName() {
    return name;
  }

}
