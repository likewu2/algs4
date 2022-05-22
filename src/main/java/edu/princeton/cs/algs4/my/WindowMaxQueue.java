package edu.princeton.cs.algs4.my;

import java.util.*;

public class WindowMaxQueue {
    public static void main(String[] args) {
        int[] a= {1,3,-1,-3,5,3,6,7};
        System.out.println("a: "+ Arrays.toString(a));
        int[] ans = parse(a, 3);
        System.out.println("ans: "+Arrays.toString(ans));
    }

    private static int[] parse(int[] a, int k) {
        if(a.length*k==0) throw new RuntimeException("can not 0");

        List<Integer> ll=new LinkedList<>();
        Deque<Integer> queue=(Deque)ll;
        int[] winmax=new int[a.length-k+1];
        for(int i=0;i<a.length;i++) {
            while(i>0 && queue.size()>0 && a[i]>ll.get(ll.size()-1)) {
                queue.pollLast();
            }
            queue.add(a[i]);
            if(i>k && a[i-k]==ll.get(0)) {
                queue.poll();
            }
            if(i>=k-1) winmax[i-k+1]=ll.get(0);
        }
        return winmax;
    }
}
