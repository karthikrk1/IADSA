import java.io.*;
import java.util.*;
public class Bipartite {
    public static void main(String[] args) throws FileNotFoundException {
	Scanner in;
	int flag=0;
	if (args.length > 0) {
	    File inputFile = new File(args[0]);
	    in = new Scanner(inputFile);
	} 
	else {
	    in = new Scanner(System.in);
	}
	Graph g = Graph.readGraph(in, false);   // read undirected graph from stream "in"
	Queue<Vertex> Q = new LinkedList<>();
	for (Vertex u : g) {
	    u.seen = false;
	    u.parent = null;
	    u.distance = Integer.MAX_VALUE;
	}
	// Run BFS on every component
	for (Vertex src : g) {
	    if (!src.seen) {
		src.distance = 0;
		Q.add(src);
		src.seen = true;

		while (!Q.isEmpty()) {
		    Vertex u = Q.remove();
		    for (Edge e : u.Adj) {
			Vertex v = e.otherEnd(u);
			if (!v.seen) {
			    v.seen = true;
			    v.parent = u;
			    v.distance = u.distance + 1;
			    Q.add(v);
			} else {
			    if (u.distance == v.distance) 		        
			    	flag=1; 
				   }
		    }
	    }
	}
	}
	if(flag==0){
	System.out.println("Graph is bipartite");
	for (Vertex u : g) {
	    if (u.distance % 2 == 0) {
		System.out.println(u + " Outer");
	    } 
	    else {
		System.out.println(u + " Inner");
	    }
	}
	}
	else{
		System.out.println("Graph is not bipartite");
		for(Vertex u : g){
			for(Vertex v : g){
				for(Edge e : v.Adj )
					if(e.otherEnd(v)==u){
						if(u.parent==v.parent){
							System.out.println("Odd Cycle Exists");
							System.out.println("Vertices "+u+", "+v+" and "+u.parent+" form an odd length cycle");
							return;		
							}
					}				
				}
			}
		}
	}
}