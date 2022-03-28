package edu.princeton.cs.algs4;

import java.util.Arrays;

/**
 *
 *  @author lkw
 */
public class InsertionMyTest {
    // This class should not be instantiated.
    private InsertionMyTest() { }

    // select the subarray from a[lo] to a[hi]
    private static void sort(int[] a, int lo, int hi) {
        for(int i=lo+1;i<=hi;i++) {
            for(int j=i;j>=lo;j--) {
                if(a[j-1]>a[j]) swap(a,j-1,j);
                else break;
            }
        }
    }

    public static void main(String[] args) {
        int[] a= {2,45,4,8,12,8,6,8,53,22,31,6,10};
        sort(a,0,a.length-1);
        System.out.println(Arrays.toString(a));
    }

    private static void swap(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
}