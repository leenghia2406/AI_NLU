package lab2.task1_2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import lab2.Edge;
import lab2.Node;
import lab2.NodeUtils;

public class BreadthFirstSearchAlgo {
	// Task 1.
	// Find the path from Root node to Goal
	public Node execute(Node tree, String goal) {
		// TODO
		Queue<Node> frontier = new LinkedList<Node>();
		List<Node> explored = new ArrayList<>();
		frontier.add(tree);
		while (!frontier.isEmpty()) {
			Node currentNode = frontier.poll();
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

//Task 2.
	// Find the path from Start node (not Root node) to Goal
	public Node execute(Node tree, String start, String goal) {
		// TODO
		Queue<Node> frontier = new LinkedList<Node>();
		List<Node> explored = new ArrayList<>();
		frontier.add(tree);
		boolean started = false;
		while (!frontier.isEmpty()) {
			Node currentNode = frontier.poll();
			if (currentNode.getLabel().equals(start)) {
				started = true;
				frontier.clear();
				currentNode.setParent(null);
				explored.clear();
			}
			if (currentNode.getLabel().equals(goal) && started == true) {
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
		BreadthFirstSearchAlgo bfsAlgo = new BreadthFirstSearchAlgo();
		
        Node resultBFS = bfsAlgo.execute(nodeS, "G");
        printResult("BFS tu node goc den node G", resultBFS);

        Node resultBFS1 = bfsAlgo.execute(nodeS, "A", "G");
        printResult("BFS tu node A den node G", resultBFS1);
		
	}
}
