package droolsExample;

import java.util.ArrayList;
import java.util.List;


public class Customer {
	private String id;
	private Cart cart;
	private String coupon;
	private boolean isNew;
	private List<Product> registeredProducts = new ArrayList<Product>();
	
	public static Customer newCustomer(String id) {
		Customer customer = new Customer(id);
		customer.isNew = true;
		return customer;
	}
	
	private Customer(String id) {
		this.id = id;
	}	
	
	public String getId() {
		return id;
	}

	public boolean isNew() {
		return isNew;
	}

	public void addItem(Product product, int qty) {
		if (cart == null) {
			cart = new Cart(this);			
		}
		cart.addItem(product, qty);
	}

	public String getCoupon() {
		return coupon;
	}

	public void setCoupon(String coupon) {
		this.coupon = coupon;
	}

	public Cart getCart() {
		return cart;
	}
	
	public void registerProduct(Product product) {
		registeredProducts.add(product);
	}
	
	public boolean isRegistered(Product p) {
		return registeredProducts.contains(p);
	}		

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Customer new? ")
		   .append(isNew)
		   .append("\nCoupon: ")
		   .append(coupon)
		   .append("\n")
		   .append(cart);
		return sb.toString();
	}	
}
