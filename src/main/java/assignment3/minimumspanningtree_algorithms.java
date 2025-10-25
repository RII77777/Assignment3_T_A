package assignment3;

import java.util.*;

public class minimumspanningtree_algorithms {

    public static class Result {
        public final List<edge> mstEdges = new ArrayList<>();
        public int totalCost = 0;
        public long operations = 0;
        public double executionTimeMs = 0.0;
    }

    public static Result prim(graph g, String start) {
        Result res = new Result();
        long ops = 0;
        long t0 = System.nanoTime();

        int n = g.V();
        if (n == 0) { res.executionTimeMs = 0; return res; }

        Set<String> inMST = new HashSet<>();
        PriorityQueue<edge> pq = new PriorityQueue<>();

        inMST.add(start);
        List<edge> adj0 = g.adjEdges(start);
        pq.addAll(adj0);
        ops += adj0.size();

        while (!pq.isEmpty() && inMST.size() < n) {
            edge e = pq.poll(); ops++;
            String u = e.from, v = e.to;
            String newV = null;
            if (inMST.contains(u) && !inMST.contains(v)) newV = v;
            else if (inMST.contains(v) && !inMST.contains(u)) newV = u;
            else continue;
            res.mstEdges.add(e);
            res.totalCost += e.weight;
            inMST.add(newV);
            List<edge> adj = g.adjEdges(newV);
            pq.addAll(adj);
            ops += adj.size();
            ops++;
        }

        long t1 = System.nanoTime();
        res.operations = ops;
        res.executionTimeMs = (t1 - t0) / 1_000_000.0;

        res.mstEdges.sort(Comparator
                .comparingInt((edge ed) -> ed.weight)
                .thenComparing(ed -> ed.from)
                .thenComparing(ed -> ed.to));

        return res;
    }

    public static Result kruskal(graph g) {
        Result res = new Result();
        long ops = 0;
        long t0 = System.nanoTime();

        int n = g.V();
        List<edge> all = new ArrayList<>(g.edges);
        Collections.sort(all);
        if (all.size() > 0) ops += all.size() * (long) (Math.log(Math.max(2, all.size())));

        DSU dsu = new DSU(n);
        Map<String, Integer> idx = new HashMap<>();
        for (int i = 0; i < g.vertices.size(); i++) idx.put(g.vertices.get(i), i);

        for (edge e : all) {
            ops++;
            Integer a = idx.get(e.from);
            Integer b = idx.get(e.to);
            if (a == null || b == null) continue;
            if (dsu.find(a) != dsu.find(b)) {
                dsu.union(a, b);
                res.mstEdges.add(e);
                res.totalCost += e.weight;
                ops += 2;
                if (res.mstEdges.size() == n - 1) break;
            }
        }

        long t1 = System.nanoTime();
        res.operations = ops + dsu.finds + dsu.unions;
        res.executionTimeMs = (t1 - t0) / 1_000_000.0;

        res.mstEdges.sort(Comparator
                .comparingInt((edge ed) -> ed.weight)
                .thenComparing(ed -> ed.from)
                .thenComparing(ed -> ed.to));

        return res;
    }
}
