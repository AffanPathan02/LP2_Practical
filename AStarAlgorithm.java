import java.util.*;

class Node {
    private int x;
    private int y;
    private int gCost;
    private int hCost;
    private int fCost;
    private Node parent;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.gCost = 0;
        this.hCost = 0;
        this.fCost = 0;
        this.parent = null;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getGCost() {
        return gCost;
    }

    public void setGCost(int gCost) {
        this.gCost = gCost;
    }

    public int getHCost() {
        return hCost;
    }

    public void setHCost(int hCost) {
        this.hCost = hCost;
    }

    public int getFCost() {
        return fCost;
    }

    public void setFCost(int fCost) {
        this.fCost = fCost;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }
}

public class AStarAlgorithm {
    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private int numRows;
    private int numCols;
    private Node[][] grid;
    private Node startNode;
    private Node targetNode;

    public AStarAlgorithm(int numRows, int numCols) {
        this.numRows = numRows;
        this.numCols = numCols;
        this.grid = new Node[numRows][numCols];
    }

    public void initializeGrid() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the grid values:");
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                System.out.print("Value at position (" + i + ", " + j + "): ");
                int value = scanner.nextInt();
                grid[i][j] = new Node(i, j);

                if (value == 1) {
                    grid[i][j] = null;  // Mark obstacle nodes as null
                }
            }
        }

        System.out.print("Enter the start node coordinates (row col): ");
        int startX = scanner.nextInt();
        int startY = scanner.nextInt();
        startNode = grid[startX][startY];

        System.out.print("Enter the target node coordinates (row col): ");
        int targetX = scanner.nextInt();
        int targetY = scanner.nextInt();
        targetNode = grid[targetX][targetY];

        scanner.close();
    }

    public List<Node> findPath() {
        if (startNode == null || targetNode == null) {
            System.out.println("Invalid start or target node!");
            return null;
        }

        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(Node::getFCost));
        Set<Node> closedSet = new HashSet<>();

        startNode.setGCost(0);
        startNode.setHCost(calculateHeuristic(startNode, targetNode));
        startNode.setFCost(startNode.getHCost());
        openSet.add(startNode);

        while (!openSet.isEmpty()) {
            Node currentNode = openSet.poll();
            closedSet.add(currentNode);

            if (currentNode == targetNode) {
                return constructPath(currentNode);
            }

            for (int[] direction : DIRECTIONS) {
                int newX = currentNode.getX() + direction[0];
                int newY = currentNode.getY() + direction[1];

                if (isValidPosition(newX, newY)) {
                    Node neighbor = grid[newX][newY];

                    if (neighbor == null || closedSet.contains(neighbor)) {
                        continue;
                    }

                    int tentativeGCost = currentNode.getGCost() + 1;

                    if (!openSet.contains(neighbor) || tentativeGCost < neighbor.getGCost()) {
                        neighbor.setParent(currentNode);
                        neighbor.setGCost(tentativeGCost);
                        neighbor.setHCost(calculateHeuristic(neighbor, targetNode));
                        neighbor.setFCost(neighbor.getGCost() + neighbor.getHCost());

                        if (!openSet.contains(neighbor)) {
                            openSet.add(neighbor);
                        }
                    }
                }
            }
        }

        System.out.println("No path found from start to target!");
        return null;
    }

    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < numRows && y >= 0 && y < numCols;
    }

    private int calculateHeuristic(Node node, Node target) {
        return Math.abs(node.getX() - target.getX()) + Math.abs(node.getY() - target.getY());
    }

    private List<Node> constructPath(Node targetNode) {
        List<Node> path = new ArrayList<>();
        Node currentNode = targetNode;

        while (currentNode != null) {
            path.add(currentNode);
            currentNode = currentNode.getParent();
        }

        Collections.reverse(path);
        return path;
    }

    public void printGrid() {
        System.out.println("Grid:");
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (grid[i][j] == null) {
                    System.out.print("X ");
                } else if (grid[i][j] == startNode) {
                    System.out.print("S ");
                } else if (grid[i][j] == targetNode) {
                    System.out.print("T ");
                } else {
                    System.out.print("O ");
                }
            }
            System.out.println();
        }
    }

    public void printPath(List<Node> path) {
        System.out.println("\nPath:");
        if (path != null) {
            for (Node node : path) {
                System.out.println("(" + node.getX() + ", " + node.getY() + ")");
            }
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of rows in the grid: ");
        int numRows = scanner.nextInt();

        System.out.print("Enter the number of columns in the grid: ");
        int numCols = scanner.nextInt();

        AStarAlgorithm algorithm = new AStarAlgorithm(numRows, numCols);
        algorithm.initializeGrid();
        algorithm.printGrid();

        List<Node> path = algorithm.findPath();
        algorithm.printPath(path);

        scanner.close();
    }
}
