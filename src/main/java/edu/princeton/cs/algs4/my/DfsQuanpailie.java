package edu.princeton.cs.algs4.my;

import java.util.*;

public class DfsQuanpailie {
    public static void main(String[] args) {
        int[] a={1, 2, 3};
        List<List<Integer>> ans=new ArrayList<List<Integer>>();
        dfs(a, new ArrayList<>(), ans);
        System.out.println("ans: "+ans);
        System.out.println("ans size: "+ans.size());
    }

    private static void dfs(int[] a, List<Integer> tmp, List<List<Integer>> aa) {
        if(tmp.size()==a.length) {
            aa.add(new ArrayList<>(tmp));
        } else {
            for(int i=0;i<a.length;i++) {
                if(!tmp.contains(a[i])){
                    tmp.add(a[i]);
                    dfs(a, tmp, aa);
                    tmp.remove(tmp.size()-1);
                }
            }
        }
    }
}
