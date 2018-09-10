import java.io.*;

/**
 * Created by youshinkim on 2017. 2. 6..
 */
public class PercolationVisualizer {

    public static void main(String args[]) throws IOException {
        PrintWriter f = new PrintWriter("visualMatrix.txt");
        int result = 0;
        int n = StdIn.readInt();
        int x, y;
        Percolation p = new Percolation(n);
        f.println(n);
        f.println();
        StdOut.println(n);
        StdOut.println();
        while(!StdIn.isEmpty()) {
            x = StdIn.readInt();
            y = StdIn.readInt();
            // StdOut.println("open("+x+","+y+")");
            p.open(x,y);
            // StdOut.println(p.percolates()+"steps");
            for(int i = p.cellSize-1 ; i >= 0 ; i--) {
                for (int j = 0 ; j < p.cellSize;j++) {
                    //0 - blocked 1 - open 2 - full
                    if(p.isOpen(i,j)) {
                        result = 1;
                        if(p.isFull(i,j)) {
                            result = 2;
                        }
                    }else {
                        result = 0;
                    }
                    StdOut.print(result+ " ");
                    f.print(result);
                    f.print(' ');
                }
                f.print('\n');
                StdOut.println();
            }
            f.println();
            StdOut.println();
        }

        f.close();
    }
}
