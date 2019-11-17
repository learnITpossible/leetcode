//给定一个只包含 '(' 和 ')' 的字符串，找出最长的包含有效括号的子串的长度。 
//
// 示例 1: 
//
// 输入: "(()"
//输出: 2
//解释: 最长有效括号子串为 "()"
// 
//
// 示例 2: 
//
// 输入: ")()())"
//输出: 4
//解释: 最长有效括号子串为 "()()"
// 
// Related Topics 字符串 动态规划

package com.markdown.leetcode.editor.cn;

public class LeetCode_32_LongestValidParentheses {
    public static void main(String[] args) {
        Solution solution = new LeetCode_32_LongestValidParentheses().new Solution();
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int longestValidParentheses(String s) {

            // a. 子问题
            //    有效的字符串总是以')'结尾
            //    i 处的最长有效字符串
            //      如果 s[i] = '('，dp[i] = 0;
            //      如果 s[i] = ')'
            //        如果 s[i - 1] = '('，刚好与之匹配，dp[i] = dp[i - 2] + 2
            //        如果 s[i - 1] = ')'，
            //          如果 s[i - dp[i - 1] - 1] = '('
            //            dp[i] = dp[i - 1] + dp[i - dp[i - 1] - 2] + 2;
            //          如果 s[i - dp[i - 1] - 1] = ')'
            //            dp[i] = 0;
            // b. 状态数组
            //    DP数组记录每个位置的最长有效字符串长度
            // c. DP 方程
            if (s == null || s.length() < 2) return 0;
            char[] chs = s.toCharArray();
            int[] dp = new int[chs.length];
            int max = 0;
            for (int i = 1; i < chs.length; i++) {
                if (chs[i] == ')') {
                    if (chs[i - 1] == '(') {
                        dp[i] = (i - 2 >= 0 ? dp[i - 2] : 0) + 2;
                    } else if (i - dp[i - 1] - 1 >= 0 && chs[i - dp[i - 1] - 1] == '(') {
                        dp[i] = dp[i - 1] + ((i - dp[i - 1] - 2 >= 0) ? dp[i - dp[i - 1] - 2] : 0) + 2;
                    }
                    max = Math.max(max, dp[i]);
                }
            }
            return max;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}