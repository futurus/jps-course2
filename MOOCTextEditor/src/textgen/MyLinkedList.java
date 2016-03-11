package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 * @author Vu Nguyen
 * Date: Mar 11, 2016
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		head = new LLNode<E>();
		tail = new LLNode<E>();
		size = 0;
		head.next = tail;
		tail.prev = head;				
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		if (element == null) {
			throw new NullPointerException("Cannot add null element!");
		}
		LLNode<E> temp = new LLNode<E>(element);
		temp.prev = tail.prev;
		temp.next = tail;
		tail.prev.next = temp;
		tail.prev = temp;
		size++;
		
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
		if (index < 0 || index > this.size - 1) {
			throw new IndexOutOfBoundsException("Out of bound!");
		}
		// Consider size/2 and go from head or tail
		LLNode<E> cur = head;
		for (int i = 0; i <= index; i++) {
			cur = cur.next;
		}
		return cur.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		if (element == null) {
			throw new NullPointerException("Cannot add null element!");
		}
		if (index < 0 || index > this.size) { // this check allows to add at MyLinkedList.size i.e. at the end of the list
			throw new IndexOutOfBoundsException("Out of bound!");
		}
		// Consider size/2 and go from head or tail
		LLNode<E> cur = head;
		for (int i = 0; i <= index; i++) {
			cur = cur.next;
		}
		
		// add element at cur
		LLNode<E> newElem = new LLNode<E>(element);
		newElem.next = cur;
		newElem.prev = cur.prev;
		cur.prev.next = newElem;
		cur.prev = newElem;
		size++;
		
		return;
	}


	/** Return the size of the list */
	public int size() 
	{
		return this.size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		if (index < 0 || index > this.size - 1) {
			throw new IndexOutOfBoundsException("Out of bound!");
		}
		// Consider size/2 and go from head or tail
		LLNode<E> cur = head;
		for (int i = 0; i <= index; i++) {
			cur = cur.next;
		}
		
		cur.prev.next = cur.next;
		cur.next.prev = cur.prev;
		
		size--;
		
		return cur.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		if (element == null) {
			throw new NullPointerException("Cannot add null element!");
		}
		if (index < 0 || index > this.size - 1) {
			throw new IndexOutOfBoundsException("Out of bound!");
		} else {
			// Consider size/2 and go from head or tail
			LLNode<E> cur = head;
			for (int i = 0; i <= index; i++) {
				cur = cur.next;
			}
			E toReturn = cur.data;
			cur.data = element;
			
			return toReturn;
		}
	}
	
	public String toString() {
		if (this.size() == 0) {
			return "This LinkedList is empty.";
		}
		
		String printOut = new String();

		for (int i = 0; i < this.size()-1; i++) {
			printOut += this.get(i) + " <-> ";
		}
		
		return printOut + this.get(this.size()-1);
	}
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	public LLNode() {
		this.data = null;
		this.prev = null;
		this.next = null;
	}
	
	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}
	
}
