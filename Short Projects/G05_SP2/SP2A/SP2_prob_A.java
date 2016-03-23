	/**
	 * Class to represent a graph
	 * 
	 *
	 */

	//import java.io.*;
	import java.util.*;

	class SP2_prob_A <T> implements Iterable<Vertex> {
	    public List<Vertex> verts; // array of vertices
	    public int numNodes; // number of verices in the graph

	    /**
	     * Constructor for Graph
	     * 
	     * @param size
	     *            : int - number of vertices
	     */
	    SP2_prob_A(int size) {
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
	  static  List<Vertex> toplogicalOrder1(SP2_prob_A<Vertex> g) {
		  /* Algorithm 1. Remove vertices with no incoming edges, one at a
     	 time, along with their incident edges, and add them to a list.
      */
		  ArrayList<Vertex> TopologicalOrderList = new ArrayList<>();
			int top = 0; int n=0;
			Queue<Vertex> q = new LinkedList<>();
			for(Vertex u:g)
			{   ++n;
				if(u.inDegree==0)
					q.add(u);
			}
			
			    
				while(!q.isEmpty())
				{ 
		          Vertex u = q.remove();
		          u.seen = true;
		          ++top;
				  TopologicalOrderList.add(u);
		          for(Edge e:u.Adj){
				    	if(e.From==u)	
				    	{   
				    		e.To.inDegree--;
				    	  	    if(e.To.inDegree==0)
				    	    	q.add(e.To);
				       }
				    }
	    }	  if (top==n){
	    	System.out.println("The given graph is a DAG");
				return TopologicalOrderList; }
	    else{System.out.println("The given graph is not a DAG");
	    	return null;
	    }
	        
	     }
	  static void DFSvisit(Vertex ve, Stack<Vertex> ss)
		{
		    ve.seen = true;
			
			for(Edge e:ve.Adj)
			{
				if(e.From==ve)
				{   if (e.To!=null)
				{
					Vertex c = e.To;
					if(!c.seen){c.parent = ve;
					c.seen = true;
					DFSvisit(c,ss);
				}}
			
				}
			} ss.push(ve);
		}

	 static  Stack<Vertex> toplogicalOrder2(SP2_prob_A<Vertex> g) {
	      /* Algorithm 2. Run DFS on g and push nodes to a stack in the
	      	 order in which they finish.  Write code without using global variables.
	       */
		    int n=0;
     		Stack<Vertex> S = new Stack<>();
			for (Vertex v: g)
			{++n;
			v.seen = false;
			v.parent = null;
			}
			
			for(Vertex v:g)
				
			{
				if(!v.seen)
					
					DFSvisit(v,S);
			}
			if(S.size()==n){System.out.println("The given graph is a DAG");
			return S;}
			else
			{
				System.out.println("The given graph is not a DAG");
				return null;
			}
	  }
	  
	    public static SP2_prob_A<Vertex> readGraph(Scanner in, int n) {
		// read the graph related parameters
	    	System.out.println("Enter number of edges: ");
	    // number of vertices in the graph
		int m = in.nextInt(); // number of edges in the graph

		// create a graph instance
		SP2_prob_A<Vertex> g = new SP2_prob_A<>(n);
		for (int i = 0; i < m; i++) {
			System.out.println("Enter the nodes connected by edge "+(i+1)+" followed by cost of edge ");
		    int u = in.nextInt();
		    int v = in.nextInt();
		    int w = in.nextInt();
			g.addEdge(u, v, w);
		    
		}
		System.out.println("Graph has been created");
		System.out.println("The topological order using Queue");
		System.out.println(toplogicalOrder1(g));
		System.out.println("The topological order using Stack");
		System.out.println(toplogicalOrder2(g));
		
		return null;
	    }
	    public static void main(String[] args) {
	    	Scanner in = new Scanner(System.in);
	    	int n;
	        if(args.length > 0) {
	            n = Integer.parseInt(args[0]);
	        }
	        System.out.println("Enter the number of vertices");
	        n = in.nextInt();
	        
	        readGraph(in, n);
	    }

}
