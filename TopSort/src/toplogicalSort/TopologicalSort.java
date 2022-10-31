package toplogicalSort;
import java.util.*;
public class TopologicalSort {
    void topologicalSortTwo(int vertices, List<List<Integer>> adjacencyList) {
        // Create an array to store the indegrees of all the vertices
        int[] indegree = new int[vertices];

        // Traverse the adjacency list to fill the indegrees array
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < adjacencyList.get(i).size(); j++) {
                indegree[adjacencyList.get(i).get(j)]++;
            }
        }
        // Create a queue and enqueue all the vertices with indegree 0
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < vertices; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }
        // Initialize count of visited vertices
//        int count = 0;
        // Create a vector to store the result (A topological ordering of the vertices)
        Vector<Integer> topologicalOrdering = new Vector<>();
        // One by one dequeue vertices from the queue and enqueue adjacent vertices
        while (!queue.isEmpty()) {
            // Extract front of queue (or perform dequeue) and add it to topological order
            int u = queue.poll();
            topologicalOrdering.add(u);

            // Iterate through all its neighbouring nodes of dequeued node u and decrease their in-degree by 1
            for (int i = 0; i < adjacencyList.get(u).size(); i++) {
                // If in-degree becomes zero, add it to queue
                if (--indegree[adjacencyList.get(u).get(i)] == 0) {
                    queue.add(adjacencyList.get(u).get(i));
                }
            }
//            count++;
        }
        // Check if there was a cycle
//        if (count != vertices) {
//            System.out.println("There exists a cycle in the graph");
//            return;
//        }
        // Print topological order
        for (int i = 0; i < topologicalOrdering.size(); i++) {
            System.out.print(topologicalOrdering.get(i) + " ");
        }
    }
 // Create a stack to store the result (A topological ordering of the vertices)
    
    Stack<Integer> stack = new Stack<>();
    boolean isCyclicUtil(int v, boolean visited[], boolean recStack[], List<List<Integer>> adjacencyList) {
        if (visited[v] == false) {
            // Mark the current node as visited and part of recursion stack
            visited[v] = true;
            recStack[v] = true;

            // Recur for all the vertices adjacent to this vertex
            for (int i = 0; i < adjacencyList.get(v).size(); i++) {
                if (!visited[adjacencyList.get(v).get(i)] && isCyclicUtil(adjacencyList.get(v).get(i), visited, recStack, adjacencyList)) {
                    return true;
                } else if (recStack[adjacencyList.get(v).get(i)]) {
                    return true;
                }
            }
        }
        recStack[v] = false; // remove the vertex from recursion stack
        return false;
    }
    boolean hasCycle(int vertices, List<List<Integer>> adjacencyList) {
        // Create a boolean array to store the visited vertices
        boolean[] visited = new boolean[vertices];

        // Create a boolean array to store the vertices that are being visited
        boolean[] recStack = new boolean[vertices];

        // Call the recursive helper function to detect cycle in different DFS trees
        for (int i = 0; i < vertices; i++) {
            if (isCyclicUtil(i, visited, recStack, adjacencyList)) {
                return true;
            }
        }
        return false;
    }
    
    void topologicalSortOne(int vertices, List<List<Integer>> adjacencyList) {
        // Create a boolean array to store the visited vertices
        boolean[] visited = new boolean[vertices];

        for (int j = 0; j < vertices; j++){
            visited[j] = false;
        }
        // Call the recursive helper function to store Topological Sort starting from all vertices one by one
        for (int i = 0; i < vertices; i++) {
            if (!visited[i]) {
                topologicalSortVisit(i, visited, adjacencyList);
            }
        }

        // Print contents of stack
        while (!stack.isEmpty()) {
        	System.out.print(stack.pop() + " ");
        }
    }
    // A recursive function used by topologicalSort
    void topologicalSortVisit(int v, boolean[] visited, List<List<Integer>> adjacencyList) {
        visited[v] = true;
        List<Integer> list = adjacencyList.get(v);
        for (int i = 0; i < list.size(); i++) {
            int n = list.get(i);
            if (!visited[n])
                topologicalSortVisit(n, visited, adjacencyList);
        }
        stack.push(v);
    }
}
