package easy.climbing_stairs;

public class Solution {
    public int climbStairs(int n) {
        if(n==1)
            return 1;
        if(n==2)
            return 2;
        int fist=1;
        int second=2;
        for (int i = 2; i < n; i++) {
            int temp=second;
            second=fist+second;
            fist=temp;
        }

        return second;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().climbStairs(4));

    }
}
