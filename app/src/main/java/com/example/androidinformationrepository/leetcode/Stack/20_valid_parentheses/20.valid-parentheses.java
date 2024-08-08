/*
 * @lc app=leetcode id=20 lang=java
 *
 * [20] Valid Parentheses
 */

// @lc code=start
class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            } else if(c == ')' && stack.size() > 0 && stack.peek() == '(') {
                stack.pop();
            } else if(c == '}' && stack.size() > 0 && stack.peek() == '{') {
                stack.pop();
            } else if(c == ']' && stack.size() > 0 && stack.peek() == '[') {
                stack.pop();
            } else {
                return false;
            }
        }

        return stack.size() == 0;
    }
}
// @lc code=end

