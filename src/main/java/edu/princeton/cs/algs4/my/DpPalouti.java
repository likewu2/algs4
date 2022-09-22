package edu.princeton.cs.algs4.my;

import java.util.Arrays;

public class DpPalouti {
    public static void main(String[] args) {
        int a=4;
        System.out.println("a: "+ a);
        int ans = parse(a);
        System.out.println("ans: "+ans);
    }

    private static int parse(int a) {
        int[] dp=new int[a];
        int ans=dfs(a, dp);

        System.out.println("dp: "+Arrays.toString(dp));
        return ans;
    }

    private static int dfs(int n, int[] dp) {
        if(n==1) {
            dp[n-1]=1;
            return dp[n-1];
        } else if(n==2) {
            dp[n-1]=2;
            return dp[n-1];
        } else {
            dp[n-1]=dfs(n-2, dp)+dfs(n-1, dp);
            return dp[n-1];
        }
    }
}
