/*
 * A concrete Room - BedRoom
 */
public class BedRoom  extends Room {
    public BedRoom() {
        System.out.println("Initiated a bedroom !");
    }
    public Door makeDoor() {
        return new BedRoomDoor();
    }
    public Wall makeWall() {
        return new BedRoomWall();
    }
}