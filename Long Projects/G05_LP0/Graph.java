/**
 * Class to represent a graph
 * 
 *
 */

import java.io.*;
import java.util.*;

class Graph<T> implements Iterable<Vertex> {
    public List<Vertex> verts; // array of vertices
    public int numNodes; // number of vertices in the graph

    /**
     * Constructor for Graph
     * 
     * @param size
     *            : int - number of vertices
     */
    Graph(int size) {
	numNodes = size;
	verts = new ArrayList<>(size + 1);
	verts.add(0, null);
	// create an array of Vertex objects
	for (int i = 1; i <= size; i++)
	    verts.add(i, new Vertex(i));
    }

    /**
     * Method to add an edge to the graph
     * 
     * @param a
     *            : int - one end of edge
     * @param b
     *            : int - other end of edge
     * @param weight
     *            : int - the weight of the edge
     */
    void addEdge(int a, int b, int weight) {
	Vertex u = verts.get(a);
	Vertex v = verts.get(b);
	Edge e = new Edge(u, v, weight);
	u.Adj.add(e);
	v.Adj.add(e);
	u.Degree++;
	v.Degree++;
	u.remDegree++;
	v.remDegree++;
    }

    /**
     * Method to add an arc (directed edge) to the graph
     * 
     * @param a
     *            : int - the head of the arc
     * @param b
     *            : int - the tail of the arc
     * @param weight
     *            : int - the weight of the arc
     */
    void addDirectedEdge(int a, int b, int weight) {
	Vertex head = verts.get(a);
	Vertex tail = verts.get(b);
	Edge e = new Edge(head, tail, weight);
	head.Adj.add(e);
	tail.revAdj.add(e);
    }


    /**
     * Method to create an instance of VertexIterator
     */
    public Iterator<Vertex> iterator() {
	return new VertexIterator();
    }

    /**
     * A Custom Iterator Class for iterating through the vertices in a graph
     * 
     *
     * @param <Vertex>
     */
    private class VertexIterator implements Iterator<Vertex> {
	private Iterator<Vertex> it;
	/**
	 * Constructor for VertexIterator
	 * 
	 * @param v
	 *            : Array of vertices
	 * @param n
	 *            : int - Size of the graph
	 */
	private VertexIterator() {
	    it = verts.iterator();
	    it.next();  // Index 0 is not used.  Skip it.
	}

	/**
	 * Method to check if there is any vertex left in the iteration
	 * Overrides the default hasNext() method of Iterator Class
	 */
	public boolean hasNext() {
	    return it.hasNext();
	}

	/**
	 * Method to return the next Vertex object in the iteration
	 * Overrides the default next() method of Iterator Class
	 */
	public Vertex next() {
	    return it.next();
	}

	/**
	 * Throws an error if a vertex is attempted to be removed
	 */
	public void remove() {
	    throw new UnsupportedOperationException();
	}
    }

    /**
     * Method to find the DFS traversal for the Graph
     * @param G 
     * 				: Input Graph G - undirected graph
     * @return
     * 				: Returns the count for checking connected components
     */
    static int dfsUtil(Graph<Vertex> G){
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
     * Method to do the DFS traversal - Recursive call
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
     * Method the read the input from Scanner and create a Graph
     * @param in
     * 				: Scanner object to read the input from
     * @param directed
     * 				: Boolean flag - when set to true, graph is a directed graph.
     * @return
     * 				: Returns a graph.
     */
    public static Graph<Vertex> readGraph(Scanner in, boolean directed) {
		// read the graph related parameters
    		//System.out.println("Enter number of vertices:");
    		int n=in.nextInt(); // number of vertices in the graph
	    	//System.out.println("Enter number of edges: ");
	    	int m = in.nextInt(); // number of edges in the graph

		// create a graph instance
		Graph<Vertex> g = new Graph<>(n);
		for (int i = 0; i < m; i++) {
			//System.out.println("Enter the nodes connected by edge "+(i+1)+" followed by cost of edge ");
		    int u = in.nextInt();
		    int v = in.nextInt();
		    int w = in.nextInt();
			g.addEdge(u, v, w);
		    
		}
		
		return g;
    }
    
    /**
     * Method testEulerian to check if the given graph is Eulerian or not.
     * A Eulerian graph is connected and the degree of each vertes is even.
     * They have a cycle, that goes through every edge of graph only once.
     * A connected graph with exactly two vertices of odd degree has Eulerian Path.
     * 
     * @param g: Input graph which is to be tested for satisfying Eulerian conditions
     */
    static int testEulerian(Graph<Vertex> g){
    	Vertex v1=null,v2=null;
    	int conn = dfsUtil(g);
    	if(conn > 1){
    		System.out.println("The graph is not connected.");
    		System.exit(0);
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
    		return 2;
    	}
    	else if(odd==2){
    		System.out.println(v1+" and "+v2);
    		return 1;
    	}
    	else
    		return 0;
    }
    
    /**
     * Method to find the Euler Tour based on the Hierholzer's algorithm.
     * @param g
     * 			: Input graph for which the tour is to be found.
     * @return
     * 			: Returns a list of edges that comprises of the Euler Tour.
     */
    public static List<Edge> findEulerTour(Graph<Vertex> g) {   	
    	 Vertex start = g.verts.get(1);
		 Stack<Edge> forward =new Stack<Edge>();
		 Stack<Edge> backtrack =new Stack<Edge>();
		 Edge e = getUnvisitedEdge(start);
		 while (e!=null) {
		  e.visited=true;
		  forward.push(e);
		  e = getUnvisitedEdge(e.To);
		 }
		  
		 while ( !( forward.isEmpty() ) ) {
		  e = forward.pop();
		  backtrack.push(e);
		  e = getUnvisitedEdge(e.From);
		  while (e!=null) {
		   e.visited = true;
		   forward.push(e);
		   e = getUnvisitedEdge(e.To);
		  }
		 }
		 
		 List<Edge> path = new LinkedList<Edge>();
		 while(!backtrack.isEmpty()) {
		  Edge edge = backtrack.pop();
		  path.add(edge);
		 }
		 return path;
		 
	}

    /**
     * Utility Method to determine if the Vertex has unvisited children
     * @param n
     * 			: Input vertex for which the children are to be determined. 
     * @return
     * 			: Returns the unvisited child Vertex for the input Vertex.
     */
	 public static Vertex getUnvisitedChildren(Vertex n) {
		   for (Edge e : n.Adj) {
		    if ( e.visited==false && e.From.equals(n) ) {
		     e.visited=true;
		     return e.To;
		    }
		   }
		   return null;
		  }
	
	/**
	 * Method to return the unvisited edge for the Graph
	 * @param n
	 * 			: Input vertex for which the unvisited Edges are to be determined.
	 * @return
	 * 			: Returns the unvisited Edges for the input Vertex.
	 */
	public static Edge getUnvisitedEdge(Vertex n) {
		   for (Edge e : n.Adj) {
		    if ( e.visited==false && e.From.equals(n) ) {
		     e.visited=true;
		     return e;
		    }
		   }
		   return null;
	}	  
    
	/**
	 * Method to verify if the Input tour is a valid tour.
	 * @param g
	 * 				: Input graph for which the tour is to be verified.
	 * @param tour
	 * 				: The tour output provided by findEulerTour() method.
	 * @param start
	 * 				: The start vertex from where the tour begins.
	 * @return
	 * 				: Returns true if tour is valid or false if the tour is rejected.
	 */
	static boolean verifyTour(Graph<Vertex> g, List<Edge> tour, Vertex start)
		    {
		    	Edge ePrevious=null;
		    	boolean previousHead=true;
		    	int count=0;
		    	System.out.println("Size "+g.verts.size());
		    	for(Edge e: tour)
		    	{
		    		e.To.seen=false;
		    		e.From.seen=false;
		    		e.visited=false;
		    	}
		    	for(Edge e: tour)
		    	{
		    		count++;
		    		if(count==2)
		    		{
		    			if(ePrevious.From.name==e.From.name||ePrevious.To.name==e.From.name)
		        		{
		        			previousHead=false;
		        			System.out.println("01");
		        			
		        		}
		        		else if(ePrevious.To.name==e.To.name||ePrevious.From.name==e.To.name)
		        		{
		        			previousHead=true;
		        			System.out.println("02");
		        		}
		        		else
		        		{
		        			System.out.println("03"+count);
		        			System.out.println(ePrevious.To.name);
		        			System.out.println(e.From.name);
		        			return false;
		        		}
		        		
		    		}
		    		else if(previousHead&&ePrevious!=null&&count>2)
		    		{
		    			if(ePrevious.From.name==e.From.name)
		        		{
		        			previousHead=false;
		        			System.out.println("1");
		        		}
		        		else if(ePrevious.From.name==e.To.name)
		        		{
		        			previousHead=true;
		        			System.out.println("2");
		        		}
		        		else{
		        			System.out.println("3"+count);
		        			return false;
		        		}
		        	}
		    		else if(!previousHead&&ePrevious!=null&&count>2)
		        	{
		        		if(ePrevious.To.name==e.From.name)
		        		{
		        			System.out.println("4");
		        			previousHead=false;
		        		}
		        		else if(ePrevious.To.name==e.To.name)
		        		{
		        			System.out.println("5");
		        			previousHead=true;
		        		}
		        		else{
		        			System.out.println("6"+count);
		        			return false;
		        		}
		        	}
		    		e.From.seen=true;  
		    		e.To.seen=true;
		    		if(e.visited)
		    		{
		    			System.out.println("Edge is visited twice");
		    			return false;
		    		}
		    		else
		    		{
		    			e.visited=true;
		    		}
		    		ePrevious=e;
		    		
		    	}
		    	Iterator<Vertex> iterator = g.iterator();
				Vertex vertex;

				while (iterator.hasNext()) /*
											 * declaring the vertices as not yet
											 * processed
											 */
				{
					vertex = (Vertex) iterator.next();
					

		    		if(!vertex.seen)
		    		{
		    			System.out.println("9");
		    			return false;
		    		}
		    		for(Edge edge: vertex.Adj)
		    		{
		    			if(!edge.visited)
		    			{
		    				System.out.println("10");
		    				return false;
		    			}
		    				
		    		}
		    	}
				return true;
		    	
		    }
		  
	
	/**
	 * Utility method to print the Euler tour
	 * @param tour
	 * 				: Input - tour provided by findEulerTour() method.
	 */
	public static void printTour(List<Edge> tour){
			  for(Edge e : tour){
				  System.out.println(e);
			  }
		  }
		  

}