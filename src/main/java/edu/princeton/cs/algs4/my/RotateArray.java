package edu.princeton.cs.algs4.my;

import java.util.Arrays;

public class RotateArray {
    public static void main(String[] args) {
        int[] a= {1, 2, 3, 4, 5, 6, 7};
        int b=3;
        System.out.println("a: "+ Arrays.toString(a));
        parse(a, b);
        System.out.println("ans: "+Arrays.toString(a));
    }

    private static void parse(int[] a, int b) {
        b=b%a.length;

        reverse(a, 0, a.length-1);
        reverse(a,0, b-1);
        reverse(a, b, a.length-1);
    }

    private static void reverse(int[] a, int start, int end) {
        int len=end-start+1;
        for(int i=start;i<start+len/2;i++) {
            int tmp=a[i];
            int right=2*start+len-1-i;
            a[i]=a[right];
            a[right]=tmp;
        }
    }
}
