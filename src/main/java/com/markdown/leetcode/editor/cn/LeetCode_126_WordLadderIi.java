//给定两个单词（beginWord 和 endWord）和一个字典 wordList，找出所有从 beginWord 到 endWord 的最短转换序列。转换需遵循如下规则： 
//
// 
// 每次转换只能改变一个字母。 
// 转换过程中的中间单词必须是字典中的单词。 
// 
//
// 说明: 
//
// 
// 如果不存在这样的转换序列，返回一个空列表。 
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
//输出:
//[
//  ["hit","hot","dot","dog","cog"],
//  ["hit","hot","lot","log","cog"]
//]
// 
//
// 示例 2: 
//
// 输入:
//beginWord = "hit"
//endWord = "cog"
//wordList = ["hot","dot","dog","lot","log"]
//
//输出: []
//
//解释: endWord "cog" 不在字典中，所以不存在符合要求的转换序列。 
// Related Topics 广度优先搜索 数组 字符串 回溯算法

package com.markdown.leetcode.editor.cn;

import java.util.*;

public class LeetCode_126_WordLadderIi {
    public static void main(String[] args) {
        Solution solution = new LeetCode_126_WordLadderIi().new Solution();
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

            Set<String> wordSet = new HashSet<>(wordList);
            if (!wordSet.contains(endWord)) return Collections.emptyList();
            List<List<String>> res = new ArrayList<>();

            // 双向 BFS 计算最短路径的长度
            Map<String, Set<String>> nextMap = new HashMap<>();
            int shortestDistance = bfs(beginWord, endWord, wordSet, nextMap);
            System.out.println(nextMap);
            // DFS 搜索所有最短路径
            List<String> solution = new ArrayList<>();
            solution.add(beginWord);
            dfs(1, shortestDistance, beginWord, endWord, solution, nextMap, res);
            return res;
        }

        private void dfs(int level, int shortestDistance, String beginWord, String endWord, List<String> solution,
                         Map<String, Set<String>> nextMap, List<List<String>> res) {

            if (level >= shortestDistance) {
                if (beginWord.equals(endWord)) {
                    res.add(new ArrayList<>(solution));
                }
                return;
            }
            if (nextMap.containsKey(beginWord)) {
                for (String next : nextMap.get(beginWord)) {
                    solution.add(next);
                    dfs(level + 1, shortestDistance, next, endWord, solution, nextMap, res);
                    solution.remove(solution.size() - 1);
                }
            }
        }

        private int bfs(String beginWord, String endWord, Set<String> dict, Map<String, Set<String>> nextMap) {
            Set<String> beginSet = new HashSet<>();
            beginSet.add(beginWord);
            Set<String> endSet = new HashSet<>();
            endSet.add(endWord);

            int shortestDistance = 1;
            boolean found = false;
            boolean order = true;
            while (!beginSet.isEmpty() && !endSet.isEmpty()) {
                if (beginSet.size() > endSet.size()) {
                    Set<String> set = endSet;
                    endSet = beginSet;
                    beginSet = set;
                    order = !order;
                }
                dict.removeAll(beginSet);
                Set<String> temp = new HashSet<>();
                for (String curWord : beginSet) {
                    char[] chs = curWord.toCharArray();
                    for (int i = 0; i < chs.length; i++) {
                        char old = chs[i];
                        for (char c = 'a'; c < 'z'; c++) {
                            chs[i] = c;
                            String newWord = new String(chs);
                            if (endSet.contains(newWord)) {
                                found = true;
                            }
                            if (dict.contains(newWord)) {
                                temp.add(newWord);
                                String key = order ? curWord : newWord;
                                String value = order ? newWord : curWord;
                                if (!nextMap.containsKey(key)) {
                                    nextMap.put(key, new HashSet<>());
                                }
                                nextMap.get(key).add(value);
                            }
                        }
                        chs[i] = old;
                    }
                }
                shortestDistance++;
                if (found) {
                    break;
                }
                beginSet = temp;
            }
            return found ? shortestDistance : 0;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}