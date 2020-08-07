package medium.maximum_product_subarray;

import javax.sound.midi.Soundbank;
import java.util.Arrays;
//该解法尝试不使用官方答案，在内存足够的情况下 解法正确， 但该解法官方不予通过，提交结果是超出内存限制， 原因是我试图通过一个
// 二维数组去保存计算结果以只求时间复杂度较优， 但官方选择了一个丧心病狂的测试用例（估计是上万的数组），二维数组的空间复杂度是n平方，所以超出了限制
//这道题 能够学到一个很有用的思想：不需要用内存去保存所有的计算结果，只需要保存必要的已经计算的结果，并找到这些结果与后续需要进行的计算的依赖关系 然后进一步保存后续的计算结果且放弃当前的计算结果，这样内存就能释放掉，
// 这其实是一道典型动态规划问题， 很多人都使用了状态转移方程，但这不意味着要用表保存所有的计算结果，而应该保存必要的计算结果
// 选择了一个比较精简的解法，详见Solution2
public class Solution {
    public int maxProduct(int[] nums) {
        int[][] arr = new int[nums.length][nums.length];
        int[] resArr = new int[nums.length];
        arr[0][0] = nums[0];
        resArr[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            arr[0][i] = arr[0][i - 1] * nums[i];
            resArr[0] = Math.max(resArr[0], arr[0][i]);
        }
        for (int i = 1; i < nums.length; i++) {
            arr[i][i] = nums[i];
            resArr[i] = nums[i];
            int tmp = 0;
            boolean flag = false;
            for (int j = i + 1; j < nums.length; j++) {
                if (tmp <= i - 1 && arr[tmp][i - 1] == 0) {
                    do {
                        tmp++;
                        arr[i][j] = 1;
                        flag = true;
                        for (int k = i; k < nums.length; k++) {
                            arr[i][j] = arr[i][j] * nums[k];
                            resArr[i] = Math.max(resArr[i], arr[i][j]);
                        }
                        break;
                    } while (tmp <= i - 1 && arr[tmp][i - 1] == 0);
                }
                if (!flag && tmp <= i - 1) {
                    arr[i][j] = arr[tmp][j] / arr[tmp][i - 1];
                    resArr[i] = Math.max(resArr[i], arr[i][j]);
                }

            }
            tmp = 0;
            flag = false;

        }
        int max = resArr[0];
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, resArr[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().maxProduct(new int[]{-5,2,4,1,-2,2,-6,3,-1,-1}));
    }
}
