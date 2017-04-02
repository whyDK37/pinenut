package Builder;

/*
 *  A test client to create a house
 *  but we do not know how the room and door be created
 */
public class TestClient  {
    public TestClient() {
    }

    public static void main(String[] args) {
        ConcreteHouseBuilderA myHouseBuilder = new ConcreteHouseBuilderA();
        HouseDirector myHouseDirector = new HouseDirector();
        myHouseDirector.CreateHouse(myHouseBuilder);
        House myHouse = myHouseBuilder.getHouse();

        System.out.println("My house has room :" + myHouse.getRoomNumber());
        System.out.println("My house has door :" + myHouse.getDoorNumber());
    }
}