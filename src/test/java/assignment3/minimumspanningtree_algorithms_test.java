package assignment3;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

public class minimumspanningtree_algorithms_test {

    @Test
    public void testsmallgraphequality() {
        List<String> nodes = Arrays.asList("A", "B", "C", "D");
        List<edge> edges = Arrays.asList(
                new edge("A", "B", 1),
                new edge("A", "C", 4),
                new edge("B", "C", 2),
                new edge("C", "D", 3),
                new edge("B", "D", 5)
        );
        graph g = new graph(nodes, edges);
        minimumspanningtree_algorithms.Result primResult =
                minimumspanningtree_algorithms.prim(g, "A");
        minimumspanningtree_algorithms.Result kruskalResult =
                minimumspanningtree_algorithms.kruskal(g);

        assertEquals(primResult.totalCost, kruskalResult.totalCost);
        assertTrue(primResult.mstEdges.size() <= g.V() - 1);
        assertTrue(kruskalResult.mstEdges.size() <= g.V() - 1);
        assertTrue(primResult.executionTimeMs >= 0.0);
        assertTrue(kruskalResult.executionTimeMs >= 0.0);
    }

    @Test
    public void testdisconnectedgraph() {
        List<String> nodes = Arrays.asList("A", "B", "C");
        List<edge> edges = Arrays.asList(
                new edge("A", "B", 1)
        );
        graph g = new graph(nodes, edges);
        minimumspanningtree_algorithms.Result primResult =
                minimumspanningtree_algorithms.prim(g, "A");
        minimumspanningtree_algorithms.Result kruskalResult =
                minimumspanningtree_algorithms.kruskal(g);

        assertTrue(primResult.mstEdges.size() < g.V() - 1 ||
                kruskalResult.mstEdges.size() < g.V() - 1);
    }

    @Test
    public void testacyclicandconnected() {
        List<String> nodes = Arrays.asList("A", "B", "C", "D");
        List<edge> edges = Arrays.asList(
                new edge("A", "B", 1),
                new edge("B", "C", 2),
                new edge("C", "D", 3),
                new edge("A", "D", 10)
        );
        graph g = new graph(nodes, edges);
        minimumspanningtree_algorithms.Result primResult =
                minimumspanningtree_algorithms.prim(g, "A");

        DSU dsu = new DSU(g.V());
        Map<String, Integer> indexMap = new HashMap<>();
        for (int i = 0; i < g.vertices.size(); i++) {
            indexMap.put(g.vertices.get(i), i);
        }

        boolean cycleDetected = false;
        for (edge e : primResult.mstEdges) {
            int u = indexMap.get(e.from);
            int v = indexMap.get(e.to);
            if (dsu.find(u) == dsu.find(v)) {
                cycleDetected = true;
                break;
            }
            dsu.union(u, v);
        }

        assertFalse(cycleDetected);
    }
}
