# A-Star-Java-Implementation
A* also called A Star, algorithm java implementation

This is a java implementation of the A Star algorithm. I couldn't find any good java implementations of this famous AI algorithm on the web so I decided to make my own. 

The implementation includes 3 files:
  - AStar.java : Main algorithm class.
  - Node.java : Class for the nodes used by the algorithm.
  - AStarTest.java : Class with a main method and a simple test for the algorithm implementation

## Search Area

              0   1   2   3   4   5   6
         0    -   -   -   -   -   -   -
         1    -   -   -   B   -   -   -
         2    -   I   -   B   -   F   -
         3    -   -   -   B   -   -   -
         4    -   -   -   -   -   -   -
         5    -   -   -   -   -   -   -

## Search Path with diagonals

              0   1   2   3   4   5   6
         0    -   -   -   *   -   -   -
         1    -   -   *   B   *   -   -
         2    -   I*  -   B   -  *F   -
         3    -   -   -   B   -   -   -
         4    -   -   -   -   -   -   -
         5    -   -   -   -   -   -   -

## Search Path without diagonals

              0   1   2   3   4   5   6
         0    -   -   *   *   *   -   -
         1    -   -   *   B   *   -   -
         2    -   I*  *   B   *  *F   -
         3    -   -   -   B   -   -   -
         4    -   -   -   -   -   -   -
         5    -   -   -   -   -   -   -
