package edu.princeton.cs.algs4.test;

import java.util.*;

public class PhoneBacktrace {
    public static void main(String[] args) {
        List<String> ans = letterCombinations("23");
        System.out.println("ans: "+ans);
    }

    public static List<String> letterCombinations(String digits) {
        List<String> res=new ArrayList<>();
        if(digits==null||digits.length()==0) {
            return res;
        }
        Map<Character, String> map=new HashMap<>(){{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "pqrs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        StringBuilder path=new StringBuilder();
        dfs(path, map, digits, 0, res);
        return res;
    }

    private static void dfs(StringBuilder path, Map<Character, String> map, String digits, int dept, List<String> res) {
        if(dept==digits.length()) {
            res.add(path.toString());
        } else {
            char digit=digits.charAt(dept);
            String str=map.get(digit);
            int len=str.length();
            for(int i=0;i<len;i++) {
                path.append(str.charAt(i));
                dfs(path, map, digits, dept+1, res);
                path.deleteCharAt(dept);
            }
        }
    }
}
