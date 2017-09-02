package State;
/**
 *  A state interface
 */
public interface IShopState {
    void shop();
    void generateBill();
    void pay();
    //protected void changeState(ShopContext c, IShopState);
}