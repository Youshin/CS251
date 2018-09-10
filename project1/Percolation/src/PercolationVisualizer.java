/**
 * Created by youshinkim on 2017. 1. 30..
 */
public class PercolationVisualizer {
    public static void main(String[] args) {
        int n = StdIn.readInt();
        String line = null;
        StdDraw.setXscale(0, n);
        StdDraw.setYscale(0, n);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.show(0);

        while ((line = StdIn.readLine())!= null) {
            int[][] temp = new int [n][n];
            for (int i = 0; i < n  ; i++) {
                for (int j = 0; j < n; j++) {
                    temp[i][j] = StdIn.readInt();
                }
            }
            new VisualizeFrames().show(temp);
            StdDraw.show(200);
        }
    }
}
