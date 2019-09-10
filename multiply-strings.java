// 656. Multiply Strings
// 中文English
// Given two non-negative integers num1 and num2 represented as strings, return the product of num1 and num2
//
// Example
// Example1
//
// Input:
// "123"
// "45"
// Output:
// "5535"
// Explanation:
// 123 x 45 = 5535
// Example2
//
// Input:
// "0"
// "0"
// Output:
// "0"
// Notice
// The length of both num1 and num2 is < 110.
// Both num1 and num2 contains only digits 0-9.
// Both num1 and num2 does not contain any leading zero.
// You must not use any built-in BigInteger library or convert the inputs to integer directly.


public class Solution {
    /**
     * @param num1 a non-negative integers
     * @param num2 a non-negative integers
     * @return return product of num1 and num2
     */
    public String multiply(String num1, String num2) {
        // Write your code here
        // num1 and num2 are non-negative => would be 0
        if (num1.equals("0") || num2.equals("0")) {
            return "0";
        }
        int len1 = num1.length();
        int len2 = num2.length();
        // 下面是我自己尝试的以乘法过程为基础的解法， 可以通过， 但是比较答案来说更为繁琐， 完全仿照乘法步骤
        // if (len1 < len2) {
        //     // 拿更短的每一位去乘以更长的数
        //     return multiplication(num2, num1);
        // } else {
        //     return multiplication(num1, num2);
        // }

        // method 2: 根据NC答案做的
        // reference: https://zhengyang2015.gitbooks.io/lintcode/multiply_strings_43_leetcode.html
        int n = len1 + len2;
        int[] array = new int[n];
        for (int i = len1 - 1; i >= 0; i--) {
            int sum = 0;
            int carry = 0;
            int j = len2 - 1;
            for (; j >= 0; j--) {
                sum = (num1.charAt(i) - '0') * (num2.charAt(j) - '0') + carry + array[i + j + 1];
                carry = sum / 10;
                array[i + j + 1] = sum % 10;
            }
            array[i + j + 1] = carry;
        }
        // 去掉前面的 leading zero
        int index = 0;
        while (index < n && array[index] == 0) {
            index++;
        }
        String result = "";
        for (int i = index; i < n; i++) {
            result += array[i];
        }
        return result;


        // method 3: NC anther solution, 把 method 2 倒过来处理， reference 是把给的 string num 倒过来， NC solution 按照正常的从后面往前面相乘， 但是按照从前往后的顺序放入数组， 再把最后的结果从数组中倒过来拿到
        // refernce: https://medium.com/@xingren14/656-big-integer-multiplication-9e020e31c020
        // int n = len1 + len2 + 1;
        // int[] array = new int[n];
        // for (int i = 0; i < len1; i++) {
        //     for (int j = 0; j < len2; j++) {
        //         // 注意这里是 +=， 因为之前可能在同个位置算过了另一组乘法
        //         array[i + j] += (num1.charAt(len1 - 1 - i) - '0') * (num2.charAt(len2 - 1 - j) - '0');
        //     }
        // }
        // // 进位: 从前往后进位
        // for (int i = 0; i < n - 1; i++) {
        //     // 注意顺序， 不能先更改现在位置的值
        //     array[i + 1] += array[i] / 10;
        //     array[i] = array[i] % 10;
        // }
        // int index = n - 1;
        // // 去掉前面的 0 （实际上在数组后几个数）
        // while (index >= 0 && array[index] == 0) {
        //     index--;
        // }
        // String result = "";
        // // 从后往前加入
        // for (int i = index; i >= 0; i--) {
        //     result += array[i];
        // }
        // return result;
    }
    // method 1
    private String multiplication(String multi, String shorter) {
        int residue = 0;
        String result = "";
        for (int i = shorter.length() - 1; i >= 0; i--) {
            int fac = shorter.charAt(i) - '0';
            // 拿短的每一位乘以更长的数
            String res = product(multi, fac);
            // 和之前的结果相加
            result = addition(result, res, residue);
            // residue 是为了补足后面的 0
            residue++;
        }
        return result;
    }
    private String product(String str, int num) {
        if (num == 1) {
            // 乘数是 1， 直接返回另一个乘数
            return str;
        }
        if (num == 0) {
            // 乘数是 0 直接返回空
            String result = "";
            return result;
        }
        int carry = 0;
        String result = "";
        for (int i = str.length() - 1; i >= 0; i--) {
            int factor = str.charAt(i) - '0';
            int sum = factor * num + carry;
            carry = sum / 10;
            result = sum % 10 + result;
        }
        // 最后别忘了判断最高位是否还有进位要补足
        if (carry != 0) {
            result = carry + result;
        }
        return result;
    }
    private String addition(String res, String addi, int residue) {
        if (residue == 0 || addi == null || addi.length() == 0) {
            return addi;
        }
        for (int i = 0; i < residue; i++) {
            // 补足新得的数的后面的0
            addi += 0;
        }
        int i = res.length() - 1;
        int j = addi.length() - 1;
        int carry = 0;
        String ans = "";
        while (i >= 0 && j >= 0) {
            int sum = (res.charAt(i) - '0') + (addi.charAt(j) - '0') + carry;
            ans = sum % 10 + ans;
            carry = sum / 10;
            i--;
            j--;
        }
        // 更长的数还有高位别忘了补足
        while (i >= 0) {
            int sum = (res.charAt(i) - '0') + carry;
            ans = sum % 10 + ans;
            carry = sum / 10;
            i--;
        }
        while (j >= 0) {
            int sum = (addi.charAt(j) - '0') + carry;
            ans = sum % 10 + ans;
            carry = sum / 10;
            j--;
        }
        // 最后别忘了判断最高位是否还有进位要补足
        if (carry != 0) {
            ans = carry + ans;
        }
        return ans;
    }
}
