//一条包含字母 A-Z 的消息通过以下方式进行了编码： 
//
// 'A' -> 1
//'B' -> 2
//...
//'Z' -> 26
// 
//
// 给定一个只包含数字的非空字符串，请计算解码方法的总数。 
//
// 示例 1: 
//
// 输入: "12"
//输出: 2
//解释: 它可以解码为 "AB"（1 2）或者 "L"（12）。
// 
//
// 示例 2: 
//
// 输入: "226"
//输出: 3
//解释: 它可以解码为 "BZ" (2 26), "VF" (22 6), 或者 "BBF" (2 2 6) 。
// 
// Related Topics 字符串 动态规划

package com.markdown.leetcode.editor.cn;

public class LeetCode_91_DecodeWays {
    public static void main(String[] args) {
        Solution solution = new LeetCode_91_DecodeWays().new Solution();
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int numDecodings(String s) {
            if (s == null || s.length() == 0) return 0;

            // dp[i] 代表 i 处能进行解码的个数
            int len = s.length();
            int[] dp = new int[len + 1];
            dp[len] = 1;
            dp[len - 1] = s.charAt(len - 1) == '0' ? 0 : 1;
            for (int i = len - 2; i >= 0; i--) {
                if (s.charAt(i) == '0') {
                    dp[i] = 0;
                } else {
                    if ((s.charAt(i) - '0') * 10 + (s.charAt(i + 1) - '0') <= 26) {
                        dp[i] = dp[i + 1] + dp[i + 2];
                    } else {
                        dp[i] = dp[i + 1];
                    }
                }
            }
            return dp[0];
        }
    }

    class Solution1 {
        public int numDecodings(String s) {

            if (s == null || s.length() == 0) return 0;

            return recursion(0, s);
        }

        private int recursion(int level, String s) {

            if (level == s.length()) {
                return 1;
            }

            char ch = s.charAt(level);
            if (ch == '0') {
                return 0;
            }
            int cnt1 = recursion(level + 1, s);
            int cnt2 = 0;
            if (level < s.length() - 1) {
                if ((ch - '0') * 10 + (s.charAt(level + 1) - '0') <= 26) {
                    cnt2 = recursion(level + 2, s);
                }
            }
            return cnt1 + cnt2;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}