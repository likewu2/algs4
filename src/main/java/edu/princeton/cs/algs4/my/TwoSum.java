package edu.princeton.cs.algs4.my;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static void main(String[] args) {
        int[] a= {2,45,4,8,12,8,6,8,53,22,31,6,10,7};
        int target=9;
        int[] b = sum(a,target);
        System.out.println("a: "+Arrays.toString(a));
        System.out.println("b: "+Arrays.toString(b));
    }

    private static int[] sum(int[] a, int target) {
        Map<Integer, Integer> aa=new HashMap<>();
        for(int i=0;i<a.length;i++) {
            if(aa.containsKey(target-a[i]))
                return new int[]{aa.get(target-a[i]),i};
            aa.put(a[i], i);
        }
        return new int[0];
    }
}
