
/**
 * A program to implement recursive and non recursive functions for:
 * a) reverse the order of a SinglyLinkedList
 * b) print the elements of SinglyLinkedList in reverse order
 * @author badhri
 *
 * 
 */
public class SP1F<T> {
	
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

	    SP1F() {
	        header = new Entry<>(null, null);
	        tail = null;
	        size = 0;
	    }

	    void add(T x) {
	        if(tail == null) {
	            header = new Entry<>(x, header);
	            tail = header;
	        } else {
	            tail.next = new Entry<>(x, null);
	            tail = tail.next;
	        }
		size++;
	    }

	    void printList() {
	        Entry<T> x = header;
	        while(x != null) {
	            System.out.print(x.element + " ");
	            x = x.next;
	        }
	        System.out.println();
	    }

	    /** 
	     * Method to reverse the elements using non recursive calls
	     */
	    void nonrecursive_reverse()
	    
	    {   
	    	Entry<T> t_tail = null;
            Entry<T> x = header;
            Entry<T> temp =  null;
	    	
	    	while (x != null)
	    	{   temp = x.next;
	    		x.next = t_tail;
	    		t_tail = x;
	    		x = temp;
	        } header = t_tail;
	    }

	    /**
	     * Method to reverse the elements using recursion
	     * @param t_tail - Tail node
	     * @param x - Current node
	     */
	    void recursive_reverse(Entry<T> t_tail, Entry<T> x)
	    {   
	    	
	    	if(t_tail==null)
	    	{   
	    		x = header;
	    		
	    	}
	    	
	    	if (x != null)
	    	{   
	    		Entry<T> temp = x.next;
	    		x.next = t_tail;
	    		t_tail = x;
	    		x=temp;
	    		recursive_reverse(t_tail, x);
	    	}
	    	else
	    	{   
	    		header = t_tail;
	    		return;
	      	}
	    	
	    }
	
	public static void main(String[] args) {
        int n = 10;
        if(args.length > 0) {
            n = Integer.parseInt(args[0]);
        }
        
        SP1F<Integer> lst = new SP1F<>();
        for(int i=1; i<=n; i++) {
            lst.add(new Integer(i));
        }
        System.out.println("Array before Reversing");
        lst.printList();
        System.out.println("Array after Reversing for the first time using non recursive method");
	    lst.nonrecursive_reverse();
	    lst.printList();
	    System.out.println("Array after Reversing for the second time using recursive method");
	    lst.recursive_reverse(null,null);
        lst.printList();
    }
}
