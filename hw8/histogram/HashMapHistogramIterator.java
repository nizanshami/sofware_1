package il.ac.tau.cs.sw1.ex8.histogram;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;


/**************************************
 *  Add your code to this class !!!   *
 **************************************/
public class HashMapHistogramIterator<T extends Comparable<T>> implements Iterator<T>{
	private HashMap<T,Integer> map = new HashMap<T, Integer>();
	private List<Map.Entry<T,Integer>> keyList = new ArrayList<>();
	private int curr = 0;
	
	public HashMapHistogramIterator(HashMapHistogram<T> hist) {
		Set<T> set = hist.getItemsSet();
		for(T key : set) {
			map.put(key, hist.getCountForItem(key));
		}
		for(Map.Entry<T, Integer> entry : map.entrySet()) {
			keyList.add(entry);
		}
		Collections.sort(keyList, new Comparator<Map.Entry<T,Integer>>() {
			@Override
			public int compare(Entry<T, Integer> o1, Entry<T, Integer> o2) {
				int comp = o1.getValue().compareTo(o2.getValue());
				if(comp != 0) {
					return (-1)*comp;
				}else {
					return o1.getKey().compareTo(o2.getKey());
				}
			}
		});
	}
	
	@Override
	public boolean hasNext() {
		return curr < keyList.size(); //replace this with the actual returned value
	}

	@Override
	public T next() {
		T result = keyList.get(curr).getKey();
		this.curr++;
		return result; //replace this with the actual returned value
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException(); //no need to change this
	}
}
