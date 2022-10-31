package toplogicalSort;
import java.util.*;
public class RandomDigraph {
	public int vertices;
	public int edges;

	// Set a maximum limit to the number of vertices
	final int MAX_LIMIT = 10;
	final int MIN_LIMIT = 5;
	// A Random instance to generate random values
	Random random = new Random();
	// An adjacency list to represent a graph
	public List<List<Integer>> adjacencyList;

	// Create the constructor
	public RandomDigraph()
	{
		// Set a minimum and maximum limit for the number of vertices
		this.vertices = random.nextInt(MAX_LIMIT - MIN_LIMIT) + MIN_LIMIT;

		// Compute the maximum possible number of edges
		// and randomly choose the number of edges less than
		// or equal to the maximum number of possible edges
		this.edges = random.nextInt(computeMaxEdges(vertices)) + 1;

		// Create an adjacency-list representation for the random digraph
		adjacencyList = new ArrayList<>(vertices);
		for (int i = 0; i < vertices; i++)
			adjacencyList.add(new ArrayList<>());

		// A for loop to randomly generate edges
		for (int i = 0; i < edges; i++) 
      {
			// Randomly select two vertices to create an edge between them
			int v = random.nextInt(vertices);
			int w = random.nextInt(vertices);

			// No multiple edges - check if there is already an edge between v and w
			if ((v == w) || adjacencyList.get(v).contains(w)) 
         {
				// Reduce the value of i so that again v and w can be chosen for the same edge count
				i = i - 1;
				continue;
			}

			// add an edge between them if not previously created
			addEdge(v, w);
		}
	}

	// Method to compute the maximum number of possible edges for a given number of vertices
	int computeMaxEdges(int numOfVertices)
	{
		// As it is a digraph, for a given number of vertices,
		// there can be at most n*(n-1) number of edges
		return numOfVertices * (numOfVertices - 1);
	}

	// Method to add edges between given vertices
	void addEdge(int v, int w)
	{
		// Add w to v's adjacency list
		adjacencyList.get(v).add(w);
	}

	public static void main(String[] args)
	{
		// Create a RandomDigraph object
		RandomDigraph randomGraph = new RandomDigraph();
		TopologicalSort topologicalSort = new TopologicalSort();
		// Print the adjacency-list representation of the digraph
		System.out.println("The generated random digraph:\n");
      for (int i = 0; i < randomGraph.adjacencyList.size(); i++) 
      {
         System.out.print(i + " -> {");
  
         List<Integer> list = randomGraph.adjacencyList.get(i);
  
         if (list.isEmpty())
            System.out.print("No adjacent vertices");
         else 
         {
            int size = list.size();
            for (int j = 0; j < size; j++)
            {
               System.out.print(list.get(j));
               if (j < size - 1)
                  System.out.print(", ");
            }
         }
         System.out.println("}");
         
		}
      System.out.print("\n");
      int numOfedges = 0;
      for (int i = 0; i < randomGraph.adjacencyList.size(); i++) 
      {
         List<Integer> list = randomGraph.adjacencyList.get(i);
         int size = list.size();
         numOfedges += size;
         
      }
      System.out.println("The graph has " + numOfedges + " edges");
      if (topologicalSort.hasCycle(randomGraph.adjacencyList.size(), randomGraph.adjacencyList))
      {
    	  System.out.println("\nGraph is not a valid DAG");
      }
      else
      {
    	System.out.println("\nTopological Sort using vertices with indegrees of zero:\n");
  		topologicalSort.topologicalSortTwo(randomGraph.adjacencyList.size(), randomGraph.adjacencyList);
  		System.out.println("\n");
  		System.out.println("\nThe Topological Sort we learned in class:\n");
  		topologicalSort.topologicalSortOne(randomGraph.adjacencyList.size(), randomGraph.adjacencyList);
  		System.out.println("\n");
      }

	}
}
