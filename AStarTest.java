package com.ai;

import java.util.List;

/**
 * Test Class for A* Algorithm
 * 
 * @author Marcelo Surriabre
 * @version  1.0, 2016-09-05 
 */
public class AStarTest {
	public static void main(String[] args) {
		Node initialNode = new Node(2, 2);
		Node finalNode = new Node(2, 6);
		int rows = 6;
		int cols = 8;
		AStar aStar = new AStar(rows, cols, initialNode, finalNode);
		int[][] blocksArray = new int[][] { { 1, 4 }, { 2, 4 }, { 3, 4 }, { 1, 5 }, { 1, 6 }, { 1, 7 } };
		aStar.setBlocks(blocksArray);
		List<Node> path = aStar.findPath();
		for (Node node : path) {
			System.out.println(node);
		}
 	}


}
