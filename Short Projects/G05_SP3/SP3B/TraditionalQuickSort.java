import java.util.*;
public class TraditionalQuickSort<T extends Comparable<T>> {
	//static int A[] = new int[100000];
	public static int randX(int p, int r) {
	    Random rand = new Random();
	    int randnum = rand.nextInt((r-p+1)) + p;
	    return randnum;
	}
	
	public void qSort(T A[], int p, int r){
		int q;
		if(p<r){
			q = partition(A,p,r);
			qSort(A,p,q-1);
			qSort(A,q,r);
		}
			
	}
	public void swap(T A[], int a, int b){
		T temp;
		temp = A[a];
		A[a] = A[b];
		A[b] = temp;
	}
	public int partition(T A[], int p, int r){
		int random = randX(p,r);
		T x = A[random];
		int i=p-1, j = r+1;
		while(true){
			do{
				i++;
			}while(A[i].compareTo(x)<0);
			do{
				j--;
			}while(x.compareTo(A[j])<0);
				
			if(i>=j)
				return j;
			swap(A,i,j);
			
		}
	}
	
	public void  print(T[] A,int n){
		System.out.println("Array");
		for(int i=0; i<n; i++ )
			System.out.print(A[i] + " ");
		System.out.println();
	}
	
	
	public void sameIn(T[]A, T x, int n){
		for(int i=0;i<n;i++){
			A[i]=x;
			
		}
	}
	
}