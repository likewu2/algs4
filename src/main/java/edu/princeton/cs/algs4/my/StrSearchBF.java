package edu.princeton.cs.algs4.my;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StrSearchBF {
    public static void main(String[] args) {
        String s= "ABCABDABCEABD";
        String a= "ABCE";
        System.out.println("s: "+s+ " a: "+a);
        int ans = parse(s, a);
        System.out.println("ans: "+ ans);
    }

    private static int parse(String s, String a) {
        int i=0,j=0;
        while (i<s.length() && j<a.length()) {
            if (s.charAt(i)==a.charAt(j)) {
                i++;
                j++;
            } else {
                i-=j-1;
                j=0;
            }
        }
        if (j==a.length()) return i-j;
        return -1;
    }
}

//cbaebabacd  abc  [0, 6]
//abab ab [0, 1, 2]
