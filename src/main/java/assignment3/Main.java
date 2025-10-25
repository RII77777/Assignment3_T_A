package assignment3;

import com.google.gson.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Main {
    static class InputGraph {
        int id;
        List<String> nodes;
        List<Map<String, Object>> edges;
    }

    static class InputRoot {
        List<InputGraph> graphs;
    }

    public static void main(String[] args) throws Exception {
        String inFile = args.length > 0 ? args[0] : "input.json";
        String outFile = args.length > 1 ? args[1] : "output.json";

        String json = new String(Files.readAllBytes(Paths.get(inFile)));
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        InputRoot root = gson.fromJson(json, InputRoot.class);

        JsonObject outRoot = new JsonObject();
        JsonArray results = new JsonArray();

        for (InputGraph ig : root.graphs) {
            List<String> nodes = ig.nodes;
            List<edge> edges = new ArrayList<>();
            for (Map<String, Object> e : ig.edges) {
                String from = (String) e.get("from");
                String to = (String) e.get("to");
                Number wnum = (Number) e.get("weight");
                int w = wnum.intValue();
                edges.add(new edge(from, to, w));
            }
            graph g = new graph(nodes, edges);

            JsonObject resObj = new JsonObject();
            resObj.addProperty("graph_id", ig.id);
            JsonObject inputStats = new JsonObject();
            inputStats.addProperty("vertices", g.V());
            inputStats.addProperty("edges", g.E());
            resObj.add("input_stats", inputStats);

            minimumspanningtree_algorithms.Result primRes = minimumspanningtree_algorithms.prim(g, nodes.get(0));
            JsonObject primObj = new JsonObject();
            JsonArray primEdges = new JsonArray();
            for (edge e : primRes.mstEdges) primEdges.add(gson.fromJson(e.toString(), JsonObject.class));
            primObj.add("mst_edges", primEdges);
            primObj.addProperty("total_cost", primRes.totalCost);
            primObj.addProperty("operations_count", primRes.operations);
            primObj.addProperty("execution_time_ms", primRes.executionTimeMs);
            resObj.add("prim", primObj);

            minimumspanningtree_algorithms.Result kruskalRes = minimumspanningtree_algorithms.kruskal(g);
            JsonObject krObj = new JsonObject();
            JsonArray krEdges = new JsonArray();
            for (edge e : kruskalRes.mstEdges) krEdges.add(gson.fromJson(e.toString(), JsonObject.class));
            krObj.add("mst_edges", krEdges);
            krObj.addProperty("total_cost", kruskalRes.totalCost);
            krObj.addProperty("operations_count", kruskalRes.operations);
            krObj.addProperty("execution_time_ms", kruskalRes.executionTimeMs);
            resObj.add("kruskal", krObj);

            results.add(resObj);
        }

        outRoot.add("results", results);
        try (Writer w = new FileWriter(outFile)) {
            gson.toJson(outRoot, w);
        }

        System.out.println("Minimum spanning tree computation completed and results in" + outFile);
    }
}
