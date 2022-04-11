package edu.princeton.cs.algs4.test;

import java.util.*;

public class ThreeSumClosest {
    public static void main(String[] args) {
        int[] a= {2,45,4,8,12,8,6,8,53,22,31,6,10,7};
        int target=9;
        int ans = parse(a, target);
        System.out.println("ans: "+ans);
    }

    public static int parse(int[] a, int target) {
        int ansI=0, ansLeft=1, ansRight=a.length-1;
        Arrays.sort(a);
        int result=a[0]+a[1]+a[2];
        for(int i=0;i<a.length-2;i++) {
            int left=i+1;
            int right=a.length-1;
            while(left!=right) {
                int sum=a[i]+a[left]+a[right];
                if(Math.abs(sum-target)<Math.abs(result-target)) {
                    result=sum;
                    ansI=i;
                    ansLeft=left;
                    ansRight=right;
                }
                if(sum>target) {right--;}
                else {left++;}
            }
        }
        System.out.println("ansI: "+ansI+" ansLeft:"+ansLeft+" ansRight:"+ansRight);
        return result;
    }
}
