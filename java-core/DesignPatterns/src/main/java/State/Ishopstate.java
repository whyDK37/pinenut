package State;

/**
 * A state interface
 */
public interface Ishopstate {
  void shop();

  void generateBill();

  void pay();
  //protected void changeState(ShopContext c, IShopState);
}