//Student ID: 20230231/ w2051597
//Name= Dinuka Induwara
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Parser for network flow input files
 */

public class NetworkParser {
    /**
     * Parses the input file and creates a flow network
     *
     * @param filename the name of the file to parse
     * @return the flow network represented by the file
     * @throws IOException if there is an error reading the file
     */


    public static FlowNetwork parseInputFile(String filename) throws IOException {
        BufferedReader reader= new BufferedReader(new FileReader(filename));

        // Read number of nodes
        int numNodes = Integer.parseInt(reader.readLine().trim());
        FlowNetwork network= new FlowNetwork(numNodes);

        // Read edges
        String line;
        while ((line= reader.readLine()) != null) {
            String[] parts= line.trim().split("\\s+");
            if (parts.length== 3) {
                int from= Integer.parseInt(parts[0]);
                int to= Integer.parseInt(parts[1]);
                int capacity=  Integer.parseInt(parts[2]);
                network.addEdge(from, to, capacity);
            }
        }

        reader.close();
        return network;
    }
}