import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Stack;
import java.util.Scanner;


public class LP2<T> implements Iterable<Vertex>{

	public List<Vertex> verts; // array of vertices
    public int numNodes; // number of verices in the graph
    
    /**
     * Constructor for Graph
     * 
     * @param size
     *            : int - number of vertices
     */
    LP2(int size) {
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
    
    public static boolean findCycle(Vertex v)
	{   
    	Vertex supervertex = v;
    	LinkedList<Vertex> supervcycle = new LinkedList<>();
    	Queue<Vertex> que = new LinkedList<>();
    	
		supervcycle.add(supervertex);
		que.add(supervertex);
		while(!que.isEmpty()){
			Vertex superv =que.remove(); 
    	for(Edge e:superv.revAdj){
    		if(e.From!=supervertex){
    		if(e.RWeight==0)
    		{   if(e.From!=superv){e.From.parent = supervertex;
    			supervcycle.add(e.From);}
    		  que.add(e.From);
    		  
    		  superv.cycleEnd = e.From;
    		 
    		}}}}
		System.out.println("End of SuperVertex "+supervertex.cycleEnd);
		supervertex.Children = supervcycle;
		return true;
	}
    
    public static void readGraph(Scanner in, int n) {
		// read the graph related parameters
	    	System.out.println("Enter number of edges: ");
	    // number of vertices in the graph
		int m = in.nextInt(); // number of edges in the graph
		Stack<Vertex> cycleHeads = new Stack<>();
		// create a graph instance
		LP2<Vertex> g = new LP2<>(n);
		for (int i = 0; i < m; i++) {
			System.out.println("Enter the nodes connected by edge "+(i+1)+" followed by cost of edge ");
		    int u = in.nextInt();
		    int v = in.nextInt();
		    int w = in.nextInt();
			g.addDirectedEdge(u, v, w);
		    
		}
		
		Vertex source = g.verts.get(1);
		LinkedList<Edge> zerolist = new LinkedList<>();
		
		Queue<Vertex> que = new LinkedList<>();
		int minWeight = 0;
		que.add(source);
		while (!que.isEmpty()){
		Vertex u = que.remove();
		for(Edge e: u.Adj)
		{
			if(!e.visited){
				minWeight = 100;
			for(Edge ed:e.To.revAdj)
			{
				if(ed.RWeight<minWeight)
					minWeight = ed.RWeight;
			
			}
			for(Edge ed:e.To.revAdj)
			{
				ed.RWeight-=minWeight;
			}
			e.visited = true;
			que.add(e.To);}
		}}
		que.add(source);
		while (!que.isEmpty()){
		Vertex v = que.remove();
		for(Edge e: v.Adj)
		{   if(e.visited){
			if(e.RWeight==0){
				System.out.println("Edge in if is "+e);
				//if(e.From==source)
					//zerolist.add(e);
			    //zerolist.add(e.To);
			    e.newTo = e.To;
	        que.add(e.To);}
			else
			{  System.out.println("Edge in else is "+e);
				System.out.println("Calling cycle for "+e.To+"*******");
			    if(findCycle(e.To))
			    {   cycleHeads.push(e.To);
			    	int intoCycleminWeight = 100;
			    	e.To.SuperVertex = true;
			    	ListIterator<Vertex> li = e.To.Children.listIterator();
			    	System.out.println("e.to children"+e.To.Children);
			    	while(li.hasNext()){
			    	for(Edge edg:li.next().revAdj)
			    	{    System.out.println("revAdj of Child "+edg);
			    		//if(edg.From==e.From){
			    			if(edg.RWeight<intoCycleminWeight&&edg.RWeight!=0){
			    				System.out.println("Inside if for this edge "+edg);
			    				intoCycleminWeight = edg.RWeight;
			    		edg.disabled = true;
			    		e.To.bestEdgeintoCycle = edg;
			    		System.out.println("Best edge into cycle weight "+e.To.bestEdgeintoCycle.RWeight);}
			    		//}
			    				
			    	}
			    	}
			    	e.To.bestEdgeintoCycle.RWeight = 0;
			    	System.out.println("Best edge reduced "+e.To.bestEdgeintoCycle.RWeight);
			    	break;
			    	//zerolist.add(cycleHeads.pop().bestEdgeintoCycle);
			    }	
			}
	    e.To.seen = false;   
		}
		}
		
		
		}
		Queue<Vertex> q = new LinkedList<>();
		q.add(source);
		while(!q.isEmpty())
		{

		Vertex x = q.remove();
		for(Edge ex:x.Adj){
		if(!x.SuperVertex){
			if(ex.To.SuperVertex&&ex.RWeight==0&&ex.From.parent==ex.To){
				System.out.println("x in breaking one "+x);
				System.out.println("ex.To.Parent, ex.To: "+ex.From.parent+","+ex.To);
				System.out.println("edge in breaking one "+ex);
				System.out.println("Inside breaking cond one");
				zerolist.add(ex);
				break;
			}
			else if(ex.RWeight==0){
				zerolist.add(ex);
			q.add(ex.To);
		}}
		else{
			if(ex.To.SuperVertex&&ex.RWeight==0){
				System.out.println("Inside breaking cond two");
				zerolist.add(ex);
				break;
			}
				
			else if(ex.RWeight==0){System.out.println(ex +"inside cycle edge");
			zerolist.add(ex);
			q.add(ex.To);
			}
		}
			//if(!ex.To.SuperVertex)
			//zerolist.add(ex);
		}
		System.out.println("zerolist "+zerolist);
		
		
		}
		
		
    }
	
	
	public static void main(String args[])
	{
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
