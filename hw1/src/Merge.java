import java.util.Comparator;

/**
 * Created by youshinkim on 2017. 2. 26..
 */

public class Merge {
    public static int sort(int[] a) {
        return sort(a,0,a.length-1);
    }

    public static int sort(int[] a, int lo, int hi) {
        if(hi <= lo) return 0;
        int mid = lo + (hi-lo)/2;
        int inversion = sort(a, lo, mid);
        inversion += sort(a, mid+1, hi);
        inversion += merge(a,lo,mid,hi);
        return inversion;
    }

    static int merge(int[] arr,int lo,int mid,int hi){
        int n1=mid-lo+1;
        int n2=hi-mid;
        int i=0,j=0,k=lo,invCount=0;
        int[] t1=new int[n1];
        int[] t2=new int[n2];

        for( i=0;i<n1;i++)
            t1[i]=arr[lo+i];
        for( j=0;j<n2;j++)
            t2[j]=arr[mid+j+1];
        i=0;
        j=0;
        while (i<n1 && j<n2){
            if(t1[i]<=t2[j])
                arr[k++]=t1[i++];
            else {
                arr[k++] = t2[j++];
                invCount+=mid-i+1-lo;
            }
        }
        while(j<n2)
            arr[k++]=t2[j++];
        while(i<n1)
            arr[k++]=t1[i++];
        return invCount;
    }

    public static void main(String args[]){
        int[] a = {2, 4, 1, 3, 5};
        System.out.println("inversion :"+ sort(a));
        for(int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }

    }
}