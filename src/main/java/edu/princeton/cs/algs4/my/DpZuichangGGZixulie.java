package edu.princeton.cs.algs4.my;

import java.util.Arrays;

public class DpZuichangGGZixulie {
    public static void main(String[] args) {
        String aStr="abcicba";
        String bStr="abdkscab";
        System.out.println("a: "+ aStr);
        System.out.println("b: "+ bStr);
        String ans = parse(aStr, bStr);
        System.out.println("ans: "+ans);
    }

    private static String parse(String aStr, String bStr) {
        int aLen=aStr.length();
        int bLen=bStr.length();
        int[][] dp=new int[aLen+1][bLen+1];

        for(int i=1;i<aLen+1;i++) {
            for(int j=1;j<bLen+1;j++) {
                if(dp[i-1][j]==dp[i][j-1]
                        && aStr.charAt(i-1)==bStr.charAt(j-1)
                        && dp[i-1][j]==dp[i-1][j-1]) {
                    dp[i][j] = dp[i-1][j]+1;
                } else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }

        int max=dp[aLen][bLen];
        StringBuilder sb=new StringBuilder(128);
        while(max>0) {
            if(dp[aLen-1][bLen]==dp[aLen][bLen-1]
                && dp[aLen-1][bLen]+1==dp[aLen][bLen]) {
                sb.append(aStr.charAt(aLen-1));
                max--;
                aLen--;
                bLen--;
            } else {
                if(dp[aLen][bLen-1]==dp[aLen][bLen]) {
                    bLen--;
                } else {
                    aLen--;
                }
            }
        }

        System.out.println("dp: "+Arrays.deepToString(dp));
        return sb.reverse().toString();
    }
}
