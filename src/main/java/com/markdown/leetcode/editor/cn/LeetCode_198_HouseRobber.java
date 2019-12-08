//你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。 
//
// 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。 
//
// 示例 1: 
//
// 输入: [1,2,3,1]
//输出: 4
//解释: 偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
//     偷窃到的最高金额 = 1 + 3 = 4 。 
//
// 示例 2: 
//
// 输入: [2,7,9,3,1]
//输出: 12
//解释: 偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
//     偷窃到的最高金额 = 2 + 9 + 1 = 12 。
// 
// Related Topics 动态规划

package com.markdown.leetcode.editor.cn;

public class LeetCode_198_HouseRobber {
    public static void main(String[] args) {
        Solution solution = new LeetCode_198_HouseRobber().new Solution();
    }


    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int rob(int[] nums) {

            if (nums == null || nums.length == 0) return 0;
            // 分解子问题
            // 第 i 个房子能偷到的最大金额
            //  1、偷这个房子，则不能偷第 i-1 个房子，最大金额为：第 i-2 个房子的最大金额 + 当前房子的金额
            //  2、不偷这个房子，则必偷第 i-1 个房子（因为都是正数），最大金额为：第 i-1 个房子的最大金额（包含了偷和不偷的情况）
            // 定义状态数组
            // dp[i] 代表第 i 个房子能偷到的最大金额
            // DP方程
            // dp(i) = max(dp(i - 2) + nums[i], dp(i - 1))
            int[] dp = new int[nums.length];
            dp[0] = nums[0];
            dp[1] = Math.max(nums[0], nums[1]);
            for (int i = 2; i < nums.length; i++) {
                dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
            }
            return dp[nums.length - 1];
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}