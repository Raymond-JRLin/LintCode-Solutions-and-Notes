// 22. Flatten List
// 中文English
// Given a list, each element in the list can be a list or integer. flatten it into a simply list with integers.
//
// Example
// Example 1:
// 	Input:  [[1,1],2,[1,1]]
// 	Output: [1,1,2,1,1]
//
// 	Explanation:
// 	flatten it into a simply list with integers.
//
// Example 2:
// 	Input:  [1,2,[1,2]]
// 	Output:[1,2,1,2]
//
// 	Explanation:
// 	flatten it into a simply list with integers.
//
// Example 3:
// 	Input: [4,[3,[2,[1]]]]
// 	Output:[4,3,2,1]
//
// 	Explanation:
// 	flatten it into a simply list with integers.
//
// Challenge
// Do it in non-recursive.
//
// Notice
// If the element in the given list is a list, it can contain list too.
//


/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 * public interface NestedInteger {
 *
 *     // @return true if this NestedInteger holds a single integer,
 *     // rather than a nested list.
 *     public boolean isInteger();
 *
 *     // @return the single integer that this NestedInteger holds,
 *     // if it holds a single integer
 *     // Return null if this NestedInteger holds a nested list
 *     public Integer getInteger();
 *
 *     // @return the nested list that this NestedInteger holds,
 *     // if it holds a nested list
 *     // Return null if this NestedInteger holds a single integer
 *     public List<NestedInteger> getList();
 * }
 */
public class Solution {

    // @param nestedList a list of NestedInteger
    // @return a list of integer
    public List<Integer> flatten(List<NestedInteger> nestedList) {
        // Write your code here
        List<Integer> result = new ArrayList<>();

        // method 1: non-recursion based on stack, similar to Flatten Nested List Iterator
        // Stack<NestedInteger> stack = new Stack<>();
        // for (int i = nestedList.size() - 1; i >= 0; i--) {
        //     // remembe we should push from end to start
        //     stack.push(nestedList.get(i));
        // }
        // while (!stack.isEmpty()) {
        //     if (stack.peek().isInteger()) {
        //         result.add(stack.pop().getInteger());
        //     } else {
        //         List<NestedInteger> list = stack.pop().getList();
        //         for (int i = list.size() - 1; i >= 0; i--) {
        //             // remembe we should push from end to start too
        //             stack.push(list.get(i));
        //         }
        //     }
        // }
        // return result;

        // method 2: recursion
        // 逻辑上也很清晰， 拿到一个 Integer 就放入， 不是的话， 把 list 拿出来， 递归调用， 按顺序的一个个放进去
        // [......[]......[]......[......[]......]......]
        //   add   |  add  | add  {       |      }  add
        //        /\       /\      /             \
        //    [flatten] [flatten]    [flatten]
        //       add       add          add
        // --------------------------------------------->
        // for (NestedInteger nest : nestedList) {
        //     if (nest.isInteger()) {
        //         // 如果是 Integer 直接放入结果
        //         result.add(nest.getInteger());
        //     } else {
        //         // 如果不是， 把 list 递归的调用自身， 得到结果再放进去
        //         result.addAll(flatten(nest.getList()));
        //     }
        // }
        // return result;

        // method 3: non-recursion without stack
        boolean isFlat = false;
        List<NestedInteger> nested = nestedList;
        while (!isFlat) {
            isFlat = true;
            List<NestedInteger> list = new ArrayList<>();
            for (NestedInteger nest : nested) {
                if (nest.isInteger()) {
                    list.add(nest);
                } else {
                    list.addAll(nest.getList());
                    isFlat = false;
                }
            }
            nested = list;
        }
        for (NestedInteger val : nested) {
            result.add(val.getInteger());
        }
        return result;
    }
}
