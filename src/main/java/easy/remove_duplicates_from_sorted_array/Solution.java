package easy.remove_duplicates_from_sorted_array;

public class Solution {
    public int removeDuplicates(int[] nums) {
        int newLength=nums.length;
        int curIndex=1;
        for (int i = 0; i <nums.length-1 ;i++) {
            if(nums[i]!=nums[i+1]){
                nums[curIndex]=nums[i+1];
                curIndex++;
            }

        }
        return curIndex;
    }
}
