package queues;

import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private class Node{
		Item item;
		Node next;
		private Node(){

        }
		private  Node(Item item){;
		    this.item=item;
		    this.next= null;
        }
	}
	private Node first,last;
	public RandomizedQueue(){
		first=new Node();
		last=new Node();
		first=null;
		last=null;
//		 head=null;
	}                 // construct an empty randomized queue
	public boolean isEmpty(){
		return first==last && last==null;
	}                 // is the queue empty?
	public int size(){
		Node pNode=first;
		Node qNode=last;
		if (first==null)
		    return 0;
		int count=1;
		while(pNode!=qNode){
			pNode=pNode.next;
			count++;
		}
		return count;
	}                        // return the number of items on the queue
	public void enqueue(Item item){
        if(item==null)
            throw new NullPointerException () ;
	    if(isEmpty())
	        first=last=new Node(item);
	    else {
            Node oldLast = last;
            last = new Node(item);
            oldLast.next = last;
        }
	}           // add the item
	private Node random() {
		if(isEmpty())
			throw new NoSuchElementException() ;
		if(size()==1)
			return first;
		int randomNum=StdRandom.uniform(size());
//		StdOut.print(randomNum);
//        StdOut.print(size());
		Node qNode=first;
		Node pNode=first.next;
		while(randomNum--!=0){
			qNode=pNode;
			pNode=pNode.next;
		}
		return qNode;
	}
	public Item dequeue(){
		Node qNode=random();
		Item item;
		if (size()==1) {
            item = first.item;
            first=null;
            last=null;
            return item;
        }
		if(qNode==first){
            item=first.item;
            first=first.next;
			qNode=null;
		}
		else{
            item = qNode.item;
            Node pNode = first;
            while (pNode.next != qNode)
                pNode = pNode.next;
            if(qNode.next==null) {
                qNode = null;
                last = pNode;
            }
            else {
                pNode.next=qNode.next;
                qNode=null;
            }
        }
		return item;
	}                    // remove and return a random item
	public Item sample(){
		Node qNode=random();
		if(qNode==first)
			return first.item;
		else 
			return qNode.item;
	}                     // return (but do not remove) a random item
	public Iterator<Item> iterator(){
		return new RandomizedQueueIterator();
	}         // return an independent iterator over items in random order
	private class RandomizedQueueIterator implements Iterator<Item>{
		Node current=first;
		public boolean hasNext(){
            return current!=null;
		}
		public void remove() {
			throw new UnsupportedOperationException("not support remove function!");
		}
		public Item next() {
		    if (!hasNext())
		        throw new NoSuchElementException();
			Item item=current.item;
			current=current.next;
			return item;
		}
	}
	public static void main(String[] args){
//		RandomizedQueue<String> r=new RandomizedQueue<String>();
//		r.enqueue("A");
////		r.dequeue();
////		r.isEmpty();
//		r.enqueue("B");
////        StdOut.print(r.size());
//		r.enqueue("C");
//
//        Iterator iterator=r.iterator();
//        int length=0;
//        while(iterator.hasNext()){
//            length++;
//            StdOut.print(iterator.next());
//        }
//        StdOut.print(length);
//		StdOut.print(r.sample());
	}   // unit testing (optional)
	}