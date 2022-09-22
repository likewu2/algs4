package edu.princeton.cs.algs4;

import java.util.Arrays;

/**
 *
 *  @author lkw
 */
public class QuickMyTest {
    // This class should not be instantiated.
    private QuickMyTest() { }

    // quicksort the subarray from a[lo] to a[hi]
    private static void sort(int[] a, int lo, int hi) {
        if(lo<hi) {
            int p=core(a,lo,hi);
            sort(a, lo, p - 1);
            sort(a,p+1,hi);
        }
    }

    // partition the subarray a[lo..hi] so that a[lo..j-1] <= a[j] <= a[j+1..hi]
    // and return the index j.
    private static int core(int[] a, int lo, int hi) {  //reverse sort
        int x=a[hi];
        int i=lo;
        for(int j=lo;j<=hi-1;j++) {
            //if(a[j]>=x){
            if(a[j]<=x){
                swap(a,i,j);
                i++;
            }
        }
        swap(a,i,hi);
        return i;
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