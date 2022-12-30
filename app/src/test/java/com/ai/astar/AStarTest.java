package com.ai.astar;

import com.ai.astar.domain.AStar;
import com.ai.astar.domain.Node;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AStarTest {

    private final Node initialNode = Node.of(2, 1);
    private final Node finalNode = Node.of(2, 5);
    private final int rows = 6;
    private final int cols = 7;
    private final int[][] blocksArray = new int[][]{{1, 3}, {2, 3}, {3, 3}};

    @Test
    void shouldFindShortestPath_whenSearchingDiagonals() {
        AStar aStar = new AStar(rows, cols, initialNode, finalNode, blocksArray, true);
        List<Node> expected = new ArrayList<>();
        expected.add(Node.of(2, 1));
        expected.add(Node.of(1, 2));
        expected.add(Node.of(0, 3));
        expected.add(Node.of(1, 4));
        expected.add(Node.of(2, 5));
        List<Node> path = aStar.findPath();
        assertEquals(expected, path);
    }

    @Test
    void shouldFindShortestPath_whenSearchingHorizontalVertical() {
        AStar aStar = new AStar(rows, cols, initialNode, finalNode, blocksArray, false);
        List<Node> expected = new ArrayList<>();
        expected.add(Node.of(2, 1));
        expected.add(Node.of(2, 2));
        expected.add(Node.of(1, 2));
        expected.add(Node.of(0, 2));
        expected.add(Node.of(0, 3));
        expected.add(Node.of(0, 4));
        expected.add(Node.of(1, 4));
        expected.add(Node.of(2, 4));
        expected.add(Node.of(2, 5));
        List<Node> path = aStar.findPath();
        assertEquals(expected, path);
    }
}
