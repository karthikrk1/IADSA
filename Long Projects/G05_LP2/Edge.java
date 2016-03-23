
public class Edge {

	/**
	 * Class that represents an arc in a Graph
	 *
	 */
	
	    public Vertex From = null; // head vertex
	    public Vertex To = null; // tail vertex
	    public Vertex newTo = null;
	    public int Weight;// weight of the arc
	    public int index;
	    public int RWeight;
	    public int count;
	    public boolean disabled;
	    public boolean visited = false;

	    /**
	     * Constructor for Edge
	     * 
	     * @param u
	     *            : Vertex - The head of the arc
	     * @param v
	     *            : Vertex - The tail of the arc
	     * @param w
	     *            : int - The weight associated with the arc
	     */
	    Edge(Vertex u, Vertex v, int w) {
		From = u;
		To = v;
		newTo = null;
		v.inDegree++;
		Weight = w;
		RWeight = w;
		visited = false;
		disabled = false;
		count = 0;
	    }

	    /**
	     * Method to find the other end end of the arc given a vertex reference
	     * 
	     * @param u
	     *            : Vertex
	     * @return
	     */
	    public Vertex otherEnd(Vertex u) {
		// if the vertex u is the head of the arc, then return the tail else return the head
		if (From == u) {
		    return To;
		} else {
		    return From;
		}
	    }

	    /**
	     * Method to represent the edge in the form (x,y) where x is the head of
	     * the arc and y is the tail of the arc
	     */
	    public String toString() {
		return "(" + From + "," + To + ")";
	    }
	
	
}
