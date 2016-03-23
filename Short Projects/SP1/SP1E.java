import java.util.*;
/**
 * A simple Java class that simulates the Zipper Function
 * Example: Input: 1 2 3 4 5 6 7 8 9 10
 * Exmaple: Output: multizip(3) : 1 4 7 10 2 5 8 3 6 9
 * @author karthikrk
 *
 */
public class SP1E<T> {
	public class Entry<T> {
        T element;
        Entry<T> next;

        Entry(T x, Entry<T> nxt) {
            element = x;
            next = nxt;
        }
    }
	
	Entry<T> header, tail;
    int size;

    SP1E() {
        header = new Entry<>(null, null);
        tail = null;
        size = 0;
    }
    
    void add(T x) {
        if(tail == null) {
            header.next = new Entry<>(x, header.next);
            tail = header.next;
        } else {
            tail.next = new Entry<>(x, null);
            tail = tail.next;
        }
	size++;
    }

    void printList() {
        Entry<T> x = header.next;
        while(x != null) {
            System.out.print(x.element + " ");
            x = x.next;
        }
        System.out.println();
    }
    
    void multiUnzip(int k){
    	if(size < k || k<2) return;
    	List<Entry<T>> head1 = new ArrayList<>(k);
    	List<Entry<T>> tail1 = new ArrayList<>(k);
    	Entry<T> c = header.next;
    	for(int i=0; i<k;i++){
    		head1.add(c);
    		tail1.add(c);
    		c=c.next;
    	}
    	int state = 0;
    	
    	// Invariant: 
    	// tail1 is the tail of index chain i, where i = 0 to k-1.
    	// c is current element to be processed.
    	// state indicates the state of the finite state machine
    	// state = i indicates that the current element is added after tail1.get(i).
    	while(c != null) {
    		tail1.get(state).next = c;
    		tail1.set(state, c);
    		c = c.next;
    	    state = ++state%k;
    	}
    	
    	for(int i=1;i<k;i++){
    		tail1.get(i-1).next=head1.get(i);
    	}
    	tail = tail1.get(k-1);
    	tail.next=null;
    	
    }
    
    public static void main(String[] args) {
        int n = 10;
        if(args.length > 0) {
            n = Integer.parseInt(args[0]);
        }

        SP1E<Integer> lst = new SP1E<>();
        for(int i=1; i<=n; i++) {
            lst.add(new Integer(i));
        }
        int k = Integer.parseInt(args[1]);
        lst.printList();
        lst.multiUnzip(k);
        lst.printList();
    }

}
