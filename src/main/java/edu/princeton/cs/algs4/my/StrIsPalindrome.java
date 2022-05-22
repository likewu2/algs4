package edu.princeton.cs.algs4.my;

import java.util.Arrays;
import java.util.Locale;

public class StrIsPalindrome {
    public static void main(String[] args) {
        //String a= "A man, a plan, a canal: Panama";
        String a= "race a car";
        System.out.println("a: "+ a);
        boolean ans = parse(a);
        System.out.println("ans: "+ans);
    }

    private static boolean parse(String a) {
        a=a.toLowerCase();
        char[] c=a.toCharArray();
        int i=0,j=a.length()-1;
        while (i<j) {
            if (!((c[i]>='0' && c[i]<='9') || (c[i]>='a' && c[i]<='z'))) {
                i++;
                continue;
            }
            if (!((c[j]>='0' && c[j]<='9') || (c[j]>='a' && c[j]<='z'))) {
                j--;
                continue;
            }

            if (c[i]!=c[j]) return false;
            i++;
            j--;
        }

        return true;
    }
}
