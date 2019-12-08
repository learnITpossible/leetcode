//给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。 
//
// 示例 1: 
//
// 
//输入: "aba"
//输出: True
// 
//
// 示例 2: 
//
// 
//输入: "abca"
//输出: True
//解释: 你可以删除c字符。
// 
//
// 注意: 
//
// 
// 字符串只包含从 a-z 的小写字母。字符串的最大长度是50000。 
// 
// Related Topics 字符串

package com.markdown.leetcode.editor.cn;

public class LeetCode_680_ValidPalindromeIi {
    public static void main(String[] args) {
        Solution solution = new LeetCode_680_ValidPalindromeIi().new Solution();
    }


    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        public boolean validPalindrome(String s) {

            int i = 0;
            int j = s.length() - 1;
            while (i < j && s.charAt(i) == s.charAt(j)) {
                i++;
                j--;
            }
            if (isValid(s, i, j - 1)) return true;
            if (isValid(s, i + 1, j)) return true;
            return false;
        }
        private boolean isValid(String s, int begin, int end) {
            while (begin < end) {
                if (s.charAt(begin) != s.charAt(end)) {
                    return false;
                }
                begin++;
                end--;
            }
            return true;
        }
    }
    class Solution1 {
        public boolean validPalindrome(String s) {

            int count = 2;
            int begin = 0, end = 0;
            for (int i = 0, j = s.length() - 1; i < j; ) {
                if (s.charAt(i) == s.charAt(j)) {
                    i++;
                    j--;
                } else {
                    if (count == 2) {
                        begin = i;
                        end = j;
                        i++;
                        count--;
                    } else if (count == 1) {
                        i = begin;
                        j = end;
                        j--;
                        count--;
                    } else {
                        return false;
                    }
                }
            }
            return true;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}