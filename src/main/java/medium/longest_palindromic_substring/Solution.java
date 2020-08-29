package medium.longest_palindromic_substring;

import java.util.*;

/**
 * 我的做法是分为两种情况
 * 1：偶数长度回文数 ，如bb，abba,abccba
 * 2：奇数长度回文数 如 a, aba, abcba
 */
public class Solution {
    public String longestPalindrome(String s) {
        char[] chars= s.toCharArray();
        if(s.length()==0)
            return "";
        if (s.length()==1)
            return s;
        HashMap<Integer,String>map=new HashMap<>();
        int curFirstIndex=0;
        int curSecondIndex=1;
        int curLength=1;
        for (int i = 0; i < chars.length; i++) {
            if(curFirstIndex==chars.length||curSecondIndex==chars.length)
                break;
            //该分支处理偶数情况
            if(curFirstIndex>-1&&curSecondIndex<chars.length&&chars[curFirstIndex]==chars[curSecondIndex]){
                curLength=0;
                int tempHead=curFirstIndex;
                int tempTail=curSecondIndex;
                //原先我把chars[tempHead]==chars[tempTail]  写成 chars[tempHead--]==chars[tempTail++].导致下标不容易处理，容易下标越界，原因就是
                //条件判断是“读操作”，如果使用++ 或-- 等写操作则形成耦合，在读条件时造成额外的，不必要的写操作，且不利于代码阅读
                //因此改成了 在循环体内维护tempHead--和tempTail++
                while (tempHead>-1&&tempTail<chars.length&&chars[tempHead]==chars[tempTail]){
                    curLength+=2;
                    tempHead--;
                    tempTail++;
                }
                map.putIfAbsent(curLength,s.substring(tempHead+1,tempTail));
            }
            //该分支处理奇数情况
            if(curFirstIndex>-1&&curSecondIndex+1<chars.length&&chars[curFirstIndex]==chars[curSecondIndex+1]){
                curLength=1;
                int tempHead=curFirstIndex;
                int tempTail=curSecondIndex+1;
                while(tempHead>-1&&tempTail<chars.length&&chars[tempHead]==chars[tempTail]){
                    curLength+=2;
                    tempHead--;
                    tempTail++;
                }
                map.putIfAbsent(curLength,s.substring(tempHead+1,tempTail));
            }
            curFirstIndex++;
            curSecondIndex++;
        }
        int max=1;
        for (Integer integer : map.keySet()) {
            max=Math.max(max,integer);
        }
        return map.get(max)==null? String.valueOf(s.charAt(0)) :map.get(max);
    }

    public static void main(String[] args) {
        System.out.println(new Solution().longestPalindrome("abcda"));
    }
}
