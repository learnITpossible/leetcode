//给出一个区间的集合，请合并所有重叠的区间。 
//
// 示例 1: 
//
// 输入: [[1,3],[2,6],[8,10],[15,18]]
//输出: [[1,6],[8,10],[15,18]]
//解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
// 
//
// 示例 2: 
//
// 输入: [[1,4],[4,5]]
//输出: [[1,5]]
//解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。 
// Related Topics 排序 数组

package com.markdown.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LeetCode_56_MergeIntervals {
    public static void main(String[] args) {
        Solution solution = new LeetCode_56_MergeIntervals().new Solution();
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int[][] merge(int[][] intervals) {

            if (intervals == null || intervals.length == 0) {
                return intervals;
            }
            List<int[]> res = new ArrayList<>();
            Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

            int i = 0;
            while (i < intervals.length) {
                int left = intervals[i][0];
                int right = intervals[i][1];
                while (i < intervals.length - 1 && right >= intervals[i + 1][0]) {
                    right = Math.max(right, intervals[i + 1][1]);
                    i++;
                }
                res.add(new int[] {left, right});
                i++;
            }
            return res.toArray(new int[0][]);
        }
    }
    class Solution1 {
        public int[][] merge(int[][] intervals) {

            if (intervals == null || intervals.length == 0) {
                return intervals;
            }
            List<int[]> res = new ArrayList<>();
            Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));

            for (int i = 0; i < intervals.length; i++) {
                int left = intervals[i][0];
                int right = intervals[i][1];
                for (int j = i + 1; j < intervals.length; j++) {
                    if (right >= intervals[j][0]) {
                        // 前面区间的右区间包含了该区间的左区间
                        right = Math.max(right, intervals[j][1]);
                        i++;
                    } else {
                        break;
                    }
                }
                res.add(new int[] {left, right});
            }
            return res.toArray(new int[0][]);
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}