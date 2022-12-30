package com.ai.astar.domain.searchstrategy;

import com.ai.astar.domain.Node;

public interface MapCheck {

    void checkNode(Node currentNode, int col, int row);

}
