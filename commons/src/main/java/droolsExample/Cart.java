package droolsExample;

import java.util.ArrayList;
import java.util.List;

public class Cart {
	private Customer customer;
	private List<CartItem> cartItems = new ArrayList<CartItem>();
	private double discount;
	private CartIssues cartIssues = new CartIssues();
	private PendingItems pendingItems = new PendingItems(customer);

	public Cart(Customer customer) {
		this.customer = customer;
	}

	public void addItem(Product p, int qty) {
		CartItem cartItem = new CartItem(this, p, qty);
		cartItems.add(cartItem);
	}

	public double getDiscount() {
		return discount;
	}

	public void addDiscount(double discount) {
		this.discount += discount;
	}

	public int getTotalPrice() {
		int total = 0;
		for (CartItem item : cartItems) {
			if (item.hasErrors()) {
				continue;
			}
			total += item.getProduct().getPrice() * item.getQty();
		}
		return total;
	}

	public Customer getCustomer() {
		return customer;
	}

	public List<CartItem> getCartItems() {
		return cartItems;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public int getFinalPrice() {
		return getTotalPrice() - (int) getDiscount();
	}
	
	public void logItemError(String key, CartItem cartItem) {
		cartIssues.logItemError(key, cartItem);
		pendingItems.addItem(cartItem);
		cartItem.setCartStatus(CartStatus.PENDING);
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (CartItem cartItem : cartItems) {
			sb.append(cartItem)
			  .append("\n");
		}
		sb.append("Discount: ")
		  .append(getDiscount())
		  .append("\nTotal: ")
		  .append(getTotalPrice())
		  .append("\nTotal After Discount: ")
		  .append(getFinalPrice());
		return sb.toString();
	}
	
	public PendingItems getPendingItems() {
		return pendingItems;
	}
	
	public CartIssues getCartIssues() {
		return cartIssues;
	}

	public boolean hasIssues() {
		return cartIssues.hasIssues();
	}
}
