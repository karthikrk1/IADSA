import java.util.*;
import java.io.*;
public class SP3CDriver {
	public static void main(String[] args) throws FileNotFoundException{
		Scanner in;
		//Reads input from file or console 
		if(args.length>0){
			File input = new File(args[0]);
			in=new Scanner(input);
		}
		else {
			in = new Scanner(System.in);
		}
		System.out.println("Enter the number of elements in the array:");
		Integer n = in.nextInt(); // Defines size of array
		Integer[] A = new Integer[n];
		Integer[] temp = new Integer[n+1];// temp array for merge sort
		final Integer x = 50;// for checking when input is not distinct 
		Timer timer = new Timer();
		/* Designed as a switch statement for checking when array has distinct or non distinct elements*/
		System.out.println("Comparison of best versions of Quick Sort and Merge Sort:");
		System.out.println("**********************************************************");
		System.out.println("1. Comparison when input array is distinct");
		System.out.println("2. Comparison when input array is not distinct");
		System.out.println("3. Exit");
		System.out.println("Enter your option:");
		int option;
		option = in.nextInt();
		switch(option){
		case 1:
			System.out.println("Array Elements are distinct:");
			for(int i=0;i<n;i++){
				A[i] = new Integer(n-i);
			}
			// choose quick or merge sort
			System.out.println("Choose the variation you prefer:");
			System.out.println("1. Dual Pivot Quick Sort");
			System.out.println("2. Merge Sort");
			System.out.println("3.Exit");
			System.out.println("Enter your choice:");
			int choice;
			choice = in.nextInt();
			switch(choice){
			case 1:
				System.out.println("Dual Pivot Quick Sort:");
				System.out.println("Unsorted:");
				DualPivot.print(A);
				timer.start();
				DualPivot.qSort(A,0,n-1);
				timer.end();
				System.out.println("Sorted:");
				DualPivot.print(A);
				System.out.println(timer);
				break;
			case 2:
				System.out.println("Merge Sort:");
				System.out.println("Unsorted:");
				MergeSort.print(A);
				timer.start();
				if( (MergeSort.mSort(A,temp,0,n)==1)){
					for(int i=0;i<n;i++)
						A[i]=temp[i];
				}
				timer.end();
				System.out.println("Sorted:");
				MergeSort.print(A);
				System.out.println(timer);
				break;
			case 3:
				System.exit(0);
			default:
				System.out.println("Enter valid choice");
			}
			break;
		case 2:
			System.out.println("Array Elements are not distinct:");
			System.out.println("Choose the variation you prefer:");
			System.out.println("1. Dual Pivot Quick Sort");
			System.out.println("2. Merge Sort");
			System.out.println("3.Exit");
			System.out.println("Enter your choice:");
			int choice2;
			choice2 = in.nextInt();
			switch(choice2){
			case 1:
				System.out.println("Dual Pivot Quick Sort:");
				DualPivot.sameIn(A, x, n);
				System.out.println("Unsorted:");
				DualPivot.print(A);
				timer.start();
				DualPivot.qSort(A, 0, n-1);
				timer.end();
				System.out.println("Sorted:");
				DualPivot.print(A);
				System.out.println(timer);
				break;
			case 2:
				System.out.println("Merge Sort:");
				System.out.println("Unsorted:");
				MergeSort.sameIn(A, x, n);
				MergeSort.print(A);
				timer.start();
				if( (MergeSort.mSort(A,temp,0,n)==1)){
					for(int i=0;i<n;i++)
						A[i]=temp[i];
				}
				timer.end();
				System.out.println("Sorted:");
				MergeSort.print(A);
				System.out.println(timer);
				break;
			case 3:
				System.exit(0);
			default:
				System.out.println("Enter valid choice");
			}
			break;
		case 3:
			System.out.println("Good Bye!!");
			System.exit(0);
		default:
			System.out.println("Enter valid choice");
			
		}
		in.close();
	}
}

/** SAMPLE IO
Enter the number of elements in the array:
10
Comparison of best versions of Quick Sort and Merge Sort:
**********************************************************
1. Comparison when input array is distinct
2. Comparison when input array is not distinct
3. Exit
Enter your option:
1
Array Elements are distinct:
Choose the variation you prefer:
1. Dual Pivot Quick Sort
2. Merge Sort
3.Exit
Enter your choice:
1
Dual Pivot Quick Sort:
Unsorted:
10 9 8 7 6 5 4 3 2 1 
Sorted:
1 2 3 4 5 6 7 8 9 10 
Time: 1 msec.
Memory: 2 MB / 128 MB.

Enter the number of elements in the array:
10
Comparison of best versions of Quick Sort and Merge Sort:
**********************************************************
1. Comparison when input array is distinct
2. Comparison when input array is not distinct
3. Exit
Enter your option:
1
Array Elements are distinct:
Choose the variation you prefer:
1. Dual Pivot Quick Sort
2. Merge Sort
3.Exit
Enter your choice:
2
Merge Sort:
Unsorted:
10 9 8 7 6 5 4 3 2 1 
Sorted:
1 2 3 4 5 6 7 8 9 10 
Time: 1 msec.
Memory: 2 MB / 128 MB.
*/
