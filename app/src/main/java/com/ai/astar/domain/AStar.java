package com.ai.astar.domain;

import com.ai.astar.domain.searchstrategy.DiagonalMapChecker;
import com.ai.astar.domain.searchstrategy.HorizontalVerticalChecker;
import com.ai.astar.domain.searchstrategy.MapChecker;
import com.ai.astar.domain.searchstrategy.NoOpChecker;

import java.util.*;

public class AStar {
    private static final int DEFAULT_HV_COST = 10; // Horizontal - Vertical Cost
    private static final int DEFAULT_DIAGONAL_COST = 14;
    private final Node[][] searchArea;
    private final PriorityQueue<Node> openList;
    private final Set<Node> closedSet;
    private final Node initialNode;
    private final Node finalNode;
    private final MapChecker diagonalsChecker;
    private final MapChecker hvChecker;

    public AStar(
            int rows,
            int cols,
            Node initialNode,
            Node finalNode,
            int[][] blocksArray,
            boolean searchDiagonals,
            int diagonalCost,
            int hvCost) {
        this.initialNode = initialNode;
        this.finalNode = finalNode;
        this.searchArea = new Node[rows][cols];
        this.openList = new PriorityQueue<>(Comparator.comparingInt(Node::f));
        initNodes();
        initBlocks(blocksArray);
        this.closedSet = new HashSet<>();
        if (searchDiagonals) {
            this.diagonalsChecker = new DiagonalMapChecker(searchArea, openList, closedSet, diagonalCost);
        } else {
            this.diagonalsChecker = new NoOpChecker(null, null, null);
        }
        this.hvChecker = new HorizontalVerticalChecker(searchArea, openList, closedSet, hvCost);
    }

    public AStar(int rows, int cols, Node initialNode, Node finalNode, int[][] blocksArray, boolean searchDiagonals) {
        this(rows, cols, initialNode, finalNode, blocksArray, searchDiagonals, DEFAULT_DIAGONAL_COST, DEFAULT_HV_COST);
    }

    private void initNodes() {
        for (int i = 0; i < searchArea.length; i++) {
            for (int j = 0; j < searchArea[0].length; j++) {
                Node node = Node.of(i, j);
                node.calculateHeuristic(finalNode);
                this.searchArea[i][j] = node;
            }
        }
    }

    private void initBlocks(int[][] blocksArray) {
        for (int[] block : blocksArray) {
            int row = block[0];
            int col = block[1];
            if (row < 0 || row >= searchArea.length) {
                continue;
            }
            if (col < 0 || col >= searchArea[0].length) {
                continue;
            }
            this.searchArea[row][col].setAsBlocked();
        }
    }

    public List<Node> findPath() {
        openList.add(initialNode);
        while (!openList.isEmpty()) {
            Node currentNode = openList.poll();
            closedSet.add(currentNode);
            if (isFinalNode(currentNode)) {
                return bestPath(currentNode);
            } else {
                addAdjacentNodes(currentNode);
            }
        }
        return new ArrayList<>();
    }

    private List<Node> bestPath(Node currentNode) {
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
        int row = currentNode.row();
        int col = currentNode.col();
        addAdjacentUpperRow(currentNode, row, col);
        addAdjacentMiddleRow(currentNode, row, col);
        addAdjacentLowerRow(currentNode, row, col);
    }

    private void addAdjacentLowerRow(Node currentNode, int row, int col) {
        diagonalsChecker.checkNode(currentNode, col, row + 1);
        hvChecker.checkNode(currentNode, col, row + 1);
    }

    private void addAdjacentMiddleRow(Node currentNode, int row, int col) {
        hvChecker.checkNode(currentNode, col - 1, row);
        hvChecker.checkNode(currentNode, col + 1, row);
    }

    private void addAdjacentUpperRow(Node currentNode, int row, int col) {
        diagonalsChecker.checkNode(currentNode, col, row - 1);
        hvChecker.checkNode(currentNode, col, row - 1);
    }

    private boolean isFinalNode(Node currentNode) {
        return currentNode.equals(finalNode);
    }

}

