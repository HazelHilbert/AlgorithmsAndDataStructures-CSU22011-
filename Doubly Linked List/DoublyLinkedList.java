package csu22011_a2;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

// -------------------------------------------------------------------------
/**
 *  This class contains the methods of Doubly Linked List.
 *
 *  @author  Hazel Hilbert
 *  @version 11/10/22
 */

/**
 * Class DoublyLinkedList: implements a *generic* Doubly Linked List.
 * @param <T> This is a type parameter. T is used as a class name in the
 * definition of this class.
 *
 * When creating a new DoublyLinkedList, T should be instantiated with an
 * actual class name that extends the class Comparable.
 * Such classes include String and Integer.
 *
 * For example to create a new DoublyLinkedList class containing String data: 
 *    DoublyLinkedList<String> myStringList = new DoublyLinkedList<String>();
 *
 * The class offers a toString() method which returns a comma-separated sting of
 * all elements in the data structure.
 * 
 * This is a bare minimum class you would need to completely implement.
 * You can add additional methods to support your code. Each method will need
 * to be tested by your jUnit tests -- for simplicity in jUnit testing
 * introduce only public methods.
 */
class DoublyLinkedList<T extends Comparable<T>>
{

	/**
	 * private class DLLNode: implements a *generic* Doubly Linked List node.
	 */
	private class DLLNode
	{
		public final T data; // this field should never be updated. It gets its
		// value once from the constructor DLLNode.
		public DLLNode next;
		public DLLNode prev;

		/**
		 * Constructor
		 * @param theData : data of type T, to be stored in the node
		 * @param prevNode : the previous Node in the Doubly Linked List
		 * @param nextNode : the next Node in the Doubly Linked List
		 * @return DLLNode
		 */
		public DLLNode(T theData, DLLNode prevNode, DLLNode nextNode) 
		{
			data = theData;
			prev = prevNode;
			next = nextNode;
		}
	}

	// Fields head and tail point to the first and last nodes of the list.
	private DLLNode head, tail;

	/**
	 * Constructor of an empty DLL
	 * @return DoublyLinkedList
	 */
	public DoublyLinkedList() 
	{
		head = null;
		tail = null;
	}

	/**
	 * Tests if the doubly linked list is empty
	 * @return true if list is empty, and false otherwise
	 *
	 * Worst-case asymptotic running time cost: Theta(1)
	 *
	 * Justification:
	 * 	For the method isEmpty, there is only one line of code.
	 * 	This line evaluates a comparison expression and returns the boolean result, which runs in constant time: Theta(1)
	 * 	Therefore the method isEmpty runs in Theta(1) time in the worst case
	 */
	public boolean isEmpty()
	{
		return head == null;
	}

	/**
	 * Inserts an element in the doubly linked list
	 * @param pos : The integer location at which the new data should be
	 *      inserted in the list. We assume that the first position in the list
	 *      is 0 (zero). If pos is less than 0 then add to the head of the list.
	 *      If pos is greater or equal to the size of the list then add the
	 *      element at the end of the list.
	 * @param data : The new data of class T that needs to be added to the list
	 * @return none
	 *
	 * Worst-case asymptotic running time cost: Theta(N)
	 *
	 * Justification:
	 * 	Splitting this method into three cases: if, else-if, and else statements
	 * 
	 * 	The if statement:
	 * 		The conditional of the if statement is a call to the function isEmpty which is of constant time as shown above: Theta(1)
	 * 		The body of the if statement consists of:
	 * 			Declaration and assignment of a variable that runs in constant time: Theta(1)
	 * 			Two variable assignments that also run in constant time: Theta(1)
	 *	Thus the if statement will run in Theta(1)+Theta(1)+Theta(1) = Theta(1)
	 *
	 *	The else-if statement:
	 *	 	The conditional of the else-if statement is a variable comparison of constant time: Theta(1)
	 *		The body of the else-if statement is the same as the if statement, just with the variable assignments slightly altered
	 *		Still all the variable declaration and assignments run in constant time: Theta(1)
	 *	Thus the else-if statement will run in Theta(1)
	 *
	 *	The else statement:
	 *		The first line of the else statement declares a variable and assigns it to the value returned by a call to the method getNode.
	 *		As shown bellow, the method getNode runs in linear time: Theta(N)
	 *		The other lines of the else statement include an if then else statement.
	 *			Both of these are similar to the previous if and else-if statements outlined above, including:
	 *			Variable declaration and assignments all costing constant runtime: Theta(1)
	 *	Thus the else statement will run in Theta(N)+Theta(1) = Theta(N)
	 *	
	 *	The first two cases have a runtime of Theta(1), however,
	 *  for the worst case (insert data into the end of a non-empty list), the else statement will be evaluated with a cost of Theta(N)
	 *	Therefore the insertBefore method will run in Theta(N) time in the worst case
	 */
	public void insertBefore( int pos, T data ) 
	{
		if (isEmpty()) {
			DLLNode temp = new DLLNode(data, null, null);
			head = temp;
			tail = temp;
		}
		else if (pos <= 0) {
			DLLNode temp = new DLLNode(data, null, head);
			head.prev = temp;
			head = temp;
		}
		else {
			DLLNode ptr = getNode(pos);
			if (ptr == null) {
				DLLNode temp = new DLLNode(data, tail, null);
				tail.next = temp;
				tail = temp;
			}
			else {
				DLLNode temp = new DLLNode(data, null, null);
				ptr.prev.next = temp;
				temp.prev = ptr.prev;
				ptr.prev = temp;
				temp.next = ptr;
			}
		}
	}

	/**
	 * Returns the DLLNode stored at a particular position
	 * @param pos : the position
	 * @return the DLLNode at pos, if pos is within the bounds of the list, and null otherwise.
	 *
	 * Worst-case asymptotic running time cost: Theta(N)
	 *
	 * Justification:
	 * 	Dividing the code into three parts: before the for loop, the for loop itself, and after the for loop.
	 * 
	 * 	Before the for loop:
	 * 		There is an if statement which calls a return statement if the conditional expression is met: Theta(1)
	 * 		The allocation and assignment of a variable named "counter" costing constant time: Theta(1)
	 * 	Thus the lines before the for-loop will run in Theta(1)+Theta(1) = Theta(1)
	 * 
	 * 	The for loop itself:
	 * 		Iterates through the DLL starting at the head until the tail (unless a DLLNode is returned) which costs linear time: Theta(N)
	 * 		In the body of the for loop there is an if statement that calls a return statement: Theta(1)
	 * 		and a variable increment: Theta(1)
	 * 		Therefore each iteration of the loop will cost Theta(1)
	 *  Thus the lines involving the for-loop will run in Theta(N)*Theta(1) = Theta(N)
	 * 
	 * 	After the for loop:
	 * 		There is a return statement with a constant runtime: Theta(1)
	 * 	Thus the lines after the for-loop will run in Theta(1)
	 * 
	 * 	In the worse case scenario (pos >= N) the code will have to iterate through the entire list before returning a DLLNode
	 * 	Therefore the getNode method will run in Theta(1)+Theta(N)+Theta(1) = Theta(N) time in the worst case
	 */
	public DLLNode getNode(int pos) 
	{	
		if (pos < 0) return null;
		int counter = 0;
		for (DLLNode iter = head; iter != null; iter = iter.next) {
			if (pos == counter) return iter;
			counter++;
		}
		return null;
	}

	/**
	 * Returns the data stored at a particular position
	 * @param pos : the position
	 * @return the data at pos, if pos is within the bounds of the list, and null otherwise.
	 *
	 * Worst-case asymptotic running time cost: Theta(N)
	 *
	 * Justification:
	 * 	The first line declares a variable "node" (constant time), and assigns it to the value 
	 *  returned by a call to the method getNode, which as shown above runs in Theta(N) time in the worse case: Theta(N)
	 *  The second line of code returns the result of a conditional statement, with the comparison and both conditions running in constant time: Theta(1)
	 *  
	 *  Therefore the get method will run in Theta(N)+Theta(1) = Theta(N) in the worst case
	 */
	public T get(int pos) 
	{	
		DLLNode node = getNode(pos);
		return (node == null ? null : node.data);
	}

	/**
	 * Deletes the element of the list passed as a parameter (DLLNode node).
	 * @param node : the DLLNode to delete in the list.
	 * @return true : on successful deletion, false : list has not been modified. 
	 *
	 * Worst-case asymptotic running time cost: Theta(1)
	 *
	 * Justification:
	 * 	For this method there is a sequence of if, else-if, else-if, else-if, and else statements.
	 * 	For all of these statements, the conditional expression is simply a variable comparison running in constant time: Theta(1)
	 * 	The body of these statements are simply variable assignments with a constant runtime: Theta(1)
	 * 	Depending on the first if statement, there are two possible return statements, both with a constant runtime: Theta(1)
	 * 
	 * 	All the cases (no matter the input) have a constant runtime Theta(1)+Theta(1)+Theta(1) = Theta(1)
	 * 	Therefore the deleteNode method will run in Theta(1) in the worst case
	 */
	public boolean deleteNode(DLLNode node) 
	{
		if (node == null) return false;
		else if (head == tail) {
			head = null;
			tail = null;
		}
		else if (node == head) {
			head = node.next;
			node.next.prev = null;
		}
		else if (node == tail) {
			tail = node.prev;
			node.prev.next = null;
		}
		else {
			node.prev.next = node.next;
			node.next.prev = node.prev;
		}
		return true;
	}


	/**
	 * Deletes the element of the list at position pos.
	 * First element in the list has position 0. If pos points outside the
	 * elements of the list then no modification happens to the list.
	 * @param pos : the position to delete in the list.
	 * @return true : on successful deletion, false : list has not been modified. 
	 *
	 * Worst-case asymptotic running time cost: Theta(N)
	 *
	 * Justification:
	 * 	The method consists of only one line which returns the result of a call to a method deleteNode.
	 * 	The deleteNode method runs in constant time as shown above: Theta(1)
	 * 	The parameter passed into the call to deleteNode is itself a method call to getNode with parameter pos
	 * 	The getNode method runs in linear time as shown above: Theta(N)
	 * 	
	 * 	Therefore the deletaAt method runs in Theta(1)+Theta(N) = Theta(N) in the worst case
	 */
	public boolean deleteAt(int pos) 
	{
		return deleteNode(getNode(pos));
	}


	/**
	 * Reverses the list.
	 * If the list contains "A", "B", "C", "D" before the method is called
	 * Then it should contain "D", "C", "B", "A" after it returns.
	 *
	 * Worst-case asymptotic running time cost: Theta(N)
	 *
	 * Justification:
	 * 	Dividing the code into two parts: outside the for loop and the for loop itself
	 * 
	 * 	Outside the for loop:
	 * 		The first line is a variable declaration running in constant time: Theta(1)
	 * 		The last three lines are variable assignments all running in constant time: Theta(1)
	 * 	Thus the lines outside the for-loop will run in Theta(1)+Theta(1) = Theta(1)
	 * 
	 * 	The for loop itself:
	 * 		Iterates through every node in the the DLL which costs linear time: Theta(N)
	 * 		The body of the for loop consists of multiple variable assignments all costing constant time: Theta(1)
	 * 		Therefore each iteration of the loop will cost Theta(1)
	 *  Thus the lines involving the for-loop will run in Theta(N)*Theta(1) = Theta(N)
	 * 
	 * 	Therefore the reverse method will run in Theta(1)+Theta(N) = Theta(N) time in the worst case
	 */
	public void reverse()
	{
		DLLNode temp;
		for (DLLNode currentNode = head; currentNode != null; currentNode = currentNode.prev) {
			temp = currentNode.next;
			currentNode.next = currentNode.prev;
			currentNode.prev = temp;
		}
		temp = tail;
		tail = head;
		head = temp;
	}

	/**
	 * Removes all duplicate elements from the list.
	 * The method should remove the _least_number_ of elements to make all elements uniqueue.
	 * If the list contains "A", "B", "C", "B", "D", "A" before the method is called
	 * Then it should contain "A", "B", "C", "D" after it returns.
	 * The relative order of elements in the resulting list should be the same as the starting list.
	 *
	 * Worst-case asymptotic running time cost: Theta(N^2)
	 *
	 * Justification:
	 *  The outer for loop:
	 * 		Iterates through every node in the the DLL starting at the head, which costs linear time: Theta(N)
	 *  
	 *  The inner for loop:
	 *  	Iterates through each node in the the DLL starting with the the next node after the current node from the outer for loop
	 *  	This also has a linear runtime cost: Theta(N)
	 *  	Body of the inner for loop:
	 *  		If statement with variable comparison as the conditional, constant time: Theta(1)
	 *  		Body of the if statement is a call to the deleteNode method, which as shown above runs in constant time: Theta(1)
	 *  	Thus, each iteration of the inner for loop costs Theta(1)+Theta(1) = Theta(1)
	 *  
	 *	Therefore the makeUnique method will run in Theta(N)*Theta(N)*Theta(1) = Theta(N^2) time in the worst case
	 */
	public void makeUnique()
	{
		for (DLLNode iter = head; iter != null; iter = iter.next) {
			for (DLLNode innerIter = iter.next; innerIter != null; innerIter = innerIter.next) {
				if (iter.data == innerIter.data) deleteNode(innerIter);
			}
		}
	}


	/*----------------------- STACK API 
	 * If only the push and pop methods are called the data structure should behave like a stack.
	 */

	/**
	 * This method adds an element to the data structure.
	 * How exactly this will be represented in the Doubly Linked List is up to the programmer.
	 * @param item : the item to push on the stack
	 *
	 * Worst-case asymptotic running time cost: Theta(1)
	 *
	 * Justification:
	 * 	The method consists of only one line of code: a call to the method insertBefore.
	 * 	The insertBefore method has a worst case runtime cost of Theta(N) as shown above, however,
	 * 	for the case that the parameter pos=0, insertBefore only has a Theta(1) runtime.
	 * 	This is because insertBefore will only evaluate the second else-if statement (pos <= 0), with as outlined above:
	 * 	"the else-if statement will run in Theta(1)"
	 * 
	 * 	Thus the push method will run in Theta(1) time in the worst case
	 */
	public void push(T item) 
	{
		insertBefore(0, item);
	}

	/**
	 * This method returns and removes the element that was most recently added by the push method.
	 * @return the last item inserted with a push; or null when the list is empty.
	 *
	 * Worst-case asymptotic running time cost: theta(1)
	 *
	 * Justification:
	 * 	The fist line is an if statement with:
	 * 		A call to the method isEmpty as the condition, isEmpty has a constant cost as shown above: Theta(1)
	 * 		A return statement running in constant time: Theta(1)
	 * 	The second line is a simple variable declaration and assignment running in constant time: Theta(1)
	 * 	The third line is a call to the method deleteAt with parameter pos=0
	 * 		The method deleteAt costs Theta(N) in the worst case as shown above, however,
	 * 		for the parameter pos=0, the call to getNode will only have to go through one iteration of the for loop before returning the node to be deleted.
	 * 		This would have a constant time cost rather than a linear cost as in the worst case: Theta(1)
	 * 	The last line returns the value of a instance variable access, constant runtime cost: Theta(1)
	 * 
	 * 	Therefore this method will run in Theta(1)+Theta(1)+Theta(1)+Theta(1) = Theta(1) time in the worst case.
	 */
	public T pop() 
	{
		if (isEmpty()) return null;
		DLLNode temp = head;
		deleteAt(0);
		return temp.data;
	}


	/*----------------------- QUEUE API
	 * If only the enqueue and dequeue methods are called the data structure should behave like a FIFO queue.
	 */
	/**
	 * This method adds an element to the data structure.
	 * How exactly this will be represented in the Doubly Linked List is up to the programmer.
	 * @param item : the item to be enqueued to the stack
	 *
	 * Worst-case asymptotic running time cost: Theta(1)
	 *
	 * Justification:
	 * 	The body of the enqueue method is exactly the same as the push method.
	 * 	Reiterating the justification for the push method, the call to insertBefore will only
	 * 	evaluate the else-if statement for the parameter pos=0, which runs in constant time.
	 * 	 
	 * 	Thus the enqueue method will run in Theta(1) time in the worst case
	 */
	public void enqueue(T item) 
	{
		insertBefore(0, item);
	}

	/**
	 * This method returns and removes the element that was least recently added by the enqueue method.
	 * @return the earliest item inserted with an equeue; or null when the list is empty.
	 *
	 * Worst-case asymptotic running time cost: Theta(1)
	 *
	 * Justification:
	 * 	The method dequeue has three possible cases (if,  else-if, and else statements) all of which have a constant runtime
	 * 	
	 * 	The if statement:
	 * 		A call to the method isEmpty as the condition, isEmpty has a constant cost as shown above: Theta(1)
	 * 		A return statement running in constant time: Theta(1)
	 * 	Thus the if statement will take Theta(1)+Theta(1) = Theta(1) time to run
	 * 
	 * 	The else-if statement:
	 * 		A simple variable comparison as the condition taking constant time: Theta(1)
	 * 		A variable declaration and multiple variable assignments all taking constant time: Theta(1)
	 * 		A return statement running in constant time: Theta(1)
	 * 	Thus the else-if statement will take Theta(1)+Theta(1)+Theta(1) = Theta(1) time to run
	 *  
	 *  The else statement:
	 *  	A variable declaration and multiple variable assignments all taking constant time: Theta(1)
	 * 		A return statement running in constant time: Theta(1)
	 * 	Thus the else statement will take Theta(1)+Theta(1) = Theta(1) time to run
	 * 
	 * 	Each case has the same runtime cost of Theta(1),
	 * 	Therefore the dequeue method will run in Theta(1) time in the worst case.
	 */
	public T dequeue() 
	{
		if (isEmpty()) return null;
		else if (head == tail) {
			T temp = head.data;
			head = null;
			tail = null;
			return temp;
		}
		else {
			DLLNode temp = tail;
			tail = temp.prev;
			tail.next = null;
			return temp.data;
		}
	}


	/**
	 * @return a string with the elements of the list as a comma-separated
	 * list, from beginning to end
	 *
	 * Worst-case asymptotic running time cost:   Theta(n)
	 *
	 * Justification:
	 *  The code consists of the lines before the for-loop, the for-loop itself and the lines after the for-loop.
	 *
	 *  The lines before the for loop involve 
	 *  - the creation of a StringBuilder object which does not depend on the length of the list. Therefore it takes Theta(1) time.
	 *  - the allocation and assignment of two variables which are constant time operations.
	 *  Thus the code before the for-loop will take Theta(1) time to run.
	 *
	 *  The lines after the for-loop involve a single return instruction and thus take Theta(1) time.
	 *
	 *  The for-loop itself will iterate over all elements of the DLL. Thus it will perform Theta(N) iterations.
	 *  Each iteration will involve:
	 *   * The if-conditional will run:
	 *       - the if-then-else conditions and branching, which are constant time operations. Thus these cost Theta(1) time.
	 *       - The then-branch of the conditional calls StringBuilder's append() method, which (from the Java documentation) we know it runs in Theta(1) time.
	 *       - the else-branch of the conditional involves a single assignment, thus runs in Theta(1) time.
	 *     Therefore the if-then-else conditions will cost Theta(1) in the worst case.
	 *   * The final call to StringBuilder's append will cost again Theta(1)
	 *   * the nested call to toString() will cost time proportional to the length of the strings (but not the length of the list). 
	 *     Assuming strings are relatively small compared to the size of the list, this cost will be Theta(1).
	 *  Therefore each iteration of the loop will cost Theta(1).
	 *  Thus the lines involving the for-loop will run in Theta(N)*Theta(1) = Theta(N).
	 *
	 * Therefore this method will run in Theta(1) + Theta(1) + Theta(N) = Theta(N) time in the worst case.
	 *
	 */
	public String toString() 
	{
		StringBuilder s = new StringBuilder();
		boolean isFirst = true; 

		// iterate over the list, starting from the head
		for (DLLNode iter = head; iter != null; iter = iter.next)
		{
			if (!isFirst)
			{
				s.append(",");
			} else {
				isFirst = false;
			}
			s.append(iter.data.toString());
		}

		return s.toString();
	}
}



