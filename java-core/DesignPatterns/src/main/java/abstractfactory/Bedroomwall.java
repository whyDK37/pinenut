package abstractfactory;

/*
 * A concrete Wall for Living Room
 */
public class Bedroomwall extends Wall {
  private String wallName;

  public Bedroomwall() {
    wallName = "BedRoomWall";
  }

  public String getName() {
    return wallName;
  }
}