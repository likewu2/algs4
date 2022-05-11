package edu.princeton.cs.algs4.my;

import java.util.Arrays;

public class MaxProfit {
    public static void main(String[] args) {
        int[] a= {7, 1, 5, 3, 6, 4};
        //int[] a= {1, 2, 3, 4, 5};
        //int[] a= {7, 6, 4, 3, 1};
        System.out.println("a: "+ Arrays.toString(a));
        int ans = parse(a);
        System.out.println("ans: "+ans);
    }

    private static int parse(int[] a) {
        if(a.length<2) return 0;

        int ans=0;
        for(int i=1;i<a.length;i++) {
            if(a[i]>a[i-1])
              ans += a[i]-a[i-1];
        }

        return ans;
    }
}
