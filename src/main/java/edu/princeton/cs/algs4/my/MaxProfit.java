package edu.princeton.cs.algs4.my;

import java.util.Arrays;

public class MaxProfit {
    public static void main(String[] args) {
        //int[] a= {7, 1, 5, 3, 6, 4};
        int[] a= {1, 2, 3, 4, 5};
        System.out.println("a: "+ Arrays.toString(a));
        int ans = parse(a);
        System.out.println("ans: "+ans);
    }

    private static int parse(int[] a) {
        if(a.length<2) return 0;

        int[][] dp=new int[a.length][2];
        dp[0][0]=0;
        dp[0][1]=-a[0];
        for(int i=1;i<a.length;i++) {
            dp[i][0]=Math.max(dp[i-1][0], dp[i-1][1]+a[i]);
            dp[i][1]=Math.max(dp[i-1][0]-a[i], dp[i-1][1]);
        }

        return dp[a.length-1][0];
    }
}
