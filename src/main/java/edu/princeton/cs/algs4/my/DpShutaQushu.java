package edu.princeton.cs.algs4.my;

import java.util.Arrays;

public class DpShutaQushu {
    public static void main(String[] args) {
        int[] a= {5,  8, 4,  3, 6, 9,  7, 2, 9, 5};
        System.out.println("a: "+ Arrays.toString(a));
        int ans = parse(4, a);
        System.out.println("ans: "+ans);
    }

    private static int parse(int n, int[] a) {
        if(a.length<2) return 0;

        int max=0;
        int[][] dp=new int[n][n];
        int count=0;
        dp[0][0]=a[count];
        for(int i=1;i<n;i++) {
            for(int j=0;j<=i;j++) {
                count++;
                if(j==0)
                    dp[i][j] = dp[i-1][j] + a[count];
                else
                    dp[i][j] = Math.max(dp[i-1][j-1], dp[i-1][j]) + a[count];
                max=Math.max(dp[i][j], max);
            }
        }

        System.out.println("dp: "+Arrays.deepToString(dp));
        return max;
    }
}

//第一层 5
//第二层 8 4
//第三层 3 6 9
//第四层 7 2 9 5

