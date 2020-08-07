package medium.permutation_sequence;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Solution {
    /**
     * 总体思路：因为所有的排列按照顺序排序，其结构是有固定规律的： 如n个元素进行n!的排列， 那么每一个元素作为开头的排列都有(n-1)!个， 而它后续的n-1个元素的排列中，每第一个元素又有(n-2)!种排列，以此类推
     * 如 输入 n=3,k=3, 那么在所有3!=6中排列中， 1开头的有2!个， 2开头的也有2!个，3开头的也有2!个， 并且其排序是112233.那么显然，想求第k=3个排列，那么其第一个元素必然落在‘22’这个区间
     * 即第k=3个排列的第一个元素是2，以此类推，再求出其后续的所有“第一个元素”即可求出这个排列
     */
    public String getPermutation(int n, int k) {
        List<Integer> list=new ArrayList<>();
        //初始化链表，保存所有“剩余待选择”的元素，注意这里已经对元素做了一次“排序”，下标越小的元素“值”越小，利用了ArrayList的下标，如下标为4 就代表是整个链表中第4+1小的元素
        for (int i = 1; i < n+1; i++)
            list.add(i);
        int[] arr=new int[n];
        arr[0]=1;
        //保存 n!的值，注意这里arr[0]=1 相当于0!=1,这本身也是数学上的规定
        // 如果需要优化空间复杂度，那么这里可以不用该数组，在后续循环中每次循环计算一次即可。
        for (int i = 1; i < n; i++)
            arr[i]=i*arr[i-1];
        int dynamic=n;int remain,tmpHead;
        StringBuilder sb=new StringBuilder();
        for (int i = 0; i < n; i++) {
            //计算商和余数，以此求出所在“区间”
            int index=k/arr[dynamic-1];
            remain=k%arr[dynamic-1];
            //余数大于0 表示 在第index+1 个区间,余数如果刚好是0，说明是某区间中最后一个元素，但仍在第index个区间
            tmpHead=remain>0?list.get(index):list.get(index-1);//获得当前第一个元素
            sb.append(tmpHead);
            //从list中删掉已经确定的元素，从而维护剩余元素的顺序关系
            list.remove((Integer) tmpHead);
            //计算下次循环的k值，如果余数是0，说明k应等于下次排列中的最后一个，即第arr[dynamic-1]个排列
            k=remain==0?arr[dynamic-1]:remain;
            dynamic--;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Solution().getPermutation(3,3));
    }
}
