import java.util.*;

public class AlgorithmDijkstra {
    private static final int MAX_PATH = 1000000;

    public static void main(String[] args) {
        Map<String, Map<String, Integer>> graph = new HashMap<>();
        Map<String, Integer> mapA = new HashMap<>();
        mapA.put("b", 2);
        mapA.put("c", 1);
        Map<String, Integer> mapB = new HashMap<>();
        mapB.put("f", 7);
        Map<String, Integer> mapC = new HashMap<>();
        mapC.put("d", 5);
        mapC.put("e", 2);
        Map<String, Integer> mapD = new HashMap<>();
        mapD.put("f", 2);
        Map<String, Integer> mapE = new HashMap<>();
        mapE.put("f", 1);
        Map<String, Integer> mapF = new HashMap<>();
        mapF.put("g", 1);
        Map<String, Integer> mapG = new HashMap<>();
        graph.put("a", mapA);
        graph.put("b", mapB);
        graph.put("c", mapC);
        graph.put("d", mapD);
        graph.put("e", mapE);
        graph.put("f", mapF);
        graph.put("g", mapG);
        shortPath(graph, "a", "g").forEach((k, v) -> System.out.println(k + " : " + v));
    }

    public static Map<String, Integer> shortPath(Map<String, Map<String, Integer>> graph, String start, String end) {
        Map<String, Integer> costs = new HashMap<>();
        List<String> proccessed = new ArrayList<>();
        Map<String, Map<String, Integer>> neighbors = new HashMap<>();
        for (Map.Entry<String, Map<String, Integer>> entry : graph.entrySet()) {
            if (entry.getKey().equals(start)) {
                costs.putAll(entry.getValue());
            } else if (!costs.containsKey(entry.getKey())) {
                costs.put(entry.getKey(), MAX_PATH);
            }
        }
        Node lowestNode = getLowestNode(costs, proccessed);
        Map<String, Integer> lowestNodeMap;
        Queue<Node> nodeQueue = new ArrayDeque<>();
        nodeQueue.add(lowestNode);
        while (!nodeQueue.isEmpty()) {
            Node n = nodeQueue.poll();
            if (n.getKey().equals(end)) {
                break;
            }
            int cost = n.getValue();
            neighbors.put(n.getKey(), graph.get(n.getKey()));
            for (Map<String, Integer> ent : neighbors.values()) {
                for (Map.Entry<String, Integer> p : ent.entrySet()) {
                    int newCost = cost + p.getValue();
                    String newKey = p.getKey();
                    updateCosts(costs, newKey, newCost);
                }
            }
            proccessed.add(n.getKey());
            lowestNodeMap = findLowestNode(costs, proccessed);
            if (!lowestNodeMap.containsKey(null)) {
                for (Map.Entry<String, Integer> ent : lowestNodeMap.entrySet()) {
                    lowestNode = new Node(ent.getKey(), ent.getValue());
                    nodeQueue.add(lowestNode);
                    neighbors.clear();
                }
            }
        }
        return costs;
    }

    private static Node getLowestNode(Map<String, Integer> costs, List<String> proccessed) {
        Map<String, Integer> lowestNodeMap = findLowestNode(costs, proccessed);
        Node lowestNode = null;
        for (Map.Entry<String, Integer> ent : lowestNodeMap.entrySet()) {
            lowestNode = new Node(ent.getKey(), ent.getValue());
        }
        return lowestNode;
    }

    public static void updateCosts(Map<String, Integer> costs, String key, int newCost) {
        for (Map.Entry<String, Integer> entry : costs.entrySet()) {
            if (entry.getKey().equals(key) && newCost < entry.getValue()) {
                costs.put(key, newCost);
            }
        }
    }

    public static Map<String, Integer> findLowestNode(Map<String, Integer> costs, List<String> proccessed) {
        int lowestValue = MAX_PATH;
        String lowestKey = null;
        Map<String, Integer> result = new HashMap<>();
        for (Map.Entry<String, Integer> entry : costs.entrySet()) {
            int value = entry.getValue();
            if (value < lowestValue && !proccessed.contains(entry.getKey())) {
                lowestValue = value;
                lowestKey = entry.getKey();
            }
        }
        result.put(lowestKey, lowestValue);
        return result;
    }

    static class Node {
        private String key;
        private Integer value;

        public Node(String key, Integer value) {
            this.key = key;
            this.value = value;
        }

        public String getKey() {
            return key;
        }

        public Integer getValue() {
            return value;
        }
    }
}