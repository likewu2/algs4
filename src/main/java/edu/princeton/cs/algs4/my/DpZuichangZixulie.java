package edu.princeton.cs.algs4.my;

import java.util.Arrays;

public class DpZuichangZixulie {
    public static void main(String[] args) {
        int[] a= {5, 1, 6, 8, 2, 4, 5, 10};
        System.out.println("a: "+ Arrays.toString(a));
        int ans = parse(a);
        System.out.println("ans: "+ans);
    }

    //dynamic program
    private static int parse1(int[] a) {
        int[] dp=new int[a.length];
        dp[0]=1;
        int Len=1;

        for (int i=1;i<a.length;i++) {
            dp[i]=1;
            for (int j=0;j<i;j++) {
                if (a[i]>a[j]) {
                    dp[i]=Math.max(dp[i], dp[j]+1);
                }
            }
            Len=Math.max(Len, dp[i]);
        }

        System.out.println("dp: "+Arrays.toString(dp));
        return Len;
    }

    //贪心+二分查找
    private static int parse(int[] a) {
        int[] dp=new int[a.length+1];
        dp[0]=Integer.MIN_VALUE;
        dp[1]=a[0];
        int Len=1;
        int pos,r,mid;
        for(int i=1;i<a.length;i++) {
            pos=0;
            r=Len;
            while(pos<=r) {
                mid=(pos+r)/2;
                if(dp[mid]<a[i]) {
                    pos=mid+1;
                } else {
                    r=mid-1;
                }
            }
            dp[pos]=a[i];
            if(pos>Len) {
                Len++;
            }
        }

        System.out.println("dp: "+Arrays.toString(dp));
        return Len;
    }
}
