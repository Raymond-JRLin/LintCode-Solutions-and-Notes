// 1305. Integer to English Words
// 中文English
// Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 2^31 - 1.
//
// Example
// 123 -> "One Hundred Twenty Three"
// 12345 -> "Twelve Thousand Three Hundred Forty Five"
// 1234567 -> "One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven"


public class Solution {
    /**
     * @param num: a non-negative integer
     * @return: english words representation
     */
    public String numberToWords(int num) {
        // Write your code here
        if (num == 0) {
            return "Zero";
        }

        return mytry(num);
    }
    private String mytry(int num) {
        String result = "";
        String[] units = {"Thousand ", "Million ", "Billion "};
        int i = -1;
        while (num != 0) {
            int curr = num % 1000;
            num /= 1000;
            String s = intToString(curr) + (i >= 0 ? " " + units[i] : "");
            result = s + result;
            i++;
        }
        return result;
    }
    private String intToString(int num) {
        String[] arr1 = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
        String[] arr2 = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
        String s = "";

        int three = num / 100;
        int one = num % 10;
        int twenty = num % 100;
        if (twenty < 20) {
            s = arr1[twenty];
        } else {
            s = arr2[twenty / 10] + " " + arr1[one];
        }
        if (three != 0) {
            s = arr1[three] + " Hundred " + s;
        }
        return s;
    }
}
