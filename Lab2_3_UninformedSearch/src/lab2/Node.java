package lab2;

import java.util.ArrayList;
import java.util.List;

public class Node implements Cloneable{
	private String label;
	private Node parent; // for printing the path from the start node to goal node
	private double pathCost;// from the root node to this node
	private int depth;// used for compute the depth of a node in a tree search
	private List<Edge> children = new ArrayList<Edge>();
	
	@Override
	public Node clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return (Node) super.clone();
	}
	public Node(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public Node getParent() {
		return parent;
	}

	public void setParent(Node parent) {
		this.parent = parent;
	}

	public List<Edge> getChildren() {
		return children;
	}

	public List<Node> getChildrenNodes() {
		return this.children.stream().map(x -> x.getEnd()).toList();
	}

	// an edge is generated using this node and "that" with the given cost
	public void addEdge(Node that, double cost) {
		Edge edge = new Edge(this, that, cost);
		this.children.add(edge);
	}

	// an edge is generated using this node and "that" with the given cost
	public void addEdge(Node that) {
		Edge edge = new Edge(this, that);
		this.children.add(edge);
	}

	public double getPathCost() {
		return pathCost;
	}

	public void setPathCost(double pathCost) {
		this.pathCost = pathCost;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null || getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return this.label + "_" + this.parent.getLabel() + " " + this.pathCost;
	}

}
