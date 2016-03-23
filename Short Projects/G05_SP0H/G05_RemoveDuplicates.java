import java.util.*;
/**
 * Program to remove duplicates from an array of objects of a class implementing hashCode and equals. 
 * Uses HashSet for finding the elements
 * @author group 05 - karthikrk, nandita, indhumathi, badhrinath
 *
 * @param <T>
 * 				: Type parameter for generic implementation
 */
public class G05_RemoveDuplicates<T> {
	/**
	 * Method to find the distinct elements in the array and move them to the front of the array - arr[0..k-1]. This also
	 * returns the number of distinct elements in the array
	 * 
	 * @param arr
	 * 				: Input unsorted array of objects of type T
	 * @return
	 * 				: Returns an integer, which corresponds to the number of distinct elements in the array
	 */
	public static<T> int findDistinct(T[] arr){
		HashSet<T> hSet = new HashSet<>();
		for(T i:arr){
			hSet.add(i);
		}
		int k=hSet.size(); // Number of distinct elements in the set
		arr=hSet.toArray(arr);
		for(int i=k;i<arr.length;i++){
			arr[k]=arr[k+1];
		}
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i]+" ");
		}
		System.out.println();
		return k;
	}
	
	public static void main(String[] args){
		//Scanner in = new Scanner(System.in);
		G05_Timer timer = new G05_Timer();
		//int k = in.nextInt();
		//Integer[] arr = new Integer[k];
		Integer[] arr={4,3,2,4,2,3,1,2,1,2,6,7,3,2,8,6,7,9};
		timer.start();
		int count=findDistinct(arr);
		timer.end();
		System.out.println("The distinct elements are:"+ count);
		System.out.println(timer);
	}

}

/* SAMPLE I/O
 * 
 * INPUT:
 * arr[]={4,3,2,4,2,3,1,2,1,2,6,7,3,2,8,6,7,9}
 * 
 * OUTPUT:
 * 1,2,3,4,6,7,8,9,1,2,6,7,3,2,8,6,7,9
 * The distinct elements are: 8
 * Time 1 msec.
 * Memory: 2 MB / 128 MB.
 */
