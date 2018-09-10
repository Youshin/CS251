import java.util.Random;

/**
 * Created by youshinkim on 2017. 2. 5..
 */
public class PercolationStats {

    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int sim = Integer.parseInt(args[1]);
        int cellOpened;
        int result;
        double[] probabilities = new double[sim];
        double[] meantime = new double[sim];
        //StdOut.println("Sim:"+sim);
        Random rn = new Random();
        Stopwatch totalTime = new Stopwatch();
        Stopwatch mean_time = null;
        for (int s = 0; s < sim; s++) {
            if (args[2].equals("fast")) {
                Percolation p = new Percolation(n);
                mean_time = new Stopwatch();
                cellOpened = 0;

                while (!p.percolates()) {
                    int n1, n2;
                    n1 = rn.nextInt(n);
                    n2 = rn.nextInt(n);
                    // StdOut.println(n1 + " " + n2);
                    //if (p.uf.connected(n1,n2)) continue;
                    if (p.isOpen(n1, n2)) continue;
                    //double start = System.nanoTime();
                    p.open(n1, n2);
                    //double endTime = System.nanoTime();
                    //double elapsed = (endTime - start) * 1000000000;
                    //System.out.println(elapsed);
                    cellOpened++;
                }

                probabilities[s] = cellOpened;
//                for (int i = n - 1; i >= 0; i--) {
//                    for (int j = 0; j < n; j++) {
//                        if (p.cell[i][j]) {
//                            result = 1;
//                            if (p.isFull(i, j)) result = 2;
//                        } else result = 0;
//                        StdOut.print(result + " ");
//                    }
//                    StdOut.println();
//                }
            }
            if(args[2].equals("slow")){
                PercolationQuick p = new PercolationQuick(n);
                mean_time = new Stopwatch();
                cellOpened = 0;
                while (!p.percolates()) {
                    int n1, n2;
                    n1 = rn.nextInt(n);
                    n2 = rn.nextInt(n);
                    // StdOut.println(n1 + " " + n2);
                    if (p.isOpen(n1, n2)) continue;
                 //   double start = System.nanoTime();
                    p.open(n1, n2);
                  //  double endTime = System.nanoTime();
                   // double elapsed = (endTime - start) * 1000000000;
                   // System.out.println(elapsed);

                    cellOpened++;
                }
//                for (int i = n - 1; i >= 0; i--) {
//                    for (int j = 0; j < n; j++) {
//                        if (p.cell[i][j]) {
//                            result = 1;
//                            if (p.isFull(i, j)) result = 2;
//                        } else result = 0;
//                        StdOut.print(result + " ");
//                    }
//                    StdOut.println();
//                }
                probabilities[s] = cellOpened;
            }
            meantime[s] = mean_time.elapsedTime();
            // double prob = cellOpened/((double)(n*n));
            //StdOut.println(prob);

//            //p.open(0,0);
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
        }


        /*
        **OUTPUT BELOW**
        mean threshold=the_threshold_value
        std dev=the_threshold_stddev
        time=total_time_in_seconds
        mean time=mean_time_in_seconds
        stddev time=stddev_time_in_second
         */

        StdOut.println("mean threshold=" + StdStats.mean(probabilities));
        StdOut.println("std dev=" + StdStats.stddev(probabilities));
        StdOut.println("time=" + totalTime.elapsedTime());
        StdOut.println("mean time=" + StdStats.mean(meantime));
        StdOut.println("stddev time=" + StdStats.stddev(meantime));


        StdOut.println();

    }
}
