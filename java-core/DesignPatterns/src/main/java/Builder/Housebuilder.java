package Builder;

/*
 *  An abstract Builder
 */
public abstract class Housebuilder  {
    public abstract void BuildRoom(int roomNo);
    public abstract void BuildDoor(int room1, int room2);
    public abstract House getHouse();
}