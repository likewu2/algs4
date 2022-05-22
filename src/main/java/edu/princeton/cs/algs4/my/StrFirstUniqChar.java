package edu.princeton.cs.algs4.my;

import java.util.Arrays;

public class StrFirstUniqChar {
    public static void main(String[] args) {
        String a= "people";
        System.out.println("a: "+ a);
        long ans = parse(a);
        System.out.println("ans: "+ans);
    }

    private static long parse(String a) {
        int[] str_a_z=new int[26];
        for(int i=0;i<a.length();i++) {
            str_a_z[a.charAt(i)-'a'] = i;
        }

        for(int i=0;i<a.length();i++) {
            if(str_a_z[a.charAt(i)-'a']==i)
                return i;
            else
                str_a_z[a.charAt(i)-'a']=-1;
        }
        System.out.println("dp: "+Arrays.toString(str_a_z));
        return -1;
    }
}
