import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by youshinkim on 2017. 2. 17..
 */
public class Fast {
    static Point[] pList = null;
    static LinkedList<Point> segments = new LinkedList<>();

    public Fast(Point[] p) {
        pList = new Point[p.length];
        pList = p.clone();
    }

    public boolean isDub(LinkedList<Point> ls) {
        for (int a = 0; a < segments.size(); a++) {
            //    StdOut.println("First: "+segments.getFirst().toString() + " " + ls.getFirst().toString());
            //  StdOut.println("Last: "+ segments.getLast().toString() + " " + ls.getLast().toString());
            if (segments.get(a).toString().equals(ls.getFirst().toString()) &&
                    segments.get(segments.size()-1-a).toString().equals(ls.getLast().toString())) {
                //      StdOut.println("return true;");
                //    StdOut.println();
                return true;
            }
        }
        //    StdOut.println("return false;");
        //  StdOut.println();
        return false;
    }

    //    for (int s = 0; s < pAList.size(); s++) {
//        pw.append(pAList.get(s).toString());
//        if (s != pAList.size() - 1) {
//            pw.append(" -> ");
//        } else {
//            pw.append('\n');
//            StdOut.println("i and j :" + i + " " + j);
//
//        }
//    }
    public void writeCollinear() {
        try {
            PrintWriter pw = new PrintWriter("visualPoints.txt");

            for (Point point : pList) {
                Point[] tmp = pList.clone();
                Arrays.sort(tmp, point.BY_SLOPE_ORDER);

                LinkedList<Point> pLList = new LinkedList<Point>();
                pLList.add(point);

                for (int j = 1; j < pList.length - 1; j++) {
                    Point tmpPoint = tmp[j];
                    Point nextTmpPoint = tmp[j + 1];
                    double slope = point.slopeTo(tmpPoint);
                    double nextSlope = point.slopeTo(nextTmpPoint);
                    if (slope == nextSlope) {
                        if (pLList.peekLast() != tmpPoint) {
                            pLList.add(tmpPoint);
                        }
                        pLList.add(nextTmpPoint);
                    }

                    if (slope != nextSlope || j == pList.length - 2) {
                        if (pLList.size() > 3) {
                            Collections.sort(pLList);
                            if (!isDub(pLList)) {
                                segments.addFirst(pLList.getFirst());
                                segments.addLast(pLList.getLast());
                                pw.append(pLList.size() + ":");
                                for (int s = 0; s < pLList.size(); s++) {
                                    pw.append(pLList.get(s).toString());
                                    if (s != pLList.size() - 1) {
                                        pw.append(" -> ");
                                    } else {
                                        pw.append("\n");
                                        //StdOut.println("i and j :" + point + " " + j);
                                    }
                                }
                            }
                        }
                        pLList.clear();
                        pLList.add(point);
                    }
                }
            }


            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Stopwatch s = new Stopwatch();
        int n = StdIn.readInt();
        Point p = null;
        pList = new Point[n];
        Fast f;
        int count = 0;
        Random rn = new Random();
        while (count < n) {
            //int x = StdIn.readInt();
            //int y = StdIn.readInt();
            //StdOut.println(x + " " + y);
            int x = rn.nextInt(32767);
            int y = rn.nextInt(32767);
            //plot the point
            p = new Point(x, y);
            pList[count++] = p;
        }
        f = new Fast(pList);
        f.writeCollinear();
        StdOut.println("Seconds : "+s.elapsedTime());
    }

}
