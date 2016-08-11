/**
 *  A very simple test
 */
import java.io.*;

class Wall {
    public Wall() {
        System.out.println("Create a wall !");
    }
}

class Door {
    public Door() {
        System.out.println("Create a door !");
    }
}

class FacadeRoom {
    public void CreateRoom() {
        Wall wall1 = new Wall();
        Wall wall2 = new Wall();
        Wall wall3 = new Wall();
        Wall wall4 = new Wall();
        Door door = new Door();
    }

}

public class Test  {
    public static void main(String[] args) {
        FacadeRoom room = new FacadeRoom();
        room.CreateRoom();
    }
}