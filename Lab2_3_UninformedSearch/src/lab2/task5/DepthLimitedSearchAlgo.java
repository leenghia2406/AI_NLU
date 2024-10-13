package lab2.task5;

import java.util.List;

import lab2.Edge;
import lab2.Node;
import lab2.NodeUtils;

public class DepthLimitedSearchAlgo {
    public Node depthLimitedSearch(Node root, String goal, int limit) {
        return recursive_DLS(root, goal, limit);
    }

    public Node recursive_DLS(Node node, String goal, int limit) {
        if (node.getLabel().equals(goal)) {
            return node;
        } else if (limit == 0) {
            return null; // cut off
        } else {
            Node bestResult = null;
            double bestCost = Double.MAX_VALUE;
            for (Edge childEdge : node.getChildren()) {
                Node childNode = childEdge.getEnd();
                childNode.setParent(node);
                childNode.setPathCost(node.getPathCost() + childEdge.getWeight());
                Node result = recursive_DLS(childNode, goal, limit - 1);
                if (result != null && result.getPathCost() < bestCost) {
                    bestResult = result;
                    bestCost = result.getPathCost();
                }
            }
            return bestResult;
        }
    }

    public Node execute(Node tree, String goal, int maxDepth) {
        return depthLimitedSearch(tree, goal, maxDepth);
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

        DepthLimitedSearchAlgo dls = new DepthLimitedSearchAlgo();

        Node resultDLS = dls.execute(nodeS, "G", 3);
        printResult("DLS tu node goc den node G với do sau gioi han 3", resultDLS);

        Node resultDLS1 = dls.execute(nodeS, "G", 6);
        printResult("DLS tu node goc den node G voi do sau gioi han 6", resultDLS1);
    }

}

