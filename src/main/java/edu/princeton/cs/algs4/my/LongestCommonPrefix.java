package edu.princeton.cs.algs4.my;

public class LongestCommonPrefix {
    public static void main(String[] args) {
        String[] a= {"flower", "flight", "flow"};
        System.out.println("a: "+ a);
        String ans = parse(a);
        System.out.println("ans: "+ans);
    }

    private static String parse(String[] strs) {
        if(strs.length==0) return "";

        int right=strs[0].length()-1;
        String s0=strs[0];
        for(int i=0;i<strs.length;i++) {
            String s=strs[i];
            while(s.indexOf(s0.substring(0, right))!=0) {
                if(right==0) return "";
                right--;
            }
            s0=strs[i];
        }

        return strs[0].substring(0, right);
    }
}
