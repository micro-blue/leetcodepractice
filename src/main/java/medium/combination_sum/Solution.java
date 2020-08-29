package medium.combination_sum;

import java.util.*;
//击败用户百分五！！！
public class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result=new ArrayList<>();
        int tempTarget;
        for(int i=0;i<candidates.length;i++){
            tempTarget=target-candidates[i];
            List<List<Integer>> tempResult=new ArrayList<>();
            if (tempTarget>0){
                tempResult=combinationSum(candidates,tempTarget); //递归嵌套，7找5,5找3，3找1. 1 发现是-1 则不继续找
                if(tempResult!=null&&tempResult.size()>0){
                    List<Integer>tempInnerList;
                    for (int j = 0; j < tempResult.size(); j++) {
                        tempInnerList=tempResult.get(j);
                        tempInnerList.add(candidates[i]);
                        result.add(tempInnerList);
                    }
                }
            }else if(tempTarget==0){
                List<Integer>tempInnerList=new ArrayList<>();
                tempInnerList.add(candidates[i]);
                result.add(tempInnerList);
            }
        }
        List<List<Integer>> newResult=new ArrayList<>();
        //下面这段逻辑主要是去重复，如[2,2,3]和和[3,2,2]是重复的，在更优的题解中，不会有以下逻辑，因为可以在前面的递归逻辑中去重，主要思路是维护一个已遍历过的头指针，在这个指针之前的元素都不考虑，这样就去重了，详见Solution2
        if(result!=null&&result.size()>1){
            for (int i = 0; i < result.size(); i++) {
                Collections.sort(result.get(i));
            }
            TreeSet<List<Integer>>treeSet=new TreeSet<List<Integer>>((l1,l2)->{
                if(l1.size()>l2.size())
                    return -1;
                else if(l1.size()==l2.size()){
                    for (int i = 0; i < l1.size(); i++) {
                        if(l1.get(i)>l2.get(i))
                            return -1;
                        else if(l1.get(i)<l2.get(i))
                            return 1;
                    }
                    return 0;
                }else
                    return 1;
            });
            for (int i = 0; i < result.size(); i++) {
                treeSet.add(result.get(i));
            }
            newResult=new ArrayList<>();
            for (List<Integer> e:treeSet) {
                newResult.add(e);
            }
        }
        return newResult.size()>0?newResult:result;//这个可以再优化，不需要两个result

    }

    public static void main(String[] args) {
        int a[]={2,3,6,7};
        System.out.println(new Solution().combinationSum(a,7));
    }
}
