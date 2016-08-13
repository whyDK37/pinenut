package State;
/**
 *  A state interface
 */
public interface IShopState {
    public void shop();
    public void generateBill();
    public void pay();
    //protected void changeState(ShopContext c, IShopState);
}