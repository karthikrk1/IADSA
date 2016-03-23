/*
 * 
 * AVL Class inherited from BST Class
 * 
 * */
class AVL<T extends Comparable<T>> extends BST<T> {
	
	/*
	 * AVLEntry class extended from Entry class in BST class
	 * */
	class AVLEntry<T> extends BST.Entry {
		int height; //additional attribute to store the height of the node
		/*
		 * Inner class Constructor
		 * */
		AVLEntry(T x, AVLEntry<T> l, AVLEntry<T> r, AVLEntry<T> p) {
			super(x, l, r, p); 	// Parent class constructor called
			height = 0;
		}

	}
	//Outer class constructor 
	AVL()
	{
		super(); // Parent class constructor called
	}

	/*
	 * Method to check whether a tree is balanced or not  using inorder concept of recursive call
	 * */
	public boolean balanceTreeCheck(AVLEntry<T> node, boolean t) {
		if (node != null) {
			if (checkBalance(node) == false || t == false) {
				return false;
			}
			t = balanceTreeCheck((AVLEntry<T>) node.left, t);
			t = balanceTreeCheck((AVLEntry<T>) node.right, t);
		}
		return true;
	}
	/*
	 * Method to check the balance of each node
	 * */
	public boolean checkBalance(AVLEntry<T> entry) {
		int leftHeight = 0;
		int rightHeight = 0;
		if (entry.left != null) {
			AVLEntry<T> leftEntry = (AVLEntry<T>) entry.left;
			leftEntry.height = this.calculateHeight(leftEntry);
			leftHeight = leftEntry.height;
		}
		if (entry.right != null) {
			AVLEntry<T> rightEntry = (AVLEntry<T>) entry.right;
			rightEntry.height = this.calculateHeight(rightEntry);
			rightHeight = rightEntry.height;
		}
		if (Math.abs(leftHeight - rightHeight) > 1) {
			return false;
		}
		return true;
	}
	/*
	 * Method to calculate the height of each node
	 * */
	public int calculateHeight(AVLEntry<T> entry) {
		if (entry == null) {
			return 0;
		} else
			return (Math.max(calculateHeight((AVLEntry<T>) entry.left),
					calculateHeight((AVLEntry<T>) entry.right)) + 1);
	}
	/*
	 * Method to check the order of the tree
	 * */
	public boolean checkOrder() {
		//Inorder traversal called
		Comparable[] arr = this.toArray();
		
		//If not in increasing sorted order then it is not ordered properly
		for (int i = 0; i < arr.length - 1; i++) {
			if (arr[i].compareTo(arr[i + 1]) > 0) {
				return false;
			}
		}
		return true;
	}
	/*
	 * Method to check whether any Null node is hanging around using inorder travesal concept
	 * */
	public boolean checkNotNull(Entry<T> node, boolean t) {
		if (node != null) {
			if (node.element == null || t == false) {
				return false;
			}
			t = checkNotNull(node.left, t);
			t = checkNotNull(node.right, t);
		}
		return true;
	}
	/*
	 * Method to verify whether a Tree satisfies the AVL tree condition
	 * */
	public boolean verifyAVLTree(AVLEntry<T> root) {
		//Checks whether the tree is balanced
		if (balanceTreeCheck(root, true) != true) {
			System.out.println("Tree Balance problem");
			return false;
		}
		//Checks whether the tree is in proper order
		if (checkOrder() != true) {
			System.out.println("Tree Order problem");
			return false;
		}
		//Checks whether the tree does not have any hanging Null node
		if (checkNotNull(root, true) != true) {
			System.out.println("Tree Null problem");
			return false;
		}
		return true;
	}
	
	/*
	 * Add function modified w.r.t. the AVL Trees by calling the AVLEntry constructor
	 * */
	public boolean add(T x) {
		if (size == 0) {
			root = new AVLEntry<>(x, null, null, null);
		} else {
			AVLEntry<T> node = (AVLEntry)find(root, x);
			int cmp = x.compareTo((T) node.element);
			if (cmp == 0) {
				node.element = x;
				return false;
			}

			AVLEntry<T> newNode = new AVLEntry<>(x, null, null, node);
			if (cmp < 0) {
				node.left = newNode;
			} else {
				node.right = newNode;
			}
		}
		size++;
		return true;
	}
}

/*
 *
 *OUTPUT When the main() in BST is run
 * 
 * 
 * 
 * 
1 3 5 7 9 2 4 6 8 10 -3 -6 -3 0
Add 1 : [1] 1
Add 3 : [2] 1 3
Add 5 : [3] 1 3 5
Add 7 : [4] 1 3 5 7
Add 9 : [5] 1 3 5 7 9
Add 2 : [6] 1 2 3 5 7 9
Add 4 : [7] 1 2 3 4 5 7 9
Add 6 : [8] 1 2 3 4 5 6 7 9
Add 8 : [9] 1 2 3 4 5 6 7 8 9
Add 10 : [10] 1 2 3 4 5 6 7 8 9 10
Remove -3 : [9] 1 2 4 5 6 7 8 9 10
Remove -6 : [8] 1 2 4 5 7 8 9 10
Remove -3 : [8] 1 2 4 5 7 8 9 10
Final: 1 2 4 5 7 8 9 10 
Tree Balance problem
NOT AN AVL TREE


5 4 6 3 7 2 8 0
Add 5 : [1] 5
Add 4 : [2] 4 5
Add 6 : [3] 4 5 6
Add 3 : [4] 3 4 5 6
Add 7 : [5] 3 4 5 6 7
Add 2 : [6] 2 3 4 5 6 7
Add 8 : [7] 2 3 4 5 6 7 8
Final: 2 3 4 5 6 7 8 
PERFECT AVL TREE

5 4 6 3 7 2 8 0 
Add 5 : [1] 5
Add 4 : [2] 4 5
Add 6 : [3] 4 5 6
Add 3 : [4] 3 4 5 6
Add 7 : [5] 3 4 5 6 7
Add 2 : [6] 2 3 4 5 6 7
Add 8 : [7] 2 3 4 5 6 7 8
Final: 2 3 4 5 6 7 8
// Internally modified as  2 3 4 1 5 6 7 
Tree Order problem
NOT AN AVL TREE



 * */
 