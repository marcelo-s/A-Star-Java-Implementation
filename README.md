# A-Star-Java-Implementation

A*, also called A Star, is a graph traversal and path search algorithm, which is used in many fields of computer science due to its completeness, optimality, and optimal efficiency.

The implementation includes several files:
  - AStar.java: Main algorithm class.
  - Node.java: Class for the nodes used by the algorithm.
  - searchstrategy package: Strategy pattern with several map search algorithms: 
    1. Diagonals - when enabled, also considers diagonal moves
    2. Manhattan distance - default mode, only considers vertical and horizontal moves


## A* specifications

- For node n, gScore[n] is the cost of the cheapest path currently known from start to n.
- For node n, fScore[n] = gScore[n] + heuristic(n), where gScore(n) is the distance from the starting node to n
    and h(n) is a heuristic value of estimation distance from node n to finish node.
- h(n) is a heuristic value of estimation distance from node n to finish node

## Example provided

### Search Area

              0   1   2   3   4   5   6
         0    -   -   -   -   -   -   -
         1    -   -   -   B   -   -   -
         2    -   I   -   B   -   F   -
         3    -   -   -   B   -   -   -
         4    -   -   -   -   -   -   -
         5    -   -   -   -   -   -   -

### Search Path with diagonals

              0   1   2   3   4   5   6
         0    -   -   -   *   -   -   -
         1    -   -   *   B   *   -   -
         2    -   I*  -   B   -  *F   -
         3    -   -   -   B   -   -   -
         4    -   -   -   -   -   -   -
         5    -   -   -   -   -   -   -

### Search Path without diagonals

              0   1   2   3   4   5   6
         0    -   -   *   *   *   -   -
         1    -   -   *   B   *   -   -
         2    -   I*  *   B   *  *F   -
         3    -   -   -   B   -   -   -
         4    -   -   -   -   -   -   -
         5    -   -   -   -   -   -   -
