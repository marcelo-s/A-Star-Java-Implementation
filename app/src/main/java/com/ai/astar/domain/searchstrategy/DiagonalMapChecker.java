package com.ai.astar.domain.searchstrategy;

import com.ai.astar.domain.Node;

import java.util.PriorityQueue;
import java.util.Set;

public class DiagonalMapChecker extends MapChecker {

    private final int diagonalCost;

    public DiagonalMapChecker(Node[][] searchArea, PriorityQueue<Node> openList, Set<Node> closedSet, int diagonalCost) {
        super(searchArea, openList, closedSet);
        this.diagonalCost = diagonalCost;
    }

    @Override
    public void checkNode(Node currentNode, int col, int row) {
        check(currentNode, col - 1, row, diagonalCost);
        check(currentNode, col + 1, row, diagonalCost);
    }
}
