package com.ai.astar.domain.searchstrategy;

import com.ai.astar.domain.Node;

import java.util.PriorityQueue;
import java.util.Set;

public class ManhattanMapChecker extends MapChecker {

    private final int manhattanCost;

    public ManhattanMapChecker(Node[][] searchArea, PriorityQueue<Node> openList, Set<Node> closedSet, int manhattanCost) {
        super(searchArea, openList, closedSet);
        this.manhattanCost = manhattanCost;
    }

    @Override
    public void checkNode(Node currentNode, int col, int row) {
        check(currentNode, col, row, manhattanCost);
    }
}
