package b100.utils;

import java.util.Iterator;

public class ArrayIterator<E> implements Iterator<E>, Iterable<E>{
	
	private int pos;
	private E[] array;
	
	public ArrayIterator(E[] array) {
		this.array = array;
		this.pos = 0;
	}
	
	public boolean hasNext() {
		return pos < array.length;
	}

	public E next() {
		return array[pos++];
	}
	
	public Iterator<E> iterator() {
		return this;
	}
	
	
	
}
