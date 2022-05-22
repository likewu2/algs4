package edu.princeton.cs.algs4.my;

import java.util.*;

public class FindAnagrams {
    public static void main(String[] args) {
        String s= "cbaebabacd";
        String a= "abc";
        System.out.println("s: "+s+ " a: "+a);
        int[] ans = parse(s, a);
        System.out.println("ans: "+ Arrays.toString(ans));
    }

    private static int[] parse(String s, String a) {
        List<Integer> ll=new ArrayList<>();

        return Arrays.stream(ll.toArray(new Integer[ll.size()])).mapToInt(Integer::valueOf).toArray();
    }
}

//cbaebabacd  abc  [0, 6]
//abab ab [0, 1, 2]
