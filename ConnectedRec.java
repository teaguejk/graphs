import java.util.LinkedList;
import java.util.Iterator;

/**
 *
 * ConnectedRec.java
 *
 * @author Alice McRae
 *
 * @version
 *     August 25, 2021
 *
 *     backtrack(v)
 *         mark vertex v as seen 
 *         for each unmarked neighbor vertex of v
 *              backtrack(vertex) 
 *
 *         call backtrack(0) and see if you get to them all
 **/  
 

public class ConnectedRec
{

    private AdjacencyLists graph;  // graph to be traversed
    private boolean [] marked;     // mark vertices as seen
    private int numMarked;         // number of vertices marked so far
    private int n;                 // number of vertices in the graph

/**
 * Construct the graph and initialize all vertices to unmarked.
 *
 * @param fileName the name of the graph file 
 *
 * Creates AdjacencyLists representation of the file.
 **/

    public ConnectedRec(String fileName)
    {
        graph = new AdjacencyLists(fileName);
        n = graph.order();
        marked = new boolean[n]; 
        numMarked = 0;
    }

 /**
  * Determines if the graph is connected.
  *
  * @return true if the graph is connected, and false otherwise
  **/ 

    public boolean isConnected()
    {
        backtrack(0);
        return numMarked == n;
    }

 /**
  * Uses a backtracking algorithm to mark a vertex and all unmarked
  * vertices reachable from it.
  *
  * @param v  the vertex to mark and to search from
  *
  **/ 
    private void backtrack(int v)
    {
        marked[v] = true;
        numMarked++;

        Iterator<Integer> it = graph.neighbors(v);
        while (it.hasNext())
        {
              int vertex = it.next();
              if (!marked[vertex])
                 backtrack(vertex);
           
        }
     
     }


 /**
  * Takes input of a filename in the form first line the number of 
  *   vertices and subsequent lines are edges: pairs of vertices delimited
  *   by whitespace.  Vertex labels are 0 through n-1, where n is the 
  *   number specified by the first line of the file.   
  * The program outputs whether or not the graph is connected.
  *
  * @param args args[0] is the name of the file to read. 
  *
  **/
    public static void main(String [] args)
    {
        if (args.length == 0) {
           System.out.println("Usage: java Connected <filename>"); 
           System.exit(0);
        }

        ConnectedRec c  = new ConnectedRec(args[0]);
        boolean connected = c.isConnected();
        if (connected)
           System.out.println("The graph is connected.");
        else
           System.out.println("The graph is not connected.");
      
    } 

}
