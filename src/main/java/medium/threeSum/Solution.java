package medium.threeSum;

import java.util.*;

/**
 * 注意，这个解法还不完全， 因为只考虑了组合的情况，还有排列没有考虑
 * 但这个解法已经做到了第一步：解耦对排序的高度依赖，虽然也进行了排序，但只用作 对 获取所有排列的辅助而已，也就是说 即使换一个函数，整个算法的核心结构也保持不变
 *  所谓组合，是指比如[1,2,3,4]，那么如果[4,1,2,3]也能满足方程，那么认为[1,2,3,4]和[4,1,2,3] 是不同的两个解。 那么为什么在leetcode的n数之和中没有考虑排列的问题呢？
 *  因为leetcode的n数之和，在函数表达式上，这n个数是“对称”的，它们可以互换位置而方程仍然成立。
 *  而对于我所追求的一般方程，参数之间不对称是很可能的，如 0=a^b+(c-2d)/f^1/2 ,此时对于同一个组合，把参数互换一下位置，等式通常就不成立了。
 *
 *  所以，如果要支持这种一般情况，需要在筛选出C(n-1)(N)的所有组合后， 对每个组合都进行“全排列”带入公式，算出最后一个参数，如果能从剩余元素中找到该值，则说明该解正确
 *
 *  由于结果集有所变化，每个结果集不能再要求从小到大排序了，只有每个位置的每个元素都相同，才能判断为相同。判断两个结果集是否是相同的逻辑应跟着变化，该细节不再赘述
 */
public class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            List<Integer> list;
            if (map.get(nums[i]) == null) {
                list = new ArrayList<>();
                list.add(i);
                map.put(nums[i], list);
            } else
                map.get(nums[i]).add(i);
        }
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list;
        TreeSet<List<Integer>> treeSet = new TreeSet<List<Integer>>((l1, l2) -> {
            if (l1.size() == l2.size()) {
                for (int i = 0; i < l1.size(); i++) {
                    if (l1.get(i) > l2.get(i)) {
                        return 1;
                    } else if (l1.get(i) < l2.get(i)) {
                        return -1;
                    }
                }
                return 0;
            } else
                return 1;
        });


        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 2; j
                    < nums.length; j++) {
                Integer p = getFinalParam(nums[j], nums[i], j, i, 0, map);
                if (p != null) {
                    list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(p);
                    list.add(nums[j]);
                    treeSet.add(list);
                    while (j < nums.length - 1 && nums[j] == nums[j + 1] && p == nums[j])
                        j++;
                    while (i < nums.length - 3 && nums[i] == nums[i + 1] && p == nums[i])
                        i++;
                }
            }
        }
        for (List<Integer> integers : treeSet) {
            res.add(integers);
        }
        return res;
    }

    private Integer getFinalParam(int second, int first, int secondIndex, int firstIndex, int constant, Map<Integer, List<Integer>> map) {
        Integer p = constant - second - first;
        if (map.get(p) == null) {
            return null;
        }
        List<Integer> list = map.get(p);
/*如果参数个数较多（如n数之和，这个n较大时，如1000等） 可以开启以下注释
        if (list.get(0) < firstIndex) {
            if (list.get(list.size() - 1) > firstIndex) {
                if (list.get(list.size() - 1) < secondIndex) {
                    return p;
                }
            }
        } else if (list.get(list.size() - 1) > secondIndex) {
            if (list.get(0) < secondIndex) {
                if (list.get(0) > firstIndex) {
                    return p;
                }
            }
        } else if (list.get(0) > secondIndex) {
            return null;
        } else if (list.get(list.size() - 1) < firstIndex) {
            return null;
        }*/
        for (Integer e : list) {
            if (e > firstIndex && e < secondIndex) {
                return p;
            }
        }
        return null;
    }


    public static void main(String[] args) {
        System.out.println(new Solution().threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
    }
}
