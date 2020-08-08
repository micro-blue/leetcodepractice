package easy.sort.MergeSort;

/**
 * 归并排序的思想启发：
 * 基本步骤
 * 1.、分解，大问题拆成小问题，由于拆分问题并不是实际的解决问题，所以分解阶段一般不会成为整个处理过程的瓶颈
 * 2、 治理，治理就是处理实际的业务需求，是这个归并程序的核心（payload），比如这里就是排序的需求，整个处理过程的优劣基本完全取决于治理阶段的设计方案。 **同时我需要着重强调：此时被拆分的若干的小问题之间很可能是可以进行并行处理的，也就是并行计算是可能在归并思想中进行应用的**
 * 3、 合并，合并与分解对应，而且往往都不会成为整个操作的瓶颈，**真正的瓶颈应该在治理部分被优化，合并往往是基于已经被处理（治理）过的数据**，故如果治理阶段处理的好，那么合并时就相当于站在巨人肩膀上，因此合并的开销一般都小于治理的成本
 *
 * > 类似的思想可以类比到我之前提到的两个有序树结构相加， 如果已有两个有序树结构，那么将这两个树合并为一个树的时间开销将低于  往其中一棵树中，一个一个地，分散地
 *  添加另一棵树中的节点，因为后者在添加节点时必须从 根节点开始往下遍历，显然会低效一些，
 * 而两棵树相加则互相利用对方已经构建好的依赖关系，可以基于现有父节点直接往下遍历而不用总是从根节点开始
 */
public class MergeSortByArray {
    public static int[] sort(int[] a,int low,int high){
        int mid = (low+high)/2;
        if(low<high){
            sort(a,low,mid);
            sort(a,mid+1,high);
            //左右归并
            merge(a,low,mid,high);
        }
        return a;
    }

    public static void merge(int[] a, int low, int mid, int high) {
        int[] temp = new int[high-low+1];
        int i= low;
        int j = mid+1;
        int k=0;
        // 把较小的数先移到新数组中
        while(i<=mid && j<=high){
            if(a[i]<a[j]){
                temp[k++] = a[i++];
            }else{
                temp[k++] = a[j++];
            }
        }
        // 把左边剩余的数移入数组
        while(i<=mid){
            temp[k++] = a[i++];
        }
        // 把右边边剩余的数移入数组
        while(j<=high){
            temp[k++] = a[j++];
        }
        // 把新数组中的数覆盖nums数组
        for(int x=0;x<temp.length;x++){
            a[x+low] = temp[x];
        }
    }
}
