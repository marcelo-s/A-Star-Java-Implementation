package com.ai;

/**
 * Node Class
 * 
 * @author	 Marcelo Surriabre
 * @version  1.0, 2016-09-05
 */
public class Node {

	private int g;
	private int f;
	private int h;
	private int row;
	private int col;
	private boolean isBlock;
	private Node parent;

	public Node(int row, int col) {
		super();
		this.row = row;
		this.col = col;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public int getF() {
		return f;
	}

	public void setF(int f) {
		this.f = f;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public boolean isBlock() {
		return isBlock;
	}

	public void setBlock(boolean isBlock) {
		this.isBlock = isBlock;
	}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

	public void calculateHeuristic(Node finalNode) {
		this.h = Math.abs(finalNode.getRow() - getRow()) + Math.abs(finalNode.getCol() - getCol());
	}

	@Override
	public boolean equals(Object arg0) {
		Node other = (Node) arg0;
		return this.getRow() == other.getRow() && this.getCol() == other.getCol();
	}

	public void setNode(Node currentNode, int cost) {
		int gCost = currentNode.getG() + cost;
		setNodeValues(currentNode, gCost);

	}

	public boolean checkBetterPath(Node currentNode, int cost) {
		int gCost = currentNode.getG() + cost;
		if (gCost < getG()) {
			setNodeValues(currentNode, gCost);
			return true;
		}
		return false;
	}

	private void setNodeValues(Node currentNode, int gCost) {
		setParent(currentNode);
		setG(gCost);
		calculateFinalCost();
	}

	private void calculateFinalCost() {
		int finalCost = getG() + getH();
		setF(finalCost);
	}

	@Override
	public String toString() {
		return "Node [row=" + row + ", col=" + col + "]";
	}
}
