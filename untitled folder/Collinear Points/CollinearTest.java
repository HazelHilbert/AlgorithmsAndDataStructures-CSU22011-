package csu22011_a1;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.*;

//-------------------------------------------------------------------------
/**
 *  Test class for Collinear.java
 *
 *  @author  Hazel Hilbert
 *  @version 03/10/22 22:33:19
 */
@RunWith(JUnit4.class)
public class CollinearTest
{
	public static void main(String[] args) throws IOException {
		int[] r1k_1 = fileToArray("r01000-1.txt"); 
		int[] r1k_2 = fileToArray("r01000-2.txt");
		int[] r1k_3 = fileToArray("r01000-3.txt");

		int[] r2k_1 = fileToArray("r02000-1.txt");
		int[] r2k_2 = fileToArray("r02000-2.txt");
		int[] r2k_3 = fileToArray("r02000-3.txt");

		int[] r4k_1 = fileToArray("r04000-1.txt");
		int[] r4k_2 = fileToArray("r04000-2.txt");
		int[] r4k_3 = fileToArray("r04000-3.txt");

		int[] r5k_1 = fileToArray("r05000-1.txt");
		int[] r5k_2 = fileToArray("r05000-2.txt");
		int[] r5k_3 = fileToArray("r05000-3.txt");

		
		Stopwatch sw1 = new Stopwatch();
		System.out.println(Collinear.countCollinear(r1k_1, r1k_2, r1k_3));
		StdOut.println("countCollinear() with 1k: " + sw1.elapsedTime());
		
		Stopwatch sw2 = new Stopwatch();
		System.out.println(Collinear.countCollinear(r2k_1, r2k_2, r2k_3));
		StdOut.println("countCollinear() with 2k: " + sw2.elapsedTime());
		
		Stopwatch sw3 = new Stopwatch();
		System.out.println(Collinear.countCollinear(r4k_1, r4k_2, r4k_3));
		StdOut.println("countCollinear() with 4k: " + sw3.elapsedTime());
		
		Stopwatch sw4 = new Stopwatch();
		System.out.println(Collinear.countCollinearFast(r1k_1, r1k_2, r1k_3));
		StdOut.println("countCollinearFast() with 1k: " + sw4.elapsedTime());
		
		Stopwatch sw5 = new Stopwatch();
		System.out.println(Collinear.countCollinearFast(r2k_1, r2k_2, r2k_3));
		StdOut.println("countCollinearFast() with 2k: " + sw5.elapsedTime());
		
		Stopwatch sw6 = new Stopwatch();
		System.out.println(Collinear.countCollinearFast(r4k_1, r4k_2, r4k_3));
		StdOut.println("countCollinearFast() with 4k: " + sw6.elapsedTime());
		
		Stopwatch sw7 = new Stopwatch();
		System.out.println(Collinear.countCollinearFast(r5k_1, r5k_2, r5k_3));
		StdOut.println("countCollinearFast() with 5k: " + sw7.elapsedTime());
	
		Stopwatch sw8 = new Stopwatch();
		System.out.println(Collinear.countCollinear(r5k_1, r5k_2, r5k_3));
		StdOut.println("countCollinear() with 5k: " + sw8.elapsedTime());
	}
	
	public static int[] fileToArray(String file) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(file));
		String[] str = br.readLine().split(" ");
		int[] arr = new int[str.length];
		for (int i = 0; i < str.length; i++) {
			arr[i] = Integer.parseInt(str[i]);
		}
		br.close(); 
		return arr;
	}

	//~ Constructor ........................................................
	@Test
	public void testConstructor()
	{
		new Collinear();
	}

	//~ Public Methods ........................................................

	// ----------------------------------------------------------
	/**
	 * Check that the two methods work for empty arrays
	 */

	@Test
	public void testEmpty()
	{
		int expectedResult = 0;

		assertEquals("countCollinear with 3 empty arrays should return zero",     expectedResult, Collinear.countCollinear(new int[0], new int[0], new int[0]));
		assertEquals("countCollinearFast with 3 empty arrays should return zero", expectedResult, Collinear.countCollinearFast(new int[0], new int[0], new int[0]));
	}

	// TODO: write more tests here to cover 100% of the instructions and the branches of Collinear.java

	/**
	 * Check that the two methods work for arrays with no collinear elements
	 */

	@Test
	public void testNonlinear()
	{
		int expectedResult = 0;

		assertEquals("countCollinear with 3 arrays with no collinear elements between them should return zero",     expectedResult, Collinear.countCollinear(new int[]{1, 2, 3}, new int[]{4, 5, 6}, new int[] {21, 22, 23}));
		assertEquals("countCollinearFast with 3 arrays with no collinear elements between them should return zero", expectedResult, Collinear.countCollinearFast(new int[]{1, 2, 3}, new int[]{4, 5, 6}, new int[] {21, 22, 23}));
	}

	/**
	 * Check that the two methods work for arrays with collinear elements
	 */

	@Test
	public void testCollinear()
	{
		int expectedResult = 2;

		assertEquals("countCollinear with 3 arrays with horizontal elements should return zero",     expectedResult, Collinear.countCollinear(new int[]{1, 2}, new int[]{10, 3, 4}, new int[] {6, 11, 5}));
		assertEquals("countCollinearFast with 3 arrays with horizontal elements them should return zero", expectedResult, Collinear.countCollinearFast(new int[]{1, 2}, new int[]{10, 3, 4}, new int[] {6, 11, 5}));
	}
}
