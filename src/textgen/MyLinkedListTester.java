/**
 * 
 */
package textgen;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

/**
 * @author UC San Diego MOOC team
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH =10; 

	MyLinkedList<String> shortList;
	MyLinkedList<Integer> emptyList;
	MyLinkedList<Integer> longerList;
	MyLinkedList<Integer> list1;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		// Feel free to use these lists, or add your own
	    shortList = new MyLinkedList<String>();
		shortList.add("A");
		shortList.add("B");
		emptyList = new MyLinkedList<Integer>();
		longerList = new MyLinkedList<Integer>();
		for (int i = 0; i < LONG_LIST_LENGTH; i++)
		{
			longerList.add(i);
		}
		list1 = new MyLinkedList<Integer>();
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
	}

	
	/** Test if the get method is working correctly.
	 */
	/*You should not need to add much to this method.
	 * We provide it as an example of a thorough test. */
	@Test
	public void testGet()
	{
		//test empty list, get should throw an exception
		try {
			emptyList.get(0);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
			
		}
		
		// test short list, first contents, then out of bounds
		assertEquals("Check first", "A", shortList.get(0));
		assertEquals("Check second", "B", shortList.get(1));
		
		try {
			shortList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			shortList.get(2);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		// test longer list contents
		for(int i = 0; i<LONG_LIST_LENGTH; i++ ) {
			assertEquals("Check "+i+ " element", (Integer)i, longerList.get(i));
		}
		
		// test off the end of the longer array
		try {
			longerList.get(-1);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		
		}
		try {
			longerList.get(LONG_LIST_LENGTH);
			fail("Check out of bounds");
		}
		catch (IndexOutOfBoundsException e) {
		}
		
	}
	
	
	/** Test removing an element from the list.
	 * We've included the example from the concept challenge.
	 * You will want to add more tests.  */
	@Test
	public void testRemove()
	{
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		try{
			a = emptyList.remove(0);
			fail("Check out of bounds emptylist");
		}catch(IndexOutOfBoundsException e){
			//fine
		}
		try{
			a = emptyList.remove(-1);
			fail("Check out of bounds emptylist");
		}catch(IndexOutOfBoundsException e){
			//fine
		}
		try{
			a = list1.remove(10);
			fail("Check out of bounds longlist");
		}catch(IndexOutOfBoundsException e){
			//fine
		}
		a = longerList.remove(1);
		assertEquals("Remove middle: check a is correct ", 1, a);
		assertEquals("Remove middle: check element 0 is correct ", (Integer)0, longerList.get(0));
		assertEquals("Remove middle: check size is correct ", 9, longerList.size());
		a = longerList.remove(8);
		assertEquals("Remove middle: check a is correct ", 9, a);
		assertEquals("Remove middle: check element 0 is correct ", (Integer)8, longerList.get(7));
		assertEquals("Remove middle: check size is correct ", 8, longerList.size());
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
		int i;
		int index = list1.size() -1;
		for(i=0;i<100;i++){
			list1.add(i);
			index += 1;
			assertEquals("Add end", i, (int)list1.get(index));
			assertEquals("Add beginning ", 65, (int)list1.get(0));
		}
		
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		assertEquals("EmptyList size test", 0, emptyList.size());
		assertEquals("LongList size test", LONG_LIST_LENGTH, longerList.size());
		int i;
		int size = longerList.size();
		for(i=0;i<100;i++){
			longerList.add(3, i);
			size += 1;
			assertEquals("MiddleAddSizeTest", size, longerList.size());
		};
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
        String s;
        s = "C";
        shortList.add(1, s);
        assertEquals("AddAtIndex center insertion", "C", shortList.get(1));
        assertEquals("AddAtIndex center previous", "A", shortList.get(0));
        assertEquals("AddAtIndex center next", "B", shortList.get(2));
        s="D";
        shortList.add(3,s);
        assertEquals("AddAtIndex center insertion", "D", shortList.get(3));
        assertEquals("AddAtIndex center previous", "B", shortList.get(2));
        s="E";
        shortList.add(0,s);
        assertEquals("AddAtIndex center insertion", "E", shortList.get(0));
        assertEquals("AddAtIndex center next", "A", shortList.get(1));		
        try{
        	shortList.add(-1,s);
        	fail("added in negative index");
        }catch(IndexOutOfBoundsException e){
        	
        }
        try{
        	shortList.add(100,s);
        	fail("added in too high index");
        }catch(IndexOutOfBoundsException e){
        	
        }
        s=null;
        try{
        	shortList.add(2,s);
        	fail("added null");
        }catch(NullPointerException e){
        	
        }
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
		int i = 656;
	    int a = list1.set(0, i);
	    assertEquals("Set: check a is correct ", 65, a);
		assertEquals("Set: check element is correct ", (Integer)i, list1.get(0));
		a = list1.set(2, i);
	    assertEquals("Set: check a is correct ", 42, a);
		assertEquals("Set: check element is correct ", (Integer)i, list1.get(2));
		a = list1.set(1, i);
	    assertEquals("Set: check a is correct ", 21, a);
		assertEquals("Set: check element is correct ", (Integer)i, list1.get(1));
		try{
			a = list1.set(6, i);
			fail("Check upper limit");
		}catch(IndexOutOfBoundsException e){
			
		}
		try{
			a = list1.set(-1, i);
			fail("Check lower limit");
		}catch(IndexOutOfBoundsException e){
			
		}
		String s = null;
		try{
			String z = shortList.set(1, s);
			fail("Check null");
		}catch(NullPointerException e){
			
		}
	}
	
	
	// TODO: Optionally add more test methods.
	
}
