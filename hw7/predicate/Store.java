package il.ac.tau.cs.software1.predicate;

import java.util.List;

public class Store<T extends Product> {
	private List<T> inventory;
	
	public Store(List <T> inventory) {
		this.inventory = inventory;
	}

	public List<T> getInventory() {
		return inventory;
	}

	public String getInventoryDescription() { // Q4
		String result = "";
		for(Product p : this.getInventory()) {
			result += p.getDescription();
		}
		return result; 
	}

	public void transform(Predicate<T> pred, Action<T> action) { // Q5
		for(T p : this.inventory) {
			if(pred.test(p)) {
				action.performAction(p);
			}
		}
	}
}
