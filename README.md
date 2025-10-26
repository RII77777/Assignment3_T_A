Assignment 3

Optimization of a City Transportation Network (Minimum Spanning Tree)

Introduction

This project implements and analyzes two Minimum Spanning Tree algorithms: Prim’s algorithm and Kruskal’s algorithm. Their performance was compared using measurements such as total Minimum Spanning Tree cost, operation count, and execution time. Additionally, unit testing and a custom graph implementation were included to ensure algorithmic reliability and scalability.

Objectives
According to the assignment requirements, this implementation:
1. Reads graph data from an input JSON file.
2. Uses the provided file format to create custom test JSON files.
3. Implements Prim’s and Kruskal’s algorithms.
4. For each algorithm, calculates and reports:
   1) list of edges forming the MST;
   2) total MST cost;
   3) number of vertices and edges in the input graph;
   4) number of algorithmic operations;
   5) execution time in milliseconds;
5. Compares the performance results of both algorithms.

Purpose
The purpose of this assignment is to simulate the optimization of a city transportation system by determining the minimum set of roads required to connect all districts at the lowest total cost. The project also includes performance evaluation and comparison of Prim’s and Kruskal’s algorithms.

Project Structure

assignment3/

 /src/main/java/assignment3/

|DSU.java|

|edge.java|

|graph.java|

|Main.java|

|minimumspannigtree_algorithms.java|

|results|

_

 /src/test/java/assignment3/

|minimumspanningtree_algorithms_test|

_

/testcases/

|testdisconnected.json|

|testlarge.json|

|testmedium.json|

|testsmall.json|

_

|input.json|

|output.json|

|pom.xml|

|README.md|

|.gitignore|

_

/bonus_section/

|Graph.java|

|Edge.java|

|BonusMain.java|

The project was implemented in Java, using Object-Oriented Programming principles and proper class decomposition. The implementation included the following files: 

'graph.java' defines the graph structure (vertices, edges, adjacency). 

'edge.java' represents an edge with two endpoints and a weight. 

'minimumspanningtree_algorithms.java' implements both Prim’s and Kruskal’s algorithms. 

'main.java' loads input data from input.json and writes results to output.json. 

'minimumspanningtree_algorithms_test.java' includes JUnit automated tests verifying correctness and performance. 

For the bonus section, a custom graph structure was designed in: 

bonus_section/Graph.java 

bonus_section/Edge.java 

bonus_section/BonusMain.java 

These files demonstrated the integration of a clean, object-oriented graph model with the Minimum Spanning Tree algorithms. 

Input Data and Testing Setup 

Three types of input datasets were created in “json” format: 
Small graphs (4-6 vertices) - used for correctness testing and debugging. 
Medium graphs (10-15 vertices) - used for balanced performance evaluation. 
Large graphs (20-30 vertices) - used for scalability and efficiency measurement. 
Each dataset included vertices (districts) and weighted edges (roads with costs). Example small dataset output: 

Prim on custom Graph: A - B: 1 B - C: 2 C - D: 3 
Kruskal on custom Graph: A - B: 1 B - C: 2 C - D: 3 
This confirmed that both algorithms produced identical Minimum Spanning Tree total costs.

Example input format (input.json)

```json
{
  "graphs": [
    {
      "id": 1,
      "nodes": ["A", "B", "C", "D", "E"],
      "edges": [
        { "from": "A", "to": "B", "weight": 4 },
        { "from": "A", "to": "C", "weight": 3 },
        { "from": "B", "to": "C", "weight": 2 },
        { "from": "B", "to": "D", "weight": 5 },
        { "from": "C", "to": "D", "weight": 7 },
        { "from": "C", "to": "E", "weight": 8 },
        { "from": "D", "to": "E", "weight": 6 }
      ]
    }
  ]
}

Algorithm Comparison (based on output.json)

1st Graph (graph_id: 1)
| Algorithm | Minimum Spanning Tree Total Cost | Operations Count | Execution Time (ms) | Minimum Spanning Tree Edges            | Notes                 |
| Prim      | 16                               | 27               | 0.7977              | (B-C, A-C, B-D, D-E)                   | Lower operation count |
| Kruskal   | 16                               | 47               | 0.9282              | (B-C, A-C, B-D, D-E)                   | Requires edge sorting |
2nd Graph (graph_id: 2)
| Algorithm | Minimum Spanning Tree Total Cost | Operations Count | Execution Time (ms) | Minimum Spanning Tree Edges       | Notes                                  |
| Prim      | 6                                | 18               | 0.0624              | (A-B, B-C, C-D)                   | Performs efficiently on smaller graphs |
| Kruskal   | 6                                | 33               | 0.0316              | (A-B, B-C, C-D)                   | Slightly faster in this case           |
Averaged Results
| Algorithm | Average Minimum Spanning Tree Total Cost | Average Operations Count | Average Execution Time (ms) | Notes                                     |
| Prim      | 11                                       | 22.5                     | 0.43005                     | More efficient operation overall          |
| Kruskal   | 11                                       | 40                       | 0.4799                      | Depends heavily on sorting and union-find |

Both Prim’s and Kruskal’s algorithms successfully produced MSTs with equal total costs, proving the accuracy of the implementations. However, Prim’s algorithm showed lower operation counts in both graphs, while Kruskal was slightly faster in execution time for smaller graphs, suggesting that their efficiency depends on graph size and edge density.

Automated Testing

JUnit tests verified that:
- both algorithms consistently produce MSTs with identical total cost;
- each resulting MST contains exactly V-1 edges;
- the MST is acyclic and fully connected;
- operation counts and execution times are valid and non-negative;
- disconnected graphs are correctly reported without causing runtime errors.
All tests passed successfully, confirming the reliability and correctness of both implementations.

Analysis and Interpretation

Both Prim’s and Kruskal’s algorithms produced the same Minimum Spanning Tree total cost (6), which confirms the correctness of the implementations. Kruskal’s algorithm executed faster on smaller graphs due to efficient edge sorting and fewer union-find operations. However, Prim’s algorithm demonstrated more consistent performance as graph density increased, benefitting from adjacency-based edge selection.
From a theoretical perspective, these observations align with known algorithmic properties: Kruskal’s algorithm is better suited for sparse graphs, where edge sorting dominates but union operations remain minimal; Prim’s algorithm is more efficient on dense graphs, where priority queue operations become more favorable.
In real-world applications, algorithm selection depends on network characteristics.
For example in compact urban infrastructure networks (dense graphs), Prim’s algorithm often offers better scalability; in large, sparsely connected districts (sparse graphs), Kruskal’s algorithm provides a simpler and faster solution.
The experimental results, therefore, confirm both theoretical expectations and practical applicability, demonstrating that algorithm performance is closely tied to graph density and structure.

--

Bonus Section

Custom Graph Design
This additional section is implemented separately in the /bonus_section folder.
It includes:
- custom Graph.java and Edge.java;
- adapted Prims and Kruskal’s algorithms working with the custom Graph structure;
- a test case demonstrating proper graph loading and Minimum Spanning Tree computation;
- a visual representation of the graph;
Minimum Spanning Tree integration example
The `BonusMain.java` file loads the graph using the custom `Graph` and successfully runs Prim’s and Kruskal’s algorithms.
1. Project folder showing `Graph.java` and `Edge.java`  
2. Code preview of `Graph.java`  
3. Execution output of `BonusMain.java` confirming MST generation
Implemented as an optional task for additional credit.
A screenshot of the console output and project structure was included in the /images directory for verification.

Conclusion
The experimental results confirm theoretical expectations: Kruskal’s algorithm is more efficient on sparse graphs, while Prim’s demonstrates better scalability for dense structures. Both implementations consistently produced identical MST costs, validating correctness. The integration of automated testing and a custom graph structure further demonstrated a solid understanding of algorithmic design, testing, and performance evaluation.

Araizhan Tazhimova
