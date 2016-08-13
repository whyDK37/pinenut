package abstractfactory;
/*
 * A concrete Wall for Living Room
 */
public class Livingroomwall  extends Wall {
    private String wallName;
    public Livingroomwall() {
        wallName = "LivingRoomWall";
    }
    public String getName() {
        return wallName;
    }
}