package medium.top_k_frequent_elements;

import java.util.*;

public class Solution {
    public int[] topKFrequent(int[] nums, int k) {
        if (nums.length==0)
            return null;
        if (nums.length==1)
            return nums;
        Map<Integer,Integer>map=new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(map.get(nums[i])==null){
                map.put(nums[i],1);
            }else {
                map.put(nums[i],map.get(nums[i])+1);
            }
        }
        /*
        这是最大堆， 如果使用最大堆，那么队列需要添加全部元素，不是最优的，最优的思路是反过来使用最小堆，当堆元素超过k个，就删除最小的，这样全部遍历完后，剩下的就是k个最大的
        PriorityQueue<Map.Entry<Integer,Integer>>queue= new PriorityQueue<>((e1, e2) -> e2.getValue() - e1.getValue());
        for (Map.Entry<Integer,Integer> e:map.entrySet()) {
            queue.add(e);
        }*/
        PriorityQueue<Map.Entry<Integer,Integer>>queue= new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getValue));
        for (Map.Entry<Integer,Integer> e:map.entrySet()) {
            queue.add(e);
            if(queue.size()>k)
                queue.poll();
        }

        int[] arr=new int[k];
        /* 最大堆则顺序赋值
        for (int i = 0; i < k; i++) {
            arr[i]=queue.poll().getKey();
        }*/
        //如果使用最小堆，那么出队后是从小到大排列，因此应反转，从尾部向前赋值
        for (int i = k-1; i >=0; i--) {
            arr[i]=queue.poll().getKey();
        }
        return arr;
    }
}
