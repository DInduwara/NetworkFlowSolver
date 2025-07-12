//Student ID: 20230231/ w2051597
//Name= Dinuka Induwara

import java.util.*;

/**
 * Implementation of the Edmonds-Karp algorithm for maximum flow
 */
public class EdmondsKarp {
    private final FlowNetwork G;
    private final int s; // Source
    private final int t; // Sink
    private final List<List<Integer>> augmentingPaths; // List of all augmenting paths found

    /**
     * Constructor to create an Edmonds-Karp algorithm instance
     */

    public EdmondsKarp(FlowNetwork G) {
        this.G= G;
        this.s= 0; // Assuming source is always node 0
        this.t= G.V() - 1; // Assuming sink is always the last node
        this.augmentingPaths= new ArrayList<>();
    }

    /**
     * Finds the maximum flow from s to t using Edmonds-Karp algorithm
     */
    public int findMaxFlow() {
        int maxFlow= 0;

        // While there is an augmenting path
        int iteration= 1;
        while (true) {
            // Find an augmenting path using BFS
            Map<Integer, Edge> edgeTo= new HashMap<>();
            Queue<Integer> queue= new LinkedList<>();
            Set<Integer> visited= new HashSet<>();

            queue.add(s);
            visited.add(s);

            boolean foundPath= false;

            // BFS to find shortest augmenting path
            while (!queue.isEmpty() && !foundPath) {
                int v = queue.poll();

                for (Edge e : G.adj(v)) {
                    int w = e.to();

                    // If residual capacity > 0 and not visited
                    if (e.residualCapacity() > 0 && !visited.contains(w)) {
                        edgeTo.put(w, e);
                        visited.add(w);
                        queue.add(w);

                        // If we reached the sink
                        if (w == t) {
                            foundPath = true;
                            break;
                        }
                    }
                }
            }



            // If no augmenting path was found, we're done
            if (!foundPath) {
                break;
            }

            // Find bottleneck capacity
            int bottleneck= Integer.MAX_VALUE;
            List<Integer> path = new ArrayList<>();
            path.add(t);

            for (int v= t; v != s; ) {
                Edge e= edgeTo.get(v);
                bottleneck= Math.min(bottleneck, e.residualCapacity());
                v= e.from();
                path.add(0, v); // Add to the beginning of the path
            }

            // Record this augmenting path
            augmentingPaths.add(path);

            // Augment flow along path
            for (int v= t; v != s; ) {
                Edge e = edgeTo.get(v);
                e.addFlow(bottleneck);
                v = e.from();
            }

            // Print information about this augmentation
            System.out.println("Iteration " + iteration + ":");
            System.out.println("  Augmenting Path: " + pathToString(path));
            System.out.println("  Bottleneck Value: " + bottleneck);
            System.out.println("  Flow Added: " + bottleneck);
            System.out.println("  Current Max Flow: " + (maxFlow + bottleneck));

            maxFlow += bottleneck;
            iteration++;
        }

        // Print all augmenting paths found
        System.out.println("\nAll Augmenting Paths:");
        for (int i= 0; i < augmentingPaths.size(); i++) {
            System.out.println("Path " + (i + 1) + ": " + pathToString(augmentingPaths.get(i)));
        }

        return maxFlow;
    }

    /**
     * Converts a path to a string representation
     */
    private String pathToString(List<Integer> path) {
        StringBuilder sb = new StringBuilder();
        for (int i= 0; i < path.size(); i++) {
            sb.append(path.get(i));
            if (i < path.size() - 1) {
                sb.append(" -> ");
            }
        }
        return sb.toString();
    }

    /**
     * Returns the list of all augmenting paths found
     */
    public List<List<Integer>> getAugmentingPaths() {
        return augmentingPaths;
    }
}