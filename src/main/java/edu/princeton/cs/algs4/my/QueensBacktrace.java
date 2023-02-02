package edu.princeton.cs.algs4.my;

import java.util.*;

public class QueensBacktrace {
    public static void main(String[] args) {
        String[] ans = queens(8, "");
        System.out.println("ans: "+ans);
        System.out.println("len: "+ans.length);
    }

    public static boolean conflict(String queen_str, int current_queent) {
        boolean flag = false;
        for(int index=0;index<queen_str.length();index++) {
            int abs = Math.abs(current_queent-Integer.valueOf(queen_str.substring(index, index+1)));
            if(abs>=0 && abs<=queen_str.length()-index) {
                flag=true;
                break;
            }
        }
        return flag;
    }

    private static String[] queens(int nums, String queen_str) {
        List<String> final_queens = new ArrayList<>();
        back(nums, queen_str, final_queens);
        return (String[]) final_queens.toArray();
    }

    private static void back(int nums, String queen_str, List<String> final_queens) {
        if(queen_str.length()==nums) {
            final_queens.add(queen_str);
            return;
        }
        for (int index=1;index<=nums;index++) {
            boolean flag = conflict(queen_str, index);
            if(!flag) {
                back(nums, queen_str+String.valueOf(index), final_queens);
            }
        }
    }
}
