package com.ai.astar.domain.searchstrategy;

import com.ai.astar.domain.Node;

import java.util.PriorityQueue;
import java.util.Set;

public class NoOpChecker extends MapChecker {

    public NoOpChecker(Node[][] searchArea, PriorityQueue<Node> openList, Set<Node> closedSet) {
        super(searchArea, openList, closedSet);
    }

    @Override
    public void checkNode(Node currentNode, int col, int row) {
        // nothing to do
    }
}
