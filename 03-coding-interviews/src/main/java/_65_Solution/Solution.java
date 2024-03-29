package _65_Solution;

/**
 * @author YeYaqiao
 * 剑指 Offer 65. 不用加减乘除做加法
 */
public class Solution {
    //异或^ 都为1则为1，否则为0
    //与&   相同为0，不同为1
    public int add(int a, int b) {
        while (a != 0) {
            //计算进位的值
            int temp = (a & b) << 1;
            //计算当前位的值
            b = b ^ a;
            //获取进位值，若不为0，则继续计算
            a = temp;
        }
        return b;
    }
}
