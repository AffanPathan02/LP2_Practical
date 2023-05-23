import java.util.*;

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

    public static void bfs(ArrayList<Edge>[] graph) {
        Queue<Integer> q = new LinkedList<>();
        boolean visited[] = new boolean[graph.length];
        q.add(0); // source = 0

        while (!q.isEmpty()) {
            int current = q.remove();

            if (!visited[current]) {
                System.out.println(current + " ");
                visited[current] = true;
                for (int i = 0; i < graph[current].size(); i++) {
                    Edge e = graph[current].get(i);
                    q.add(e.dest);
                }
            }
        }
    }

    public static void dfs(ArrayList<Edge>[] graph, int current, boolean visited[]) {
        System.out.println(current + " ");
        visited[current] = true;

        for (int i = 0; i < graph[current].size(); i++) {
            Edge e = graph[current].get(i);
            if (!visited[e.dest]) {
                dfs(graph, e.dest, visited);
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        char ch1;
        System.out.println("Create A Graph");
        ArrayList<Edge>[] graph = createGraph();
        do{
            System.out.println("1. BFS");
            System.out.println("2. DFS");
            System.out.println("Enter your choice: ");

            int ch = sc.nextInt();

            if (ch == 1) {


                System.out.println("BFS Traversal is: ");
                bfs(graph);

            } else if (ch == 2) {
                System.out.println("Creating graph for DFS traversal:");
                System.out.println("DFS Traversal is: ");
                dfs(graph, 0, new boolean[graph.length]);

            }else {
                System.out.println("Invalid choice!");
            }

            System.out.print("Do you want to continue? (y/n): ");
            ch1=sc.next().charAt(0);
        }while(ch1 =='Y' ||ch1 =='y');



//        System.out.print("Do you want to continue? (y/n): ");
//        String continueChoice = sc.next();
//        if (!continueChoice.equalsIgnoreCase("y")) {
//            System.out.println("Program exited. Thank you!");
//        }



    }


}