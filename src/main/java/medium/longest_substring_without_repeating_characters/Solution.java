package medium.longest_substring_without_repeating_characters;

import java.util.*;

/**
 * todo-》done 对比官方的解法，这里做了一点优化，就是在remove时，即左指针右移时，每次右移后都会走到while的判断分支，
 *      而这个分支会调用contains，如果能记录重复元素的下标，那么其实在下标之前的元素都可以直接删除（见line 24的循环，每次删除时，不需要调用contains），无需做contains判断
 *
 * 另外，相比官方解答，这里将循环的i值和左指针lk作了区分，官方解法中混淆了这两个概念，只使用了i变量，虽然结果是对的，但在需要解耦这两个概念时，只使用一个变量是不行的，比如我在to do中的需求
 *
 */
class Solution {
        public int lengthOfLongestSubstring(String s) {
            // 哈希集合，记录每个字符是否出现过
            Map<Character,Integer> occ = new HashMap<>();
            int n = s.length();
            // 右指针，初始值为 -1，相当于我们在字符串的左边界的左侧，还没有开始移动
            int rk = -1, ans = 0;
            int duplicateIdx=-1;
            int lk=0;
            int count=0;//间接地帮助维护i值
            for (int i = 0; i < n; ) {
                if (i != 0) {
                    if (duplicateIdx!=-1){
                        count=duplicateIdx-lk+1;
                        for (int j = lk; j <= duplicateIdx; j++) {
                            // 左指针向右移动一格，移除一个字符
                            occ.remove(s.charAt(j));
                            lk++;
                        }
                    }
                }
                while (rk + 1 < n && !occ.containsKey(s.charAt(rk + 1))) {
                    // 不断地移动右指针
                    occ.put(s.charAt(rk + 1),rk+1);
                    ++rk;
                }
                if(rk+1<s.length())
                    duplicateIdx=occ.get(s.charAt(rk + 1));
                // 第 i 到 rk 个字符是一个极长的无重复字符子串
                ans = Math.max(ans, rk - lk + 1);
                i++;
                if(count>0)
                    i=i+count-1;
                count=0;
            }
            return ans;
        }

    public static void main(String[] args) {
        System.out.println(new Solution().lengthOfLongestSubstring("abcdefgdwqzx"));
    }

}
