package abstractfactory;

/*
 * A concrete Room - LivingRoom
 */
public class Livingroom extends Room {
  public Livingroom() {
    System.out.println("Initiated a living room !");
  }

  public Door makeDoor() {
    return new Livingroomdoor();
  }

  public Wall makeWall() {
    return new Livingroomwall();
  }
}