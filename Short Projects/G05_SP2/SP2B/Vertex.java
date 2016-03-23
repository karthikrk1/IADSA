import java.util.*;

public class Vertex {

	/**
	 * Class to represent a vertex of a graph
	 * 
	 *
	 */

	
	    public int name; // name of the vertex
	    public boolean seen; // flag to check if the vertex has already been visited
	    public Vertex parent; // parent of the vertex
	    public int distance; // distance to the vertex from the source vertex
	    public List<Edge> Adj, revAdj; // adjacency list; use LinkedList or ArrayList
        public int inDegree;
	    /**
	     * Constructor for the vertex
	     * 
	     * @param n
	     *            : int - name of the vertex
	     */
	    Vertex(int n) {
		name = n;
		seen = false;
		parent = null;
		inDegree = 0;
		Adj = new ArrayList<Edge>();
		revAdj = new ArrayList<Edge>();   /* only for directed graphs */
	    }

	    /**
	     * Method to represent a vertex by its name
	     */
	    public String toString() {
		return Integer.toString(name);
	    }
	
	
}
