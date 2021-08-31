import java.util.*;

public class BreadthFirstSearch {
    private static int count = 0;

    public static void main(String[] args) {
        Map<String, String[]> graph = new HashMap<>();
        graph.put("a", new String[]{"b", "c"});
        graph.put("b", new String[]{"f"});
        graph.put("c", new String[]{"d", "e"});
        graph.put("d", new String[]{"f"});
        graph.put("e", new String[]{"f"});
        graph.put("f", new String[]{"g"});
        System.out.println(breadthSearch(graph, "a", "g"));
        System.out.println("Number of operations: " + count);
    }

    private static boolean breadthSearch(Map<String, String[]> graph, String start, String end) {
        Queue<String> queue = new ArrayDeque<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            String current = queue.poll();
            graph.computeIfAbsent(current, k -> new String[]{});
            String[] array = graph.get(current);
            if (isContains(array, end)) {
                return true;
            } else {
                queue.addAll(Arrays.asList(array));
            }
            count++;
        }
        return false;
    }

    private static boolean isContains(String[] array, String end) {
        for (String s : array) {
            if (s.equals(end)) {
                return true;
            }
        }
        return false;
    }
}

