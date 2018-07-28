package droolsExample;

import java.util.ArrayList;
import java.util.List;

public class PendingItems {
	private Customer customer;
	private List<CartItem> cartItems = new ArrayList<CartItem>();
	
	public PendingItems(Customer customer) {
		this.customer = customer;
	}
	
	public Customer getCustomer() {
		return customer;
	}	
	
	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void addItem(CartItem cartItem) {
		cartItems.add(cartItem);
	}
}
