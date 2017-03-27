package queues;

import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.NoSuchElementException;

//import edu.princeton.cs.algs4.*;
//import edu.rice.cs.drjava.*;

public class Deque<Item> implements Iterable<Item> {
	private Item []stored;
	private int first;
	private int last;
	public Deque(){
		   stored=(Item[])new Object[1];
		   first=last=0;
	   }                           // construct an empty deque
	public boolean isEmpty(){
		return first==last;
	}                 // is the deque empty?
	public int size() {
		int size=first-last;
		if(size<0)
			return size+stored.length;
		else {
			return size;
		}
	}                       // return the number of items on the deque
	private void resize(int capacity) {
		Item []copy=(Item[])new Object[capacity];
		for(int i=0;i<stored.length;++i)
			copy[i]=stored[i];
		stored=copy;
	}
	public void addFirst(Item item){
		if(item==null)
			throw new NullPointerException("add null item!");
		if(size()==stored.length)
			resize(2*stored.length);
		stored[--first]=item;
	}          // add the item to the front
	public void addLast(Item item){
		if(item==null)
			throw new NullPointerException("add null item!");
		if(size()==stored.length)
			resize(2*stored.length);
		stored[++last]=item;
	}           // add the item to the end
	public Item removeFirst(){
		if(size()==0)
			throw new NoSuchElementException("remove from a null deque!"); 
		Item item=stored[first];
		stored[first]=null;
		first++;
		if(size()==stored.length/4)
			resize(stored.length/2);
		return item;
	}                // remove and return the item from the front
	public Item removeLast(){
		if(size()==0)
			throw new NoSuchElementException("remove from a null deque!"); 
		Item item=stored[last];
		stored[last]=null;
		last--;
		if(size()==stored.length/4)
			resize(stored.length/2);
		return item;
	}                 // remove and return the item from the end
	public Iterator<Item> iterator(){
		return new DequeItrator();
	}         // return an iterator over items in order from front to end
	private class DequeItrator implements Iterator<Item>{
		private int i=first;
		public boolean hasNext(){
			return stored[++i]!=null;
		}
		public void remove() {
			throw new UnsupportedOperationException("not support remove function!");
		}
		public Item next() {
			if(first==last)
				throw new NoSuchElementException("no more item to iterate");
			return stored[++i];
		}
	}
	public static void main(String[] args){
		
	}  // unit testing (optional)
}