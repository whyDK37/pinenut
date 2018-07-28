package droolsExample;

public class CartItem {
	private Cart cart;
	private Product product;
	private int qty;
	private boolean errors;
	private String error;
	private CartStatus cartStatus;
	
	public CartItem(Cart cart, Product product, int qty) {
		this.cart = cart;
		this.product = product;
		this.qty = qty;
		cartStatus = CartStatus.NEW;
	}

	public Product getProduct() {
		return product;
	}

	public int getQty() {
		return qty;
	}
	
	public String toString() {
		return "Product: " + product + ", qty: " + qty + ", processed: " + hasErrors() + (hasErrors() ? ", Issue: " + getError() : "");
	}

	public Cart getCart() {
		return cart;
	}

	public boolean hasErrors() {
		return errors;
	}

	public void setErrors(boolean errors) {
		this.errors = errors;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}		
		
	public void updateAsProcessed() {
		cartStatus = CartStatus.PROCESSED;
	}

	public CartStatus getCartStatus() {
		return cartStatus;
	}

	public void setCartStatus(CartStatus cartStatus) {
		this.cartStatus = cartStatus;
	}
	
	
		
}
