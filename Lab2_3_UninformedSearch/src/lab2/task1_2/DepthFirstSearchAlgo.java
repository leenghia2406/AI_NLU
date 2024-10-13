package lab2.task1_2;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import lab2.Edge;
import lab2.Node;
import lab2.NodeUtils;

public class DepthFirstSearchAlgo {
	// Task 1.
// Find the path from Root node to Goal
	public Node execute(Node tree, String goal) {
		// TODO
		Stack<Node> frontier = new Stack<Node>();
		List<Node> explored = new ArrayList<>();
		frontier.add(tree);
		while (!frontier.isEmpty()) {
			Node currentNode = frontier.pop();
			if (currentNode.getLabel().equals(goal)) {
				return currentNode;
			}
			explored.add(currentNode);
			List<Edge> childrens = currentNode.getChildren();
			for (Edge childEdge : childrens) {
				Node childNode = childEdge.getEnd();
				if (!frontier.contains(childNode) && !explored.contains(childNode)) {
                    childNode.setParent(currentNode);
                    childNode.setPathCost(currentNode.getPathCost() + childEdge.getWeight());
                    frontier.add(childNode);
                }
			}
		}
		return null;
	}

	// Task 2.
	// Find the path from Start node (not Root node) to Goal
	public Node execute(Node tree, String start, String goal) {
	    Stack<Node> frontier = new Stack<>();
	    List<Node> explored = new ArrayList<>();
	    boolean started = false;
	    Node startNode = null;
	    frontier.add(tree);
	    while (!frontier.isEmpty() && !started) {
	        Node currentNode = frontier.pop();
	        if (currentNode.getLabel().equals(start)) {
	            startNode = currentNode;
	            started = true;
	        } else {
	            explored.add(currentNode);
	            for (Edge childEdge : currentNode.getChildren()) {
	                Node childNode = childEdge.getEnd();
	                if (!frontier.contains(childNode) && !explored.contains(childNode)) {
	                    frontier.add(childNode);
	                }
	            }
	        }
	    }
	    if (!started) {
	        return null;
	    }
	    frontier.clear();
	    explored.clear();
	    frontier.add(startNode);

	    while (!frontier.isEmpty()) {
	        Node currentNode = frontier.pop();
	        if (currentNode.getLabel().equals(goal)) {
	            return currentNode;
	        }
	        explored.add(currentNode);
	        for (Edge childEdge : currentNode.getChildren()) {
	            Node childNode = childEdge.getEnd();
	            if (!frontier.contains(childNode) && !explored.contains(childNode)) {
	                childNode.setParent(currentNode);
	                childNode.setPathCost(currentNode.getPathCost() + childEdge.getWeight());
	                frontier.add(childNode);
	            }
	        }
	    }
	    return null;
	}
	private static void printResult(String description, Node result) {
        List<String> path = NodeUtils.printPath(result);
        double cost = result != null ? result.getPathCost() : Double.NaN;
        System.out.println(description + ": " + path + " voi chi phi " + cost);
    }
	public static void main(String[] args) {
        Node nodeS = new Node("S");
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        Node nodeE = new Node("E");
        Node nodeF = new Node("F");
        Node nodeG = new Node("G");
        Node nodeH = new Node("H");

        nodeS.addEdge(nodeA, 5);
        nodeS.addEdge(nodeB, 2);
        nodeS.addEdge(nodeC, 4);
        nodeA.addEdge(nodeD, 9);
        nodeA.addEdge(nodeE, 4);
        nodeB.addEdge(nodeG, 6);
        nodeC.addEdge(nodeF, 2);
        nodeD.addEdge(nodeH, 7);
        nodeE.addEdge(nodeG, 6);
        nodeF.addEdge(nodeG, 1);
		DepthFirstSearchAlgo dfsAlgo = new DepthFirstSearchAlgo();
		
        Node resultDFS = dfsAlgo.execute(nodeS, "G");
        printResult("DFS tu node goc den node G", resultDFS);

        Node resultDFS1 = dfsAlgo.execute(nodeS, "A", "G");
        printResult("DFS tu node A den node G", resultDFS1);
	}


}