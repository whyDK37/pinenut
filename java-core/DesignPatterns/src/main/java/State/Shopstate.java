/**
 *  The parent class of state
 */
public class ShopState implements IShopState { 
    public ShopState() {
    }
    public void shop() { }
    public void generateBill() { }
    public void pay() { }
    protected void changeState(ShopContext c, ShopState s) {
        c.changeState(s);
    }
}