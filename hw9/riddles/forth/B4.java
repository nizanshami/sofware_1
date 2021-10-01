package il.ac.tau.cs.sw1.ex9.riddles.forth;

import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

public class B4 implements Iterator<String> {
	
	String[] strings;
	int k;
	int count = 0;
	public B4(String[] strings, int k) {
		this.strings = strings;
		this.k = k;
	}

	@Override
	public boolean hasNext() {
		return this.count < strings.length*k;
	}

	@Override
	public String next() {
		String next = strings[count % strings.length];
		count++;
		return next;
	}
}
	

