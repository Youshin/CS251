/*************************************************************************
 * Compilation:  javac Point.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {
    // compare points by slope
    public final Comparator<Point> BY_SLOPE_ORDER = new Comparator<Point>() {
        @Override
        public int compare(Point o1, Point o2) {
            if (slopeTo(o1) < slopeTo(o2)) {
                return -1;
            }
            if (slopeTo(o1) == slopeTo(o2)) {
                return 0;
            }

            return 1;

        }
    };    // YOUR DEFINITION HERE

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // constructor
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }

    public double slopeTo(Point that) {
        if (that.x == this.x) {
            if (that.y == this.y) {
                return Double.NEGATIVE_INFINITY;
            }
            return Double.POSITIVE_INFINITY;
        }

        if (that.y == this.y) {
            return 0.0;
        }

        return (double) (that.y - this.y) / (that.x - this.x);
    }

    // are the 3 points p, q, and r collinear?
    public static boolean areCollinear(Point p, Point q, Point r) {
        /* YOUR CODE HERE */
        if (p.slopeTo(q) == q.slopeTo(r)) {
            return true;
        }
        return false;
    }

    // are the 4 points p, q, r, and s collinear?
    public static boolean areCollinear(Point p, Point q, Point r, Point s) {
        /* YOUR CODE HERE */
        if (areCollinear(p, q, r) && areCollinear(q, r, s)) {
            return true;
        }
        return false;
    }

    // is this point lexicographically smaller than that one?
    public int compareTo(Point that) {
        /* YOUR CODE HERE */
        // a1 < a2 or a1=a2 and b1 < b2 then (a1,b1) < (a2,b2)
        if (this.x < that.x) {
            return -1;
        } else if (this.x == that.x && this.y < that.y) return -1;
        else if (this.x == that.x && this.y == that.y) return 0;
        else {
            return 1;
        }
    }

}
