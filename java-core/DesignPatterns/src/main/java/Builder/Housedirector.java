package Builder;

/*
 *  This class is a Director
 */
public class Housedirector {
  public void CreateHouse(Housebuilder concreteBuilder) {
    concreteBuilder.BuildRoom(1);
    concreteBuilder.BuildRoom(2);
    concreteBuilder.BuildDoor(1, 2);

    //return builder.getHouse();
  }
}