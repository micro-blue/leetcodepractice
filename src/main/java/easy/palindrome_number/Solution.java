package easy.palindrome_number;

public class Solution {
    public boolean isPalindrome(int x) {
        char[] chars=Integer.toString(x).toCharArray();
        int lastIdx=chars.length-1;
        for (int i = 0; i <chars.length/2 ; i++) {
            if(chars[i]!=chars[lastIdx-i])
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        new Solution().isPalindrome(121);
    }
}
