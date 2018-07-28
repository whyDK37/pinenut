package droolsExample;

import java.util.HashMap;
import java.util.Map;

public class CartIssues {
	private Map<String, CartItem> cartErrors = new HashMap<String, CartItem>();
	
	public void logItemError(String key, CartItem cartItem) {
		cartErrors.put(key,  cartItem);
	}
	
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (String key : cartErrors.keySet()) {
			sb.append(key).append(cartErrors.get(key)).append("\n");
		}
		return sb.toString();
	}
	
	public boolean hasIssues() {
		return !cartErrors.isEmpty();
	}
}
