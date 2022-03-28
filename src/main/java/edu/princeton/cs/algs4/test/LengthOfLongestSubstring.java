package edu.princeton.cs.algs4.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LengthOfLongestSubstring {
    public static void main(String[] args) {
        String a= "pwwkew";
        System.out.println("a: "+a);
        int b = parse(a);
        System.out.println("len: "+b);
    }

    private static int parse(String s) {
        int n=s.length(),ans=0;
        Map<Character, Integer> aa=new HashMap<>();
        int end=0,start=0;
        for(;end<n;end++) {
            char alpha=s.charAt(end);
            if(aa.containsKey(alpha)) {
                start=Math.max(aa.get(alpha), start);
            }
            aa.put(alpha, end+1);
        }
        System.out.println("sub: "+s.substring(start,end));
        return end-start;
    }
}
