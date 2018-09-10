import java.util.Random;

public class PercolationQuick {
    public QuickUnionUF getUf() {
        return uf;
    }

    boolean[][] cell;
    int n;
    QuickUnionUF uf;
    int water;
    public PercolationQuick(int n) {
        this.n = n;
        this.cell = new boolean[n][n];
        water = n * n+1;
        this.uf = new QuickUnionUF(n * n + 2);
    }
    public int getId(int x, int y) {
        return x*cell.length+y;
    }

    public void open(int x, int y) {
        if(isOpen(x,y)) return;
        cell[x][y] = true;

        if (x == n - 1) {
            //StdOut.println("water");
            uf.union(getId(x, y), uf.find(water));
        }
        //        if (x == 0) {
        //            StdOut.println("floor");
        //            uf.union(uf.getId(x, y), floor);
        //        }
        else if (x < cell.length) {
            if (isOpen(x, y + 1)) {
                uf.union(getId(x, y + 1),getId(x, y));
                //      StdOut.println("top");

            }
        }
        if (y < cell.length) {
            if (isOpen(x + 1, y)) {
                uf.union(getId(x + 1, y),getId(x, y));
                //   StdOut.println("right");

            }
        }
        if (x >= 0) {
            if (isOpen(x, y - 1)) {
                //StdOut.println(uf.getId(x,y) + " " + uf.getId(x,y-1));
                uf.union(getId(x, y - 1),getId(x, y));
                //   StdOut.println("bottom");

            }
        }
        if (y >= 0) {
            if (isOpen(x - 1, y)) {
                uf.union(getId(x - 1, y),getId(x, y));
                // StdOut.println("left");

            }
        }

    }

    public boolean isOpen(int x, int y) {
        if (x < cell.length && y < cell.length && x >= 0 && y >= 0) {
            return cell[x][y];
        }
        return false;
    }

    public boolean isFull(int x, int y) {
        if (x < cell.length && y < cell.length && x >= 0 && y >= 0) {
            return uf.connected(water, getId(x, y));
        }
        return false;
    }

    public boolean percolates() {
        for (int i = 0; i < cell.length; i++) {
            if (isFull(0, i)) return true;
        }
        return false;
    }

    //size * (i - 1) + j;
    public static void main(String args[]) {
        int n;
        PercolationQuick p;
        n = StdIn.readInt();
        // String[] nums;
        int result;

        int n1, n2;
        // StdOut.println(n);
        //  StdOut.println();
        p = new PercolationQuick(n);
        while (!StdIn.isEmpty()) {
            //nums = s.split("\\\\s+");
            //   StdOut.println(nums[0]);
            //StdOut.println(s.charAt(0)+" "+s.charAt(2));
            n1 = StdIn.readInt();
            n2 = StdIn.readInt();
            //   StdOut.println(n1 + " " + n2);
            p.open(n1, n2);
            for (int i = n - 1; i >= 0; i--) {
                for (int j = 0; j < n; j++) {
                    if (p.cell[i][j]) {
                        result = 1;
                        if (p.isFull(i, j)) result = 2;
                    } else result = 0;
                    StdOut.print(result + " ");
                }
                StdOut.println();
            }
            StdOut.println();
        }



//        int n = 10;
//        Random rn = new Random();
//        Percolation p = new Percolation(n);
//        int result;
//        for (int c = 0; c < 10; c++ ) {
//            while (!p.percolates()) {
//                int n1, n2;
//                n1 = rn.nextInt(n);
//                n2 = rn.nextInt(n);
//                // StdOut.println(n1 + " " + n2);
//                if (p.isOpen(n1,n2)) continue;
//                p.open(n1, n2);
//
//            }
//            for (int i = n - 1; i >= 0; i--) {
//                for (int j = 0; j < n; j++) {
//                    if (p.cell[i][j]) {
//                        result = 1;
//                        if (p.isFull(i, j)) result = 2;
//                    } else result = 0;
//                    StdOut.print(result + " ");
//                }
//                StdOut.println();
//            }
//            StdOut.println(p.percolates());
//            p = new Percolation(n);
//            StdOut.println();
//        }

    }
}