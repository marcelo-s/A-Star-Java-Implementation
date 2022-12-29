package com.ai.astar;

import java.util.*;

/**
 * A Star Algorithm
 *
 * @author Marcelo Surriabre
 * @version 2.1, 2017-02-23
 */
public class AStar {
    private static final int DEFAULT_HV_COST = 10; // Horizontal - Vertical Cost
    private static final int DEFAULT_DIAGONAL_COST = 14;
    private final int hvCost;
    private final int diagonalCost;
    private final Node[][] searchArea;
    private final PriorityQueue<Node> openList;
    private final Set<Node> closedSet;
    private final Node initialNode;
    private final Node finalNode;

    public AStar(int rows, int cols, Node initialNode, Node finalNode, int hvCost, int diagonalCost) {
        this.hvCost = hvCost;
        this.diagonalCost = diagonalCost;
        this.initialNode = initialNode;
        this.finalNode = finalNode;
        this.searchArea = new Node[rows][cols];
        this.openList = new PriorityQueue<>(Comparator.comparingInt(Node::f));
        initNodes();
        this.closedSet = new HashSet<>();
    }

    public AStar(int rows, int cols, Node initialNode, Node finalNode) {
        this(rows, cols, initialNode, finalNode, DEFAULT_HV_COST, DEFAULT_DIAGONAL_COST);
    }

    private void initNodes() {
        for (int i = 0; i < searchArea.length; i++) {
            for (int j = 0; j < searchArea[0].length; j++) {
                Node node = new Node(i, j);
                node.calculateHeuristic(finalNode);
                this.searchArea[i][j] = node;
            }
        }
    }

    public void initBlocks(int[][] blocksArray) {
        for (int[] ints : blocksArray) {
            int row = ints[0];
            int col = ints[1];
            this.searchArea[row][col].setAsBlocked();
        }
    }

    public List<Node> findPath() {
        openList.add(initialNode);
        while (!openList.isEmpty()) {
            Node currentNode = openList.poll();
            closedSet.add(currentNode);
            if (isFinalNode(currentNode)) {
                return generatePath(currentNode);
            } else {
                addAdjacentNodes(currentNode);
            }
        }
        return new ArrayList<>();
    }

    private List<Node> generatePath(Node currentNode) {
        List<Node> path = new ArrayList<>();
        path.add(currentNode);
        Node parent;
        while ((parent = currentNode.parent()) != null) {
            path.add(0, parent);
            currentNode = parent;
        }
        return path;
    }

    private void addAdjacentNodes(Node currentNode) {
        addAdjacentUpperRow(currentNode);
        addAdjacentMiddleRow(currentNode);
        addAdjacentLowerRow(currentNode);
    }

    private void addAdjacentLowerRow(Node currentNode) {
        int row = currentNode.row();
        int col = currentNode.col();
        int lowerRow = row + 1;
        if (lowerRow >= searchArea.length) {
            return;
        }
        if (col - 1 >= 0) {
            checkNode(currentNode, col - 1, lowerRow, diagonalCost); // Comment this line if diagonal movements are not allowed
        }
        if (col + 1 < searchArea[0].length) {
            checkNode(currentNode, col + 1, lowerRow, diagonalCost); // Comment this line if diagonal movements are not allowed
        }
        checkNode(currentNode, col, lowerRow, hvCost);
    }

    private void addAdjacentMiddleRow(Node currentNode) {
        int row = currentNode.row();
        int col = currentNode.col();
        if (col - 1 >= 0) {
            checkNode(currentNode, col - 1, row, hvCost);
        }
        if (col + 1 < searchArea[0].length) {
            checkNode(currentNode, col + 1, row, hvCost);
        }
    }

    private void addAdjacentUpperRow(Node currentNode) {
        int row = currentNode.row();
        int col = currentNode.col();
        int upperRow = row - 1;
        if (upperRow < 0) {
            return;
        }
        if (col - 1 >= 0) {
            checkNode(currentNode, col - 1, upperRow, diagonalCost); // Comment this if diagonal movements are not allowed
        }
        if (col + 1 < searchArea[0].length) {
            checkNode(currentNode, col + 1, upperRow, diagonalCost); // Comment this if diagonal movements are not allowed
        }
        checkNode(currentNode, col, upperRow, hvCost);
    }

    private void checkNode(Node currentNode, int col, int row, int cost) {
        Node adjacentNode = searchArea[row][col];
        if (adjacentNode.isBlocked() || closedSet.contains(adjacentNode)) {
            return;
        }
        if (!openList.contains(adjacentNode)) {
            adjacentNode.setNodeData(currentNode, cost);
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

    private boolean isFinalNode(Node currentNode) {
        return currentNode.equals(finalNode);
    }

}

