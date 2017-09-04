package abstractfactory;

/*
 * A concrete Door for Bed Room
 */
public class Bedroomdoor extends Door {
  private String doorName;

  public Bedroomdoor() {
    doorName = "BedRoomDoor";
  }

  public String getName() {
    return doorName;
  }
}