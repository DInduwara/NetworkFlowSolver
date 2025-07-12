//Student ID: 20230231/ w2051597
//Name= Dinuka Induwara
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the flow network using an adjacency list
 */
public class FlowNetwork {
    private final int V; // Number of vertices
    private final List<Edge>[] adj; // Adjacency list
    private final List<Edge> edges;
    private int numEdges;// All edges

    /**
     * Constructor to create an empty flow network with V vertices
     */
    @SuppressWarnings("unchecked")
    public FlowNetwork(int V) {
        this.V = V;
        adj = (List<Edge>[]) new ArrayList[V];
        for (int v= 0; v < V; v++) {
            adj[v]= new ArrayList<>();
        }
        edges= new ArrayList<>();
        numEdges = 0;
    }

    /**
     * Adds a directed edge from v to w with the given capacity
     */
    public void addEdge(int v, int w, int capacity) {
        Edge e= new Edge(v, w, capacity);
        Edge reverse= new Edge(w, v, 0); // Reverse edge with 0 capacity

        // Set as each other's reverse
        e.setReverse(reverse);
        reverse.setReverse(e);

        // Add to adjacency lists
        adj[v].add(e);
        adj[w].add(reverse);

        // Add to edges list
        edges.add(e);
        numEdges++;
    }

    /**
     * Returns the list of edges adjacent to vertex v
     */
    public Iterable<Edge> adj(int v) {
        return adj[v];
    }

    /**
     * Returns the number of vertices
     */
    public int V() {
        return V;
    }


    /**
     * Returns the number of edges in the flow network
     */
    public int numEdges() {
        return numEdges; // Returns the current count of edges
    }




}
