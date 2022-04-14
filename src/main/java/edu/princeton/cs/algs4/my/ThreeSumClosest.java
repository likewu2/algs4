package edu.princeton.cs.algs4.my;

import java.util.*;

public class ThreeSumClosest {
    public static void main(String[] args) {
        int[] a= {2,45,4,8,12,8,6,8,53,22,31,6,10,7};
        int target=19;
        int ans = parse(a, target);
        System.out.println("ans: "+ans);
    }

    public static int parse(int[] a, int target) {
        int ansI=0, ansLeft=1, ansRight=2;
        Arrays.sort(a);
        System.out.println("a: "+Arrays.toString(a));
        int result=a[0]+a[1]+a[2];
        for(int i=0;i<a.length-2;i++) {
            int left=i+1;
            int right=a.length-1;
            while(left!=right) {
                int min=a[i]+a[left]+a[left+1];
                if(target<min) {
                    if(Math.abs(result-target)>Math.abs(min-target))
                        result=min;
                    break;
                }
                int max=a[i]+a[right]+a[right-1];
                if(target>max) {
                    if(Math.abs(result-target)>Math.abs(max-target))
                        result=max;
                    break;
                }
                int sum=a[i]+a[left]+a[right];
                if(Math.abs(sum-target)<Math.abs(result-target)) {
                    result=sum;
                    ansI=i;
                    ansLeft=left;
                    ansRight=right;
                }
                if(sum>target) {
                    right--;
                    while(left!=right && a[right]==a[right+1])
                        right--;
                }
                else {
                    left++;
                    while(left!=right && a[left]==a[left-1])
                        left++;
                }
            }
            while(i<a.length-2 && a[i]==a[i+1]) i++;
        }
        System.out.println("ansI: "+ansI+" ansLeft:"+ansLeft+" ansRight:"+ansRight);
        return result;
    }
}
