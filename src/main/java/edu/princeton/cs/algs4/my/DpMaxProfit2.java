package edu.princeton.cs.algs4.my;

import java.util.Arrays;

public class DpMaxProfit2 {
    public static void main(String[] args) {
        int[] a= {7, 1, 5, 3, 6, 4, 6};
        //int[] a= {1, 2, 3, 4, 5};
        //int[] a= {7, 6, 4, 3, 1};
        System.out.println("a: "+ Arrays.toString(a));
        int ans = parse(a);
        System.out.println("ans: "+ans);
    }

    private static int parse(int[] a) {
        if(a.length<2) return 0;

        int[][] dp1=new int[a.length][2];
        int[][] dp2=new int[a.length][2];
        dp1[0][0]=0;
        dp1[0][1]=-a[0];
        dp2[0][0]=0;
        dp2[0][1]=-a[0];
        for(int i=1;i<a.length;i++) {
            dp1[i][0]=Math.max(dp1[i-1][0], dp1[i-1][1]+a[i]);  //sell1=max(sell1, buy1+a[i])
            dp1[i][1]=Math.max(-a[i], dp1[i-1][1]);            //buy1=max(buy1, -a[i])  //第一次买入和卖出股票
            dp2[i][0]=Math.max(dp2[i-1][0], dp2[i-1][1]+a[i]);  //sell2=max(sell2, buy2+a[i])
            dp2[i][1]=Math.max(dp1[i-1][0]-a[i], dp2[i-1][1]);  //buy=max(buy1, sell-a[i])  //第二次买入和卖出股票
        }

        System.out.println("dp1: "+Arrays.deepToString(dp1));
        System.out.println("dp2: "+Arrays.deepToString(dp2));
        return dp2[a.length-1][0];
    }
}
