package edu.princeton.cs.algs4.my;

import java.util.Arrays;

public class DpJuzhenQushu {
    public static void main(String[] args) {
        int[] a= {1, 3, 3,  2, 1, 3,  2, 2, 1};
        System.out.println("a: "+ Arrays.toString(a));
        int ans = parse(3, a);
        System.out.println("ans: "+ans);
    }

    private static int parse(int n, int[] a) {
        //if(a.length<2) return 0;

        int[] dp=new int[n+1];
        int[] readMax=new int[n+1];
        int count=0;
        for(int i=0;i<n;i++) {
            for(int k=1;k<n+1;k++) {
                readMax[k] = a[count];
                count++;
            }
            for(int j=1;j<n+1;j++) {
                dp[j] = Math.max(dp[j], dp[j-1]) + readMax[j];
            }
        }

        System.out.println("readMax: "+Arrays.toString(readMax));
        System.out.println("dp     : "+Arrays.toString(dp));
        return dp[n];
    }
}

//第一层 1 3 3
//第二层 2 1 3
//第三层 2 2 1
