package medium.word_break;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 用一个布尔数组保存 之前的计算结果，如果s的前j个字符可以拆分为合法的单词，那么只需要判断第j+1 及之后的若干字符能否拼为合法单词即可
 * 简单粗暴使用双重循环，这道题的复杂度定性分析也不可能很低，此时大胆使用双重循环也许还能简化题目
 */
public class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }
}
