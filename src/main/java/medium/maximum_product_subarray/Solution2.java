package medium.maximum_product_subarray;

/**
 * 解题思路
 * 这题是求数组中子区间的最大乘积，对于乘法，我们需要注意，负数乘以负数，会变成正数，所以解这题的时候我们需要维护两个变量，当前的最大值，以及最小值，最小值可能为负数，但没准下一步乘以一个负数，当前的最大值就变成最小值，而最小值则变成最大值了。
 *
 * 我们的动态方程可能这样：
 *
 * maxDP[i + 1] = max(maxDP[i] * A[i + 1], A[i + 1],minDP[i] * A[i + 1])
 * minDP[i + 1] = min(minDP[i] * A[i + 1], A[i + 1],maxDP[i] * A[i + 1])
 * dp[i + 1] = max(dp[i], maxDP[i + 1])
 *
 * 这里，我们还需要注意元素为0的情况，如果A[i]为0，那么maxDP和minDP都为0，
 * 我们需要从A[i + 1]重新开始。
 *
 * 作者：spicy_onion
 * 链接：https://leetcode-cn.com/problems/maximum-product-subarray/solution/dpfang-fa-xiang-jie-by-yang-cong-12/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class Solution2 {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        if(n == 0){
            return 0;
        } else if(n == 1) {
            return nums[0];
        }
        int p = nums[0];
        int maxP = nums[0];
        int minP = nums[0];
        for(int i = 1; i < n; i++) {
            int t = maxP;
            maxP = Math.max(Math.max(maxP * nums[i], nums[i]), minP *nums[i]);
            minP = Math.min(Math.min(t * nums[i], nums[i]), minP * nums[i]);
            p =Math.max(maxP, p);
        }
        return p;
    }
}
