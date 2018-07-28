package droolsExample;

public class Product {
	private int price;
	private String desc;
	private int availableQty = 5;
	private boolean requiresRegistration;
	private boolean isOutOfStock;

	public void setRequiresRegistration(boolean requiresRegistration) {
		this.requiresRegistration = requiresRegistration;
	}

	public boolean isRequiresRegisteration() {
		return requiresRegistration;
	}	
	
	public Product(String desc, int price) {
		this.desc = desc;
		this.price = price;
	}

	public int getPrice() {
		return price;
	}

	public String getDesc() {
		return desc;
	}
	
	public String toString() {
		return "product: " + desc + ", price: " + price;
	}
	
	public int getAvailableQty() {
		return availableQty;
	}

	public void setAvailableQty(int availableQty) {
		this.availableQty = availableQty;
	}

	public boolean isOutOfStock() {
		return isOutOfStock;
	}

	public void setOutOfStock(boolean isOutOfStock) {
		this.isOutOfStock = isOutOfStock;
	}

	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (!(o instanceof Product)) {
			return false;
		}
		Product p = (Product) o;
		return getDesc().equals(p.getDesc());
	}
	
	public int hashCode() {
		return getDesc().hashCode();
	}
}
