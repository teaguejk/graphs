import java.util.Iterator;
import java.util.LinkedList;

public class Bipartite
{
    private AdjacencyLists graph;
    private static int[] colors;
    private static boolean isBipartite;
    private static int n;

    public Bipartite(String fileName)
    {
        graph = new AdjacencyLists(fileName);
        n = graph.order();
        colors = new int[n];
        isBipartite = true;
        for (int i = 0; i < n; i++) {
            colors[i] = -1;
        }
    }

    public static void main(String[] args)
    {
        if (args.length == 0)
        {
            System.out.println("Usage java Bipartite <filename>");
            System.exit(0);
        }

        Bipartite b = new Bipartite(args[0]);

        //isConnected = b.isConnected();
        //numComponenets = b.numComponents();
        
        boolean res = b.isBipartite();

        if (res == true) {
            System.out.println("The graph is bipartite.");
            printColors(colors);
        } else {
            System.out.println("The graph is not bipartite.");
        }

    }

    private static void printColors(int[] arr) {
        for (int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    private boolean isBipartite() {
        for (int i = 0; i < n; i++) {
            if (colors[i] == -1) {
                if (i == 0) { 
                    colors[0] = 1;
                }
                isBipartite = backtrack(i);
                if (isBipartite == false) {
                    return false;
                }
                //backtrack(i);
            }
        }
        //return isBipartite;
        return true;
    }
    
    private boolean backtrack(int v)
    {
        Iterator<Integer> it = graph.neighbors(v);
        while (it.hasNext())
        {
            int vertex = it.next();    
            
            if (colors[vertex] == -1) {
                colors[vertex] = 1 - colors[v];
                backtrack(vertex);

            } else if (colors[vertex] == colors[v]) {
                return  false;
            }
        }
        return true;
    }
}
