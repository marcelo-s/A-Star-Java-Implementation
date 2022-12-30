package com.ai.astar.domain;

public class Node {

    private int g;
    private int f;
    private int heuristic;
    private final int row;
    private final int col;
    private boolean isBlocked;
    private Node parent;

    private Node(int row, int col) {
        super();
        this.row = row;
        this.col = col;
    }

    public static Node of(int row, int col) {
        return new Node(row, col);
    }

    public void calculateHeuristic(Node finalNode) {
        this.heuristic = Math.abs(finalNode.row() - row) + Math.abs(finalNode.col() - col);
    }

    public void updateNode(Node currentNode, int cost) {
        int gCost = currentNode.g() + cost;
        this.parent = currentNode;
        this.g = gCost;
        this.f = g + heuristic;
    }

    public boolean checkBetterPath(Node currentNode, int cost) {
        int gCost = currentNode.g() + cost;
        if (gCost < g()) {
            updateNode(currentNode, cost);
            return true;
        }
        return false;
    }

    public int g() {
        return g;
    }

    public int f() {
        return f;
    }

    public Node parent() {
        return parent;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setAsBlocked() {
        this.isBlocked = true;
    }

    public int row() {
        return row;
    }

    public int col() {
        return col;
    }

    @Override
    public int hashCode() {
        int result = row;
        result = 31 * result + col;
        return result;
    }

    @Override
    public boolean equals(Object arg0) {
        if (arg0 == null) {
            return false;
        }
        Node other = (Node) arg0;
        return this.row() == other.row() && this.col() == other.col();
    }

    @Override
    public String toString() {
        return "Node [row=" + row + ", col=" + col + "]";
    }

}
