package edu.princeton.cs.algs4.my;

import java.util.Arrays;

public class DpEditDistance {
    public static void main(String[] args) {
        String aStr="kitten";
        String bStr="sitting";
        System.out.println("a: "+ aStr);
        System.out.println("b: "+ bStr);
        int ans = parse(aStr, bStr);
        System.out.println("ans: "+ans);
    }

    private static int parse(String aStr, String bStr) {
        int aLen=aStr.length();
        int bLen=bStr.length();
        int[][] dp=new int[aLen+1][bLen+1];

        for(int i=0;i<aLen+1;i++) {
            dp[i][0]=i;
        }
        for(int i=0;i<bLen+1;i++) {
            dp[0][i]=i;
        }
        System.out.println("dp: "+Arrays.deepToString(dp));
        for(int i=1;i<aLen+1;i++) {
            for(int j=1;j<bLen+1;j++) {
                if(aStr.charAt(i-1)==bStr.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                }
            }
        }

        System.out.println("dp: "+Arrays.deepToString(dp));
        return dp[aLen][bLen];
    }
}

//kitten -> sitting
//sitten  (k->s)
//sittin  (e->i)
//sitting ( ->g)
