# Optimization of a City Transportation Network (Minimum Spanning Tree)

## Introduction
This project focuses on optimizing a city transportation network using Minimum Spanning Tree algorithms. The goal is to connect all city districts which represented as graph nodes with the minimum total construction cost while avoiding redundant roads.

To achieve this, I implemented and compared two classical MST algorithms:
1) Prim’s Algorithm
2) Kruskal’s Algorithm

## Assignment Objectives
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

## Purpose
The purpose of this assignment is to simulate the optimization of a city transportation system by determining the minimum set of roads required to connect all districts at the lowest total cost. The project also includes performance evaluation and comparison of Prim’s and Kruskal’s algorithms.

## Project Structure

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

## Implementation Details

1) The MST algorithms (Prim and Kruskal) are implemented in: `minimumspannigtree_algorithms.java`
2) The program is executed using: `Main.java`
3) Kruskal’s algorithm uses the Disjoint Set Union (DSU) structure implemented in: `DSU.java`
4) Graph and Edge structures are defined in: `graph.java` and `edge.java`

## Example input format (input.json)

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

How to run the program

Open the project in IntelliJ IDEA.
Ensure input.json is located in the project root directory.
Run Main.java.
The results will be written to output.json.

Algorithm Comparison Table (based on output.json)
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

--

Bonus Section
Custom Graph Design

This additional section is implemented separately in the /bonus_section folder.
It includes:
- custom Graph.java and Edge.java;
- adapted Prims and Kruskal’s algorithms working with the custom Graph structure;
- a test case demonstrating proper graph loading and Minimum Spanning Tree computation;
- a visual representation of the graph;


Minimum Spanning Tree Integration Example
The `BonusMain.java` file loads the graph using the custom `Graph` and successfully runs Prim’s and Kruskal’s algorithms.
screenshot in github
1. Project folder showing `Graph.java` and `Edge.java`  
2. Code preview of `Graph.java`  
3. Execution output of `BonusMain.java` confirming MST generation

Implemented as an optional task for additional credit.


Araizhan Tazhimova