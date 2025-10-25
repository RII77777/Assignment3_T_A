package assignment3;

import java.util.*;

public class graph {
    public final List<String> vertices;
    public final List<edge> edges;
    private final Map<String, Integer> index;

    public graph(List<String> vertices, List<edge> edges) {
        this.vertices = new ArrayList<>(vertices);
        this.edges = new ArrayList<>(edges);
        index = new HashMap<>();
        for (int i = 0; i < this.vertices.size(); i++) index.put(this.vertices.get(i), i);
    }

    public int V() { return vertices.size(); }
    public int E() { return edges.size(); }

    public List<edge> adjEdges(String v) {
        List<edge> out = new ArrayList<>();
        for (edge e : edges) {
            if (e.from.equals(v) || e.to.equals(v)) out.add(e);
        }
        return out;
    }

    public Integer vertexIndex(String v) {
        return index.get(v);
    }
}
