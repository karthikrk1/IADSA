import java.util.*;
import java.io.*;
/**
 * Program to check if the given graph is Eulerian. 
 * @author karthikrk
 *
 */
public class CheckEulerian {
	/**
     * Utility function to run DFS on the graph
     * @param g : Input graph for which the dfs should run
     * @return cno: Returns the count of connected components in the graph.
     */
    static int dfsUtil(Graph G){
    	for(Vertex u : G){
    		u.seen = false;
    		u.parent=null;
    	}
    	int cno=0; // Initializing count variable to check for connected components
    	for(Vertex u : G){
    		if(!u.seen)
    			dfsVisit(u, ++cno);
    	}
    	return cno;
    }
    
    /**
     * Method : dfsVisit 
     * @param u: Vertex for which the DFS checks the adjacency list.
     * @param cno: Count variable to keep the number of connected components.
     */
    static void dfsVisit(Vertex u, int cno){
    	u.seen = true;
    	u.cno=cno;
    	for(Edge e: u.Adj){
    		Vertex v=e.otherEnd(u);
    		if(!v.seen){
    			v.parent=u;
    			dfsVisit(v,cno);
    		}
    	}
    }
    
    /**
     * Method testEulerian to check if the given graph is Eulerian or not.
     * A Eulerian graph is connected and the degree of each vertes is even.
     * They have a cycle, that goes through every edge of graph only once.
     * A connected graph with exactly two vertices of odd degree has Eulerian Path.
     * 
     * @param g: Input graph which is to be tested for satisfying Eulerian conditions
     */
    static void testEulerian(Graph g){
    	Vertex v1=null,v2=null;
    	int conn = dfsUtil(g);
    	if(conn > 1){
    		System.out.println("The graph is not connected.");
    		return;
    	}
    	
    	int odd=0;
    	
    	for(Vertex u: g){
    		if(u.Adj.size()%2!=0){
    			odd++;
    			if(odd==1){
    				v1=u;
    			}
    			else if(odd==2){
    				v2=u;
    			}		
    		}
    	}
    	if(odd==0){
    		System.out.println("Graph is Eulerian.");
    	}
    	else if(odd==2){
    		System.out.println("Graph has an Euler path between vertices\t"+v1+" and "+v2+".");
    	}
    	else{
    		System.out.println("Graph is not Eulerian. It has"+odd+"vertices of odd degree.");
    	}
    
    }
    public static void main(String[] args) throws FileNotFoundException {
    	Scanner in;
    	if(args.length>0){
    		File inputFile=new File(args[0]);
    		in = new Scanner(inputFile);
    	}
    	else {
    		in = new Scanner(System.in);
    	}
    	//readGraph method to read the input graph from File or console.
    	Graph g = Graph.readGraph(in,false);
    	testEulerian(g);
    }
}
