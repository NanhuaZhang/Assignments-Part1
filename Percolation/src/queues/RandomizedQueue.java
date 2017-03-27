package queues;

import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private class Node{
		Item item;
		Node next;
	}
	private Node first,last;
	public RandomizedQueue(){
		first=new Node();
		last=new Node();
		first=last=null;
//		 head=null;
	}                 // construct an empty randomized queue
	public boolean isEmpty(){
		return first==last;
	}                 // is the queue empty?
	public int size(){
		Node pNode=first;
		Node qNode=last;
		int count=0;
		while(pNode!=qNode){
			pNode=pNode.next;
			count++;
		}
		return count;
	}                        // return the number of items on the queue
	public void enqueue(Item item){
		Node oldLast=last;
		last=new Node();
		last.item=item;
		last.next=null;
		if(isEmpty())
			first=last;
		else
			oldLast.next=last;
	}           // add the item
	private Node random() {
		if(isEmpty())
			return null;
		if(size()==1)
			return first;
		int randomNum=StdRandom.uniform(size());
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
		if(qNode==first){
			first=last=null;
			item=first.item;
		}
		else{
			Node pNode=qNode.next;
			qNode.next=pNode.next;
			item=pNode.item;
			pNode=null;
		}
		return item;
	}                    // remove and return a random item
	public Item sample(){
		Node qNode=random();
		if(qNode==first)
			return first.item;
		else 
			return qNode.next.item;
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
			Item item=current.item;
			current=current.next;
			return item;
		}
	}
	public static void main(String[] args){
		
	}   // unit testing (optional)
	}