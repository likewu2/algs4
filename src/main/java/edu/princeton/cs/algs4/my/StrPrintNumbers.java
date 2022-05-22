package edu.princeton.cs.algs4.my;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StrPrintNumbers {
    public static void main(String[] args) {
        char[] a= {'1', '2', '3', '4', '5', '6', '7'};
        char[] b = {'0', '0', '0', '1', '2', '3', '4'};
        System.out.println("a: "+ Arrays.toString(a) + "b: " + Arrays.toString(b));
        long ans = parse(a, b);
        System.out.println("ans: "+ans);

        //List ans2 = printNumbers(2);
        //System.out.println("ans2: "+ ans2);
    }

    private static long parse(char[] origin, char[] aim) {

        return -1;
    }

    private static List printNumbers(int n) {
        List<Integer> ans=new ArrayList<>();
        int l=0;
        while(n>0) {
            n--;
            l=l*10+9;
        }
        for (int i=1;i<=l;i++) {
            ans.add(i);
        }
        return ans;
    }
}
