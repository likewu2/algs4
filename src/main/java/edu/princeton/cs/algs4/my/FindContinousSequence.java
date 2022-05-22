package edu.princeton.cs.algs4.my;

import java.util.*;

public class FindContinousSequence {
    public static void main(String[] args) {
        int[][] ans = parse(9);
        System.out.println("ans: "+Arrays.deepToString(ans));
    }

    private static int[][] parse(int k) {
        List<int[]> ll=new ArrayList<>();
        int i=1;
        int j=1;
        int win=0;
        while(i<=k/2) {
            if(win<k) {
                win+=j;
                j++;
            } else if(win>k) {
                win-=i;
                i++;
            } else {
                int[] arr=new int[j-i];
                for (int n=i;n<j;n++) {
                    arr[n-i]=n;
                }
                ll.add(arr);
                win-=i;
                i++;
            }
        }
        return ll.toArray(new int[ll.size()][]);
    }
}
