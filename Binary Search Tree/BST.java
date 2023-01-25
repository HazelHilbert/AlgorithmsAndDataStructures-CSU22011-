/*************************************************************************
 *  Binary Search Tree class.
 *  Adapted from Sedgewick and Wayne.
 *
 *  @version 14/11/22 21:39:35
 *
 *  @author Hazel Hilbert
 *
 *************************************************************************/
package csu22011_a3;

import java.util.NoSuchElementException;

public class BST<Key extends Comparable<Key>, Value> {
	private Node root;             // root of BST

	/**
	 * Private node class.
	 */
	private class Node {
		private Key key;           // sorted by key
		private Value val;         // associated data
		private Node left, right;  // left and right subtrees
		private int N;             // number of nodes in subtree

		public Node(Key key, Value val, int N) {
			this.key = key;
			this.val = val;
			this.N = N;
		}
	}

	// is the symbol table empty?
	public boolean isEmpty() { return size() == 0; }

	// return number of key-value pairs in BST
	public int size() { return size(root); }

	// return number of key-value pairs in BST rooted at x
	private int size(Node x) {
		if (x == null) return 0;
		else return x.N;
	}

	/**
	 *  Search BST for given key.
	 *  Does there exist a key-value pair with given key?
	 *
	 *  @param key the search key
	 *  @return true if key is found and false otherwise
	 */
	public boolean contains(Key key) {
		return get(key) != null;
	}

	/**
	 *  Search BST for given key.
	 *  What is the value associated with given key?
	 *
	 *  @param key the search key
	 *  @return value associated with the given key if found, or null if no such key exists.
	 */
	public Value get(Key key) { return get(root, key); }

	private Value get(Node x, Key key) {
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if      (cmp < 0) return get(x.left, key);
		else if (cmp > 0) return get(x.right, key);
		else              return x.val;
	}

	/**
	 *  Insert key-value pair into BST.
	 *  If key already exists, update with new value.
	 *
	 *  @param key the key to insert
	 *  @param val the value associated with key
	 */
	public void put(Key key, Value val) {
		if (val == null) { delete(key); return; }
		root = put(root, key, val);
	}

	private Node put(Node x, Key key, Value val) {
		if (x == null) return new Node(key, val, 1);
		int cmp = key.compareTo(x.key);
		if      (cmp < 0) x.left  = put(x.left,  key, val);
		else if (cmp > 0) x.right = put(x.right, key, val);
		else              x.val   = val;
		x.N = 1 + size(x.left) + size(x.right);
		return x;
	}

	/**
	 * Tree height.
	 *
	 * Asymptotic worst-case running time using Theta notation: Theta(N)
	 * 
	 * Explanation:
	 * the public method height() returns the value from the result to a call to a private method of the same name 
	 * with root as the parameter. Thus the runtime of the public height() will be the same as the private one
	 * 
	 * for the private method height():
	 *  	- the first line is a simple if expression returning a integer value all running in constant time: Theta(1)
	 * 		- the last line has a return statement of an addition between 1 and the maximum of two recursive call to height.
	 * 		  other than the cost of the recursive calls, the rest of the operations in this line will run in constant time: Theta(1)
	 *  Thus the cost of each recursive call to height is: Theta(1) + Theta(1) = Theta(1)
	 * 	To find the worst case run time of this function, multiply the number of recursive calls to the cost of each call
	 * 
	 * Number of recursive calls:
	 * 	For each call to height(), the height of the left and right subtrees will also be evaluated, returning whichever is larger or -1 if null.
	 * 	This is done by recursively calling height() on the left and right child of the node until the leaf nodes.
	 * 	This means that stating from the root, every node of the tree will be passed as the parameter to height 
	 * 	(plus some additional calls with null as the parameter for leaf nodes).
	 * Thus if the BST has N nodes, there will be N + a few additional recursive calls to height(): Theta(N)
	 * 
	 * Therefore, the private method height() will have an asymptotic worst case running time of Theta(1) * Theta(N) = Theta(N)
	 * The public method height() will also have a run time of Theta(N) in the worst case
	 * 
	 * @return the number of links from the root to the deepest leaf.
	 *
	 * Example 1: for an empty tree this should return -1.
	 * Example 2: for a tree with only one node it should return 0.
	 * Example 3: for the following tree it should return 2.
	 *   B
	 *  / \
	 * A   C
	 *      \
	 *       D
	 */
	public int height() {
		return height(root);
	}
	private int height(Node x) {
		if (x == null) return -1;
		return 1 + Math.max(height(x.left), height(x.right));
	}

	/**
	 * Median key.
	 * If the tree has N keys k1 < k2 < k3 < ... < kN, then their median key 
	 * is the element at position (N-1)/2 (where "/" here is integer division)
	 *
	 * @return the median key, or null if the tree is empty.
	 */
	public Key median() {
		if (isEmpty()) return null;
		return select(root, (size() - 1) / 2).key;
	}
	private Node select(Node x, int n) {
		//if (x == null) return null;
		int t = size(x.left);
		if (t > n) return select(x.left, n);
		else if (t < n) return select(x.right, n-t-1);
		else return x;
	}


	/**
	 * Print all keys of the tree in a sequence, in-order.
	 * That is, for each node, the keys in the left subtree should appear before the key in the node.
	 * Also, for each node, the keys in the right subtree should appear before the key in the node.
	 * For each subtree, its keys should appear within a parenthesis.
	 *
	 * Example 1: Empty tree -- output: "()"
	 * Example 2: Tree containing only "A" -- output: "(()A())"
	 * Example 3: Tree:
	 *   B
	 *  / \
	 * A   C
	 *      \
	 *       D
	 *
	 * output: "((()A())B(()C(()D())))"
	 *
	 * output of example in the assignment: (((()A(()C()))E((()H(()M()))R()))S(()X()))
	 *
	 * @return a String with all keys in the tree, in order, parenthesized.
	 */
	public String printKeysInOrder() {
		return "(" + printKeysInOrder(root) + ")";
	}
	public String printKeysInOrder(Node x) {
		if (x == null) return "";
		return "(" + printKeysInOrder(x.left) + ")" + x.key + "(" + printKeysInOrder(x.right) + ")";
	}

	/**
	 * Pretty Printing the tree. Each node is on one line -- see assignment for details.
	 *
	 * @return a multi-line string with the pretty ascii picture of the tree.
	 */
	public String prettyPrintKeys() {
		return prettyPrintKeys(root, "");
	}
	private String prettyPrintKeys(Node x, String prefix) {
		if (x == null) return prefix + "-null\n";
		return prefix + "-" + x.val + "\n" + prettyPrintKeys(x.left, prefix + " |") + prettyPrintKeys(x.right, prefix + "  ");
	}

	/**
	 * Deteles a key from a tree (if the key is in the tree).
	 * Note that this method works symmetrically from the Hibbard deletion:
	 * If the node to be deleted has two child nodes, then it needs to be
	 * replaced with its predecessor (not its successor) node.
	 *
	 * @param key the key to delete
	 */
	public void delete(Key key) { 
		root = delete(root, key); 
	}
	private Node delete(Node x, Key key) {
		if (x == null) return null;
		int cmp = key.compareTo(x.key);
		if (cmp < 0) x.left = delete(x.left, key);
		else if (cmp > 0) x.right = delete(x.right, key);
		else {
			if (x.right == null) return x.left;
			if (x.left == null) return x.right;
			Node t = x;
			x = max(t.left);
			x.left = deleteMax(t.left);
			x.right = t.right;
		}
		x.N = size(x.left) + size(x.right) + 1;
		return x;
	}
	private Node deleteMax(Node x) {
		if (x.right == null) return x.left;
		x.right = deleteMax(x.right);
		x.N = 1 + size(x.right) + size(x.left);
		return x;
	}
	private Node max(Node x) {
		if (x.right == null) return x;
		return max(x.right);
	}
}
