package lab2.task4;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

import lab2.Edge;
import lab2.Node;
import lab2.NodeUtils;
import lab2.task1_2.BreadthFirstSearchAlgo;
import lab2.task1_2.DepthFirstSearchAlgo;

public class UniformCostSearchAlgo {
	public static Comparator<Node> getComparator() {
		return Comparator.comparing(Node::getPathCost).thenComparing(Node::getLabel);
	}

	// Find the path from Root node to Goal
	public Node execute(Node tree, String goal) {
		PriorityQueue<Node> frontier = new PriorityQueue<>(getComparator());
		Set<Node> explored = new HashSet<>();
		tree.setPathCost(0);
		frontier.add(tree);
		while (!frontier.isEmpty()) {
			Node currentNode = frontier.poll();
			if (currentNode.getLabel().equals(goal)) {
				return currentNode;
			}
			explored.add(currentNode);
			for (Edge childEdge : currentNode.getChildren()) {
				Node childNode = childEdge.getEnd();
				if (!explored.contains(childNode) && !frontier.contains(childNode)) {
					childNode.setPathCost(currentNode.getPathCost() + childEdge.getWeight());
					childNode.setParent(currentNode);
					frontier.add(childNode);
				} else if (frontier.contains(childNode)
						&& childNode.getPathCost() > currentNode.getPathCost() + childEdge.getWeight()) {
					childNode.setParent(currentNode);
					childNode.setPathCost(currentNode.getPathCost() + childEdge.getWeight());
					frontier.remove(childNode);
					frontier.add(childNode);
				}
			}
		}
		return null;
	}

	// Find the path from Start node (not Root node) to Goal
	public Node execute(Node tree, String start, String goal) throws CloneNotSupportedException {
		PriorityQueue<Node> frontier = new PriorityQueue<>(getComparator());
		List<Node> explored = new ArrayList<>();
		Node startNode = findNode(tree, start);
		if (startNode == null) {
			return null;
		}
		startNode.setPathCost(0);
		frontier.add(startNode);
		while (!frontier.isEmpty()) {
			Node currentNode = frontier.poll();
			if (currentNode.getLabel().equals(goal)) {
				return currentNode;
			}
			explored.add(currentNode);
			List<Edge> childrens = currentNode.getChildren();
			for (Edge childEdge : childrens) {
				Node childNode = childEdge.getEnd().clone();
				double newPathCost = currentNode.getPathCost() + childEdge.getWeight();
				if (!explored.contains(childNode) && !frontier.contains(childNode)) {
					childNode.setParent(currentNode);
					childNode.setPathCost(newPathCost);
					frontier.add(childNode);
				} else if (frontier.contains(childNode) && newPathCost < childNode.getPathCost()) {
					frontier.remove(childNode);
					childNode.setParent(currentNode);
					childNode.setPathCost(newPathCost);
					frontier.add(childNode);
				}
			}
		}

		return null;
	}

	private Node findNode(Node tree, String label) {
		if (tree.getLabel().equals(label)) {
			return tree;
		}

		for (Edge childEdge : tree.getChildren()) {
			Node found = findNode(childEdge.getEnd(), label);
			if (found != null) {
				return found;
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

		UniformCostSearchAlgo ucs = new UniformCostSearchAlgo();

		Node resultUCS = ucs.execute(nodeS, "G");
		printResult("UCS tu node goc den node G", resultUCS);

		Node resultUCS1;
		try {
			resultUCS1 = ucs.execute(nodeS, "A", "G");
			printResult("UCS tu node A den node G", resultUCS1);
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
