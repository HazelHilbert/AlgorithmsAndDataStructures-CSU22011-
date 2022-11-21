package csu22011_a2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertNull;

import org.junit.Test;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for Doubly Linked List
 *
 *  @author  Hazel Hilbert
 *  @version 13/10/16 18:15
 */
@RunWith(JUnit4.class)
public class DoublyLinkedListTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
      new DoublyLinkedList<Integer>();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check if the insertBefore works
     */
    @Test
    public void testInsertBefore()
    {
        // test non-empty list
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);
        
        testDLL.insertBefore(0,4);
        assertEquals( "Checking insertBefore to a list containing 3 elements at position 0", "4,1,2,3", testDLL.toString() );
        testDLL.insertBefore(1,5);
        assertEquals( "Checking insertBefore to a list containing 4 elements at position 1", "4,5,1,2,3", testDLL.toString() );
        testDLL.insertBefore(2,6);       
        assertEquals( "Checking insertBefore to a list containing 5 elements at position 2", "4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(-1,7);        
        assertEquals( "Checking insertBefore to a list containing 6 elements at position -1 - expected the element at the head of the list", "7,4,5,6,1,2,3", testDLL.toString() );
        testDLL.insertBefore(7,8);        
        assertEquals( "Checking insertBefore to a list containing 7 elemenets at position 7 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8", testDLL.toString() );
        testDLL.insertBefore(700,9);        
        assertEquals( "Checking insertBefore to a list containing 8 elements at position 700 - expected the element at the tail of the list", "7,4,5,6,1,2,3,8,9", testDLL.toString() );
        
        // test empty list
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);        
        assertEquals( "Checking insertBefore to an empty list at position 0 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(10,1);        
        assertEquals( "Checking insertBefore to an empty list at position 10 - expected the element at the head of the list", "1", testDLL.toString() );
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(-10,1);        
        assertEquals( "Checking insertBefore to an empty list at position -10 - expected the element at the head of the list", "1", testDLL.toString() );
     }

    // TODO: add more tests here. Each line of code in DoublyLinkedList.java should
    // be executed at least once from at least one test.
    /**
     * Check if get works
     */
    @Test
    public void testGet()
    {
        // test non-empty list
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);
        testDLL.insertBefore(0,4);
        testDLL.insertBefore(1,5);
        
        assertEquals( "Checking that get returns 4 at position 0 for the list 4,5,1,2,3", "4", testDLL.get(0)+"");
        assertEquals( "Checking that get returns 1 at position 2 for the list 4,5,1,2,3", "1", testDLL.get(2)+"");
        assertEquals( "Checking that get returns null at position -2 for the list 4,5,1,2,3", null, testDLL.get(-2));
        assertEquals( "Checking that get returns null at position 5 for the list 4,5,1,2,3", null, testDLL.get(5));

        // test empty list
        testDLL = new DoublyLinkedList<Integer>();
        assertEquals( "Checking that get returns null at position 0 for the list 4,5,1,2,3", null, testDLL.get(0));
        assertEquals( "Checking that get returns null at position 2 for the list 4,5,1,2,3", null, testDLL.get(2));
     }
   
    /**
     * Check if deleteAt works
     */
    @Test
    public void testDeleteAt()
    {
        // test non-empty list
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.insertBefore(0,1);
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);
        testDLL.insertBefore(0,4);
        testDLL.insertBefore(1,5);
        
        testDLL.deleteAt(0);
        assertEquals( "Checking deleteAt position 0", "5,1,2,3", testDLL.toString() );
        testDLL.deleteAt(3);
        assertEquals( "Checking deleteAt position 3", "5,1,2", testDLL.toString() );
        testDLL.deleteAt(-2);
        assertEquals( "Checking deleteAt position -2", "5,1,2", testDLL.toString() );
        testDLL.deleteAt(4);
        assertEquals( "Checking deleteAt position 4", "5,1,2", testDLL.toString() );
        
        // test empty list
        testDLL = new DoublyLinkedList<Integer>();
        testDLL.deleteAt(0);
        assertEquals( "Checking deleteAt position 0", "", testDLL.toString() );
        testDLL.deleteAt(2);
        assertEquals( "Checking deleteAt position 2", "", testDLL.toString() );
     }
   
    /**
     * Check if reverse works
     */
    @Test
    public void testReverse()
    {
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.reverse();
        assertEquals( "Checking reverse on a list with <1 element", "", testDLL.toString() );
        testDLL.insertBefore(0,1);
        testDLL.reverse();
        assertEquals( "Checking reverse on a list with 1 element", "1", testDLL.toString() );
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);
        testDLL.insertBefore(0,4);
        testDLL.insertBefore(1,5); 
        testDLL.reverse();
        assertEquals( "Checking reverse on a list with >1 element", "3,2,1,5,4", testDLL.toString() );
     }

    /**
     * Check if makeUnique works
     */
    @Test
    public void testMakeUnique()
    {
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.makeUnique();
        assertEquals( "Checking makeUnique on a list with <1 element", "", testDLL.toString() );
        testDLL.insertBefore(0,1);
        testDLL.makeUnique();
        assertEquals( "Checking makeUnique on a list with 1 element", "1", testDLL.toString() );
        testDLL.insertBefore(1,2);
        testDLL.insertBefore(2,3);
        testDLL.insertBefore(0,4);
        testDLL.insertBefore(1,5); 
        testDLL.makeUnique();
        assertEquals( "Checking makeUnique on a list with unique element", "4,5,1,2,3", testDLL.toString() );
        testDLL.insertBefore(2,2); 
        testDLL.insertBefore(3,5); 
        testDLL.makeUnique();
        assertEquals( "Checking makeUnique on a list with duplicate elements", "4,5,2,1,3", testDLL.toString() );
     }
    
    /**
     * Check if pop and push works
     */
    @Test
    public void testStack()
    {
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.pop();
        assertEquals( "Checking pop on an empty list", "", testDLL.toString() );
        testDLL.push(1);
        assertEquals( "Checking push on an empty list", "1", testDLL.toString() );
        testDLL.push(2);
        assertEquals( "Checking push on a list with 1 element", "2,1", testDLL.toString() );
        testDLL.push(3);
        assertEquals( "Checking push on a list with >1 element", "3,2,1", testDLL.toString() );
        testDLL.pop();
        testDLL.pop();
        assertEquals( "Checking pop on a list with 1+ element", "1", testDLL.toString() );
        testDLL.pop();
        assertEquals( "Checking pop on a list with 1 element", "", testDLL.toString() );
     }
    
    /**
     * Check if dequeue and enqueue works
     */
    @Test
    public void testQueue()
    {
        DoublyLinkedList<Integer> testDLL = new DoublyLinkedList<Integer>();
        testDLL.dequeue();
        assertEquals( "Checking dequeue on an empty list", "", testDLL.toString() );
        testDLL.enqueue(1);
        assertEquals( "Checking enqueue on an empty list", "1", testDLL.toString() );
        testDLL.enqueue(2);
        assertEquals( "Checking enqueue on a list with 1 element", "2,1", testDLL.toString() );
        testDLL.enqueue(3);
        assertEquals( "Checking enqueue on a list with >1 element", "3,2,1", testDLL.toString() );
        testDLL.dequeue();
        testDLL.dequeue();
        assertEquals( "Checking dequeue on a list with 1+ element", "3", testDLL.toString() );
        testDLL.dequeue();
        assertEquals( "Checking dequeue on a list with 1 element", "", testDLL.toString() );
     }
}

