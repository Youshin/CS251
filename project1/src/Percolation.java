import java.util.Random;

/**
 * Created by youshinkim on 2017. 2. 6..
 */
public class Percolation {
    boolean[] cell;
    int cellSize;
    WeightedQuickUnionUF uf;
    int water;
    public Percolation(int n){
        cell = new boolean[n*n];
        uf = new WeightedQuickUnionUF(n*n+1);
        water = n*n;
        cellSize = n;
    }

    //cell[x][y]의 id
    public int getId(int x, int y) {
        return x*cellSize+y;
    }

    //가능할까?
    public boolean checkValid(int x, int y) {
        if(x < cellSize && y < cellSize && x >= 0 && y >= 0) {
            return true;
        }
        return false;
    }

    //cell[x][y] x 가 세로 y 가 가로
    public void open(int x, int y){
        if(!checkValid(x, y)) return;
        cell[getId(x,y)] = true;

        //맨위면 물이랑 이어져
        if(x == cellSize - 1) {
         //   StdOut.println("water connected");
            uf.union(getId(x,y),water);
        }

        //위 오른쪽 아래 왼쪽순서 validity -> isopen확인 -> isFull확인 -> water연결
        if(checkValid(x+1,y)) {
            if(isOpen(x+1,y)) {
        //        StdOut.println("top connected");
                uf.union(getId(x+1,y),getId(x,y));
            }
        }

        if(checkValid(x,y+1)) {
            if(isOpen(x,y+1)){
        //        StdOut.println("right connected");
                uf.union(getId(x,y+1),getId(x,y));
            }
        }

        if(checkValid(x-1,y)) {
            if(isOpen(x-1,y)) {
          //      StdOut.println("bottom connected");
                uf.union(getId(x-1,y), getId(x,y));
            }
        }
        if(checkValid(x,y-1)) {
            if(isOpen(x,y-1)) {
          //      StdOut.println("left connected");
                uf.union(getId(x,y-1),getId(x,y));
            }
        }

    }

    public boolean isOpen(int x,int y) {
        return cell[getId(x,y)];
    }

    public boolean isFull(int x, int y) {
        return uf.connected(getId(x,y),water);
    }

    public boolean percolates(){
        for(int i = 0; i < cellSize; i++) {
            if(isFull(0,i)) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int n = StdIn.readInt();
        int x, y;
        Percolation p = new Percolation(n);
        while(!StdIn.isEmpty()) {
            x = StdIn.readInt();
            y = StdIn.readInt();
           // StdOut.println("open("+x+","+y+")");
            p.open(x,y);
          // StdOut.println(p.percolates()+"steps");       
		   }
        if(p.percolates()) {
            StdOut.println("Yes");
        }else{
            StdOut.println("No");
        }
	
   //     int n = 10;
    //    Percolation p = new Percolation(n);
     //   Random rn = new Random();
        int result = 0;
     //   while(!p.percolates()) {
            //StdOut.println("while");
     //       p.open(rn.nextInt(n),rn.nextInt(n));
     //   }
        for(int i = n-1; i >= 0; i--) {
            for(int j = 0; j < n; j++) {
                if(p.isOpen(i,j)){
                    result = 1;
                    if(p.isFull(i,j)) {
                        result = 2;
                    }
                }else {
                    result = 0;
                }
                StdOut.print(result+" ");
            }
            StdOut.println();
        }
    }
}
