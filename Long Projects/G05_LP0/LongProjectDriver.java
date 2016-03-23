import java.io.*;
import java.util.*;
/**Long Project 0 
 * Program to implement the Hierholzer's algorithm. 
 * This program finds an Euler Tour or Euler Path if the Graph is Eulerian. 
 * 
 * @authors @karthikrk, @badhri, @indhu, @nandita
 *
 */
public class LongProjectDriver {
	
	public static void main(String[] args) throws FileNotFoundException{
		Scanner in;
		Timer timer=new Timer(); 
		// If exists, Read input from a file
		if(args.length>0){
			File input = new File(args[0]);
			in=new Scanner(input);
		}
		// Else input read from console.
		else {
			in = new Scanner(System.in);
		}
		Graph<Vertex> g = Graph.readGraph(in, false);
		List<Edge> tour = new LinkedList<Edge>();
		boolean isTour=false; 
		Vertex start = g.verts.get(1); // start Vertex of the tour
		int Eulerian = Graph.testEulerian(g); //tests if the Graph is Eulerian or not
		/*Designed as an finite state machine. 
		 * If 0 - Not and Euler Graph
		 * If 1 - There exists an Euler Path between two vertices
		 * If 2 - There is an Euler Tour and this program finds it.*/
		if(Eulerian==0){
			System.out.println("Graph is not Eulerian");
		}
		else if(Eulerian==1){
			System.out.println("Euler path exists between the above vertices");
		}
		else if(Eulerian==2){ 
			timer.start(); //start the timer before the findEulerTour() method.
			tour = Graph.findEulerTour(g);
			isTour = Graph.verifyTour(g, tour, start);
			timer.end(); // Timer is stopped after verifyTour() 
			if (isTour){ // If an Euler tour exists then print the tour
				Graph.printTour(tour);
				System.out.println(timer); //print Timer statistics 
			}
			else{ //Reject the tour based on the output of verifyTour
				System.out.println("Verification failed"); 
				System.out.println(timer);
			}
		}	
	}
}

/* Sample Input 1
 * /Users/karthikrk/Desktop/Courses/IADSA/lp0-data/none/lp0-none2.txt
 * 
 * Sample Output 1
 * The graph is not connected.
 * 
 * Sample Input 2
 * /Users/karthikrk/Desktop/Courses/IADSA/lp0-data/path/lp0-s1-ck.txt
 * 
 * Sample Output 2
 * 42 and 50
 * Euler path exists between the above vertices
 * 
 * Sample Input 3
 * /Users/karthikrk/Desktop/Courses/IADSA/simpleIO2.txt
 * 
 * Sample Output 3
 * 
 *Size 6
 *01
 *4
 *4
 *4
 *4
 *(1,2)
 *(2,3)
 *(3,4)
 *(4,5)
 *(5,3)
 *(3,1)
 *Time: 1 msec.
 *Memory: 2 MB / 128 MB.
 *
 */
