// 40. Implement Queue by Two Stacks
// 中文English
// As the title described, you should only use two stacks to implement a queue's actions.
//
// The queue should support push(element), pop() and top() where pop is pop the first(a.k.a front) element in the queue.
//
// Both pop and top methods should return the value of first element.
//
// Example
// Example 1:
//
// Input:
//     push(1)
//     pop()
//     push(2)
//     push(3)
//     top()
//     pop()
// Output:
//     1
//     2
//     2
// Example 2:
//
// Input:
//     push(1)
//     push(2)
//     push(2)
//     push(3)
//     push(4)
//     push(5)
//     push(6)
//     push(7)
//     push(1)
// Output:
// []
// Challenge
// implement it by two stacks, do not use any other data structure and push, pop and top should be O(1) by AVERAGE.
//
// Notice
// Suppose the queue is not empty when the pop() function is called.
//


public class MyQueue {
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    public MyQueue() {
        // do initialization if necessary

        // my method: 我把 stack1 作为主 stack， 总是维持 stack1 中的元素为正确的 queue 的顺序
        stack1 = new Stack<Integer>(); // main stack
        stack2 = new Stack<Integer>();

        // 参考答案给的是总是往 2 中 push， 当我要做 pop 和 top 的时候， 如果 1 是空的， 就把 2 全倒入 1， 然后再对 1 操作
        // 从 Challenge 要求所有操作为 O(1) by average 的角度看， 参考答案的方法更好， 因为我自己写的话， 每次 push 大概率都是 1 中已经有了， 要去把所有的元素倒入 2、 加入新元素、 倒入 1； 不知道是不是不是 O(1), 但没答案好； 因为答案要做第一次 pop 和 top 的时候， 就倒入 1， 这样以后如果 1 中还有就可以直接出栈， push 也可以直接进入 2
    }

    public void push(int element) {
        // write your code here

        // my method:
        // if (stack1.isEmpty()) {
        //     stack1.push(element);
        // } else {
        //     while (!stack1.isEmpty()) {
        //         stack2.push(stack1.pop());
        //     }
        //     stack2.push(element);
        //     while (!stack2.isEmpty()) {
        //         stack1.push(stack2.pop());
        //     }
        // }

        // better method:
        stack2.push(element);
    }

    public int pop() {
        // write your code here

        // my method:
        // return stack1.pop();

        // better method:
        if (stack1.isEmpty()) {
            stack2to1(stack1, stack2);
        }
        return stack1.pop();
    }

    public int top() {
        // write your code here

        // my method:
        // return stack1.peek();

        // better method:
        if (stack1.isEmpty()) {
            stack2to1(stack1, stack2);
        }
        return stack1.peek();
    }

    private void stack2to1(Stack<Integer> s1, Stack<Integer> s2) {
        // 在参考答案中， 直接把这个方法写成没有参数的， 用 this 调用
        while (!s2.isEmpty()) {
            s1.push(s2.pop());
        }
    }
}
