import java.util.LinkedList;
import java.util.Iterator;
import java.util.Scanner;
import java.io.IOException;
import java.io.FileReader;

/**
 * Adjacency Lists implementation of an undirected graph.
 *
 * @author Alice McRae 
 * @version 21 January 2019
 *
 *     Vertices are labeled 0..n-1, where n is
 *     the number of vertices in the graph.
 */

public class AdjacencyLists 
{ 
    private LinkedList<Integer> [] adjList;
    private int numVertices;
    private int numEdges;

    /**
     * Constructor sets up the adjacency lists for a graph
     *       with a set number of vertices, and no edges  
     *
     * @param num  number of vertices in the graph
     */

    public AdjacencyLists(int num)
    {
         numVertices = num;
         clearGraph();
    }

    /**
     * Constructor sets up the adjacency lists for a graph
     *       from a file.  The file is in the format
     *       first entry: the number of vertices
     *       subsequent entries: pairs of vertices 
     *               representing the edges  
     *
     * @param filename  name of the input file
     */

    public AdjacencyLists(String filename)
    {
       readGraph(filename);
    }

    /**
     * Removes all edges from the graph.
     */

    @SuppressWarnings("unchecked")
    public void clearGraph()
    {
        if (adjList == null)
           adjList = new LinkedList[numVertices];
        numEdges = 0;
        for (int i=0; i < numVertices; i++)
           adjList[i] = new LinkedList<Integer>();
    }

    /**
     * Inputs adjacency lists from a file.
     *
     * @param filename  name of the input file 
     *
     *       Reads the number of vertices and
     *       each edge from a file.  The file format is
     *       first entry: the number of vertices
     *       subsequent entries: pairs of vertices 
     *                          representing the edges.  
     */

    public void readGraph(String filename)
    {
       Scanner fileIn = null;
       int vertex1, vertex2;

       try {
           fileIn = new Scanner (new FileReader(filename));
           numVertices = fileIn.nextInt();
           clearGraph();
           vertex1 = fileIn.nextInt();
           while (vertex1 != -1) {
              vertex2 = fileIn.nextInt();
              addEdge(vertex1, vertex2);
              vertex1 = fileIn.nextInt();
           }
           fileIn.close();
        } catch (IOException ioe)
          {
             System.out.println (ioe.getMessage());
             System.exit(0);
          }
    }

    /**
     * Accessor for the number of vertices.
     *
     * @return  number of vertices in the graph
     */

    public int order()
    {
        return numVertices;
    }

    /**
     * Accessor for the number of edges.
     *
     * @return  number of edges in the graph
     */

    public int size()
    {
        return numEdges;
    }

    /**
     * Adds an edge uv to an undirected graph.
     *
     * @param u  one endpoint
     * @param v  one endpoint 
     *
     *  u is added to v's list, and v to u's list.
     */

    public void addEdge(int i,  int j)
    {
          adjList[i].add(j);
          adjList[j].add(i);
          numEdges++;
    }

    /**
     * Accessor for the degree of a vertex.
     *
     * @param   i  vertex in the graph 
     * @return  degree of vertex i 
     */

    public int degree(int i)
    {
        return adjList[i].size();
    }

    /**
     * Accessor for the neighbors of a vertex.
     *
     * @param   i  vertex in the graph 
     * @return  an iterator to the neighbors of i 
     */

    public Iterator<Integer> neighbors(int i)
    {
        return adjList[i].iterator();
    }
}
