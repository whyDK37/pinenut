package droolsExample;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.List;


/**
 * This is a sample class to launch a rule.
 */
public class DroolsRuleEngineExample {

    public static final void main(String[] args) {
        try {
            // load up the knowledge base
            KieServices ks = KieServices.Factory.get();
            KieContainer kContainer = ks.getKieClasspathContainer();
            KieSession kSession = kContainer.newKieSession("ksession-rules");

            Customer customer = Customer.newCustomer("RS");
    		Product p1 = new Product("Laptop", 15000);
    		Product p2 = new Product("Mobile", 5000);
    		p2.setRequiresRegistration(true);
    		Product p3 = new Product("Books", 2000);
    		
    		Product p4OutOfStock = new Product("TV", 2000);
    		p4OutOfStock.setAvailableQty(0);
    		
    		Product p5 = new Product("Tab", 10000);
    		p5.setAvailableQty(2);
    		
    		customer.addItem(p1, 1);
    		customer.addItem(p2, 2);
    		customer.addItem(p3, 5);
    		customer.setCoupon("DISC01");

    		List<CartItem> cartItems = customer.getCart().getCartItems();
    		for (CartItem cartItem: cartItems) {
    			kSession.insert(cartItem);
    		}
    		System.out.println("************* Fire Rules **************");
            kSession.fireAllRules(); 
            System.out.println("************************************");
            System.out.println("Customer cart\n" + customer);
            
            Customer newCustomer = Customer.newCustomer("JOHN01");
    		newCustomer.addItem(p1, 1);
    		newCustomer.addItem(p2, 2);
    		newCustomer.addItem(p4OutOfStock, 1);
    		newCustomer.addItem(p5, 10);    		
    		
    		cartItems = newCustomer.getCart().getCartItems();
    		for (CartItem cartItem: cartItems) {
    			kSession.insert(cartItem);
    		}
    		kSession.insert(newCustomer.getCart());
    		kSession.setGlobal("outOfStockProducts", new ArrayList<Product>());
    		System.out.println("************* Fire Rules **************");
            kSession.fireAllRules(); 
            System.out.println("************************************");
            System.out.println("Customer cart\n" + customer);
                        
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
}