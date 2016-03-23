import java.util.*;

public class SP2_prob_B<T> implements Iterable<Vertex> {
  
	public List<Vertex> verts; // array of vertices
    public int numNodes; // number of verices in the graph

    /**
     * Constructor for Graph
     * 
     * @param size
     *            : int - number of vertices
     */
    SP2_prob_B(int size) {
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
    
    
    
	static void BFS(SP2_prob_B<Vertex> g, Vertex z, int Run){
		Queue<Vertex> Q = new LinkedList<>();

		for (Vertex u : g) {
		    u.seen = false;
		    u.parent = null;
		    u.distance = Integer.MAX_VALUE;
		}

		// Run BFS on every component
		for (Vertex src : g) {
			if(Run==2)
				src = z;
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
				  } 
			    } 
			 }
		   }
		}
		
	}
		 public static SP2_prob_B<Vertex> readGraph(Scanner in, int n) {
				// read the graph related parameters
			    	System.out.println("Enter number of edges: ");
			    // number of vertices in the graph
				int m = in.nextInt(); // number of edges in the graph
                int maxdistance = 0;
                Vertex z = null;
				// create a graph instance
				SP2_prob_B<Vertex> g = new SP2_prob_B<>(n);
				for (int i = 0; i < m; i++) {
					System.out.println("Enter the nodes connected by edge "+(i+1)+" followed by cost of edge ");
				    int u = in.nextInt();
				    int v = in.nextInt();
				    int w = in.nextInt();
					g.addEdge(u, v, w);
				    
				}
				System.out.println("Graph has been created");
				BFS(g,null,1);
				
				for(Vertex ud:g)
			    {   
			        if (maxdistance < ud.distance)
			        { maxdistance = ud.distance;
			          z = ud;
			        }
			    }
				System.out.println("BFS Run 1: Vertex Z with maximum distance from assumed root");
				System.out.println(z.name);
				BFS(g,z,2);
				
				for(Vertex ud:g)
			    {   
			        if (maxdistance < ud.distance)
			        { maxdistance = ud.distance;
			          z = ud;
			        }
			    }
				System.out.println("BFS Run 2: Vertex Z with maximum distance from assumed root");
				System.out.println(z.name);
				return null;
			    }


		public static void main(String[] args) {
			Scanner in;
			int n;
			    in = new Scanner(System.in);
			    System.out.println("Enter the number of vertices");
			n = in.nextInt();
			readGraph(in, n);   // read undirected graph from stream "in"
}


}
