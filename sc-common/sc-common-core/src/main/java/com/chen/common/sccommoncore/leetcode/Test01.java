package com.chen.common.sccommoncore.leetcode;

/**
 * @program: scdemo
 * @description: 目标和
 * 给你一个整数数组 nums 和一个整数 target 。
 * 向数组中的每个整数前添加'+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
 * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
 * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
 * @author: chenxiaoming
 * @create: 2021-06-07 11:10:18
 */
public class Test01 {
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] nums = {1, 1, 1, 1, 1};
        int target = 3;
        System.out.println("目标和" + solution.findTargetSumWays(nums, target));
    }
}

class Solution {
    int count = 0;

    public int findTargetSumWays(int[] nums, int target) {
        backtrack(nums, target, 0, 0);
        return count;
    }

    public void backtrack(int[] nums, int target, int index, int sum) {
        System.out.println("index->" + index + "    sum->" + sum+ "    target->" + target);
        if (index == nums.length) {
            System.out.println("-----index->" + index + "    sum->" + sum+ "    target->" + target);
            if (sum == target) {
                count++;
            }
        } else {
            backtrack(nums, target, index + 1, sum + nums[index]);
            backtrack(nums, target, index + 1, sum - nums[index]);
        }
    }
}