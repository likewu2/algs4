package edu.princeton.cs.algs4.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MaxArea {
    public static void main(String[] args) {
        int[] a= {1,8,3,15,2,8,20,5,6,9,7,10,14};
        System.out.println("a: "+ Arrays.toString(a));
        double ans = parse(a);
        System.out.println("ans: "+ans);
    }

    private static double parse(int[] a) {
        int i=0,j=a.length-1,ans=0;
        int ansI=i,ansJ=j;
        int count=0;
        while(i<j) {
            int h= Math.min(a[i],a[j]);
            int tmpAns=h*(j-i);
            if(ans<tmpAns) {
                ans=tmpAns;
                ansI=i;
                ansJ=j;
            }
            System.out.println("i: "+i+" j: "+j+" tmpAns: "+tmpAns);
            //if(count % 2 ==0) i++; else j--;
            if(a[i]<a[j]) i++; else j--;
            count++;
        }
        System.out.println("ansI: "+ansI+" ansJ: "+ansJ);
        return ans;
    }
}
