//jacob good jacgood
//Lab 13
//April 25, 2017

import java.util.Random;

public class BST<Key extends Comparable<Key>, Value> { 
	private Node root;
	// inner Node class
	// note: an outer class has direct access to values in an inner class 
	private class Node {
		private Key key;
		private Value value;
		private Node lChild; // left child 
		private Node rChild; // right child
		// number of nodes at this subtree
		// the value of N for the root will be # of nodes in entire tree 
		// the value of N for a leaf node would be 1
		private int N;
		public Node (Key key, Value val, int N) {
			
			this.key = key; //set initial variables equal to the arguments given
			
			this.value = val;
			
			this.N = N;
		}
	}
	public int size() { 
		return size(root); //find the size of the root
	} 
	
	private int size(Node n) {
		
		if (n == null) { //if the node is empty, size is zero
			
			return 0;
		} else { 
			
			return n.N; //return the N value of the node
		}
	}

	// returns # of nodes in the tree

	// returns the value associated with they key 
	// returns null if the key is not in the tree 
	public Value get(Key key) { 
		return get(root, key); //return the value of the root
	}
	
	
	private Value get(Node n, Key k) {
		if (n == null) {
			return null; //if the node is null then the value is null
		}
		
		int compare = k.compareTo(n.key); //returns negative if k is greater than n.key, 
										//returns positive if n.key is greater than
										//returns zero is they are equal
		if (compare < 0) {
			
			return get(n.lChild, k); //get the lchild is negative
		} else if (compare > 0) {
			return get(n.rChild, k); //get the rchild is positive
			
		} else {
			
			return n.value; //return the value of n
		}
	}

	public void put(Key key, Value val) { 
		root = put(root, key, val); //returns the put of the root node
		
	} 
	
	private Node put(Node n, Key k, Value v) {
		if (n == null) {
			
			return new Node(k, v, 1); //return a node with size one
		}
		
		int compare = k.compareTo(n.key); //same comparison as above
		
		if (compare < 0) {
			
			n.lChild = put(n.lChild, k, v); 
		} else if (compare > 0) {
			
			n.rChild = put(n.rChild, k, v);
		} else {
			
			n.value = v;
		}
		
		n.N = size(n.lChild) + size(n.rChild) + 1; //change the size to fit with the new node
		return n; //return the node
	}

	// inserts the key value pair into the tree
	// performs an in order walk of the tree printing the values 
	public void walk() { 
		walk(root); //walk the subtree
		
	}
	
	public void walk(Node n) { //walk in-order method
		
		
		if (n == null) { 
			return; //if the node is empty go back up the tree 
		}
		
		walk(n.lChild); //go down to the left
		
		System.out.println(n.value); //show the value of the node
		
		walk(n.rChild); //go down to the right
	}
	// here are some of the test cases I performed 
	public static void main(String[] args) {
		Random rand = new Random();
		BST<Integer, Character> tree = new BST<>();
		for (int i = 0; i < 25; i++) {
			int key = rand.nextInt(26) + 'a';
			char val = (char)key;
			tree.put(key, val); 
		}
		// note: not all of these chars will end up being generated from the for loop 
		// some of these return values will be null 
		System.out.println(tree.get((int)'a'));
		System.out.println(tree.get((int)'b'));
		System.out.println(tree.get((int)'c')); 
		System.out.println(tree.get((int)'f')); 
		System.out.println(tree.get((int)'k')); 
		System.out.println(tree.get((int)'x'));
		System.out.println("---tree.walk()---");
		tree.walk(); //moved walk into the main method
	}
}