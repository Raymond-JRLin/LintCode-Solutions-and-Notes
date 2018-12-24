// 633. Find the Duplicate Number
// Description
// Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive), prove that at least one duplicate number must exist. Assume that there is only one duplicate number, find the duplicate one.
//
// You must not modify the array (assume the array is read only).
// You must use only constant, O(1) extra space.
// Your runtime complexity should be less than O(n^2).
// There is only one duplicate number in the array, but it could be repeated more than once.
// Have you met this question in a real interview?
// Example
// Given nums = [5,5,4,3,2,1] return 5
// Given nums = [5,4,4,3,2,1] return 4


public class Solution {
    /**
     * @param nums: an array containing n + 1 integers which is between 1 and n
     * @return: the duplicate one
     */
    public int findDuplicate(int[] nums) {
        // write your code here
        if (nums == null || nums.length == 0) {
            return -1;
        }

        // return mytry(nums);

        // return method2(nums);

        return method3(nums);
    }

    private int method3(int[] nums) {
        // BS: 基于值的二分法。
        // 这个题比较好的理解方法是画一个坐标轴：

        // x轴是 0, 1, 2, ... n。
        // y轴是对应的 <=x 的数的个数，比如 <=0 的数的个数是0，就在（0,0）这个坐标画一个点。<=n 的数的个数是 n+1 个，就在 (n,n+1)画一个点。
        // 我们可以知道这个折线图的有如下的一些属性：

        // 大部分时候，我们会沿着斜率为 1 的那条虚线前进
        // 如果出现了一些空缺的数，就会有横向的折线
        // 一旦出现了重复的数，就会出现一段斜率超过 1 的折线
        // 斜率超过 1 的折线只会出现一次
        // 试想一下，对比 y=x 这条虚线，当折线冒过了这条虚线出现在这条虚线的上方的时候，一定是遇到了一个重复的数。
        // 一旦越过了这条虚线以后，就再也不会掉到虚线的下方或者和虚线重叠。
        // 因为折线最终会停在 (n,n+1) 这个位置，如果要从 y=x 这条虚线或者这条虚线的下方到达 (n,n+1) 这个位置，
        // 一定需要一个斜率 > 1的折线段，而这个与题目所说的重复的数只有一个就是矛盾的。因此可以证明，斜率超过1 的折线只会出现1次，
        // 且会将折线整体带上 y=x 这条虚线的上方。因此第一个在 y=x 上方的 x 点，就是我们要找的重复的数。

        // 时间复杂度是 O(nlogn)
        int start = 1;
        int end = nums.length - 1; // n
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            int count = countEqualOrLess(nums, mid);
            if (count <= mid) {
                start = mid;
            } else {
                end = mid;
            }
        }
        // 查小于等于的个数， 先看 start， edge case : [1, 1, 1, 1, 1]
        if (countEqualOrLess(nums, start) <= start) {
            return end;
        } else {
            return start;
        }
    }
    private int countEqualOrLess(int[] nums, int target) {
        int count = 0;
        for (int num : nums) {
            if (num <= target) {
                count++;
            }
        }
        return count;
    }

    private int method2(int[] nums) {
        // 快慢指针， O(N) time and O(1) space
        int n = nums.length;
        int slow = nums[0];
        int fast = nums[nums[0]];
        // 和 LinkedList 不太相同的是， 停下来的时候要是 slow 和 fast 相等， 那么初始值就要不一样了， 相当于 fast 多走了一步， 之后在找环的入口的时候， 要为 0 而不是 nums[0]
        // 我自己的理解是： fast 在一开始多走了， 所以相当于它相遇时候的总路程少了一步， 或者说在环的追击过程中， fast 抢先一步遇到 slow， 这样子， 相遇的点就会比两者同时从 0 开始的时候更靠前一步， 即远离相遇点一步， 这时候让 slow 退后一步开始， 再相遇时才会正好是相遇点
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
            // System.out.println("slow and fast are " + slow + ", " + fast);
        }

        fast = 0;
        while (fast != slow) {
            fast = nums[fast];
            slow = nums[slow];
            // System.out.println("slow and fast are " + slow + ", " + fast);
        }
        return slow;

    }

    private int mytry(int[] nums) {
        // if we can modify the array
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            nums[nums[i] % n] += n;
        }
        for (int i = 0; i < n; i++) {
            if (nums[i] / n > 1) {
                return i;
            }
        }
        return -1;
    }
}
