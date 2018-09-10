import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Created by youshinkim on 2017. 2. 14..
 */
/*
input을 받아서 첫줄은 포인트의 갯수
좌표 (x,y) 를 받아

brute force 를 사용해 맨 처음 좌표부터 이어져있는걸 찾아

이어진 갯수: 첫번쨰 포인트(x,y) -> 두번째 포인트(x,y) -> ... -> 이어진갯수만큼의 포인트(x,y)를 visualPoints.txt 에
적어 넣어야 한다

brute 에서 해야할것 : 만약 point 들이 collinear 일 때 arrayList 를 사용해 add


 */
public class Brute {
    static List<Point> pList_Sorted = new ArrayList<Point>();
    static Point[] pList = null;

    public Brute(Point[] p) {
        pList = new Point[p.length];
        pList = p;
    }

    public void sort(Point[] p) {
        Point tmp;
        for(int i = 0; i < p.length; i++) {
            for(int j = 0; j < p.length; j++) {
                if(p[i].compareTo(p[j]) == -1) {
                    tmp = p[i];
                    p[i] = p[j];
                    p[j] = tmp;
                }
            }
        }
    }

    public void writeCollinear() {
        String s = "";
        int n = 0;
        try {
            PrintWriter pw = new PrintWriter("visualPoints.txt");
//            for (int i = 0; i < this.pList.length; i++) {
//                StdOut.println("before x :" + this.pList[i].getX() + " y :" + this.pList[i].getY());
//            }
            sort(this.pList);
//            for (int i = 0; i < this.pList.length; i++) {
//                StdOut.println("after x :" + this.pList[i].getX() + " y :" + this.pList[i].getY());
//            }
            if (pList.length < 3) {
            } else {
                for (int i = 0; i < pList.length; i++) {
                 //   StdOut.println("pList["+i+"] : "+pList[i]);
                    for (int j = i+1; j < pList.length; j++) {
                        for (int r = j+1; r < pList.length; r++) {
                            for (int q = r+1; q < pList.length; q++) {
                                if (pList[i].areCollinear(pList[i], pList[r], pList[q],pList[j])) {
                                    pw.append("4:" + pList[i].toString() + " -> " + pList[j].toString() + " -> "
                                            + pList[r].toString() + " -> " + pList[q].toString() + '\n');
                                }
                            }
                        }
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
        Random r = new Random();
        Brute b;
        int count = 0;
        while (count < n) {
            //int x = StdIn.readInt();
            //int y = StdIn.readInt();
            //plot the point
            int x = r.nextInt(32767);
            int y = r.nextInt(32767);
            p = new Point(x, y);
            pList[count++] = p;
        }
        b = new Brute(pList);
        b.writeCollinear();
        StdOut.println("Seconds : "+s.elapsedTime());
    }

}
