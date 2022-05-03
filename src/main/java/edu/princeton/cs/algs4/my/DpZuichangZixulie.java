package edu.princeton.cs.algs4.my;

import java.util.Arrays;

public class DpZuichangZixulie {
    public static void main(String[] args) {
        int[] a= {5, 1, 6, 8, 2, 4, 5, 10};
        System.out.println("a: "+ Arrays.toString(a));
        int ans = parse(a);
        System.out.println("ans: "+ans);
    }

    private static int parse(int[] a) {
        int[] dp=new int[a.length+1];
        dp[0]=Integer.MIN_VALUE;
        dp[1]=-a[0];
        int Len=1;
        int p,r,m;
        for(int i=1;i<a.length;i++) {
            p=0;
            r=Len;
            while(p<=r) {
                m=(p+r)/2;
                if(dp[m]<a[i]) {
                    p=m+1;
                } else {
                    r=m-1;
                }
            }
            dp[p]=a[i];
            if(p>Len) {
                Len++;
            }
        }

        System.out.println("dp: "+Arrays.toString(dp));
        return Len;
    }
}
