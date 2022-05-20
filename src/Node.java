
import java.util.ArrayList;
import java.util.List;

public class Node implements Comparable<Node> {
    public String id;

    // Parent in the path
    public Node parent = null;

    public List<Edge> neighbors;

    // Evaluation functions
    public double f = Double.MAX_VALUE;
    public double g = Double.MAX_VALUE;
    // Hardcoded heuristic
    public double h;

    Node(double h, String id) {
        this.h = h;
        this.id = id;
        this.neighbors = new ArrayList<>();
    }

    @Override
    public int compareTo(Node n) {
        return Double.compare(this.f, n.f);
    }

    public static class Edge {

        Edge(int weight, Node node) {
            this.weight = weight;
            this.node = node;
        }

        public int weight;
        public Node node;
    }

    public void addBranch(int weight, Node node) {
        Edge newEdge = new Edge(weight, node);
        neighbors.add(newEdge);
    }

    public double calculateHeuristic(Node target) {
        return this.h;
    }
    
    @Override
    public String toString(){
        return id;
    }
}
