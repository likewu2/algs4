package edu.princeton.cs.algs4.my;

import java.util.Arrays;

public class DpZuidaZiduanhe {
    public static void main(String[] args) {
        int[] a= {-2, 11, -4, 13, -5, -2};
        System.out.println("a: "+ Arrays.toString(a));
        int ans = parse2(a);
        System.out.println("ans: "+ans);
    }

    private static int parse(int[] a) {
        int maxSum=0, nowSum=0;
        for(int i=0;i<a.length;i++) {
            nowSum=nowSum+a[i];
            if(nowSum>maxSum) {
                maxSum=nowSum;
            }
            if(nowSum<0) {
                nowSum=0;
            }
        }

        return maxSum;
    }

    private static int parse2(int[] a) {  //error
        int maxSum=0;
        int nowSum=0;
        int left=0,right=a.length-1;
        while(left<=right) {
            if(a[left]<0) {
                left++;
                continue;
            }
            if(a[right]<0) {
                right--;
                continue;
            }

            nowSum=nowSum+a[left];
            if(nowSum>maxSum) {
                maxSum=nowSum;
            }
            left++;
        }

        return maxSum;
    }
}
