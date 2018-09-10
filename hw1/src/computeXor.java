import java.util.HashMap;

/**
 * Created by youshinkim on 2017. 2. 26..
 */
public class computeXor {
    HashMap<Integer, Integer> a;
    int b[];
    boolean xor[];
    public computeXor(int a[], int b[]) {
         this.a = new HashMap<Integer, Integer>();
         this.b = b.clone();
         for(int i = 0; i < a.length; i++) {
             this.a.put(a[i],a[i]);
        }
        xor = new boolean[b.length];
    }

    public void compute(HashMap a,int b[]) {
        for(int i = 0 ; i < b.length; i++) {
            if(a.containsKey(b[i])) {
                xor[i] = false;
            }else {
                xor[i] = true;
            }
        }
    }

    public HashMap<Integer, Integer> getA() {
        return a;
    }

    public int[] getB() {
        return b;
    }

    public boolean[] getXor() {
        return xor;
    }

    public static void main(String[] args) {
        int[] a = {0,0,1};
        int[] b = {0,1,2};
        computeXor x = new computeXor(a,b);
        x.compute(x.getA(),x.getB());
        for(int i = 0; i < x.getXor().length;i++) {
            System.out.println(x.getXor()[i]);
        }
    }
}
