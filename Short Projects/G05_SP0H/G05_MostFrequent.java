import java.util.*;
import java.util.Map.Entry;
/**
 * Program to find the most frequent element in an unsorted array of integers.
 * 
 * @author group 05 - karthikrk, nandita, indhumathi, badhrinath
 *
 */
public class G05_MostFrequent {
	/**
	 * Method to find the most frequent element by using QuickSort (through the Arrays.sort() method).
	 * 
	 * @param arr
	 * 				: Input unsorted array of integers.
	 * @return
	 * 				: Returns the most frequent element in the array.
	 */
	public static int mostFrequentUsingSort(int[] arr){
		Arrays.sort(arr);
		int[] count = new int[arr.length];
		int cnt=0;
		for(int i=0;i<arr.length;i++){
			cnt++;
			if(i==arr.length-1||arr[i]!=arr[i+1]){
				count[i]=cnt;
				cnt=0;
			}
		}
		int maxFrequent = count[0];
		for(int i=1;i<count.length;i++){
			if(count[i]>maxFrequent){
				maxFrequent=count[i];
			}
		}
		int index = find(count, maxFrequent);
		return arr[index];
	}
	
	/**
	 * Method to find the most frequent element by using Hashing (Java's HashMap).
	 * 
	 * @param arr
	 * 				: Input unsorted array of integers.
	 * 
	 * @return
	 * 				: Returns the most frequent element.
	 */
	public static int mostFrequentUsingHash(int[] arr){
		HashMap<Integer,Integer> hm = new HashMap<>();
		for(int i=0;i<arr.length;i++){
			int number = arr[i];
			/*If element is present in hash map, increment count by 1 and put it back
			 * Else add the element with value 1.
			 * 
			 */
			if(hm.containsKey(number)){
				int val = hm.get(number);
				hm.put(number, val+1);
			}
			else{
				hm.put(number,1);
			}
		}
		//Iterate through the hash map
		Iterator<?> hmIter = hm.entrySet().iterator();
		int maxVal=0, maxKey=0;
		while(hmIter.hasNext()){
			Entry<Integer,Integer> entry = (Entry<Integer,Integer>)hmIter.next();
			if(entry.getValue()>maxVal){
				maxVal=entry.getValue();
				maxKey=entry.getKey();
			}
		}
		return maxKey;
	}
	
	/**
	 * Helper function to find the index of the most frequent element in the array.
	 * @param arr
	 * 				: Input sorted array of integers.
	 * @param value
	 * 				: The most frequent element in the array.
	 * @return
	 * 				: Returns the index of the most frequent element in the array.
	 */
	public static int find(int[] arr, int value){
		for(int i=0;i<arr.length;i++){
			if(arr[i]==value){
				return i;
			}
		}
		return -1;
	}
	
	public static void main(String[] args){
		Scanner in=new Scanner(System.in);
		G05_Timer timer=new G05_Timer();
		Random r = new Random();
		System.out.println("Enter the array size:");
		int k=in.nextInt();
		int[] arr=new int[k];
		for(int i=0;i<k;i++){
			arr[i]=r.nextInt(100);
		}
		
		System.out.println("Most Frequent Element:");
		System.out.println("1. Using O(nlogn) Sorting Algorithm:");
		System.out.println("2. Using O(n) HashMap solution:");
		System.out.println("3. Exit");
		System.out.println("Enter your choice:");
		int choice = in.nextInt();
		switch(choice){
		case 1:
			System.out.println("Using sort");
			timer.start();
			int mostFrequentSort = mostFrequentUsingSort(arr);
			timer.end();
			System.out.println("The most frequent element is:"+mostFrequentSort);
			System.out.println(timer);
			break;
		case 2:
			System.out.println("Using HashMap");
			timer.start();
			int mostFrequentHash = mostFrequentUsingHash(arr);
			timer.end();
			System.out.println("The most frequent element is:"+mostFrequentHash);
			System.out.println(timer);
			break;
		case 3:
			System.exit(0);
		default:
			System.out.println("Not a valid choice:");
			break;
		}
		in.close();
	}

}

/*
 * SAMPLE IO-1
 * 
 * Enter the array size:
 * 300
 * 
 * Most Frequent Element:
 * 1. Using O(nlogn) Sorting Algorithm:
 * 2. Using O(n) HashMap solution:
 * 3. Exit
 * 
 * Enter your choice:
 * 1
 * Using sort
 * The most frequent element is:24
 * Time: 0 msec.
 * Memory: 4 MB / 128 MB.
 * 
 * SAMPLE IO-2
 * 
 * Enter the array size:
 * 300
 * 
 * Most Frequent Element:
 * 1. Using O(nlogn) Sorting Algorithm:
 * 2. Using O(n) HashMap solution:
 * 3. Exit
 * 
 * Enter your choice:
 * 2
 * Using HashMap
 * The most frequent element is:24
 * Time: 1 msec.
 * Memory: 4 MB / 128 MB.
 */
