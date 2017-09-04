package abstractfactory;

/*
 * A concrete Door for Living Room
 */
public class Livingroomdoor extends Door {
  private String doorName;

  public Livingroomdoor() {
    doorName = "LivingRoomDoor";
  }

  public String getName() {
    return doorName;
  }
}