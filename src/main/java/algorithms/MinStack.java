package algorithms;

import java.util.Stack;

/**
 * Created by ZhangDong on 2018/1/9.
 */
public class MinStack {
    public MinStack() {
        // do intialization if necessary
    }

    private Stack<Integer> stack = new Stack<Integer>();
    private Stack<Integer> minStack = new Stack<Integer>();

    /*
     * @param number: An integer
     * @return: nothing
     */
    public void push(int number) {
        // write your code here
        stack.push(number);
        if (minStack.isEmpty()) {
            minStack.push(number);
        } else {

            if (number < minStack.peek()) {
                minStack.push(number);
            } else {
                minStack.push(minStack.peek());
            }
        }
    }

    /*
     * @return: An integer
     */
    @SuppressWarnings("unused")
    public int pop() {
        // write your code here
        minStack.pop();
        return stack.pop();
    }

    /*
     * @return: An integer
     */
    public int min() {
        // write your code her
        return minStack.peek();
    }
}