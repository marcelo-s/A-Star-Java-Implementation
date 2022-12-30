package com.ai.astar.domain.searchstrategy;

import com.ai.astar.domain.Node;

import java.util.PriorityQueue;
import java.util.Set;

public abstract class MapChecker implements MapCheck {
    protected final Node[][] searchArea;
    protected final PriorityQueue<Node> openList;
    protected final Set<Node> closedSet;
    protected final int columns;

    protected MapChecker(Node[][] searchArea, PriorityQueue<Node> openList, Set<Node> closedSet) {
        this.searchArea = searchArea;
        this.openList = openList;
        this.closedSet = closedSet;
        this.columns = searchArea == null ? 0 : searchArea[0].length;
    }

    void check(Node currentNode, int col, int row, int cost) {
        if (row < 0 || row >= searchArea.length) {
            return;
        }
        if (col < 0 || col >= columns) {
            return;
        }
        Node adjacentNode = searchArea[row][col];
        if (adjacentNode.isBlocked() || closedSet.contains(adjacentNode)) {
            return;
        }
        if (!openList.contains(adjacentNode)) {
            adjacentNode.updateNode(currentNode, cost);
            openList.add(adjacentNode);
        } else {
            boolean changed = adjacentNode.checkBetterPath(currentNode, cost);
            if (changed) {
                // Remove and Add the changed node, so that the PriorityQueue can sort again its
                // contents with the modified "finalCost" value of the modified node
                openList.remove(adjacentNode);
                openList.add(adjacentNode);
            }
        }
    }

}
