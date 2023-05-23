import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Prims {

    static class Edge implements Comparable<Edge> {
        int src;
        int dest;
        int wt;

        public Edge(int s, int d, int w) {
            this.src = s;
            this.dest = d;
            this.wt = w;
        }

        @Override
        public int compareTo(Edge e2) {
            return this.wt - e2.wt;
        }
    }

    static void createGraph(ArrayList<Edge> graph, int V, int E, Scanner scanner) {
        for (int i = 0; i < E; i++) {
            System.out.print("Enter source vertex of edge " + (i + 1) + ": ");
            int src = scanner.nextInt();
            System.out.print("Enter destination vertex of edge " + (i + 1) + ": ");
            int dest = scanner.nextInt();
            System.out.print("Enter weight of edge " + (i + 1) + ": ");
            int wt = scanner.nextInt();

            graph.add(new Edge(src, dest, wt));
        }
    }

    static class Pair implements Comparable<Pair> {
        int v;
        int cost;

        public Pair(int v, int c) {
            this.v = v;
            this.cost = c;
        }

        @Override
        public int compareTo(Pair p2) {
            return this.cost - p2.cost;
        }
    }

    public static void prims(ArrayList<Edge> graph, int V) {
        boolean[] vis = new boolean[V];
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        pq.add(new Pair(0, 0));
        int finalCost = 0;

        while (!pq.isEmpty()) {
            Pair curr = pq.remove();
            if (!vis[curr.v]) {
                vis[curr.v] = true;
                finalCost += curr.cost;

                for (int i = 0; i < graph.size(); i++) {
                    Edge e = graph.get(i);
                    if (e.src == curr.v) {
                        pq.add(new Pair(e.dest, e.wt));
                    }
                }
            }
        }

        System.out.println("Final cost of MST = " + finalCost);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of vertices: ");
        int V = scanner.nextInt();

        System.out.print("Enter the number of edges: ");
        int E = scanner.nextInt();

        ArrayList<Edge> graph = new ArrayList<>();
        createGraph(graph, V, E, scanner);

        prims(graph, V);

        scanner.close();
    }
}