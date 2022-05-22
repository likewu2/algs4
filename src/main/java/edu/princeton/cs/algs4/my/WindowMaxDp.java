package edu.princeton.cs.algs4.my;

import java.util.*;

public class WindowMaxDp {
    public static void main(String[] args) {
        int[] a= {1,3,-1,-3,5,3,6,7};
        System.out.println("a: "+ Arrays.toString(a));
        int[] ans = parse(a, 3);
        System.out.println("ans: "+Arrays.toString(ans));
    }

    private static int[] parse(int[] a, int k) {
        if(a.length*k==0) throw new RuntimeException("can not 0");

        int[] winmax=new int[a.length-k+1];
        for(int i=0;i<winmax.length;i++) {
            int max=Integer.MIN_VALUE;
            for(int j=i;j<i+k;j++) {
                max=Math.max(a[j], max);
            }
            winmax[i]=max;
        }
        return winmax;
    }
}
