/*
 *  A test client to create a house
 *  but we do not know how the room and door be created
 */
public class TestClient  {
    public TestClient() {
    }

    public static void main(String[] args) {
        House myHouse = new House();
        ConcreteHouseBuilderA myHouseBuilder = new ConcreteHouseBuilderA();
        HouseDirector myHouseDirector = new HouseDirector();
        myHouseDirector.CreateHouse(myHouseBuilder);
        myHouse = myHouseBuilder.getHouse();

        System.out.println("My house has room :" + myHouse.getRoomNumber());
        System.out.println("My house has door :" + myHouse.getDoorNumber());
    }
}