package bonus_section;

import assignment3.minimumspanningtree_algorithms;
import assignment3.edge;
import assignment3.graph;

import java.util.*;

public class BonusMain {
    public static void main(String[] args) {
        List<String> nodes = Arrays.asList("A", "B", "C", "D");
        List<Edge> bonusEdges = Arrays.asList(
                new Edge("A", "B", 1),
                new Edge("A", "C", 4),
                new Edge("B", "C", 2),
                new Edge("C", "D", 3),
                new Edge("B", "D", 5)
        );

        Graph bg = new Graph(nodes, bonusEdges);

        List<edge> convertedEdges = new ArrayList<>();
        for (Edge be : bonusEdges) {
            convertedEdges.add(new edge(be.from, be.to, be.weight));
        }
        graph g = new graph(nodes, convertedEdges);

        System.out.println("Prim on custom Graph:");
        minimumspanningtree_algorithms.Result primResult =
                minimumspanningtree_algorithms.prim(g, "A");
        for (edge e : primResult.mstEdges) {
            System.out.println(e.from + " - " + e.to + " : " + e.weight);
        }

        System.out.println("Kruskal on custom Graph:");
        minimumspanningtree_algorithms.Result kruskalResult =
                minimumspanningtree_algorithms.kruskal(g);
        for (edge e : kruskalResult.mstEdges) {
            System.out.println(e.from + " - " + e.to + " : " + e.weight);
        }
    }
}
