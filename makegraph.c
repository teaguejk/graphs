/* where n is the number of vertices
 * and vertices 0..x-1 are in the first bipartite set,
 * and vertices x .. n-1 are in the second bipartite set
 * makes an input file graph with this format
 * first line: n x 
 * each other line: y z   where y z is an edge y in set 1, z in set 2
*/

#include <stdio.h>
#include <math.h>


main ()

{
   int numVerts, connectivity, i, j;
   char fileName[30];
   FILE *outfile;

   srandom (time (0)); 
   printf ("How many vertices do you want: ");
   scanf ("%d", &numVerts);
   printf ("Enter the connectivity (0 through 100): ");
   scanf ("%d", &connectivity);
   printf ("Enter the filename for the output: ");
   scanf ("%s", fileName);
   outfile = fopen (fileName,"w");

   fprintf (outfile, "%d\n", numVerts);

   for(i = 0; i < numVerts-1; i++)
      for (j = i+1; j < numVerts; j++)
          if (choose(1, 100) <= connectivity)
              fprintf(outfile, "%d  %d\n", i, j);
 
   fprintf(outfile, "-1  -1\n");
   fclose(outfile);
}


// returns a random int from lo to hi, inclusive
int choose ( int lo, int hi )
{
   int range;

   range = hi - lo + 1;
   return ( (random () % range) + lo );
}

