package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		this.head = new LLNode<E>(null);
		this.tail = new LLNode<E>(null);
		this.head.next = this.tail;
		this.tail.prev = this.head;
		this.size = 0;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		try{
			LLNode<E> nodo = new LLNode<E>(element);
			if(this.head.data == null){
				nodo.prev = nodo;
				this.head = nodo;
				this.tail = nodo;			
			}else{
				nodo.prev = this.tail;
				this.tail.next = nodo;
				this.tail=nodo;
			}
			this.size += 1;
			return true;
		}catch(Exception e){
			System.out.println("Failiure inserting");
			throw e;
		}
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index) 
	{
        int i = 0;
        LLNode<E> current = this.head;
        E result = null;
        if(index<this.size && !(index<0)){
        	for(i=0;i<=index;i++){
        		if(i==index){
        			result = current.data;
        		}else{
        			current = current.next;
        		}
        	}
        }else{
        	throw new IndexOutOfBoundsException();
        }
        return result;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		int i = 0;
        LLNode<E> current = this.head;
        LLNode<E> insert = new LLNode<E>(element);
        LLNode<E> displaced;
		if(index<this.size && !(index<0)){
			 if(index==0){
				insert.next = current;
				current.prev = insert;
				this.head = insert;
				this.size +=1; 
			}else{
				for(i=0;i<=index;i++){
	        		if(i==(index-1)){
	        			//operate
	        			displaced = current.next;
	        			displaced.prev = insert;
	        			insert.next = displaced;
	        			insert.prev = current;
	        			current.next = insert;
	        			this.size +=1; 
	        			break;
	        		}else{
	        			current = current.next;
	        		}
	        	}
			}
		}else if(index==this.size && !(index<0)){
			add(element);
		}else
		{
			throw new IndexOutOfBoundsException();
		}
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
		return null;
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
		return null;
	}   
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
