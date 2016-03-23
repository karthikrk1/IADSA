import java.util.*;
public class MergeSort {
	/**
	 * Method to merge the two arrays
	 * @param A
	 * 			: Input - Left array of elements
	 * @param B
	 * 			: Input - Right array of elements
	 * 
	 * @param p
	 * 			: Input - left limit
	 * @param q
	 * 			: Input - middle element
	 * @param r
	 * 			: Input - right limit
	 */
	public static<T extends Comparable<T>> void merge(T A[], T B[], int p, int q, int r){
		int i=p, j=q+1;
		for(int k=p; k<r; k++){
			if(j>r || (i<=q && (A[i].compareTo(A[j]))<=0)){
				B[k++]=A[i];
			}
			else{
				B[k++]=A[j];
			}
		}
	}
	/**
	 * Method to do insertion sort
	 * @param A
	 * 			: Input - Unsorted array
	 * @param p
	 * 			: Input - lower limit
	 * @param r
	 * 			: Input - upper limit
	 */
	public static<T extends Comparable<T>> void insertionSort(T A[], int p, int r){
		T temp;
        for (int i = p; i < r; i++) {
            for(int j = i ; j > 0 ; j--){
                if(A[j].compareTo(A[j-1])<0){
                    temp = A[j];
                    A[j] = A[j-1];
                    A[j-1] = temp;
                }
            }
        }
		
	}
	/**
	 * Method to perform the merge sort
	 * @param A
	 * 			: Unsorted array of elements
	 * @param temp
	 * 			: Temp array to store the elements
	 * @param p
	 * 			: Lower Limit
	 * @param r
	 * 			: Upper Limit
	 * @return	
	 * 			: Returns 0 or 1 - to inform where the elements of array are - either in A or in Temp
	 */
	public static<T extends Comparable<T>> int mSort(T A[], T temp[], int p, int r){
		
		int n=r-p+1;
		int q,t1,t2;
		int threshold=11;
		if(n<=threshold){
			insertionSort(A,p,r);
			return 0;
		}
		else{
			q=(r+p)/2;
			t1=mSort(A,temp,p,q);
			t2=mSort(A,temp,q+1,r);
		}
		if(t1==0){	
			merge(A,temp,p,q,r);
			return 1;
		}
		else{
			merge(temp,A,p,q,r);
			return 0;
		}
	}
	/**
	 * Utility method to print the elements of the array. If array size > 20 . prints only first 20 elements
	 * @param A
	 * 			: Array to be printed
	 */
	public static<T> void  print(T A[]){
		int a = Math.min(A.length, 20);
		for(int i=0; i<a; i++ )
			System.out.print(A[i]+ " ");
		System.out.println();
	}
	
	/**
	 * Method to create a non-distinct array
	 * @param A
	 * 			: Array to be filled	
	 * 
	 * @param x
	 * 			: Value to be filled
	 * @param n
	 * 			: Size of array
	 */
	public static<T> void sameIn(T[]A, T x,int n){
		for(int i=0;i<n;i++){
			A[i]=x;
		}
	}
}