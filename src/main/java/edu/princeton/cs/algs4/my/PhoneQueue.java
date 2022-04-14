package edu.princeton.cs.algs4.my;

import java.util.*;

public class PhoneQueue {
    public static void main(String[] args) {
        List<String> ans = letterCombinations("29");
        System.out.println("ans: "+ans);
    }

    public static List<String> letterCombinations(String digits) {
        List<String> res=new ArrayList<>();
        if(digits==null||digits.length()==0) {
            return res;
        }
        Map<Character, String[]> map=new HashMap<>(){{
            put('2', new String[]{"a","b","c"});
            put('3', new String[]{"d","e","f"});
            put('4', new String[]{"g","h","i"});
            put('5', new String[]{"j","k","l"});
            put('6', new String[]{"m","n","o"});
            put('7', new String[]{"p","q","r","s"});
            put('8', new String[]{"t","u","v"});
            put('9', new String[]{"w","x","y","z"});
        }};
        Queue<String> queue=new LinkedList<>();
        for (int i=0;i<digits.length();i++) {
            queue_letterCobinations(queue, map.get(digits.charAt(i)));
        }
        for (String s: queue) {
            res.add(s);
        }
        return res;
    }

    private static Queue<String> queue_letterCobinations(Queue<String> queue, String[] letters) {
        if(queue.size()==0) {
            for(String letter: letters) {
                queue.add(letter);
            }
        } else {
            int len=queue.size();
            for(int i=0;i<len;i++) {
                String s=queue.poll();
                for(String letter: letters) {
                    queue.add(s+letter);
                }
            }
        }
        return queue;
    }
}
