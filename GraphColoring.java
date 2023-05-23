import java.util.ArrayList;
import java.util.Scanner;

public class Graph {

    static class Edge {
        int src;
        int dest;

        public Edge(int s, int d) {
            this.src = s;
            this.dest = d;
        }
    }

    public static ArrayList<Edge>[] createGraph() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int numVertices = scanner.nextInt();
        
        ArrayList<Edge>[] graph = new ArrayList[numVertices];
        for (int i = 0; i < numVertices; i++) {
            graph[i] = new ArrayList<>();
        }

        System.out.print("Enter the number of edges: ");
        int numEdges = scanner.nextInt();

        System.out.println("Enter the edges (source and destination):");
        for (int i = 0; i < numEdges; i++) {
            int source = scanner.nextInt();
            int destination = scanner.nextInt();
            graph[source].add(new Edge(source, destination));
            graph[destination].add(new Edge(destination, source));
        }

        return graph;
    }

    public static int graphColoring(ArrayList<Edge>[] graph) {
        int numVertices = graph.length;
        int[] result = new int[numVertices];

        // Array to track the available colors for each vertex
        boolean[] availableColors = new boolean[numVertices];
        int minColors = 0;

        // Color the first vertex with color 1
        result[0] = 1;

        // Assign colors to the remaining vertices
        for (int v = 1; v < numVertices; v++) {
            // Reset the available colors array
            for (int i = 0; i < numVertices; i++) {
                availableColors[i] = true;
            }

            // Check the adjacent vertices and mark their colors as unavailable
            for (Edge edge : graph[v]) {
                if (result[edge.dest] != 0) {
                    availableColors[result[edge.dest] - 1] = false;
                }
            }

            // Find the first available color
            int color;
            for (color = 0; color < numVertices; color++) {
                if (availableColors[color]) {
                    break;
                }
            }

            // Assign the color to the current vertex
            result[v] = color + 1;

            // Update the minimum number of colors
            minColors = Math.max(minColors, color + 1);
        }

        return minColors;
    }

    public static void main(String[] args) {
        // Create the graph
        ArrayList<Edge>[] graph = createGraph();

        // Perform graph coloring to find the minimum number of colors
        int minColors = graphColoring(graph);

        // Print the minimum number of colors required
        System.out.println("Minimum number of colors required: " + minColors);
    }
}