import javafx.scene.control.Cell;

import java.io.BufferedReader;
import java.io.*;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

/**
 * Created by youshinkim on 2017. 1. 29..
 */

public class Percolation_old {
    int cell[][];
    int[] connected;
    Queue<Integer> q;
    int water;
  //  WeightedQuickUnionUF wuf;
    //  int count = 0;

    //Create a new n by n grid where all cells are initially blocked
    public Percolation_old(int n) {
        this.cell = new int[n][n];
        //this.wuf = new WeightedQuickUnionUF(n * n+1);
        this.water = n*n;
        this.connected = new int[n * n];
    }

    //x*n + y = index in connected
    //Open the site at coordinate (x,y), where x represents the row number and y the column number.
    // For consistency purposes, (0,0) will be the bottom­left cell of the grid and (n­1,n­1) will be on the top­right.
    // The graphical capabilities discussed later assume a similar convention.
    public void open(int x, int y) {
        if (x >= cell.length || y >= cell.length || x < 0 || y < 0) {
            return;
        }
        if (cell[x][y] == 0) {
            if (x == cell.length - 1) {
                cell[x][y] = 2;
            } else {
                cell[x][y] = 1;
            }
        }
        switch (0) {
            case 0:
                if (x != cell.length - 1) {
                    //   StdOut.println("y!");
                    if (cell[x + 1][y] == 2) {
                        cell[x][y] = 2;
                    }
                }
            case 1:
                if (y != cell.length - 1) {
                    //    StdOut.println("x!");
                    if (cell[x][y + 1] == 2) {
                        cell[x][y] = 2;
                    }
                }
            case 2:
                if (x != 0) {
                    //    StdOut.println("y != 0");
                    if (cell[x - 1][y] == 2) {
                        cell[x][y] = 2;
                    }
                }
            case 3:
                if (y != 0) {
                    //  StdOut.println("x != 0");
                    if (cell[x][y - 1] == 2) {
                        cell[x][y] = 2;
                    }
                }
            default:
                if (isOpen(x,y+1) && isFull(x,y)) {
                    open(x, y + 1);
                }
                if (isOpen(x+1,y) && isFull(x,y)) {
                    open(x + 1, y);
                }
                if (isOpen(x,y-1) && isFull(x,y)) {
                    // StdOut.print("if");
                    open(x, y - 1);
                }
                if (isOpen(x-1,y) && isFull(x,y)) {
                    open(x - 1, y);
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

    //Returns true if there is a path from cell (x,y) to the surface (i.e. there is percolation up to this cell)
    public boolean isFull(int x, int y) {
        if (x >= cell.length || y >= cell.length || x < 0 || y < 0)
            return false;
        if (cell[x][y] == 2) return true;
        return false;
    }

    //Analyzes the entire grid and returns true if the whole system percolates
    public boolean percolates() {
        for (int i = 0; i < cell.length; i++) {
            if (isFull(0, i)) return true;
        }
        return false;

    }

    //Create a main method that reads a description of a grid from standard input and validates if the system described
    //percolates or not, printing to standard output a simple "Yes" or "No" answer.
    public static void main(String[] args) {
//        int n = 10;
//        Percolation p= new Percolation(n);
//        Random rn = new Random();
//        while(!p.percolates()){
//            p.open(rn.nextInt(n),rn.nextInt(n));
//        }
//        //p.open(0,0);
//        for (int i = n-1; i >= 0  ; i--) {
//                for (int j = 0; j < n; j++) {
//                    StdOut.print(p.cell[i][j] + " ");
//                }
//                StdOut.println();
//            }
//        StdOut.println();
        int n;
        Percolation_old p;
        n = StdIn.readInt();
        // String[] nums;
        StdOut.println(n);
        StdOut.println();
        p = new Percolation_old(n);
        while (!StdIn.isEmpty()) {
            //nums = s.split("\\\\s+");
            //   StdOut.println(nums[0]);
            //StdOut.println(s.charAt(0)+" "+s.charAt(2));
            p.open(StdIn.readInt(),StdIn.readInt());
            for (int i = n-1; i >= 0  ; i--) {
                for (int j = 0; j < n; j++) {
                    StdOut.print(p.cell[i][j] + " ");
                }
                StdOut.println();
            }
            StdOut.println();
        }
        StdOut.println(p.percolates());

//        for(int i = 0; i < 3; i++) {
//            for(int j = 0; j < 3; j++) {
//                StdOut.print(p.cell[i][j]);
//            }
//            StdOut.println();
//        }

        //StdOut.println(p.isOpen(0, 1));
        //StdOut.println(p.isFull(0, 0));
    }
}
