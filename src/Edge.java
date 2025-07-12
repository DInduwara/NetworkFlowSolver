//Student ID: 20230231/ w2051597
//Name= Dinuka Induwara


/**
 * Represents an edge in the flow network
 */
public class Edge {
    private final int v; // From
    private final int w; // To
    private final int capacity; // Capacity
    private int flow; // Current flow
    private Edge reverse; // Reverse edge

    /**
     * Constructor to create an edge from v to w with the given capacity
     */
    public Edge(int v, int w, int capacity) {
        this.v= v;
        this.w= w;
        this.capacity= capacity;
        this.flow= 0;
    }

    /**
     * Returns the capacity of this edge
     */
    public int capacity() {
        return capacity;
    }

    /**
     * Returns the flow on this edge
     */
    public int flow() {
        return flow;
    }

    /**
     * Returns the residual capacity of this edge
     */
    public int residualCapacity() {
        return capacity - flow;
    }

    /**
     * Returns the source vertex
     */
    public int from() {
        return v;
    }

    /**
     * Returns the destination vertex
     */
    public int to() {
        return w;
    }

    /**
     * Adds the given flow to this edge and subtracts it from the reverse edge
     */
    public void addFlow(int delta) {
        flow += delta;
        reverse.flow -= delta;
    }

    /**
     * Sets the reverse edge
     */
    public void setReverse(Edge reverse) {
        this.reverse= reverse;
    }

    /**
     * Returns the reverse edge
     */
    public Edge getReverse() {
        return reverse;
    }

    /**
     * Returns a string representation of the edge
     */
    @Override
    public String toString() {
        return v + " -> " + w + " (Flow: " + flow + "/" + capacity + ")";
    }


}
