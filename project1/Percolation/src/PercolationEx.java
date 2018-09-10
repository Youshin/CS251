import java.util.Random;

/*----------------------------------------------------------------
 *  Author:        Matt Farmer
 *  Written:       08/17/2012
 *  Last updated:  08/25/2012
 *
 *  Compilation:   javac Percolation.java
 *  Execution:     java Percolation
 *
 *  Tests the percolation as per the specification available at:
 *    http://coursera.cs.princeton.edu/algs4/assignments/percolation.html
 *
 *----------------------------------------------------------------*/
public class PercolationEx {

    int[][] cell;
    private WeightedQuickUnionUF uf;
    int water;
    int id;
    public PercolationEx(int n) {
        this.cell = new int[n][n];
        this.uf = new WeightedQuickUnionUF(n * n+1);
        this.water = n*n;
    }

    public void open(int x, int y) {
        if (x >= cell.length || y >= cell.length || x < 0 || y < 0) {
            return;
        }
        id = x*cell.length+y;
        if (cell[x][y] == 0) {
            if (x == cell.length - 1) {
                uf.union(id,water);
            } else {
                cell[x][y] = 1;
            }
        }
        switch (0) {
            case 0:
                if (x != cell.length - 1) {
                    //   StdOut.println("y!");
                    if (cell[x + 1][y] == 2&&!uf.connected(id+cell.length,id)) {
                        cell[x][y] = 2;
                        uf.union(id+cell.length,id);
                    }
                }
            case 1:
                if (y != cell.length - 1) {
                    //    StdOut.println("x!");
                    if (cell[x][y + 1] == 2&&!uf.connected(id+1,id)) {
                        cell[x][y] = 2;
                        uf.union(id+1,id);
                    }
                }
            case 2:
                if (x != 0) {
                    //    StdOut.println("y != 0");
                    if (cell[x - 1][y] == 2&&!uf.connected(id-cell.length,id)) {
                        cell[x][y] = 2;
                        uf.union(id-cell.length,id);
                    }
                }
            case 3:
                if (y != 0) {
                    //  StdOut.println("x != 0");
                    if (cell[x][y - 1] == 2&&!uf.connected(id-1,id)) {
                        cell[x][y] = 2;
                        uf.union(id-1,id);
                    }
                }

        }
    }

    //Returns true if cell (x,y) is open due to a previous call to open(int x, int y)
    public boolean isOpen(int x, int y) {
        if (x >= cell.length || y >= cell.length || x < 0 || y < 0)
            return false;
        if (cell[x][y] == 1) return true;
        return false;
    }

    public boolean isFull(int x, int y) {
        if (x >= cell.length || y >= cell.length || x < 0 || y < 0)
            return false;
        if (cell[x][y] == 2) return true;
        return false;
    }


    // does the system percolate?
    public boolean percolates() {
        for (int i = 0; i < cell.length; i++) {
            if (isFull(0, i)) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int n = 10;
        Random rn = new Random();
        PercolationEx p= new PercolationEx(n);
        int result;
        for(int c = 0; c < 10; c++) {
            p= new PercolationEx(n);
            while (!p.percolates()) {

                int n1, n2;
                n1 = rn.nextInt(n);
                n2 = rn.nextInt(n);
                // StdOut.println(n1 + " " + n2);
                //if (p.uf.connected(n1,n2)) continue;
                p.open(n1, n2);
                for (int i = n - 1; i >= 0; i--) {
                    for (int j = 0; j < n; j++) {
                        StdOut.print(p.cell[i][j] + " ");
                    }
                    StdOut.println();
                }
                StdOut.println();
            }
        }
        StdOut.println(p.percolates());

    }
}