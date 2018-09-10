/**
 * Created by youshinkim on 2017. 2. 6..
 */
public class PercolationStats {
    public static void main(String args[]) {
        int n = Integer.parseInt(args[0]);
        int sim = Integer.parseInt(args[1]);
        int n1,n2;
        Stopwatch totalw,meanw = null;
        double totalTime;
        double count = 0;
        double[] meanTime = new double[sim];
        double[] threshold = new double[sim];

        totalw = new Stopwatch();
        for(int i = 0; i < sim; i++) {
            meanw = new Stopwatch();
            if(args[2].equals("fast")) {
                Percolation p = new Percolation(n);
                while(!p.percolates()){
                    n1 = StdRandom.uniform(n);
                    n2 = StdRandom.uniform(n);
                    if(p.isOpen(n1,n2)) continue;
                    p.open(n1,n2);
                    count++;
                }
            }else if(args[2].equals("slow")) {
                PercolationQuick p = new PercolationQuick(n);
                while(!p.percolates()){
                    n1 = StdRandom.uniform(n);
                    n2 = StdRandom.uniform(n);
                    if(p.isOpen(n1,n2)) continue;
                    p.open(n1,n2);
                    count++;
                }
            }
            threshold[i] = count/((double)(n*n));
            meanTime[i] = meanw.elapsedTime();
            count = 0;
        }

        totalTime = totalw.elapsedTime();

        StdOut.println("**OUTPUT BELOW**");
        StdOut.println("mean threshold="+StdStats.mean(threshold));
        StdOut.println("std dev="+StdStats.stddev(threshold));
        StdOut.println("time="+totalTime);
        StdOut.println("mean time="+StdStats.mean(meanTime));
        StdOut.println("stddev time="+StdStats.stddev(meanTime));
        StdOut.println();
    }
}
