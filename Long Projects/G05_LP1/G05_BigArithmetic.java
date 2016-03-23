import java.util.*;
/**
 * A project to implement arithmetic with large integers.
 * 
 * @author Group-05
 *
 */
public class G05_BigArithmetic {
	final static int B = 100; // Base value is set as 100. 
	final static Long TEN=10L; // A long representation of 10
	final static Long ZERO=0L; // A long representation of 0
	final static long BASE = (long) B; // A long representation of the Base "B".
	int effectiveSize; // The effective size of the linked list after leading zeros are removed.
	List<Long> number = new LinkedList<>(); // Linked List for storing the numbers.
	/**
	 * Default Constructor 
	 */
	public G05_BigArithmetic(){} 
	/**
	 * Constructor to take a String input and assign to the number.
	 * @param s 
	 * 			: Input String s, which is a String of numbers.
	 */
	public G05_BigArithmetic(String s){
		G05_BigArithmetic baseBA = new G05_BigArithmetic(); //A temp linked list which contains the number in the required base
		G05_BigArithmetic tenBA = new G05_BigArithmetic(TEN); // A BigArithmetic representation of 10.
		/*
		 * Convert the string to character array
		 * Get the integer value for each
		 * Multiply temp list with "TEN"
		 * Add the digit to the product 
		 * Store it in the temp list
		 * Assign the list to the current object
		 */
		for(Character c:s.toCharArray()){ 
			int val = Character.getNumericValue(c);
			baseBA=add(product(baseBA,tenBA), new G05_BigArithmetic((long) val));
		}
		this.number=baseBA.number;
	} 
	
	/**
	 * Constructor for taking a long number as input and storing in the Linked List
	 * 
	 * @param num
	 * 			: Input Long integer.
	 */
	public G05_BigArithmetic(Long num){
		if(num==0){
			number.add(0L);
			effectiveSize=0;
		}
		while(num>0){
			number.add(num%B);
			num/=B;
		}
	}
	

	/**Method to return the list as a String object with decimal places.
	 * 
	 */
	public String toString(){
			return this.convertToBase10();
	}
	
	/**
	 * Utility function to convert from Base B to Base 10.
	 * @return
	 * 			Returns a string of base 10 representation of output
	 */
	public String convertToBase10(){
		int size=this.number.size();
		Long digit=ZERO;
		Long y=1L;
		for(int i=0;i<size;i++){
			digit=digit+(this.number.get(i)*y);
			y=y*BASE;
		}
		return digit.toString();
	}
	
	/** Method to add two large integers
	 *  
	 * @param a
	 * 			: Input a - an object of the BigArithmetic Class.
	 * @param b
	 * 			: Input b -  Second object of the BigArithmetic Class.
	 * @return
	 * 			: An object of the type BigArithmetic, that contains the result of the addition.
	 */
	public static G05_BigArithmetic add(G05_BigArithmetic a, G05_BigArithmetic b){
		Iterator<Long> ita = a.number.iterator();
		Iterator<Long> itb = b.number.iterator();
		G05_BigArithmetic result = new G05_BigArithmetic();
		Long sum=0L,carry=0L, firstElement=nextElement(ita), secondElement=nextElement(itb);
		while(firstElement!=null && secondElement!=null){
			sum=firstElement+secondElement+carry;
			result.number.add(sum%B);
			carry=sum/B;
			firstElement=nextElement(ita);
			secondElement=nextElement(itb);
		}
		while(firstElement!=null){
			sum=firstElement+carry;
			result.number.add(sum%B);
			carry=sum/B;
			firstElement=nextElement(ita);
		}
		while(secondElement!=null){
			sum=secondElement+carry;
			result.number.add(sum%B);
			carry=sum/B;
			secondElement=nextElement(ita);
		}
		if(carry>0L) result.number.add(carry);
		
		return result;
	}
	
	/**
	 * Method to subtract two large numbers
	 * @param a
	 * 			: Input a - an object of the BigArithmetic Class.
	 * @param b
	 * 			: Input b -  Second object of the BigArithmetic Class.
	 * @return
	 * 			: An object of the type BigArithmetic, that contains the result of the subtraction.
	 */	
	public static G05_BigArithmetic subtract(G05_BigArithmetic a, G05_BigArithmetic b){
		boolean sign=false,bo=false; // Two boolean flags to represent sign and borrow
		/*
		 * If second number is bigger than first, swap the two and set sign to true
		 * We will return zero if sign is still set to true - Level 1 - No negative nos.
		 */
		if(a.number.size()<b.number.size()){
			G05_BigArithmetic t=b;
			b=a;
			a=t;
			sign=true;
		}
		/*
		 * If the size of the two numbers are equal, then iterate to see if second is bigger than first.
		 * If yes, swap and set sign as true like the previous case.
		 * Else, we are good to continue with the subtraction, so break out of the loop.
		 */
		else if(a.number.size()==b.number.size()){
			for(int i=a.number.size()-1;i>=0;i--){
				if(a.number.get(i)<b.number.get(i)){
					sign=true;
					G05_BigArithmetic t=b;
					b=a;
					a=t;
					continue;
				}
				else
					break;
			}
		}
		//Iterators for looping through the linked list.
		Iterator<Long> ita = a.number.iterator();
		Iterator<Long> itb = b.number.iterator();
		G05_BigArithmetic result = new G05_BigArithmetic();
		Long diff, firstElement=nextElement(ita), secondElement = nextElement(itb);
		while(secondElement!=null){
			if(bo){
				if(firstElement==0){
					firstElement=9L;
					bo=true;
				}
				else{
					firstElement=firstElement-1;
					bo=false;
				}
			}
			if(firstElement<secondElement){
				firstElement+=10;
				bo=true;
			}
			diff=firstElement-secondElement;
			result.number.add(diff%B);
			firstElement=nextElement(ita);
			secondElement=nextElement(itb);
		}
		while(firstElement!=null){
			if(bo){
				if(firstElement==0){
					firstElement=9L;
					bo=true;
				}
				else{
					firstElement=firstElement-1;
					bo=false;
				}
			}
			result.number.add(firstElement);
			firstElement=nextElement(ita);
		}
		//If sign, result is a negative number - so return zero
		if(sign){
			return new G05_BigArithmetic(0L);
		}
		// Remove the most significant zeros from the list.
		result.removeMSZeros();
		//If after removing the zeros the size is zero, we return the answer as zero
		if(result.effectiveSize==0){
			return new G05_BigArithmetic(0L);
		}
		return result;
	}
	
	/**
	 * DAC Algorithm to multiply two long numbers
	 * @param a
	 * 			: Input object of BigArithmetic class
	 * @param b
	 * 			: Input object of BigArithmetic class
	 * @return
	 * 			: Returns an object of the BigArithmetic class which is a product of the two inputs
	 */
	public static G05_BigArithmetic product(G05_BigArithmetic a, G05_BigArithmetic b){
		//If any of the two lists is null return null
		if(a==null||b==null){
			return null;
		}
		//We remove the MS zeros to get the effective size of the two numbers.
		G05_BigArithmetic x=a.removeMSZeros();
		G05_BigArithmetic y=b.removeMSZeros();
		/*If the size is zero, then the list is 0, so return zero - Multiply by zero */
		if(x.effectiveSize==0||y.effectiveSize==0){
			return new G05_BigArithmetic(0L);
		}
		//Call the productBA for DAC
		G05_BigArithmetic c = productBA(x,y);
		return c;
	}
	
	/**
	 * Method to multiply the two Big Arithmetic objects
	 * @param a
	 * 			: Input - object of class BigArithmetic
	 * @param b
	 * 			: Input - object of class BigArithmetic
	 * @return
	 * 			: Returns the product of the two inputs.
	 */
	public static G05_BigArithmetic productBA(G05_BigArithmetic a, G05_BigArithmetic b){
		int maxSize = Math.max(a.effectiveSize, b.effectiveSize); //Get the max size of the two lists
		// If max size is one - normal single digit multiplication
		if(maxSize<=1){
			return new G05_BigArithmetic(a.number.get(0)*b.number.get(0));
		}
		//If any of the lists is small - pad zeros to make the size equal
		if(a.number.size()<maxSize){
			a.addZero(maxSize-a.number.size());
		}
		else if(b.number.size()<maxSize){
			b.addZero(maxSize-b.number.size());
		}
		//Get the middle of the two lists
		int mid = (maxSize + 1)/2;
		// Find a1, a2, b1 and b2
		G05_BigArithmetic a1 = a.split(0,mid);
		G05_BigArithmetic a2 = a.split(mid,maxSize);
		G05_BigArithmetic b1 = b.split(0,mid);
		G05_BigArithmetic b2 = b.split(mid,maxSize);
		G05_BigArithmetic temp1=productBA(a1,b1); //a1*b1
		G05_BigArithmetic temp2=productBA(a2,b2); // a2*b2
		G05_BigArithmetic temp12=productBA(G05_BigArithmetic.add(a1, a2),G05_BigArithmetic.add(b1, b2)); // (a1+a2)*(b1+b2)
		// [(a1+a2)*(b1+b2) - {(a1*b1) + (a2*b2)}]
		G05_BigArithmetic temp = G05_BigArithmetic.subtract(temp12, G05_BigArithmetic.add(temp1,temp2)); 
		temp12=temp;
		//Pad zeroes - similar to raising to base
		temp12=temp12.addLeastZero(mid);
		temp2=temp2.addLeastZero(mid*2);
		//Return the product while removing MS Zeros
		return G05_BigArithmetic.add(G05_BigArithmetic.add(temp2, temp12),temp1).removeMSZeros();
	}
	
	/**
	 * DAC Algorithm to compute power 
	 * @param x
	 * 			: Input - Object of BigArithmetic class
	 * @param n
	 * 			: Input - Long number
	 * @return
	 * 			: Returns an object of the BigArithmetic class which is a (x^n)
	 */
	public static G05_BigArithmetic power(G05_BigArithmetic x, long n){
		G05_BigArithmetic result=new G05_BigArithmetic();
		if (n==0){
			result.number.add(1L);
			return result;
		}
		else if (n==1) {
			return x;
			
		}
		result=power(x,n/2);
		result=product(result,result);
		if(n%2!=0){
			return product(x, result);
		}
		return result;
	}
	
	/**
	 * Method to print the internal representation of the linked list
	 */
	public void printList(){
		System.out.print(B+":");
		Iterator<Long> itNumber = number.iterator();
		Long val = nextElement(itNumber);
		while(val!=null){
			System.out.print(" "+val);
			val=nextElement(itNumber);
		}
		System.out.println();
	}
	
	/**
	 * Utility function to get the next Element in the Iterator
	 * @param it
	 * 			: Input iterator of Long type
	 * @return
	 * 			: Return the next element if present, else returns null
	 */
	public static Long nextElement(Iterator<Long> it){
		if(it.hasNext()){
			return it.next();
		}
		else
			return null;
	}
	
	/**
	 * Utility function to add zeros at the index specified.
	 * @param index
	 * 				: Pad zeros at the end of the list till the index specified
	 */
	public void addZero(int index){
		int size=this.number.size();
		for(int i=size;i<size+index;i++){
			this.number.add(0L);
		}
	}
	
	/**
	 * Utility function to add zeros to the least significant digits of the linked list.
	 * @param index
	 * 				: Input index till where the zeros should be padded.
	 * @return
	 * 				: Returns a list of numbers with zero padded in the end.
	 */
	public G05_BigArithmetic addLeastZero(int index){
		G05_BigArithmetic temp = new G05_BigArithmetic();
		for(int i=0;i<index;i++){
			temp.number.add(0L);
		}
		for(int i=0;i<this.number.size();i++){
			temp.number.add(this.number.get(i));
		}
		return temp;
		
	}
	
	/**
	 * Utility method to split the list into two 
	 * @param left
	 * 				: Starting index for split
	 * @param right
	 * 				: Ending index for the split
	 * @return
	 * 				: Returns an object of the BigArithmetic class with the list split
	 */
	public G05_BigArithmetic split(int left, int right){
		G05_BigArithmetic temp = new G05_BigArithmetic();
		for(int i=left;i<right;i++){
			temp.number.add(this.number.get(i));
		}
		temp.effectiveSize=temp.number.size();
		return temp;
	}
	
	/**
	 * Utility method to remove the most significant zeros from the list.
	 * @return
	 * 			: Returns the list with most significant zeros removed.
	 */
	public G05_BigArithmetic removeMSZeros(){
		int size = this.number.size();
		this.effectiveSize=size;
		for(int i=size-1;i>=0;i--){
			if(this.number.get(i)==0){
				this.number.remove(i);
				effectiveSize--;
			}
			else
				break;
		}
		if(this.number.size()==0){
			return new G05_BigArithmetic(0L);
		}
		return this;
	}
	
}
