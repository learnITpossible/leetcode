//给你两个数组，arr1 和 arr2， 
//
// 
// arr2 中的元素各不相同 
// arr2 中的每个元素都出现在 arr1 中 
// 
//
// 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末尾。 
//
// 
//
// 示例： 
//
// 输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
//输出：[2,2,2,1,4,3,3,9,6,7,19]
// 
//
// 
//
// 提示： 
//
// 
// arr1.length, arr2.length <= 1000 
// 0 <= arr1[i], arr2[i] <= 1000 
// arr2 中的元素 arr2[i] 各不相同 
// arr2 中的每个元素 arr2[i] 都出现在 arr1 中 
// 
// Related Topics 排序 数组

package com.markdown.leetcode.editor.cn;

import java.util.Arrays;

public class LeetCode_1122_RelativeSortArray {
    public static void main(String[] args) {
        Solution solution = new LeetCode_1122_RelativeSortArray().new Solution();
    }


    //leetcode submit region begin(Prohibit modification and deletion)

    class Solution {
        public int[] relativeSortArray(int[] arr1, int[] arr2) {
            // 计数排序
            int[] counterArr = new int[1001];
            for (int num : arr1) {
                counterArr[num] += 1;
            }
            int[] res = new int[arr1.length];
            int i = 0;
            for (int num : arr2) {
                while (counterArr[num] > 0) {
                    res[i++] = num;
                    counterArr[num] -= 1;
                }
            }
            for (int j = 0; j < counterArr.length; j++) {
                while (counterArr[j] > 0) {
                    res[i++] = j;
                    counterArr[j] -= 1;
                }
            }
            return res;
        }
    }

    class Solution1 {
        public int[] relativeSortArray(int[] arr1, int[] arr2) {

            int counter = 0;
            for (int num : arr2) {
                for (int i = counter; i < arr1.length; i++) {
                    if (arr1[i] == num) {
                        int temp = arr1[i];
                        arr1[i] = arr1[counter];
                        arr1[counter] = temp;
                        counter++;
                    }
                }
            }
            if (counter < arr1.length) {
                Arrays.sort(arr1, counter, arr1.length);
            }
            return arr1;
        }
    }
    //leetcode submit region end(Prohibit modification and deletion)

}