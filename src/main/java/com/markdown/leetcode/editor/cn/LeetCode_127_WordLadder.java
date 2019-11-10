//给定两个单词（beginWord 和 endWord）和一个字典，找到从 beginWord 到 endWord 的最短转换序列的长度。转换需遵循如下规则： 
//
// 
// 每次转换只能改变一个字母。 
// 转换过程中的中间单词必须是字典中的单词。 
// 
//
// 说明: 
//
// 
// 如果不存在这样的转换序列，返回 0。 
// 所有单词具有相同的长度。 
// 所有单词只由小写字母组成。 
// 字典中不存在重复的单词。 
// 你可以假设 beginWord 和 endWord 是非空的，且二者不相同。 
// 
//
// 示例 1: 
//
// 输入:
//beginWord = "hit",
//endWord = "cog",
//wordList = ["hot","dot","dog","lot","log","cog"]
//
//输出: 5
//
//解释: 一个最短转换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog",
//     返回它的长度 5。
// 
//
// 示例 2: 
//
// 输入:
//beginWord = "hit"
//endWord = "cog"
//wordList = ["hot","dot","dog","lot","log"]
//
//输出: 0
//
//解释: endWord "cog" 不在字典中，所以无法进行转换。 
// Related Topics 广度优先搜索

package com.markdown.leetcode.editor.cn;

import java.util.*;

public class LeetCode_127_WordLadder {
    public static void main(String[] args) {
        Solution solution = new LeetCode_127_WordLadder().new Solution();
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution3 {
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {
            if (!wordList.contains(endWord)) return 0;
            if (beginWord.equals(endWord)) return 2;

            // 可能遇见的节点集
            Set<String> meets = new HashSet<>(wordList); // O(n)

            Set<String> beginSet = new HashSet<>(Collections.singleton(beginWord));
            Set<String> endSet = new HashSet<>(Collections.singleton(endWord));

            return this._search(1, beginSet, endSet, meets);
        }

        private int _search(int level, Set<String> beginSet, Set<String> endSet, Set<String> meets) {
            // terminator
            if (beginSet.size() == 0 || endSet.size() == 0) return 0;

            // process
            meets.removeAll(beginSet);
            level++;
            Set<String> nextLevelSet = new HashSet<>();
            // iter every begin word
            for (String beginWord : beginSet) {
                char[] chars = beginWord.toCharArray();
                // iter for every char
                for (int i = 0; i < chars.length; i++) {
                    char temp = chars[i];
                    // replace every letter
                    for (char ch = 'a'; ch < 'z'; ch++) {
                        chars[i] = ch;
                        String newWord = String.valueOf(chars);
                        if (!meets.contains(newWord)) continue;
                        if (endSet.contains(newWord)) return level;
                        nextLevelSet.add(newWord);
                    }
                    // reverse
                    chars[i] = temp;
                }
            }

            // drill down
            // always from less to more
            if (nextLevelSet.size() <= endSet.size()) {
                beginSet = nextLevelSet;
            } else {
                beginSet = endSet;
                endSet = nextLevelSet;
            }

            return this._search(level, beginSet, endSet, meets);
        }
    }

    class Solution {
        public int ladderLength(String beginWord, String endWord, List<String> wordList) {

            Set<String> wordSet = new HashSet<>(wordList);
            if (!wordSet.contains(endWord)) return 0;
            if (beginWord.equals(endWord)) return 2;

            // 双端广度优先搜索
            Set<String> beginSet = new HashSet<>();
            Set<String> endSet = new HashSet<>();
            beginSet.add(beginWord);
            endSet.add(endWord);

            Set<String> visited = new HashSet<>();
            int res = 1;

            while (!beginSet.isEmpty() && !endSet.isEmpty()) {
                // 每次都遍历数量少的 Set
                if (beginSet.size() > endSet.size()) {
                    Set<String> set = beginSet;
                    beginSet = endSet;
                    endSet = set;
                }
                //
                wordSet.removeAll(beginSet);
                Set<String> temp = new HashSet<>();
                for (String str : beginSet) {
                    visited.add(str);
                    char[] chars = str.toCharArray();
                    // 逐个替换字符
                    for (int i = 0; i < chars.length; i++) {
                        // 保留原字符
                        char old = chars[i];
                        for (char c = 'a'; c < 'z'; c++) {
                            chars[i] = c;
                            String newWord = String.valueOf(chars);
                            // 找到了
                            if (endSet.contains(newWord)) {
                                return res + 1;
                            }
                            //
                            if (!visited.contains(newWord) && wordSet.contains(newWord)) {
                                visited.add(newWord);
                                temp.add(newWord);
                            }
                        }
                        // 还原
                        chars[i] = old;
                    }
                }
                beginSet = temp;
                res++;
            }

            return 0;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}