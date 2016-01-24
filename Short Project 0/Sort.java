import java.util.*;

/**
 *The Sort class is a simple implementation of an application that compares the performance of two sorting algorithms namely 
 *Merge Sort and Heap Sort through a Priority Queue.
 * 
 * @author karthikrk
 */

public class Sort <T extends Comparable<? super T>>{

private static int phase = 0;
private static long startTime, endTime, elapsedTime;

/**A procedure to calculate the execution time of the sort algorithms.
 * 
 */
public static void timer()
{
	if(phase==0){
	startTime = System.currentTimeMillis();
	phase=1;
}
	else {
		endTime=System.currentTimeMillis();
		elapsedTime =  endTime - startTime;
		System.out.println("Time"+ elapsedTime + "msec.");
		memory();
		phase = 0;
	}
}

/**A recursive implementation of the mergeSort algorithm
 * 
 * @param A - input array - Precondition: unsorted array
 * @param lowerLimit - Lower index value of the array 
 * @param upperLimit - Upper limit of the index value of the array
 */
public void mergeSort(T[] A, int lowerLimit, int upperLimit)
{
	if (lowerLimit < upperLimit) {
		int middle = (lowerLimit + upperLimit)/ 2;
		/* Divide phase*/
		mergeSort(A,lowerLimit, middle);
		mergeSort(A,middle+1,upperLimit);
		/* Conquer phase*/
		merge(A,lowerLimit,middle,upperLimit);
	}
}

/**A merge routine that merges two sorted sub arrays.
 * 
 * @param A - input unsorted array, that is sorted in the end of the method execution
 * @param lowerLimit - lower index value of array
 * @param middle - middle value of the index of the array
 * @param upperLimit - upper limit index value of array
 */
public void merge(T[] A, int lowerLimit, int middle, int upperLimit){
	int i = lowerLimit;
	int j = middle + 1;
	int k = 0;
	T temp[] = (T[]) new Comparable[upperLimit-lowerLimit+1]; // Creates a temporary array to store the intermediate values.
	while(i <= middle && j <= upperLimit) {
		if (A[i].compareTo(A[j])<=0)
			temp[k++] = A[i++];
		else
			temp[k++] = A[j++];
		
	}
	
		while(i <= middle)
			temp[k++] = A[i++];
	
	
		while(j <= upperLimit)
			temp[k++] = A[j++];
		
	/* The sorted array is stored back into the original array */
	for(k=0;k<temp.length;k++)
	{
		A[k+lowerLimit] = temp[k];
	}
	
}

/**Priority Queue implementation of the Heap Sort.
 * 
 * @param A - input array - Precondition: unsorted array
 */
public void priQueue(T[] A){
	List<T> inputList = new ArrayList<T>();
	for(int i=0;i<A.length;i++){
		inputList.add(A[i]);
	}
	PriorityQueue<T> pq = new PriorityQueue<>(inputList);
	int arrayIndex=0;
	while(!pq.isEmpty()){
		A[arrayIndex++]=pq.remove();
	}
}

/**A method to calculate the memory used for the execution of the algorithms
 * 
 */
public static void memory() {
    long memAvailable = Runtime.getRuntime().totalMemory();
    long memUsed = memAvailable - Runtime.getRuntime().freeMemory();
    System.out.println("Memory: " + memUsed/1000000 + " MB / " + memAvailable/1000000 + " MB.");
}

/**A method to print few values of the array. Prints first 20 elements if array size is greater than 20 
 * or all the elements if array size is less than 20
 * 
 * @param A - Input Array 
 */
static <T> void printFew(T[] A)
{
	int a = Math.min(A.length, 20);
	for(int i=0;i<a;i++)
	{
		System.out.println(A[i] + " ");
	}
	System.out.println();
}

public static void main(String args[]){

	Scanner in = new Scanner(System.in);
	System.out.println("Enter the input value");
	int n = in.nextInt(); 
	Sort<Integer> ms = new Sort<Integer>();
	Integer A[] = new Integer[n]; // Creates an Integer array of size n - user defined input
	/* The array is populated with values in reverse */
	for(int i=0;i<n;i++)
	{
		A[i] = new Integer(n-i);
	}
	/* A switch statement to choose which algorithm to perform */
	System.out.println("Comparison of sorting algorithms:");
	System.out.println("1. Merge Sort");
	System.out.println("2. Heap Sort using Priority Queue");
	System.out.println("3.Exit");
	System.out.println("Enter your choice");
	int choice;
	choice = in.nextInt();
	switch(choice) {
	case 1:
		System.out.println("Merge Sort");
		System.out.println("Unsorted Array:");
		printFew(A);
		System.out.println("Running Merge Functions");
		timer();
		ms.mergeSort(A,0,n-1);
		timer();
		System.out.println("Sorted Array:");
		printFew(A);
		break;
	case 2:
		System.out.println("Heap Sort using Priority Queue");
		System.out.println("Unsorted Array:");
		printFew(A);
		System.out.println("Running Priority Queue functions");
		timer();
		ms.priQueue(A);
		timer();
		System.out.println("Sorted Array:");
		printFew(A);
		break;
	case 3:
		System.exit(0);
	default:
		System.out.println("Enter valid input");
	}
	in.close();
}
}
