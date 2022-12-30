package com.ai.astar.domain.searchstrategy;

import com.ai.astar.domain.Node;

import java.util.PriorityQueue;
import java.util.Set;

public class HorizontalVerticalChecker extends MapChecker {

    private final int hvCost;

    public HorizontalVerticalChecker(Node[][] searchArea, PriorityQueue<Node> openList, Set<Node> closedSet, int hvCost) {
        super(searchArea, openList, closedSet);
        this.hvCost = hvCost;
    }

    @Override
    public void checkNode(Node currentNode, int col, int row) {
        check(currentNode, col, row, hvCost);
    }
}
