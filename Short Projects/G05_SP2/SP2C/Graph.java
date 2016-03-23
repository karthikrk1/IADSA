import java.io.*;
import java.util.*;

class Graph implements Iterable<Vertex> {
    public List<Vertex> verts; // array of vertices
    public int numNodes; // number of verices in the graph
    //  public int timeVariable=0;
    public Stack<Vertex> stack=new Stack<Vertex>();
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
	 * @param v            : Array of vertices
	 * @param n            : int - Size of the graph
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
   }

    public static Graph readGraph(Scanner in, boolean directed) {
	// read the graph related parameters
    System.out.println("Enter number of vertices : ");
	int n = in.nextInt(); // number of vertices in the graph
	System.out.println("Enter number of edges :");
	int m = in.nextInt(); // number of edges in the graph

	// create a graph instance
	Graph g = new Graph(n);
	
	for (int i = 1; i <= m; i++) {
		System.out.println("Edge "+i);
		System.out.println("Enter head name: ");
	    int u = in.nextInt();
	    System.out.println("Enter tail name: ");
	    int v = in.nextInt();
	    if(directed) {
		g.addDirectedEdge(u, v, 0);
	    } else {
		g.addEdge(u, v, 0);
	    }
	}
	in.close();
	return g;
    }
    public static void DFS(Graph g)
    {
    	Iterator<Vertex> iterator=g.iterator();
    	Vertex u;
    	//	g.timeVariable=0;
    	while(iterator.hasNext())
    	{
    		u=(Vertex) iterator.next();
    		if(u.seen==false)
    		{
    			u.separateRoot=true;
    			DFS_Visit(g, u);
    		}
    	}  	    	
    }
    public static void DFS_Visit(Graph g, Vertex u)
    {
    	u.seen=true;
    	Vertex v;
    	Iterator<Edge> iterator=u.Adj.iterator();
    	while (iterator.hasNext()) {
    		v=((Edge) iterator.next()).otherEnd(u);
			if(v.seen==false)
			{
				DFS_Visit(g, v);
			}
		}
    	g.stack.push(u);
    }
    public static void main(String[] arr)
    {
    	Graph g=readGraph(new Scanner(System.in), true);
    	System.out.println("Number of components "+stronglyConnectedComponents(g));
    }
    public static int stronglyConnectedComponents(Graph g) { 
    	DFS(g); //Calling DFS on original graph
    	Graph gReverse=g; //Defining reverse of original Graph
    	List<Vertex> reverseVertex= new ArrayList<Vertex>(); //Defining reversed vertex list of original graph
    	while (!g.stack.isEmpty()) {
    		Vertex v=(Vertex) g.stack.pop();
    		v.seen=false;
    		v.Adj=v.revAdj;
    		reverseVertex.add(v);
    	}
    	gReverse.verts=reverseVertex;
    	DFS(gReverse);
    	Vertex v;
    	int componentCount=0;
    	System.out.println("Node Name  Component Number");
    	while (!gReverse.stack.isEmpty()) {
    	    v=(Vertex) gReverse.stack.pop();
    		if(v.separateRoot)
    		{
    			componentCount++;
    		}
    		v.componentNumber=componentCount;
    		System.out.println(v.name+" \t "+v.componentNumber);
    	}
		return componentCount;
    }    
}