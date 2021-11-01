import java.util.Iterator;
import java.util.LinkedList;

public class Tree 
{
    private AdjacencyLists graph;
    private static boolean isTree;
    private static int n;
    private static boolean[] visited;
    private static int[] parent;

    public Tree(String fileName)
    {
        graph = new AdjacencyLists(fileName);
        n = graph.order();
        isTree = true;
        visited = new boolean[n];
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            visited[i] = false;
            parent[i] = -1;
        }
    }

    public static void main(String[] args)
    {
        if (args.length == 0 || args.length != 2)
        {
            System.out.println("Usage java tree <filename>");
            System.exit(0);
        }

        Tree t = new Tree(args[0]);
     
        int root = Integer.parseInt(args[1]);
        boolean res = t.isTree();

        if (res == true) {
            System.out.println(args[0] + " is a tree.");
            printTree(t, root);

        } else {
            System.out.println(args[0] + " is not a tree.");
        }
    }

    private static void printTree(Tree t, int root) {
        
        System.out.printf("Vertex Parent\n");
        for (int i = parent[root]; i < n; i++) {
            System.out.printf(" %d\t%d\n", i, parent[i]);
        }   

    }

    private boolean isTree() {
        
        parent[0] = 0;

        if (hasCycle(0)) {
            return false;
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                return false;
            }
        }

        return true;
    }

    private boolean hasCycle(int v) {
       
       visited[v] = true;
       Iterator<Integer> it = graph.neighbors(v);
       while (it.hasNext()) {
                   
            int vertex = it.next();
            parent[vertex] = v;
            
            if (!visited[vertex]) {
                if (hasCycle(vertex)) {
                    return true;
                }
            } else if (vertex == parent[vertex]) {
                return true;
            }
       }
       return false;
    }

}

