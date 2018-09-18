// 1467. Ask For Cooling Time
// Description
// You have a bunch of skills that need to be released. The release order is arr. Must be released in order. Each skill has a cooldown of length n. That is, there must be an interval of at least n seconds between two similar skills. It takes 1 second to release each skill, return the time it takes to finish all skills.
//
// The length of the array does not exceed 100,000.
// 1 \leq n \leq 201≤n≤20
// The skill number is a positive integer that does not exceed 100.
// Have you met this question in a real interview?
// Example
// Given arr=[1,1,2,2],n=2.Return 8.
//
// The order is [1, _, _, 1, 2, _, _, 2].So return 8.
// Skill 1 is released in the 1st second, in the 2nd second and the 3rd second enters the cooling time, and the 4th second releases the second time.
// Skill 2 is released in the 5th second, in the 6th second and the 7th second enters the cooling time, and the 8th second releases the second time.
//
// Given arr=[1,2,1,2], n=2. Return 5.
//
// The order is [1, 2, _, 1, 2].So return  5.
// Skill 1 is released in the 1st second, in the 2nd second and the 3rd second enters the cooling time, and the 4th second releases the second time.
// Skill 2 is released in the 2nd second, in the 3rd second and the 4th second enters the cooling time, and the 5th second releases the second time.


public class Solution {
    /**
     * @param arr: The release order
     * @param n: The cooldown
     * @return: Return the time
     */
    public int askForCoolingTime(int[] arr, int n) {
        // Write your code here
        if (arr == null || arr.length == 0) {
            return 0;
        }

        // return mytry(arr, n);

        return method2(arr, n);
    }

    private int method2(int[] nums, int n) {
        Map<Integer, Integer> map = new HashMap<>(); // <skill, realeased time spot>
        int result = 0; // 定义为第几秒 （释放） based on 1
        for (int num : nums) {
            if (map.containsKey(num)) {
                // 如果之前这个技能放过了， 查看是否已经冷却
                if (map.get(num) + n > result) {
                    // 之前这个技能释放的时间点 + 冷却时间 > (超过了) 当前的第几秒， 意味着这个技能没有冷却完毕， 那么最少要等到这个技能释放完毕， 因为在这个 if 外面统一做了 result++， 所以这里不多 + 1
                    result = map.get(num) + n;
                }
                // 之前这个技能也已经冷却好了， 那么就直接下一秒放这个技能好了
                result++;
                map.put(num, result); // update
            } else {
                // 这个技能没有被放过， 直接下一秒放
                result++;
                map.put(num, result); // update
            }
        }
        return result;
    }

    private int mytry(int[] nums, int n) {
        // 模拟技能释放与冷却， 但是每次更新 map 其实蛮花时间的
        int len = nums.length;
        int i = 0;
        Map<Integer, Integer> map = new HashMap<>();
        int result = 0;
        while (i < len) {
            result++;
            if (map != null && map.containsKey(nums[i])) {
                reduce(map);
                continue;
            }
            reduce(map);
            map.put(nums[i], n);
            i++;
        }
        return result;
    }
    private void reduce(Map<Integer, Integer> map) {
        Set<Integer> set = new HashSet<>();
        for (int key : map.keySet()) {
            map.put(key, map.get(key) - 1);
            if (map.get(key) == 0) {
                set.add(key);
            }
        }
        for (int key : set) {
            map.remove(key);
        }
    }
}


// answer:

考点: hash

题解: 数组记录每个技能之前一次的释放时间，然后对于当前技能，计算cd。

public class Solution {
    /**
     * @param arr: The release order
     * @param n: The cooldown
     * @return: Return the time
     */
    public int askForCoolingTime(int[] arr, int n) {
        // Write your code here
        Map<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
        int ans = 0;
        for (int i = 0; i < arr.length; i++) {
            if (hashMap.containsKey(arr[i])) {
                if (hashMap.get(arr[i]) + n > ans) {
                    ans = hashMap.get(arr[i]) + n;
                }
                ans += 1;
                hashMap.put(arr[i], ans);
            } else {
                ans += 1;
                hashMap.put(arr[i], ans);
            }
        }
        return ans;
    }
}
