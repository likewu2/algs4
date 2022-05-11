package edu.princeton.cs.algs4.my;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class PlusOne {
    public static void main(String[] args) {
        int[] a= {2,5,4,8,9,9};
        System.out.println("a: "+Arrays.toString(a));
        int[] b = add1(a);
        System.out.println("b: "+Arrays.toString(b));
    }

    private static int[] add1(int[] a) {
        for(int i=a.length-1;i>=0;i--) {
            a[i]++;
            a[i] %= 10;
            if(a[i]!=0)
                return a;
        }
        int[] b=new int[a.length+1];
        b[0]=1;
        return b;
    }
}
