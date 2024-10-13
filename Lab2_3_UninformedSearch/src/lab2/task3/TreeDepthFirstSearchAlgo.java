package lab2.task3;

import java.util.List;
import java.util.Stack;

import lab2.Edge;
import lab2.Node;
import lab2.NodeUtils;

public class TreeDepthFirstSearchAlgo {
// Find the path from Root node to Goal
	public Node execute(Node tree, String goal) {
	    Stack<Node> frontier = new Stack<>();
	    frontier.add(tree);
	    while (!frontier.isEmpty()) {
	        Node currentNode = frontier.pop();
	        if (currentNode.getLabel().equals(goal)) {
	            return currentNode;
	        }
	        List<Edge> childrens = currentNode.getChildren();
	        for (Edge childEdge : childrens) {
	            Node childNode = childEdge.getEnd();
	            childNode.setParent(currentNode);
	            childNode.setPathCost(currentNode.getPathCost() + childEdge.getWeight());
	            frontier.add(childNode);
	        }
	    }
	    return null;
	}


	// Find the path from Start node (not Root node) to Goal
	public Node execute(Node tree, String start, String goal) {
	    Stack<Node> frontier = new Stack<>();
	    boolean started = false;
	    frontier.add(tree);
	    while (!frontier.isEmpty()) {
	        Node currentNode = frontier.pop();
	        if (currentNode.getLabel().equals(start)) {
	            started = true;
	            frontier.clear();  // Clear the stack to restart from the start node
	            frontier.add(currentNode);
	            currentNode.setParent(null);
	        }
	        if (currentNode.getLabel().equals(goal) && started) {
	            return currentNode;
	        }
	        List<Edge> childrens = currentNode.getChildren();
	        for (Edge childEdge : childrens) {
	            Node childNode = childEdge.getEnd();
	            childNode.setParent(currentNode);
	            childNode.setPathCost(currentNode.getPathCost() + childEdge.getWeight());
	            frontier.add(childNode);
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
        
        TreeDepthFirstSearchAlgo dfsAlgo = new TreeDepthFirstSearchAlgo();
        Node resultDFS = dfsAlgo.execute(nodeS, "G");
        printResult("DFS tu node goc den node G", resultDFS);

        Node resultDFS1 = dfsAlgo.execute(nodeS, "A", "G");
        printResult("DFS tu node A den node G", resultDFS1);
	}

}