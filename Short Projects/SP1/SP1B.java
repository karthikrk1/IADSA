import java.util.*;
/**
 * A simple class to implement large integer addition and subtraction using linked lists. SP1 b
 * @author karthikrk
 *
 */
public class SP1B <T extends Iterator<? super T>>{

/**
 * Method to add large integers using linked lists
 * @param l1 - Input: List One
 * @param l2 - Input: List Two
 * @param out - Output List.
 * @param base - base of the number system
 */
public static void add(List<Integer> l1, List<Integer> l2, List<Integer> out, Integer base) {

	//Create iterators for the lists
	Iterator<Integer> il1 = l1.iterator();
	Iterator<Integer> il2 = l2.iterator();
	Integer sum, carry=0, listOneElement = nextElement(il1), listTwoElement = nextElement(il2);
	while(listOneElement != null || listTwoElement != null){
		sum=0;
		sum+=carry;
		if(listOneElement != null){
			sum+=listOneElement;
			listOneElement=nextElement(il1);
		}
		if(listTwoElement != null){
			sum+=listTwoElement;
			listTwoElement=nextElement(il2);
		}
		out.add(sum%base);
		carry=sum/base;
	}
	while(carry!=0){
		out.add(carry%base);
		carry/=base;
	}
	printList(out);
  }
	
/**
 * Method to subtract large integers using linked lists
 * @param l1 - Input List 1
 * @param l2 - Input List 2
 * @param out - Output List 
 * @param base - base number system
 */
public static void subtract(List<Integer> l1, List<Integer> l2, List<Integer> out, int base){
	Iterator<Integer> il1 = l1.iterator();
	Iterator<Integer> il2 = l2.iterator();
	Integer diff,borrow=0, listOneElement = nextElement(il1), listTwoElement = nextElement(il2);
	while(listOneElement !=null || listTwoElement !=null){
	diff = listOneElement - listTwoElement - borrow;
	if(diff < 0){
		diff+=10;
		borrow=1;
	}
	else {
		borrow = 0;
	}
	out.add(diff%base);
	listOneElement = nextElement(il1); listTwoElement = nextElement(il2);
}
while(listOneElement !=null){
	diff = listOneElement - borrow;
	out.add(diff%base);
	borrow = diff/base;
	listOneElement = nextElement(il1);
}
	
	printList(out);
 }
	

/**
 * Utility Method to print the list 
 * @param inputList - the list which should be printed
 */
public static<T> void printList(List<T> inputList){
	Iterator<T> iInputList = inputList.iterator();
	T x = nextElement(iInputList);
	while(x != null){
		System.out.print(x);
		x = nextElement(iInputList);
		if(x!=null)
			System.out.print("->");
	}
	System.out.println("\n");
}

/**
 * Helper method to iterate using an Iterator without eliminating last element
 * @param il - Iterator 
 * @return
 */
public static<T> T nextElement(Iterator<T> il){
	if(il.hasNext())
		return il.next();
	else
		return null;
	
}
public static void main(String[] args){

Scanner sc = new Scanner(System.in);
System.out.println("Enter the base");
int base = sc.nextInt();
System.out.println("Enter two numbers");
String str1=sc.next();
String str2=sc.next();
List<Integer> l1 = new LinkedList<Integer>();
List<Integer> l2 = new LinkedList<Integer>();
List<Integer> out = new LinkedList<Integer>();
//get the number as a string and append by digit.
for(int i=str1.length()-1;i>=0;i--){
	l1.add(str1.charAt(i) - '0');
}
for(int j=str2.length()-1; j>=0;j--){
	l2.add(str2.charAt(j) - '0');
}
System.out.println("Integer addition and Subtraction Using Linked List");
System.out.println("1. Addition");
System.out.println("2. Subtraction");
System.out.println("3.Exit");
System.out.println("Enter your choice");
int choice;
choice = sc.nextInt();
switch(choice) {
case 1:
	System.out.println("Addition");
	add(l1,l2,out,base);
	break;
case 2:
	System.out.println("Subtraction");
	subtract(l1,l2,out,base);
	break;
case 3:
	System.exit(0);
default:
	System.out.println("Not a valid choice");
	break;
}
sc.close();
}
}

