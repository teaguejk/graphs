import java.util.Iterator;

public class Components
{
    private AdjacencyLists graph;
    private int[] components;
    private int compNum;
    private int numMarked;
    private int n;

    public Components(String fileName)
    {
        graph = new AdjacencyLists(fileName);
        n = graph.order();
        components = new int[n];
        numMarked = 0;
        compNum = 0;
    }

    public static void main(String[] args)
    {
        if (args.length == 0)
        {
            System.out.println("Usage java Components <filename>");
            System.exit(0);
        }

        Components c = new Components(args[0]);
        c.countComponents();
        int numComponents = c.numComponents();
        System.out.println("There are " + numComponents + " components.");
    }

    public int numComponents()
    {
        return compNum;
    }

    public void countComponents()
    {
        for (int i = 0; i < n; i++)
        {
            if (components[i] == 0)
            {
                compNum++;
                backtrack(i);
            }
        }
    }

    private void backtrack(int v)
    {
        components[v] = compNum;
        numMarked++;

        Iterator<Integer> it = graph.neighbors(v);
        while (it.hasNext())
        {
            int vertex = it.next();
            if (components[vertex] == 0)
                backtrack(vertex);
        }
    }
}
