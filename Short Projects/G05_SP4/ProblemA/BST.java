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
  
    // Find x in subtree rooted at node t.  Returns node where search ends.
    Entry<T> find(Entry<T> t, T x) {
	Entry<T> pre = t;
	while(t != null) {
	    pre = t;
	    int cmp = x.compareTo(t.element);
	    if(cmp == 0) {
		return t;
	    } else if(cmp < 0) {
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

    // Add x to tree.  If tree contains a node with same key, replace element by x.
    // Returns true if x is a new element added to tree.
    public boolean add(T x) {
	if(size == 0) {
	    root = new Entry<>(x, null, null, null);
	} else {
	    Entry<T> node = find(root, x);
	    int cmp = x.compareTo(node.element);
	    if(cmp == 0) {
		node.element = x;
		return false;
	    }
	    Entry<T> newNode = new Entry<>(x, null, null, node);
	    if(cmp < 0) {
		node.left = newNode;
	    } else {
		node.right = newNode;
	    }
	}
	size++;
	return true;
    }

    //Print the level order of tree
    public void printLevel(Entry<T> node, int level){
    	if(level == 1){
    		System.out.println(node.element); 
    	}
    	else{
    		printLevel(node.left, level-1);
    		printLevel(node.right, level-1);
    	}	
    }
    
    //Return maximum of left and right sub tree heights 
    int max(int n1, int n2){
    	if(n1>n2)
    		return n1;
    	else 
    		return n2;
    }
    
    // Find height of the current tree
    int treeHeight(Entry<T> node){
    	//height is 0
    	if(node == null)
    		return 0;
    	//tree of some height h
    	else
    		return max(treeHeight(node.left), treeHeight(node.right)) + 1; 	
    }
    
    // Level Order traversal of tree
    void printTree(Entry<T> root) {
    	int height = treeHeight(root);
	for(int i=1;i<height; height++){
		printLevel(root,i);
	}
    } 
    public static void main(String[] args) {
	BST<Integer> t = new BST<>();
	int n;
	Scanner in = new Scanner(System.in);
	System.out.println("Enter the number of nodes");
	n=in.nextInt();
	System.out.println("Enter the values");
	while (n>0){
		int x = in.nextInt();
		t.add(x);
		n--;
	}
	System.out.println("Level Order");
	t.printTree(t.root);
	}
}
   
/*
 * SAMPLE OUTPUT
 * Enter the number of nodes: 6
 * Enter the values
 * 1
 * 2
 * 3
 * 4
 * 5
 * Level Order: 1 2 3 4 5
 */


