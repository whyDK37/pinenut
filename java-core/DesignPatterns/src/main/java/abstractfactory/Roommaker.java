/*
 * A Room Maker to test our simple Room Abstract Factory
 */
public class RoomMaker  {
    public Room CreateRoom(String roomType) {
        if(roomType.equals("LivingRoom")) {
            return new LivingRoom();
        } else if(roomType.equals("BedRoom")) {
            return new BedRoom();
        } else {
            return new LivingRoom();
        }
    }

    public static void main(String[] args) {
        RoomMaker myMaker = new RoomMaker();
        //-----  Create Living Room
        Room myLivingRoom = myMaker.CreateRoom("LivingRoom");
        //-----  Create a door in living room
        Door livingDoor = myLivingRoom.makeDoor();
        System.out.println("Living room door name is:" + livingDoor.getName() );
        //-----  Create a wall in living room
        Wall livingWall = myLivingRoom.makeWall();
        System.out.println("Living room wall name is:" + livingWall.getName() );

        //-----  Create Bed Room
        Room myBedRoom = myMaker.CreateRoom("BedRoom");
        //-----  Create a door in bedroom
        Door BedDoor = myBedRoom.makeDoor();
        System.out.println("Bed room door name is:" + BedDoor.getName() );
        //-----  Create a wall in bedroom
        Wall BedWall = myBedRoom.makeWall();
        System.out.println("Bed room wall name is:" + BedWall.getName() );

    }
}