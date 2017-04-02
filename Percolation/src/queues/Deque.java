//package queues;

import edu.princeton.cs.algs4.StdOut;
import java.util.Iterator;
import java.util.NoSuchElementException;



public class Deque<Item> implements Iterable<Item> {
	private Item []stored;
	private int first;
	private int last;
	public Deque(){
		   stored=(Item[])new Object[1];
		   first=last=0;
		   stored[first]=null;
	   }                           // construct an empty deque
	public boolean isEmpty(){
		return first==last && stored[first]==null && stored[last]==null;
	}                 // is the deque empty?
	public int size() {
        int newTail = last;
        if (first == last && stored[first] != null) {
            return stored.length;
        }
        if (last < first) {
            newTail = stored.length + last;
        }
        return newTail - first;
	}                       // return the number of items on the deque
	private void resize(int capacity) {
		Item []copy=(Item[])new Object[capacity];
        int size=size();
        if (first>=last){
            last=stored.length+last;
        }
		for(int i=first;i<last;++i)
			copy[i-first]=stored[i%stored.length];
		first=0;
		last=size;
		stored=copy;
	}
	public void addFirst(Item item){
		if(item==null)
			throw new NullPointerException("add null item!");
		if(size()==stored.length)
			resize(2*stored.length);
		if (first==0)
		    first=stored.length-1;
		else
		    first=(first-1)%stored.length;
		stored[first]=item;
	}          // add the item to the front
	public void addLast(Item item){
		if(item==null)
			throw new NullPointerException("add null item!");
		if(size()==stored.length)
			resize(2*stored.length);
		stored[last]=item;
		last=(last+1)%stored.length;
	}           // add the item to the end
	public Item removeFirst(){
		if(size()==0)
			throw new NoSuchElementException("remove from a null deque!");
        if(size()==stored.length/4)
            resize(stored.length/2);
		Item item=stored[first];
		stored[first]=null;
        first=(first+1)%stored.length;
		return item;
	}                // remove and return the item from the front
	public Item removeLast(){
		if(isEmpty())
			throw new NoSuchElementException("remove from a null deque!");
		if(size()==stored.length/4)
            resize(stored.length/2);
		if (last==0)
		    last=stored.length-1;
		else
		    last=(last-1)%stored.length;
		Item item=stored[last];
		stored[last]=null;
		return item;
	}                 // remove and return the item from the end
	public Iterator<Item> iterator(){
		return new DequeItrator();
	}         // return an iterator over items in order from front to end
	private class DequeItrator implements Iterator<Item>{
		private int i=first;
		public boolean hasNext(){
		    if(i>last) {
                return i>last;
            }
            if (i==stored.length-1 && first!=0){
		        return true;
            }
            return i<last;
		}
		public void remove() {
			throw new UnsupportedOperationException("not support remove function!");
		}
		public Item next() {
			if(!hasNext())
				throw new NoSuchElementException("no more item to iterate");
            Item item = stored[i];
            i = (i+1) % stored.length;
            return item;
		}
	}
	public static void main(String[] args){
//		Deque <String> d =new Deque<String>();
////		d.addFirst("C");
//		d.addLast("D");
////        d.addFirst("B");
////		d.addLast("E");
////        d.addFirst("A");
//////		d.addLast("F");
//        Iterator iterator=d.iterator();
//        int length=0;
//        while(iterator.hasNext()){
//            length++;
//            StdOut.print(iterator.next());
//        }
//        StdOut.print(length);
////		d.removeFirst();
////        StdOut.print(d.size());
////		d.removeLast();
////        StdOut.print(d.size());

	}  // unit testing (optional)
}
