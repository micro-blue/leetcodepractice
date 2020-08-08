package dp.easy.fib;

/**
 * 1.不使用简单的递归 ，因为递归会造成大量重复的计算，应使用dp思想
 * 2. 避免使用数组保存所有的计算结果，因为 根据递推关系，新的值仅仅依赖前两个值，所以仅需要两个指针
 */
public class Solution {
    public int fib(int N) {
        if(N==0)
            return 0;
        if(N==1)
            return 1;
        int temp;
        int first=0;
        int second=1;
        for(int i=2;i<N+1;i++){
            temp=second;
            second=first+second;
            first=temp;
        }
        return second;


    }

    public static void main(String[] args) {
        System.out.println(new Solution().fib(48));
    }
}
