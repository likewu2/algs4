package edu.princeton.cs.algs4.my;

import java.util.Arrays;

public class FindMedianSortedArrays {
    public static void main(String[] args) {
        int[] a= {1,3,5,8,10,11};
        int[] b= {2,5,6,7,10,14};
        System.out.println("a: "+ Arrays.toString(a));
        System.out.println("b: "+ Arrays.toString(b));
        double ans = parse(a,b);
        System.out.println("ans: "+ans);
    }

    private static double parse(int[] a,int[] b) {
        int m=a.length,n=b.length;
        int len=m+n;
        int left=-1,right=-1;
        int aStart=0,bStart=0;
        for(int i=0;i<=len/2;i++) {
            left=right;
            if(aStart<m&&(bStart>=n||a[aStart]<b[bStart]))
                right=a[aStart++];
            else
                right=b[bStart++];
        }
        System.out.println("left: "+left+" right: "+right);
        if((len&1)==0)
            return (left + right) / 2.0;
        else
            return right;
    }
}
