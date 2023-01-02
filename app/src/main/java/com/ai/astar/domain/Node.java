package com.ai.astar.domain;

public class Node {

    private int gScore;
    private int fScore;
    private int h;
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
        this.h = Math.abs(finalNode.row() - row) + Math.abs(finalNode.col() - col);
    }

    public void updateNode(Node currentNode, int cost) {
        this.parent = currentNode;
        updateGScore(currentNode.gScore(), cost);
        updateFScore();
    }

    public boolean checkBetterPath(Node currentNode, int cost) {
        int updatedScore = currentNode.gScore() + cost;
        if (updatedScore < gScore()) {
            updateNode(currentNode, cost);
            return true;
        }
        return false;
    }

    public int gScore() {
        return gScore;
    }

    private void updateGScore(int currentGScore, int cost) {
        this.gScore = currentGScore + cost;
    }

    public int fScore() {
        return fScore;
    }

    private void updateFScore() {
        this.fScore = gScore + h;
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
