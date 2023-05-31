import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.*;
public class MyGraph<Vertex> {
    private Map<Vertex, List<Vertex>> list;

    public MyGraph() {
        list = new HashMap<>();
    }

    public void addVertex(Vertex vertex) {
        list.put(vertex, new LinkedList<>());
    }

    public void addEdge(Vertex source, Vertex destination) {
        validateVertex(source);
        validateVertex(destination);
        list.get(source).add(destination);
        list.get(destination).add(source);

    }

    private void validateVertex(Vertex index) {
        if (!list.containsKey(index)) {
            throw new IllegalArgumentException("Vertex " + index + " is out of the range");
        }
    }

    public void printGraph() {
        for (Map.Entry<Vertex, List<Vertex>> entry : list.entrySet()) {
            Vertex vertex = entry.getKey();
            List<Vertex> neighbors = entry.getValue();
            System.out.print("Vertex " + vertex + " is connected to: ");
            for (Vertex neighbor : neighbors) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }

    public void removeEdge(Vertex source, Vertex destination) {
        validateVertex(source);
        validateVertex(destination);
        List<Vertex> neighbors = list.get(source);
        if (neighbors!=null) {
            neighbors.remove(destination);
        }
        list.get(destination).remove(source);
    }

    public boolean hasEdge(Vertex source, Vertex destination) {
        validateVertex(source);
        validateVertex(destination);
        List<Vertex> neighbors = list.get(source);
        return neighbors != null && neighbors.contains(destination);
    }

    public List<Vertex> getNeighbors(Vertex vertex) {
        validateVertex(vertex);
        return list.getOrDefault(vertex, new LinkedList<>());
    }

    public void DFS(Vertex start) {
        validateVertex(start);
        Map<Vertex, Boolean> visited = new HashMap<>();
        for (Vertex vertex:list.keySet()) {
            visited.put(vertex,false);
        }
        DFSHelper(start, visited);

    }

    public void BFS(Vertex start) {
        validateVertex(start);
        Map<Vertex, Boolean> visited = new HashMap<>();
        for (Vertex vertex : list.keySet()) {
            visited.put(vertex, false);
        }

        Queue<Vertex> queue = new LinkedList<>();
        visited.put(start, true);
        queue.offer(start);

        while (!queue.isEmpty()) {
            Vertex vertex = queue.poll();
            System.out.print(vertex + " ");

            for (Vertex neighbor : list.get(vertex)) {
                if (!visited.get(neighbor)) {
                    visited.put(neighbor, true);
                    queue.offer(neighbor);
                }
            }
        } }


    private void DFSHelper(Vertex vertex, Map<Vertex, Boolean> visited) {
        visited.put(vertex, true);
        System.out.print(vertex + " ");
        for (Vertex neighbor : list.get(vertex)) {
            if (!visited.get(neighbor)) {
                DFSHelper(neighbor, visited);
            }
        }
    }

    public Map<Vertex, Integer> dijkstraShortestPath(Vertex source) {
        validateVertex(source);

        Map<Vertex, Integer> distance = new HashMap<>();
        Map<Vertex, Vertex> previous = new HashMap<>();
        PriorityQueue<Vertex> queue = new PriorityQueue<>(Comparator.comparingInt(distance::get));

        for (Vertex vertex : adjacencyList.keySet()) {
            distance.put(vertex, Integer.MAX_VALUE);
            previous.put(vertex, null);
            queue.offer(vertex);
        }

        distance.put(source, 0);

        while (!queue.isEmpty()) {
            Vertex current = queue.poll();
            List<Edge<Vertex>> neighbors = adjacencyList.get(current);
            if (neighbors != null) {
                for (Edge<Vertex> edge : neighbors) {
                    Vertex neighbor = edge.getDestination();
                    int newDistance = distance.get(current) + edge.getWeight();
                    if (newDistance < distance.get(neighbor)) {
                        distance.put(neighbor, newDistance);
                        previous.put(neighbor, current);
                        queue.remove(neighbor);
                        queue.offer(neighbor);
                    }
                }
            }
        }

        return distance;
    }
    private static class Edge<Vertex> {
        private Vertex destination;
        private int weight;

        public Edge(Vertex destination, int weight) {
            this.destination = destination;
            this.weight = weight;
        }

        public Vertex getDestination() {
            return destination;
        }

        public int getWeight() {
            return weight;
        }
    }

}