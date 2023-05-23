import java.util.*;

class Edge implements Comparable<Edge> {
    int src, dest, weight;

    public int compareTo(Edge edge) {
        return this.weight - edge.weight;
    }
}

class Graph {
    int V, E;
    Edge[] edges;

    Graph(int v, int e) {
        V = v;
        E = e;
        edges = new Edge[E];
        for (int i = 0; i < E; i++) {
            edges[i] = new Edge();
        }
    }
}

public class KruskalAlgorithm {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the number of vertices in the graph: ");
        int V = scanner.nextInt();

        System.out.print("Enter the number of edges in the graph: ");
        int E = scanner.nextInt();

        Graph graph = new Graph(V, E);

        for (int i = 0; i < E; i++) {
            System.out.println("Enter details of edge " + (i + 1) + ":");
            System.out.print("Source: ");
            graph.edges[i].src = scanner.nextInt();
            System.out.print("Destination: ");
            graph.edges[i].dest = scanner.nextInt();
            System.out.print("Weight: ");
            graph.edges[i].weight = scanner.nextInt();
        }

        int minimumCost = kruskalMST(graph);
        System.out.println("Minimum Cost Spanning Tree: " + minimumCost);

        scanner.close();
    }

    static int kruskalMST(Graph graph) {
        int V = graph.V;
        int E = graph.E;

        // Sort edges in ascending order of their weights
        Arrays.sort(graph.edges);

        // Create a parent array for tracking disjoint sets
        int[] parent = new int[V];
        for (int i = 0; i < V; i++) {
            parent[i] = i;
        }

        int minimumCost = 0; // Stores the total weight of the minimum spanning tree
        int edgeCount = 0; // Keeps track of the number of edges included in the MST

        int i = 0;
        while (edgeCount < V - 1) {
            Edge nextEdge = graph.edges[i++];

            int x = find(parent, nextEdge.src);
            int y = find(parent, nextEdge.dest);

            // Check if including the next edge forms a cycle or not
            if (x != y) {
                minimumCost += nextEdge.weight;
                union(parent, x, y);
                edgeCount++;
            }
        }

        return minimumCost;
    }

    static int find(int[] parent, int vertex) {
        if (parent[vertex] != vertex) {
            parent[vertex] = find(parent, parent[vertex]);
        }
        return parent[vertex];
    }

    static void union(int[] parent, int x, int y) {
        int xSet = find(parent, x);
        int ySet = find(parent, y);
        parent[ySet] = xSet;
    }
}