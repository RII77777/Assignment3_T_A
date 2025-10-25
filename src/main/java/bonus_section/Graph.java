package bonus_section;

import java.util.*;

public class Graph {
    public List<String> vertices;
    public List<Edge> edges;
    private Map<String, List<Edge>> adjList;

    public Graph(List<String> vertices, List<Edge> edges) {
        this.vertices = vertices;
        this.edges = edges;
        adjList = new HashMap<>();
        for (String v : vertices) {
            adjList.put(v, new ArrayList<>());
        }
        for (Edge e : edges) {
            adjList.get(e.from).add(e);
            adjList.get(e.to).add(new Edge(e.to, e.from, e.weight));
        }
    }

    public int V() {
        return vertices.size();
    }

    public List<Edge> adjEdges(String v) {
        return adjList.getOrDefault(v, new ArrayList<>());
    }
}
