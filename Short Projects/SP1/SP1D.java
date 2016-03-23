import java.util.*;
import java.io.*;
/** 
 * Program to simulate a recursive algorithm using Stacks
 * @author nandita
 *
 */
public class SP1D
{
    
    //prints the array
    static void printArr(int input[], int length)
    {
    	int i;
    	if(length > 20)
    		length = 20;
    	for(i=0;i<length;i++)
            System.out.print(input[i] + "  "); 
    }	
    
	//swaps the two numbers
    static void swap(int[] arr, int down, int up)
    {
    	int temp;
        temp=arr[down]; 
        arr[down]=arr[up];
        arr[up]=temp;
    }
    //Partitions the array
    static int partition(int arr[], int lower, int higher)
    {
    	//@param arr[] the input array
    	//@param lower index of the array
    	//@param higher index of the array
        int a, down, up;
        a=arr[lower];
        up=higher;
        down=lower;
        while(down<up)
        {
            while(arr[down]<=a && down<up)
                down++;       
            while(arr[up]>a)
                up--;         

            if(down<up)
            	swap(arr,down,up);
        }
        arr[lower]=arr[up];
        arr[up]=a;
        return (up);
    }

    static void quickSort(int[] arr, int lower, int higher)
    {
        
    	Stack<Integer> stack = new Stack<Integer>();
        stack.push(lower);
        stack.push(higher);
        while (!stack.empty())
        {
            higher = stack.pop();
            lower = stack.pop();
            if (higher <= lower) 
            	continue;
            int i = partition(arr, lower, higher);
            if (i-lower > higher-i)
            {
                stack.push(lower);
                stack.push(i-1);
            }
            stack.push(i+1);
            stack.push(higher);
            if (higher-i >= i-lower)
            {
                stack.push(lower);
                stack.push(i-1);
            }
        }
    }

    public static void main(String args[ ])
    {
        int i,n,j=0;
        int input[]=new int[10000000];
        Scanner in = new Scanner(System.in);
        System.out.println("Enter the limit");
        n=in.nextInt();
        for(i=n;i>0;i--)
            {
        	input[j] = i;
            j++;
            }
        //print the array before sorting
        System.out.println("\n The Unsorted List is :");
        printArr(input,n);
        quickSort(input,0,n-1);
        //print the array after sorting
        System.out.println("\n The Sorted List is :");
        printArr(input, n);
    }
}    
