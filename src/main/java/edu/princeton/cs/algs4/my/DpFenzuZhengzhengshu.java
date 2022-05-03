package edu.princeton.cs.algs4.my;

import java.util.Arrays;

public class DpFenzuZhengzhengshu {
    public static void main(String[] args) {
        int[] a= {1, 2, 3, 4, 5};
        System.out.println("a: "+ Arrays.toString(a));
        long ans = parse(a);
        System.out.println("ans: "+ans);
    }

    private static long parse(int[] a) {
        long sum=0, max=0;
        for(int i=0;i<a.length;i++) {
            sum += a[i];
        }
        int[] dp=new int[(int)(sum/2+1)];
        for(int i=0;i<a.length;i++) {
            for(int j=(int)(sum/2);j>0;j--) {
                if(j>=a[i]) {
                    dp[j]=Math.max(dp[j], dp[j-a[i]]+a[i]);
                }
            }
        }
        for(int i=1;i<sum/2+1;i++) {
            max=max>dp[i] ? max : dp[i];
        }
        System.out.println("dp: "+Arrays.toString(dp));
        return Math.abs((sum-max)-max);
    }
}
