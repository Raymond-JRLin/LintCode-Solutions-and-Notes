// 399. Nuts & Bolts Problem
// 中文English
// Given a set of n nuts of different sizes and n bolts of different sizes. There is a one-one mapping between nuts and bolts.
//
// Comparison of a nut to another nut or a bolt to another bolt is not allowed. It means nut can only be compared with bolt and bolt can only be compared with nut to see which one is bigger/smaller. We will give you a compare function to compare nut with bolt.
//
// Using the function we give you, you are supposed to sort nuts or bolts, so that they can map in order.
//
// Example
// Given nuts = ['ab','bc','dd','gg'], bolts = ['AB','GG', 'DD', 'BC'].
//
// Your code should find the matching of bolts and nuts.
//
// According to the function, one of the possible return:
//
// nuts = ['ab','bc','dd','gg'], bolts = ['AB','BC','DD','GG'].
//
// If we give you another compare function, the possible return is the following:
//
// nuts = ['ab','bc','dd','gg'], bolts = ['BC','AA','DD','GG'].
//
// So you must use the compare function that we give to do the sorting.
//
// The order of the nuts or bolts does not matter. You just need to find the matching bolt for each nut.
//


/**
 * public class NBCompare {
 *     public int cmp(String a, String b);
 * }
 * You can use compare.cmp(a, b) to compare nuts "a" and bolts "b",
 * if "a" is bigger than "b", it will return 1, else if they are equal,
 * it will return 0, else if "a" is smaller than "b", it will return -1.
 * When "a" is not a nut or "b" is not a bolt, it will return 2, which is not valid.
*/
public class Solution {
    /**
     * @param nuts: an array of integers
     * @param bolts: an array of integers
     * @param compare: a instance of Comparator
     * @return: nothing
     */
    public void sortNutsAndBolts(String[] nuts, String[] bolts, NBComparator compare) {
        // write your code here
        if (nuts == null || bolts == null || nuts.length != bolts.length) {
            return;
        }
        quickSort(nuts, bolts, compare, 0, nuts.length - 1);
    }
    private void quickSort(String[] nuts, String[] bolts, NBComparator compare, int left, int right) {
        if (left >= right) {
            return;
        }
        int index = partition(nuts, bolts[left], compare, left, right);
        partition(bolts, nuts[index], compare, left, right);

        quickSort(nuts, bolts, compare, left, index);
        quickSort(nuts, bolts, compare, index + 1, right);
    }
    private int partition(String[] str, String pivot, NBComparator compare, int left, int right) {
        for (int i = 0; i < str.length; i++) {
            if (compare.cmp(str[i], pivot) == 0 || compare.cmp(pivot, str[i]) == 0) {
                swap(str, i, left);
                break;
            }
        }
        String curr = str[left];
        int start = left;
        int end = right;
        while (start < end) {
            while (start < end && (compare.cmp(str[end], pivot) == 1 || compare.cmp(pivot, str[end]) == -1)) {
                end--;
            }
            str[start] = str[end];
            while (start < end && (compare.cmp(str[start], pivot) == -1 || compare.cmp(pivot, str[start]) == 1)) {
                start++;
            }
            str[end] = str[start];
        }
        str[start] = curr;
        return start;
    }
    private void swap(String[] str, int left, int right) {
        String temp = str[left];
        str[left] = str[right];
        str[right] = temp;
    }
};
