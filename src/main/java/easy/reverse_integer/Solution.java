package easy.reverse_integer;

public class Solution {

    public int reverse(int x) {
        char[] chars=Integer.toString(x).toCharArray();
        int firstIdx=0;
        if(chars[0]=='-')
            firstIdx=1;
        char tmp;
        int lastIdx=chars.length-1;
        int i = firstIdx;
        do {
            tmp=chars[i];
            chars[i]=chars[lastIdx-i+firstIdx];
            chars[lastIdx-i+firstIdx]=tmp;
            i++;
        }while ( i < lastIdx/2+1);
        String s=new String(chars);
        long l= Long.parseLong(s);
        if(l>Integer.MAX_VALUE||l<Integer.MIN_VALUE)
            return 0;

        return (int) l;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().reverse(-901000));
    }
}
