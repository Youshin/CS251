import java.util.NoSuchElementException;

public class SAP {
    Digraph digraph;

    // constructor
    public SAP(Digraph G) {
        this.digraph = G;
    } // Use the Digraph implementation provided by the book

    // return length of shortest ancestral path of v and w; -1 if no such path
    public int length(int v, int w) {
        //StdOut.println("v: " +v+ " w: "+ w);
        BreadthFirstDirectedPaths bfsv = new BreadthFirstDirectedPaths(this.digraph, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(this.digraph, w);
        int shortest = Integer.MAX_VALUE;
        for (int i = 0; i < this.digraph.V(); i++) {
            if (bfsv.hasPathTo(i) && bfsW.hasPathTo(i) && bfsv.distTo(i) + bfsW.distTo(i) < shortest) {
                shortest = bfsv.distTo(i) + bfsW.distTo(i);
            }
        }
        //StdOut.println("v: " +v+ " w: "+ w);
        if (shortest == Integer.MAX_VALUE) {
            shortest = -1;
        }
        return shortest;
    }

    //for wordNet.java
    public int length(Iterable<Integer> v, Iterable<Integer> w) {
        if(v == null || w == null) return -1;
        BreadthFirstDirectedPaths bfsv = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths bfsw = new BreadthFirstDirectedPaths(digraph, w);
        int shortest = Integer.MAX_VALUE;

        for (int i = 0; i < this.digraph.V(); i++) {
            if (bfsv.hasPathTo(i) && bfsw.hasPathTo(i) && bfsv.distTo(i)
                    + bfsw.distTo(i) < shortest) {

                shortest = bfsv.distTo(i) + bfsw.distTo(i);

            }
        }
        if (shortest == Integer.MAX_VALUE)
            shortest = -1;
        return shortest;
    }

    // return a common ancestor of v and w that participates in a shortest
    // ancestral path; -1 if no such path
    public int ancestor(int v, int w) {
        //StdOut.println("v: " +v+ " w: "+ w);
        BreadthFirstDirectedPaths bfsv = new BreadthFirstDirectedPaths(this.digraph, v);
        BreadthFirstDirectedPaths bfsW = new BreadthFirstDirectedPaths(this.digraph, w);

        //StdOut.println("v: " +v+ " w: "+ w);
        int smallest = -1;
        int length = Integer.MAX_VALUE;
        for (int i = 0; i < this.digraph.V(); i++) {
            if (bfsv.hasPathTo(i) && bfsW.hasPathTo(i)) {
                int l = bfsv.distTo(i) + bfsW.distTo(i);
                if (l < length) {
                    length = l;
                    smallest = i;
                }
            }
        }
        return smallest;
    }
    //for wordnet.java
    public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
        if(v == null || w == null) return -1;
        BreadthFirstDirectedPaths dfsv = new BreadthFirstDirectedPaths(digraph, v);
        BreadthFirstDirectedPaths dfsw = new BreadthFirstDirectedPaths(digraph, w);

        int smallest = -1;
        int length = Integer.MAX_VALUE;
        for (int i = 0; i < this.digraph.V(); i++) {
            if (dfsv.hasPathTo(i) && dfsw.hasPathTo(i)) {
                int l = dfsv.distTo(i) + dfsw.distTo(i);
                if (l < length) {
                    length = l;
                    smallest = i;
                }
            }
        }
        return smallest;
    }


    public static void main(String args[]) {
        In in = new In(args[0]);
        Digraph G = new Digraph(in);
        //StdOut.println(G.toString());
        SAP sap = new SAP(G);
        In in1 = new In(args[1]);
        try {
            while (!in1.isEmpty()) {
                int v = in1.readInt();
                int w = in1.readInt();

                System.out.println("sap = " + sap.length(v, w) + ", ancestor = " + sap.ancestor(v, w));
            }
        } catch (NoSuchElementException e) {
            throw new IllegalArgumentException("invalid input format in Digraph constructor", e);
        }

    }
}