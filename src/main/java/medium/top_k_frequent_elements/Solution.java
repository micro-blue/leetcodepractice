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
        PriorityQueue<Map.Entry<Integer,Integer>>queue= new PriorityQueue<>((e1, e2) -> e2.getValue() - e1.getValue());
        for (Map.Entry<Integer,Integer> e:map.entrySet()) {
            queue.add(e);
        };
        int[] arr=new int[k];
        for (int i = 0; i < k; i++) {
            arr[i]=queue.poll().getKey();
        }
        return arr;
    }
}
