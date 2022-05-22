package edu.princeton.cs.algs4.my;

import java.util.Arrays;

public class DpBeibao {
    public static void main(String[] args) {
        int[] price=  {0, 8, 4, 3, 6, 9, 7, 2, 9, 5};
        int[] weight= {0, 8, 4, 3, 6, 9, 7, 2, 9, 5};
        //int n=10;
        int v=13;
        System.out.println("price: "+ Arrays.toString(price));
        System.out.println("weight: "+ Arrays.toString(weight));
        int ans = parse(v, price, weight);
        System.out.println("ans: "+ans);
    }

    private static int parse(int v, int[] price, int[] weight) {
        int[] dp=new int[v+1];
        int max=0;

        for(int i=1;i<price.length;i++) {
            for(int j=v;j>0;j--) {
                if(j-weight[i]>=0) {
                    dp[j] = Math.max(dp[j], dp[j-weight[i]]+price[i]);
                } else {
                    dp[j] = dp[i];
                }
            }
        }
        for(int i=1;i<v+1;i++) {
            max = max>dp[i] ? max : dp[i];
        }

        System.out.println("dp: "+Arrays.toString(dp));
        return max;
    }
}

//kitten -> sitting
//sitten  (k->s)
//sittin  (e->i)
//sitting ( ->g)
