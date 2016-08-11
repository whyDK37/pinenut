/**
 *  A state interface
 *  用户在网上购物的状态变化:
 *  选择商品 --> 生成订单 --> 付款取货
 */
public interface IShopState {
    public void shop();
    public void generateBill();
    public void pay();
    //protected void changeState(ShopContext c, IShopState);
}