/**
 * Driver program for Long Project 1 - Level 1
 * @author Group-05
 *
 */
public class G05_BigArithmeticDriver {

	public static void main(String[] args) {
		G05_BigArithmetic a = new G05_BigArithmetic("200");
		a.printList();
		G05_BigArithmetic b = new G05_BigArithmetic(50L);
		G05_BigArithmetic c = G05_BigArithmetic.add(a,b);
		G05_BigArithmetic d = G05_BigArithmetic.subtract(c,a);
		G05_BigArithmetic e = G05_BigArithmetic.product(a,a);
		G05_BigArithmetic zero = new G05_BigArithmetic(0L);
		G05_BigArithmetic f = G05_BigArithmetic.product(a, zero);
		G05_BigArithmetic two = new G05_BigArithmetic(2L);
		G05_BigArithmetic g = G05_BigArithmetic.power(two,10L);
		System.out.println("a="+a);
		System.out.println("b="+b);
		System.out.println("c=a+b="+c);
		System.out.println("a+b-a="+d);
		System.out.println("a*a="+e);
		System.out.println("a*0="+f);
		System.out.println("2^10="+g);
		System.out.println("Internal Representation:");
		g.printList();	
	}
}
/*Sample Output
 * 100: 0 2
 *a=200
 *b=50
 *c=a+b=250
 *a+b-a=50
 *a*a=40000
 *a*0=0
 *2^10=1024
 *Internal Representation:
 *100: 24 10
*/
