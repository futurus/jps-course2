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
 * @author Vu Nguyen
 * Date: Mar 11, 2016
 *
 */
public class MyLinkedListTester {

	private static final int LONG_LIST_LENGTH = 10; 

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
		System.out.println("We are in testRemove:");
		
		System.out.println(list1.toString());
		int a = list1.remove(0);
		assertEquals("Remove: check a is correct ", 65, a);
		assertEquals("Remove: check element 0 is correct ", (Integer)21, list1.get(0));
		assertEquals("Remove: check size is correct ", 2, list1.size());
		System.out.println(list1.toString());
		
		System.out.println(longerList.toString());
		int b = longerList.remove(1);
		assertEquals("Remove: check b is correct ", 1, b);
		assertEquals("Remove: check element 0 is correct ", (Integer)0, longerList.get(0));
		assertEquals("Remove: check element 1 is correct ", (Integer)2, longerList.get(1));
		assertEquals("Remove: check size is correct ", 9, longerList.size());
		System.out.println(longerList.toString());

		int c = longerList.remove(8);
		assertEquals("Remove: check c is correct ", 9, c);
		assertEquals("Remove: check element is correct ", (Integer)8, longerList.get(7));
		assertEquals("Remove: check size is correct ", 8, longerList.size());
		System.out.println(longerList.toString());
		
		try {
			emptyList.remove(0);
			fail("cannot remove from empty list");
		} catch (IndexOutOfBoundsException e) {}
		
		try {
			emptyList.remove(5);
			fail("cannot remove from empty list");
		} catch (IndexOutOfBoundsException e) {}
		
		try {
			longerList.remove(-1);
			fail("cannot remove index -1");
		} catch (IndexOutOfBoundsException e) {}
		
	}
	
	/** Test adding an element into the end of the list, specifically
	 *  public boolean add(E element)
	 * */
	@Test
	public void testAddEnd()
	{
		System.out.println("We are in testAddEnd:");
		
		System.out.println(emptyList.toString());
        assertTrue(emptyList.add(5));
        assertEquals(1, emptyList.size());
        System.out.println(emptyList.toString());
        
        System.out.println(shortList.toString());
        assertTrue(shortList.add("C"));
        assertEquals(3, shortList.size());
		assertEquals("B", shortList.get(1));
		System.out.println(shortList.toString());
	}

	
	/** Test the size of the list */
	@Test
	public void testSize()
	{
		assertEquals(0, emptyList.size());
		assertEquals(2, shortList.size());
		assertEquals(10, longerList.size());
		assertEquals(3, list1.size());
	}

	
	
	/** Test adding an element into the list at a specified index,
	 * specifically:
	 * public void add(int index, E element)
	 * */
	@Test
	public void testAddAtIndex()
	{
		System.out.println("We are in testAddAtIndex:");
		
		System.out.println(shortList.toString());
		shortList.add(1, "C");
		assertEquals(3, shortList.size());
		assertEquals("C", shortList.get(1));
		assertEquals("B", shortList.get(2));
		System.out.println(shortList.toString());
		
		System.out.println(emptyList.toString());
		emptyList.add(0, 5);
		assertEquals(1, emptyList.size());
		assertEquals((Integer)5, emptyList.get(0));
		System.out.println(emptyList.toString());
		
		try {
			emptyList.add(-4, 10);
			fail("Index out of bound!");
		} catch (IndexOutOfBoundsException e) {}
		
		try {
			emptyList.add(500, 10);
			fail("Index out of bound!");
		} catch (IndexOutOfBoundsException e) {}
		
		try {
	    	shortList.add(0, null);
	    	fail("Cannot add null element");
	    } catch (NullPointerException e) {}
	}
	
	/** Test setting an element in the list */
	@Test
	public void testSet()
	{
		System.out.println("We are in testSet:");
	
		System.out.println(shortList.toString());
	    assertEquals("B", shortList.set(1, "C"));
	    assertEquals("C", shortList.get(1));
	    System.out.println(shortList.toString());
	    
	    System.out.println(shortList.toString());
	    assertEquals("A", shortList.set(0, "D"));
	    assertEquals("D", shortList.get(0));
	    System.out.println(shortList.toString());
	    
	    assertEquals(2, shortList.size());
	    
	    try {
	    	shortList.set(5, "E");
	    	fail("Out of bound!");
	    } catch (IndexOutOfBoundsException e) {}
	    
	    try {
	    	shortList.set(-1, "E");
	    	fail("Out of bound!");
	    } catch (IndexOutOfBoundsException e) {}
	    
	    try {
	    	shortList.set(0, null);
	    	fail("Cannot set null element");
	    } catch (NullPointerException e) {}
	}
	
}
