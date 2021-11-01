import java.util.LinkedList;
import java.util.Iterator;

/************************************
 *
 *   start vertex  u = 0
 *   mark vertex u = 0 as seen 
 *   add vertex u = 0 to the queue
 *   while (q is not empty)
 *      dequeue vertex v 
 *      for each unmarked neighbor vertex of v
 *            mark the vertex as seen
 *            add the vertex to the queue
 *
 */  
 

public class Connected
{

    public static void main(String [] args)
    {
        if (args.length == 0) {
           System.out.println("Usage: java Connected <filename>"); 
           System.exit(0);
        }

        // read the graph 
        AdjacencyLists graph = new AdjacencyLists(args[0]);

        if (isConnected(graph))
           System.out.println("The graph is connected.");
        else
           System.out.println("The graph is not connected.");
     }

     public static boolean isConnected(AdjacencyLists graph)
     {
         int n = graph.order();
      
         // marked[i] keeps track of whether vertex i has 
         //    been seen yet. Java initializes to all false. 
            
         boolean [] marked = new boolean[n]; 

         LinkedList<Integer> queue = new LinkedList<Integer>();
         int numMarked = 0;
          
         queue.add(0);
         marked[0] = true;
         numMarked++;

         while (!queue.isEmpty() && numMarked < n)
         {
             int v = queue.remove();
             Iterator<Integer> it = graph.neighbors(v);
             while (it.hasNext())
             {
                 int vertex = it.next();
                 if (!marked[vertex])
                 {
                     numMarked++;
                     marked[vertex] = true;
                     queue.add(vertex);
                 }
             }
         }
      
         return numMarked == n;
    }
}
