import java.util.*;

public class BST<T extends Comparable<? super T>> {
	class Entry<T> {
		T element;
		Entry<T> left, right, parent;

		Entry(T x, Entry<T> l, Entry<T> r, Entry<T> p) {
			element = x;
			left = l;
			right = r;
			parent = p;
		}
	}

	Entry<T> root;
	int size;

	BST() {
		root = null;
		size = 0;
	}

	// Find x in subtree rooted at node t. Returns node where search ends.
	Entry<T> find(Entry<T> t, T x) {
		Entry<T> pre = t;
		while (t != null) {
			pre = t;
			int cmp = x.compareTo(t.element);
			if (cmp == 0) {
				return t;
			} else if (cmp < 0) {
				t = t.left;
			} else {
				t = t.right;
			}
		}
		return pre;
	}

	// Is x contained in tree?
	public boolean contains(T x) {
		Entry<T> node = find(root, x);
		return node == null ? false : x.equals(node.element);
	}

	// Add x to tree. If tree contains a node with same key, replace element by
	// x.
	// Returns true if x is a new element added to tree.
	public boolean add(T x) {
		if (size == 0) {
			root = new Entry<>(x, null, null, null);
		} else {
			Entry<T> node = find(root, x);
			int cmp = x.compareTo(node.element);
			if (cmp == 0) {
				node.element = x;
				return false;
			}

			Entry<T> newNode = new Entry<>(x, null, null, node);
			if (cmp < 0) {
				node.left = newNode;
			} else {
				node.right = newNode;
			}
		}
		size++;
		return true;
	}

	// Remove x from tree. Return x if found, otherwise return null
	public T remove(T x) {
		T rv = null;
		if (size > 0) {
			Entry<T> node = find(root, x);
			if (x.equals(node.element)) {
				rv = node.element;
				remove(node);
				size--;
			}
		}
		return rv;
	}

	// Called when node has at most one child. Returns that child.
	Entry<T> oneChild(Entry<T> node) {
		return node.left == null ? node.right : node.left;
	}

	// Remove a node from tree
	void remove(Entry<T> node) {
		if (node.left != null && node.right != null) {
			removeTwo(node);
		} else {
			removeOne(node);
		}
	}

	// remove node that has at most one child
	void removeOne(Entry<T> node) {
		if (node == root) {
			Entry<T> nc = oneChild(root);
			root = nc;
			root.parent = null;
		} else {
			Entry<T> p = node.parent;
			Entry<T> nc = oneChild(node);
			if (p.left == node) {
				p.left = nc;
			} else {
				p.right = nc;
			}
			if (nc != null)
				nc.parent = p;
		}
	}

	// remove node that has two children
	void removeTwo(Entry<T> node) {
		Entry<T> minRight = node.right;
		while (minRight.left != null) {
			minRight = minRight.left;
		}
		node.element = minRight.element;
		removeOne(minRight);
	}

	public static void main(String[] args) {
		AVL<Integer> t = new AVL<>();
		Scanner in = new Scanner(System.in);

		while (in.hasNext()) {
			int x = in.nextInt();
			if (x > 0) {
				System.out.print("Add " + x + " : ");
				t.add(x);
				t.printTree();
			} else if (x < 0) {
				System.out.print("Remove " + x + " : ");
				t.remove(-x);
				t.printTree();
			} else {
				Comparable[] arr = t.toArray();
				System.out.print("Final: ");
				for (int i = 0; i < t.size; i++) {
					System.out.print(arr[i] + " ");
				}
				System.out.println();
	
				/*
				 * AVL tree verification method called 
				 * */
				if (t.verifyAVLTree((AVL<Integer>.AVLEntry<Integer>) t.root)) {
					System.out.println("PERFECT AVL TREE");
				} else {
					System.out.println("NOT AN AVL TREE");
				}

				return;
			}

		}

	}

	// Create an array with the elements using in-order traversal of tree
	public Comparable[] toArray() {
		Comparable[] arr = new Comparable[size];
		inOrder(root, arr, 0);
		return arr;
	}

	// Recursive in-order traversal of tree rooted at "node".
	// "index" is next element of array to be written.
	// Returns index of next entry of arr to be written.
	int inOrder(Entry<T> node, Comparable[] arr, int index) {
		if (node != null) {
			index = inOrder(node.left, arr, index);
			arr[index++] = node.element;
			index = inOrder(node.right, arr, index);
		}
		return index;
	}

	public void printTree() {
		System.out.print("[" + size + "]");
		printTree(root);
		System.out.println();
	}

	// Inorder traversal of tree
	void printTree(Entry<T> node) {
		if (node != null) {
			printTree(node.left);
			System.out.print(" " + node.element);
			printTree(node.right);
		}
	}

	// Preorder traversal of tree
	void preTree(Entry<T> node) {
		if (node != null) {
			System.out.print(" " + node.element);
			preTree(node.left);
			preTree(node.right);
		}
	}
}