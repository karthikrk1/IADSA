import java.util.*;
/**Program to implement Intersection, Union and Set Difference on two lists implementing sorted sets
 * 
 * @author badhri
 *
 * 
 */

public class SP1A<T> implements Iterable<T>{

	static LinkedList<Long> listone = new LinkedList<>();
	static LinkedList<Long> listtwo = new LinkedList<>();
	static LinkedList<Long> IntersectList = new LinkedList<>();
	static LinkedList<Long> UnionList = new LinkedList<>();
	static LinkedList<Long> DifferenceList = new LinkedList<>();

/**
 * Method to add element and returns success once add is done correctly.
 * @param E - element to be added to the list.
 * @return
 */
static boolean add(Integer E)
{   if(E!=0)
{
	for(Long i=(long) 1; i<100; i+=2)
     {//All Odd number till 100 will fall into this list
	listone.add(i);
	 }
    for(Long j=(long) 3; j<100; j+=3)
    {//All Multiples of 3 till 100 will fall into this list
    listtwo.add(j);
    }

    
    return true;
}
return false;
}

/**
 * Helper method to iterate using an Iterator and return the next value or null
 * 
 * @param il - Input: Iterator object for the list.
 * @return
 */
public static<T> T nextElement(Iterator<T> il){
	if(il.hasNext())
		return il.next();
	else
		return null;
	
}

/**Method to implement the intersection operation on two lists.
 * 
 * @param listone - input list1
 * @param listtwo - input list2
 * @param IntersectList - Output array to store the result of intersection
 */
static void intersection(LinkedList<Long> listone, LinkedList<Long> listtwo, LinkedList<Long> IntersectList) 

{
	
	Iterator<Long> IterateListOne = listone.iterator();
	Iterator<Long> IterateListTwo = listtwo.iterator();
	
    Long ListOneElement = null;
    Long ListTwoElement = null;
    
    ListOneElement  = nextElement(IterateListOne);
    ListTwoElement  = nextElement(IterateListTwo);
    while (ListOneElement!=null && ListTwoElement!=null)
    { //Loop till end of two lists
    
    	//Since lists are sorted keep moving till a common element is found
    	if(ListOneElement > ListTwoElement) 
    	    ListTwoElement = IterateListTwo.next();
    	//Since lists are sorted keep moving till a common element is found	
       	else if(ListOneElement < ListTwoElement)
       		ListOneElement = IterateListOne.next();
       	//Common element point in both the lists
       	else {
       	IntersectList.add(ListOneElement);
       	ListOneElement  = nextElement(IterateListOne);
        ListTwoElement  = nextElement(IterateListTwo);
       	}
  }
  System.out.println(IntersectList);
}



/**
 * Method to find the union of two sets 
 * @param listone - Input list1
 * @param listtwo - Input list2
 * @param UnionList - Output list. Contains the output after union is done
 */
static void union(LinkedList<Long> listone, LinkedList<Long> listtwo, LinkedList<Long> UnionList) 

{
	
	Iterator<Long> IterateListOne = listone.iterator();
	Iterator<Long> IterateListTwo = listtwo.iterator();
	
    Long ListOneElement = null;
    Long ListTwoElement = null;
    
    ListOneElement  = nextElement(IterateListOne);
    ListTwoElement  = nextElement(IterateListTwo);
    while (ListOneElement != null && ListTwoElement!=null)
    { //Loop till end of two lists
    
    	//Since lists are sorted keep moving till a common element is found
    	if(ListOneElement > ListTwoElement)
    	{   UnionList.add(ListTwoElement);
    	    ListTwoElement = nextElement(IterateListTwo);
    	}
    	//Since lists are sorted keep moving till a common element is found	
       	else if(ListOneElement < ListTwoElement)
       	{
       		UnionList.add(ListOneElement);
       		ListOneElement = nextElement(IterateListOne);
       	}
       	//Common element point in both the lists
       	else {
       	UnionList.add(ListOneElement);
       	ListOneElement  = nextElement(IterateListOne);
        ListTwoElement  = nextElement(IterateListTwo);
       	}
  }
  System.out.println(UnionList);
}

/** 
 * Method to find the set difference 
 * @param listone - Input list1
 * @param listtwo - Input list2
 * @param DifferenceList - Output List. Contains the result of the difference operation
 */
static void difference(LinkedList<Long> listone, LinkedList<Long> listtwo, LinkedList<Long> DifferenceList) 

{
	
	Iterator<Long> IterateListOne = listone.iterator();
	Iterator<Long> IterateListTwo = listtwo.iterator();
	
    Long ListOneElement = null;
    Long ListTwoElement = null;
    
    ListOneElement  = nextElement(IterateListOne);
    ListTwoElement  = nextElement(IterateListTwo);
    while (IterateListOne.hasNext() && IterateListTwo.hasNext())
    { //Loop till end of two lists
    
    	//Since lists are sorted keep moving till a common element is found
    	if(ListOneElement > ListTwoElement)
    	{   DifferenceList.add(ListOneElement);
    	ListOneElement  = nextElement(IterateListOne);
        ListTwoElement  = nextElement(IterateListTwo);
    	}
    	//Since lists are sorted keep moving till a common element is found	
       	else if(ListOneElement < ListTwoElement)
       	{
       		DifferenceList.add(ListOneElement);
       		ListOneElement = nextElement(IterateListOne);
       	}
       	//Common element point in both the lists
       	else {
       		ListOneElement  = nextElement(IterateListOne);
       	    ListTwoElement  = nextElement(IterateListTwo);
       	}
  }
  System.out.println(DifferenceList);
}

public Iterator<T> iterator() {
	return null;
}


public static void main(String args[])
{
	
	if(SP1A.add(1))
	{
		System.out.println("DONE populating lists");
		System.out.println(listone);
		System.out.println(listtwo);
	}
	System.out.println("Odd multiples of 3:");
 SP1A.intersection(listone, listtwo, IntersectList);
 System.out.println("Union of Odd numbers & multiples of 3:");
 SP1A.union(listone, listtwo, UnionList);
 System.out.println("Odd numbers MINUS multiples of 3:");
 SP1A.difference(listone, listtwo, DifferenceList);
  
}
}
