import java.util.*;
/**
 * Program to implement the Red-Black Tree add(element) method in java
 * @author karthikrk
 *
 * @param <T> 
 * 				: Type parameter
 */

public class RBTree<T extends Comparable<? super T>> extends BST<T>{
	//Assign boolean flags for Red and Black colors
	private static final boolean RED = false;
	private static final boolean BLACK = true;
	/*
	 * Create a Entry type RBEntry - that holds the information for each node of the tree.
	 */
	class RBEntry<T>{
		T element;
		RBEntry<T> left,right,parent;
		boolean color;
		
		/**
		 * Constructor for the Class RBEntry
		 * @param x
		 * 			: Element to be inserted
		 * @param l
		 * 			: Left pointer
		 * @param r
		 * 			: Right pointer
		 * @param p
		 * 			: Parent pointer
		 * @param col
		 * 			: color of the node.
		 */
		RBEntry(T x, RBEntry<T> l, RBEntry<T> r, RBEntry<T> p, boolean col){
			element=x;
			left=l; right=r; parent=p;
			color = col;
		}
	}
	
	RBEntry<T> root;
	int size;
	
	//Create a empty tree with null root and size 0
	RBTree(){
		root=null;
		size=0;
	}
	
	//Empty node to avoid some edge cases
	RBEntry<T> emptyNode = new RBEntry<>(null,null,null,null,BLACK);
	
	/**
	 * Method to find if an element in the tree
	 * @param entry
	 * 				: Input - An entry of the RB Tree
	 * @param x
	 * 				: Input - The element to be searched for
	 * @return
	 * 				: Returns the entry node if the element is present, else returns null
	 */
	RBEntry<T> find(RBEntry<T> entry, T x){
		RBEntry<T> pre = entry;
		while(entry!=null){
			pre=entry;
			int cmp=x.compareTo(entry.element);
			if(cmp==0){
				return entry;
			}
			else if(cmp<0){
				entry=entry.left;
			}
			else{
				entry=entry.right;
			}
		}
		return pre;
	}
	
	/**
	 * Method to check if the element is present in the tree
	 * @param element
	 * 					: The element to be searched for. 
	 */
	public boolean contains(T element){
		RBEntry<T> node = find(root, element);
		return node==null ? false: element.equals(node.element);
	}
	
	/**
	 * Method to add the elements to the tree
	 * @param element:
	 * 					Input element to be added to the tree
	 */
	public boolean add(T element){
		//If size=0, create an root entry
		if(size==0){
			root = new RBEntry<>(element,null,null,null,BLACK);
			root.parent=root;
		}
		else{
			RBEntry<T> node = find(root, element);
			int cmp = element.compareTo(node.element);
			if(cmp==0){
				node.element=element;
				return false;
			}
			RBEntry<T> newEntry = new RBEntry<>(element,null,null,node,RED);
			if(cmp<0){
				node.left=newEntry;
			}
			else{
				node.right=newEntry;
			}
			//Call repair to fix insertion bugs - to match the Red Black tree properties
			repair(newEntry);
		}
		size++;
		return true;
	}
	
	/**
	 * Method to repair the anomalies created due to insertion of new elementes.
	 * @param node
	 * 				: The node whose insertion caused an anomaly
	 */
	public void repair(RBEntry<T> node){
 		while(node.parent.color==RED && node!=root){
 			//If the node's parent is a left child of the tree
			if(node.parent==node.parent.parent.left){
				RBEntry<T> uncle = node.parent.parent.right;
				/*
				 * Case 1 - When both parent and uncle are Red in color - we flip to black and propagate till the root
				 */
				
				if(uncle.color==RED){
					node.parent.color=BLACK;
					uncle.color=BLACK;
					node.parent.parent.color=RED;
					node=node.parent.parent;
				}
				
				else{
					/*
					 * Case 2 - When the uncle is black and node is a right child of the parent - ZigZag
					 */
					if(node==node.parent.right){
						node=node.parent;
						rotateLeft(node);
					}
					/*
					 * Case 3 - When the uncle is black and the node is a left child of the parent - ZigZig
					 */
					node.parent.color=BLACK;
					node.parent.parent.color=RED;
					rotateRight(node.parent.parent);
				}
			}
			else{
				/*
				 * Node's parent is a right child of the tree
				 */
				RBEntry<T> uncle = node.parent.parent.left;
				/*
				 * Case 1 - When the uncle's color is RED
				 */
				
				if(uncle.color==RED){
					node.parent.color=BLACK;
					uncle.color=BLACK;
					node.parent.parent.color=RED;
					node=node.parent.parent;
				}
				
				else{
					/*
					 * Case 2 - When the uncle is black and node is a left child - ZigZag
					 */
					if(node==node.parent.left){
						node=node.parent;
						rotateRight(node);
					}
					/*
					 * Case 3 - When the uncle is black and node is a right child of the parent - ZigZig
					 */
					node.parent.color=BLACK;
					node.parent.parent.color=RED;
					rotateLeft(node.parent.parent);
				}
			}	
		}
		root.color=BLACK;
	}
	
	/**
	 * Method to rotate the tree to the left based on the node
	 * @param node
	 * 				: Node which is to be rotated
	 */
	public void rotateLeft(RBEntry<T> node){
		RBEntry<T> curr = node;
		RBEntry<T> r = curr.right;
		curr.right=r.left;
		if(r.left!=emptyNode){
			r.left.parent=curr;
		}
		r.parent=curr.parent;
		if(curr.parent==emptyNode){
			root=r;
		}
		else if(curr == curr.parent.left){
			curr.parent.left=r;
		}
		else{
			curr.parent.right=r;
		}
		r.left=curr;
		curr.parent=r;
	}
	
	/**
	 * Method to rotate the tree to the right based on the node
	 * @param node
	 * 				: Node which is to be rotated
	 */
	public void rotateRight(RBEntry<T> node){
		RBEntry<T> curr=node;
		RBEntry<T> l = curr.left;
		curr.left=l.right;
		if(l.right!=emptyNode){
			l.right.parent=curr;
		}
		l.parent=curr.parent;
		if(curr.parent==emptyNode){
			root=l;
		}
		else if(curr==curr.parent.right){
			curr.parent.right=l;
		}
		else{
			l.parent.left=l;
		}
		l.right=curr;
		curr.parent=l;
	}
	
	/**
	 * Utility method to print the tree 
	 */
	public void printTree() {
		System.out.print("[" + size + "]");
		printTree(root);
		System.out.println();
	    }

	    // Inorder traversal of tree
	void printTree(RBEntry<T> node) {
		if(node != null) {
		    printTree(node.left);
		    System.out.print(" " + node.element);
		    printTree(node.right);
		}
	}
	
	public static void main(String[] args){
		RBTree<Integer> rbt = new RBTree<>();
		Scanner in = new Scanner(System.in);
		while(in.hasNext()) {
			int x=in.nextInt();
			if(x>0){
				rbt.add(x);
				rbt.printTree();
			}
		}
		in.close();
	}
}

/*SAMPLE OUTPUT
50
[1] 50
25
[2] 25 50
75
[3] 25 50 75
12
[4] 12 25 50 75
35
[5] 12 25 35 50 75
20
[6] 12 20 25 35 50 75
100
[7] 12 20 25 35 50 75 100

*/