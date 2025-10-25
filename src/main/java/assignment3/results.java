package assignment3;

import com.google.gson.*;
import java.io.*;
import java.nio.file.*;

public class results {
    public static void writeCsvFromOutput(String outJsonPath, String csvPath) throws Exception {
        String json = new String(Files.readAllBytes(Paths.get(outJsonPath)));
        JsonObject root = JsonParser.parseString(json).getAsJsonObject();
        JsonArray results = root.getAsJsonArray("results");

        try (PrintWriter pw = new PrintWriter(new FileWriter(csvPath))) {
            pw.println("graph_id,algorithm,total_cost,operations,execution_time_ms");
            for (JsonElement el : results) {
                JsonObject robj = el.getAsJsonObject();
                int gid = robj.get("graph_id").getAsInt();

                JsonObject prim = robj.getAsJsonObject("prim");
                pw.printf("%d,Prim,%d,%d,%.6f%n",
                        gid,
                        prim.get("total_cost").getAsInt(),
                        prim.get("operations_count").getAsLong(),
                        prim.get("execution_time_ms").getAsDouble());

                JsonObject kr = robj.getAsJsonObject("kruskal");
                pw.printf("%d,Kruskal,%d,%d,%.6f%n",
                        gid,
                        kr.get("total_cost").getAsInt(),
                        kr.get("operations_count").getAsLong(),
                        kr.get("execution_time_ms").getAsDouble());
            }
        }
    }
}
