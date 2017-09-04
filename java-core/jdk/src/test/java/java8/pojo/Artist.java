package java8.pojo;

public class Artist {

  private String name;

  private String nationality;

  public Artist(String name, String nationality) {
    this.name = name;
    this.nationality = nationality;
  }

  @Override
  public String toString() {
    return "Artist{" +
            "name='" + name + '\'' +
            ", nationality='" + nationality + '\'' +
            '}';
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNationality() {
    return nationality;
  }

  public void setNationality(String nationality) {
    this.nationality = nationality;
  }

}
