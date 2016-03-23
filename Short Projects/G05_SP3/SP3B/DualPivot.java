import java.util.*;
/**
 * Program to implement the Dual Pivot Quick Sort Algorithm 
 * @author group-05
 *
 */
public class DualPivot{
	static int q1, q2;
	/**
	 * Method to generate random integer pivots
	 * @param p
	 * 			: Input - lower limit 
	 * @param r
	 * 			: Input - upper limit
	 * @param same
	 * 			: Input - to check if the two pivots are not same
	 * @return
	 * 			: Output - return a random pivot
	 */
	public static int randX(int p, int r, int same) {
	    Random rand = new Random();
	    
	    int randnum = rand.nextInt((r-p+1)) + p;
	    if(randnum==same){
	    	randnum=randX(p,r,same);
	    }
	    	return randnum;
	}
	/**
	 * Method to print the elements of array. Prints first 20 if array size is more than 20
	 * @param A
	 * 			: Input - Array of elements
	 */
	public static<T >void  print(T A[]){
		int a = Math.min(A.length, 20);
		for(int i=0; i<a; i++ )
			System.out.print(A[i] + " ");
		System.out.println();
	}
	/**
	 * Method to swap two elements in the array
	 * @param A
	 * 			: Input - Array
	 * @param a
	 * 			: Input - index one 
	 * @param b
	 * 			: Input - index two
	 */
	public static <T> void swap(T A[], int a, int b){
		T temp;
		temp = A[a];
		A[a] = A[b];
		A[b] = temp;
	}
	/**
	 * Method to perform quick sort
	 * @param A
	 * 			: Input - Unsorted array of elements
	 * @param p
	 * 			: Input - lower limit of array
	 * @param r
	 * 			: Input - upper limit of array
	 */
	public static<T extends Comparable<T>> void qSort(T A[], int p, int r){
		
		if(r-p+1 > 2){
			
			int q[] = dualPivotPartition(A,p,r);
			qSort(A,p,q[0]);
			if(q[0] != q[1])
				qSort(A,q[0],q[1]);
			qSort(A,q[1],r);
		}		
		else if(p<r){
			if(A[p].compareTo(A[r])>=0){
				swap(A,p,r);
			}
		}
	}
	
	/**
	 * Method to find the dual pivot partition to perform the quick sort
	 * @param A
	 * 			: Input - Unsorted array of elements
	 * @param p	
	 * 			: Input - lower limit of array
	 * @param r
	 * 			: Input - upper limit of array
	 * @return
	 * 			: Returns an array containing the two pivot elements
	 */
	static<T extends Comparable<T>> int[] dualPivotPartition(T A[], int p, int r){
	   int r2=0;
	   T temp, x1, x2 ;
	   int r1=randX(p,r,r2);
	   r2=randX(p,r,r1);
	   if(r1>r2){
		   swap(A,p,r2);
		   swap(A,r,r1);
	   }
	   else{
		   swap(A,p,r1);
		   swap(A,r,r2);
	   }
	   // Choose two elements and move to end of array
	   x1=A[p];
	   x2=A[r];
	   /*Loop Invariant:
	    * x1=p;
	    * x2=r;
	    * A[p..l-1] = Elements < x1;
	    * A[l..i] = Elements between x1 and x2;
	    * A[i..j] = Unprocessed Elements
	    * A[j..r-1] = Elements greater than x2;	   
	   */
	   int i=p+1, l=p+1, j=r-1;
	   int unproc = r-p-1;
	   while(unproc>0){
		   while(A[i].compareTo(x2)<0 && unproc>0){
			   if(x1.compareTo(A[i])<0){
				   i++;
				   unproc--;
			   }
			   else{
				   swap(A,l,i);
				   l++;
				   i++;
				   unproc--; 
			   }
		   }
		   if(unproc<=0)
			   break;
		   while(x2.compareTo(A[j])<0 && unproc>0){
			   j--;
			   unproc--;
		   }
		   if(unproc<=0)
			   break;
		   if(A[j].compareTo(x1)<0){
			   if(l!=i){
				   temp=A[l];
				   A[l]=A[j];
				   A[j]=A[i];
				   A[i]=temp;
			   }
			   else
				   swap(A,i,j);
			   l++;   
		   }
		   else{
			   swap(A,i,j);
		   }
		   i++;
		   j--;
		   unproc-=2;
	   }
	   swap(A,p,l-1);
	   swap(A,j+1,r);
	   return new int[] {l-1,j+1};
	}
	
	/**
	 * Utility function to fill the array with same elements.
	 * @param A
	 * 			: Input - Array of elements A
	 * @param x
	 * 			: Input - X - to be filled in the array
	 * @param n
	 * 			: Input - Corresponds to size of array
	 */
	public static<T> void sameIn(T[]A, T x, Integer n){
		for(int i=0;i<n;i++){
			A[i]=x;
		}
	}
}



