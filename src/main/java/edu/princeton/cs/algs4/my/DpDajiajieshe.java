package edu.princeton.cs.algs4.my;

import java.util.Arrays;

public class DpDajiajieshe {
    public static void main(String[] args) {
        int[] a= {1, 2, 3, 1};
        //int[] a= {2, 7, 9, 3, 1};
        System.out.println("a: "+ Arrays.toString(a));
        int ans = parse(a);
        System.out.println("ans: "+ans);
    }

    private static int parse(int[] a) {
        if(a.length<1) return 0;
        if(a.length==1) return a[0];
        if(a.length==2) return Math.max(a[0], a[1]);

        int[] dp=new int[a.length];
        dp[0]=a[0];
        dp[1]=Math.max(a[0], a[1]);
        for(int i=2;i<a.length;i++) {
            dp[i]=Math.max(dp[i-2]+a[i], dp[i-1]);
        }

        System.out.println("dp: "+Arrays.deepToString(dp));
        return dp[a.length-1];
    }
}
