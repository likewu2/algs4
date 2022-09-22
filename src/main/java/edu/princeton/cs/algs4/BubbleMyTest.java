package edu.princeton.cs.algs4;

import java.util.Arrays;

/**
 *
 *  @author lkw
 */
public class BubbleMyTest {
    // This class should not be instantiated.
    private BubbleMyTest() { }

    private static void sort(int[] a, int lo, int hi) {
        for(int i=lo;i<hi;i++) {
            boolean swaped=false;
            for(int j=lo;j<hi-i;j++){
                if(a[j]>a[j+1]) {
                    swap(a, j, j + 1);
                    swaped = true;
                }
            }
            if(!swaped) break;
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