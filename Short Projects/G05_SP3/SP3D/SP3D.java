import java.util.*;

public class SP3D<T extends Comparable<? super T>> {

static int kElements[] = new int[3];
static	int left,pivot,right,k;
static	int pivotElement;
static int size = 0;
	
	
public static<T extends Comparable<? super T>> int Partition(T[] A, int px, int rx)
{
	 left = px; right =rx;
     T tmp;
   Random r = new Random();
   int pivotind = left + r.nextInt(right-left+1);
     T pivot = A[pivotind];

     while (true) {
           
           do  {  if(left<=right) left++; } while (A[left].compareTo(pivot)<0);
           
           do  {  if(left<=right)  right--; }while (A[right].compareTo(pivot)>0);
           if (left <= right) {
                 tmp = A[left];
                 A[left] = A[right];
                 A[right] = tmp;
                 left++;
                 right--;
           }
      if(left>right)     return pivotind;
     }
  
}

public static<T extends Comparable<? super T>> int select(T[] A, int px, int rx, int kx)
{   
	pivot =  Partition(A,px,rx);
	//if(rx-pivot+1<kx){
	if(rx-pivot>=kx)
		return select(A,pivot+1,rx,kx);
	else if(rx-pivot+1==kx)
	{   for (int i = pivot; i<=rx; i++)
		{
		System.out.println("why muttai "+A[i]);
	    
	    }
		return rx-pivot+1;
	}
	else
	{   
	    for(int i=pivot;i<=rx;i++)
	    {System.out.println("inga muttai? "+A[i]);
}
		return select(A,px,pivot-1,kx-(rx-pivot+1));
}
	//}else return rx-pivot+1;
}
	
public static void main (String args[])
{   
	System.out.println("Enter number of elements in the array");
	Scanner in = new Scanner(System.in);
	int n = in.nextInt();
	Integer A[] = new Integer[n];
	for (int i=0; i<n;i++)
		A[i] = in.nextInt();
	int q = select(A, 0, n-1, 3);
	//if(q==3)
	for (int i=q; i<n;i++){	
		System.out.println(A[i]);}
	//else
	//	for(int i=n-4; i<n;i++)
	//		System.out.println(A[i]);
	in.close();
}
}