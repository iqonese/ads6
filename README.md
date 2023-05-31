# ads6
```public void BFS(Vertex start) {
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
        } }```
BFS is a graph method to traverse. Unlike DFS, it traverses level by level, so we need another approach to solve the problem. My BFS method takes Vertex start as a starting
vertex and right off the bat validates it. Then I use HashMap to check whether each vertex has been visited or not. We use queue to keep track of visited Vertices. 
