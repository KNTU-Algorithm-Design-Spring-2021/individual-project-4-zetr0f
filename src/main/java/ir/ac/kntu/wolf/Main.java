package ir.ac.kntu.wolf;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

class Edge {
    public final int source, dest;

    private Edge(int source, int dest) {
        this.source = source;
        this.dest = dest;
    }

    public static Edge of(int a, int b) {
        return new Edge(a, b);        // calls private constructor
    }
}

class Graph {
    List<List<Integer>> adjList = null;

    Graph(List<Edge> edges, int N) {
        adjList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            adjList.add(new ArrayList<>());
        }

        for (Edge edge : edges) {
            adjList.get(edge.source).add(edge.dest);
        }
    }
}

class Main {
    public static boolean isConnected(Graph graph, int src, int dest,
                                      boolean[] discovered, Stack<Integer> path) {//dfs
        discovered[src] = true;

        path.add(src);

        if (src == dest) {
            return true;
        }

        // do for every edge `src —> i`
        for (int i : graph.adjList.get(src)) {
            if (!discovered[i]) {
                if (isConnected(graph, i, dest, discovered, path)) {
                    return true;
                }
            }
        }

        path.pop();

        return false;
    }

    public static void main(String[] args) {
        List<Edge> edges = Arrays.asList(Edge.of(0, 3), Edge.of(0, 7), Edge.of(1, 0), Edge.of(1, 2),
                Edge.of(1, 4), Edge.of(2, 7), Edge.of(3, 4),
                Edge.of(3, 5), Edge.of(4, 3), Edge.of(4, 6),
                Edge.of(5, 6), Edge.of(6, 7));

        int N = 8;

        Graph graph = new Graph(edges, N);

        boolean[] discoveredW = new boolean[N];
        boolean[] discoveredS = new boolean[N];

        int src = 0, dest = 7;

        Stack<Integer> pathW = new Stack<>();
        Stack<Integer> pathS = new Stack<>();

        if (isConnected(graph, src, dest, discoveredW, pathW)) {
            for (int i = 0; i < pathW.size() - 1; i++) {
                graph.adjList.get(pathW.get(i)).remove(pathW.get(i + 1));
            }
            if (isConnected(graph, src, dest, discoveredS, pathS)) {
                System.out.println("2 disjoint path exists from vertex " + src +
                        " to vertex " + dest);
                System.out.println("The path for wolf is " + pathW);
                System.out.println("The path for sheep is " + pathS);
            }
        } else {
            System.out.println("No 2 disjoint path exists between vertices " + src +
                    " and " + dest);
        }
    }
}
