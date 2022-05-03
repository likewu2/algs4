package edu.princeton.cs.algs4.my;

import java.util.Arrays;

public class DpMaxProfit {
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

        int[][] dp=new int[a.length][2];
        dp[0][0]=0;
        dp[0][1]=-a[0];
        for(int i=1;i<a.length;i++) {
            dp[i][0]=Math.max(dp[i-1][0], dp[i-1][1]+a[i]);  //sell=max(sell, buy+a[i])
            //dp[i][1]=Math.max(-a[i], dp[i-1][1]);  //buy=max(buy, -a[i])  //买入和卖出一次股票
            dp[i][1]=Math.max(dp[i-1][0]-a[i], dp[i-1][1]);  //buy=max(buy, sell-a[i])  //买入和卖出多次股票
        }

        System.out.println("dp: "+Arrays.deepToString(dp));
        return dp[a.length-1][0];
    }
}

//7 dp[0]=[0, -7]
//1 dp[1]=[0, -1]   max(0, -7+1)  max(0-1, -7)
//5 dp[2]=[4, -1]   max(0, -1+5)  max(0-5, -1)
//3 dp[3]=[4,  1]   max(4, -1+3)  max(4-3, -1)
//6 dp[4]=[7,  1]   max(4, 1+6)  max(4-6, 1)
//4 dp[5]=[7,  3]   max(7, 1+4)  max(7-4, 1)
