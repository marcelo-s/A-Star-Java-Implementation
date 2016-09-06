package com.ai;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
/**
 * A Star Algorithm
 * 
 * @author	 Marcelo Surriabre
 * @version  1.0, 2016-09-05
 */
public class AStar {
	private static int HV_COST = 10;
	private static int DIAGONAL_COST = 14;
	private Node[][] searchArea;
	private PriorityQueue<Node> openList;
	private List<Node> closedList;
	private Node initialNode;
	private Node finalNode;

	public AStar(int rows, int cols, Node initialNode, Node finalNode) {
		setInitialNode(initialNode);
		setFinalNode(finalNode);
		this.searchArea = new Node[rows][cols];
		this.openList = new PriorityQueue<Node>(new Comparator<Node>() {
			@Override
			public int compare(Node node0, Node node1) {
				return node0.getF() < node1.getF() ? -1 : node0.getF() > node1.getF() ? 1 : 0;
			}
		});
		setNodes();
		this.closedList = new ArrayList<Node>();
	}

	private void setNodes() {
		for (int i = 0; i < searchArea.length; i++) {
			for (int j = 0; j < searchArea[0].length; j++) {
				Node node = new Node(i, j);
				node.calculateHeuristic(getFinalNode());
				this.searchArea[i][j] = node;
			}
		}

	}

	public void setBlocks(int[][] blocksArray) {
		for (int i = 0; i < blocksArray.length; i++) {
			int row = blocksArray[i][0];
			int col = blocksArray[i][1];
			setBlock(row, col);
		}
	}

	public List<Node> findPath() {
		openList.add(initialNode);
		while (!isEmpty(openList)) {
			Node currentNode = openList.poll();
			closedList.add(currentNode);
			if (isFinalNode(currentNode)) {
				return getPath(currentNode);
			} else {
				addAdyacentNodes(currentNode);
			}

		}
		return new ArrayList<Node>();
	}

	private List<Node> getPath(Node currentNode) {
		List<Node> path = new ArrayList<Node>();
		path.add(currentNode);
		Node parent;
		while ((parent = currentNode.getParent()) != null) {
			path.add(0, parent);
			currentNode = parent;
		}
		return path;
	}

	private void addAdyacentNodes(Node currentNode) {
		addAdyacentUpperRow(currentNode);
		addAdyacentMiddleRow(currentNode);
		addAdyacentLowerRow(currentNode);
	}

	private void addAdyacentLowerRow(Node currentNode) {
		int row = currentNode.getRow();
		int col = currentNode.getCol();
		int lowerRow = row + 1;
		if (lowerRow < getSearchArea().length) {
			if (col - 1 >= 0) {
				 checkNode(currentNode, col - 1, lowerRow, DIAGONAL_COST); // Comment this if diagonal movements are not allowed
			}
			if (col + 1 < getSearchArea()[0].length) {
				 checkNode(currentNode, col + 1, lowerRow, DIAGONAL_COST); // Comment this if diagonal movements are not allowed
			}
			checkNode(currentNode, col, lowerRow, HV_COST);
		}

	}

	private void addAdyacentMiddleRow(Node currentNode) {
		int row = currentNode.getRow();
		int col = currentNode.getCol();
		int middleRow = row;
		if (col - 1 >= 0) {
			checkNode(currentNode, col - 1, middleRow, HV_COST);
		}
		if (col + 1 < getSearchArea()[0].length) {
			checkNode(currentNode, col + 1, middleRow, HV_COST);
		}

	}

	private void addAdyacentUpperRow(Node currentNode) {
		int row = currentNode.getRow();
		int col = currentNode.getCol();
		int upperRow = row - 1;
		if (upperRow >= 0) {
			if (col - 1 >= 0) {
				checkNode(currentNode, col - 1, upperRow, DIAGONAL_COST); // Comment this if diagonal movements are not allowed
			}
			if (col + 1 < getSearchArea()[0].length) {
				checkNode(currentNode, col + 1, upperRow, DIAGONAL_COST); // Comment this if diagonal movements are not allowed
			}
			checkNode(currentNode, col, upperRow, HV_COST);
		}
	}

	private void checkNode(Node currentNode, int col, int row, int cost) {
		Node adyacentNode = getSearchArea()[row][col];
		if (!adyacentNode.isBlock() && !getClosedList().contains(adyacentNode)) {
			if (!getOpenList().contains(adyacentNode)) {
				adyacentNode.setNode(currentNode, cost);
				getOpenList().add(adyacentNode);
			} else {
				boolean changed = adyacentNode.checkBetterPath(currentNode, cost);
				if (changed) {
					// Remove and Add the changed node, so that the PriorityQueue can sort again its
					// contents with the modified "finalCost" value of the modified node
					getOpenList().remove(adyacentNode);
					getOpenList().add(adyacentNode);
				}
			}
		}
	}

	private boolean isFinalNode(Node currentNode) {
		return currentNode.equals(finalNode);
	}

	private boolean isEmpty(PriorityQueue<Node> openList) {
		return openList.size() == 0;
	}

	private void setBlock(int row, int col) {
		this.searchArea[row][col].setBlock(true);
	}

	public Node getInitialNode() {
		return initialNode;
	}

	public void setInitialNode(Node initialNode) {
		this.initialNode = initialNode;
	}

	public Node getFinalNode() {
		return finalNode;
	}

	public void setFinalNode(Node finalNode) {
		this.finalNode = finalNode;
	}

	public Node[][] getSearchArea() {
		return searchArea;
	}

	public void setSearchArea(Node[][] searchArea) {
		this.searchArea = searchArea;
	}

	public PriorityQueue<Node> getOpenList() {
		return openList;
	}

	public void setOpenList(PriorityQueue<Node> openList) {
		this.openList = openList;
	}

	public List<Node> getClosedList() {
		return closedList;
	}

	public void setClosedList(List<Node> closedList) {
		this.closedList = closedList;
	}

}
