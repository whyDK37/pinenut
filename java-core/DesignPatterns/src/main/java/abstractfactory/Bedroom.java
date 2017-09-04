package abstractfactory;

/*
 * A concrete Room - BedRoom
 */
public class Bedroom extends Room {
  public Bedroom() {
    System.out.println("Initiated a bedroom !");
  }

  public Door makeDoor() {
    return new Bedroomdoor();
  }

  public Wall makeWall() {
    return new Bedroomwall();
  }
}