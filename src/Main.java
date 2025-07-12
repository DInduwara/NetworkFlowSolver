//Student ID: 20230231/ w2051597
//Name= Dinuka Induwara

import java.io.File;
import java.io.IOException;

/**
 * Main class to run the network flow algorithm
 */
public class Main {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java Main <input_file_or_directory>");
            return;
        }

        String path= args[0];
        File file= new File(path);

        if (file.isDirectory()) {
            // Process all .txt files in the directory
            System.out.println("Processing directory: " + path);
            File[] benchmarks= file.listFiles((dir, name) -> name.toLowerCase().endsWith(".txt"));

            if (benchmarks != null) {
                System.out.println("Found " + benchmarks.length + " benchmark files.");
                for (File benchmark : benchmarks) {
                    System.out.println("\n===================================");
                    System.out.println("Processing file: " + benchmark.getName());
                    System.out.println("====================================");
                    processFile(benchmark.getAbsolutePath());
                }
            } else {
                System.out.println("No benchmark files found in directory.");
            }
        } else {
            // Process a single file
            processFile(path);
        }
    }

    /**
     * Process a single input file
     */
    private static void processFile(String filename) {
        try {
            // Parse input file and create a flow network
            FlowNetwork network= NetworkParser.parseInputFile(filename);

            // Create and run the Edmonds-Karp algorithm
            EdmondsKarp edmonds= new EdmondsKarp(network);

            // Measure execution time
            long startTime= System.nanoTime();
            int maxFlow= edmonds.findMaxFlow();
            long endTime= System.nanoTime();

            double executionTimeMs= (endTime - startTime) / 1_000_000.0;

            // Print results
            System.out.println("Number of nodes: " + network.V());
            System.out.println("Number of edges: " + network.numEdges());
            System.out.println("Maximum Flow: " + maxFlow);
            System.out.println("Execution Time: " + executionTimeMs + " ms");


        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error processing file: " + e.getMessage());
        }
    }
}