package edu.princeton.cs.algs4;

import java.util.Arrays;

/**
 *
 *  @author lkw
 */
public class SelectMyTest {
    // This class should not be instantiated.
    private SelectMyTest() { }

    // no impl
    private static void sort(int[] a, int lo, int hi) {
        for(int i=lo;i<hi;i++) {
            int minIdx=i;
            for(int j=i+1;j<=hi;j++) {
                if (a[minIdx] > a[j]) minIdx = j;
            }
            swap(a,minIdx,i);
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