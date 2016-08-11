/*
 * A concrete Room - LivingRoom
 */
public class LivingRoom  extends Room {
    public LivingRoom() {
        System.out.println("Initiated a living room !");
    }
    public Door makeDoor() {
        return new LivingRoomDoor();
    }
    public Wall makeWall() {
        return new LivingRoomWall();
    }
}