import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Dijkstras {

    static class Edge {
        int src;
        int dest;
        int wt;

        public Edge(int s, int d, int w) {
            this.src = s;
            this.dest = d;
            this.wt = w;
        }
    }
    static class Pair implements Comparable<Pair> {
        int n;
        int path;

        public Pair(int n, int path) {
            this.n = n;
            this.path = path;
        }

        @Override
        public int compareTo(Pair p2) {
            return this.path - p2.path;
        }
    }

    public static void createGraph(ArrayList<Edge>[] graph, int E, Scanner scanner) {
        for (int i = 0; i < E; i++) {
            System.out.print("Enter source vertex of edge " + (i + 1) + ": ");
            int src = scanner.nextInt();
            System.out.print("Enter destination vertex of edge " + (i + 1) + ": ");
            int dest = scanner.nextInt();
            System.out.print("Enter weight of edge " + (i + 1) + ": ");
            int wt = scanner.nextInt();

            graph[src].add(new Edge(src, dest, wt));
        }
    }
    

    public static void dijkstras(ArrayList<Edge>[] graph, int src) {
        int V = graph.length;
        int[] dist = new int[V];
        for (int i = 0; i < V; i++) {
            if (i != src) {
                dist[i] = Integer.MAX_VALUE;
            }
        }

        boolean[] vis = new boolean[V];
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(src, 0));

        while (!pq.isEmpty()) {
            Pair curr = pq.remove();
            if (!vis[curr.n]) {
                vis[curr.n] = true;

                for (int i = 0; i < graph[curr.n].size(); i++) {
                    Edge e = graph[curr.n].get(i);
                    int u = e.src;
                    int v = e.dest;
                    int wt = e.wt;

                    if (dist[u] + wt < dist[v]) {
                        dist[v] = dist[u] + wt;
                        pq.add(new Pair(v, dist[v]));
                    }
                }
            }
        }
        int cost=0;

        for (int i = 0; i < V; i++) {
            System.out.println("Shortest distance from source to vertex " + i + ": " + dist[i]);
            cost+=dist[i];
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int V = scanner.nextInt();

        System.out.print("Enter the number of edges: ");
        int E = scanner.nextInt();

        ArrayList<Edge>[] graph = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            graph[i] = new ArrayList<>();
        }

        createGraph(graph, E, scanner);

        System.out.print("Enter the source vertex: ");
        int src = scanner.nextInt();

        dijkstras(graph, src);

        scanner.close();
    }
}