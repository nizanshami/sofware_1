package il.ac.tau.cs.sw1.ex8.histogram;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**************************************
 *  Add your code to this class !!!   *
 **************************************/
public class HashMapHistogram<T extends Comparable<T>> implements IHistogram<T>{
	HashMap<T,Integer> map = new HashMap<T,Integer>();
	
	@Override
	public Iterator<T> iterator() {
		HashMapHistogramIterator<T> iterator = new HashMapHistogramIterator<>(this);
		return iterator;
	}

	@Override
	public void addItem(T item) {
		if(!map.containsKey(item)) {
			map.put(item, 1);
		}else {
			Integer val = map.get(item);
			map.replace(item, val + 1);
		}
		
	}

	@Override
	public void removeItem(T item) throws IllegalItemException {
		if(!map.containsKey(item)) {
			throw new IllegalItemException();
		}
		map.remove(item);
	}

	@Override
	public void addItemKTimes(T item, int k) throws IllegalKValueException {
		if(k < 1) {
			throw new IllegalKValueException(k);
		}
		if(map.containsKey(item)) {
			Integer val = map.get(item);
			map.replace(item, val + k);
		}else {
			map.put(item, k);
		}
	}

	@Override
	public void removeItemKTimes(T item, int k) throws IllegalItemException, IllegalKValueException {
		Integer val = map.get(item);
		if(k < 1) {
			throw new IllegalKValueException(k);
		}
		if(!map.containsKey(item)) {
			throw new IllegalItemException();
		}
		else if(val < k) {
			throw new IllegalItemException();
		}
		map.replace(item, val - k);
		if(map.get(item) == 0) {
			map.remove(item);
		}
	}

	@Override
	public int getCountForItem(T item) {
		if(map.get(item) == null) {
			return 0;
		}
		return map.get(item); //replace this with the actual returned value
	}

	@Override
	public void addAll(Collection<T> items) {
		for(T item : items) {
			this.addItem(item);
		}
	}

	@Override
	public void clear() {
		map.clear();
	}

	@Override
	public Set<T> getItemsSet() {
		return map.keySet(); //replace this with the actual returned value
	}

	@Override
	public void update(IHistogram<T> anotherHistogram) {
		for(T item : anotherHistogram) {
			try {
				this.addItemKTimes(item, anotherHistogram.getCountForItem(item));
			} catch (IllegalKValueException e) {
				System.out.print("someting worng");// should never happen;
			}
		}
	}
	
}
