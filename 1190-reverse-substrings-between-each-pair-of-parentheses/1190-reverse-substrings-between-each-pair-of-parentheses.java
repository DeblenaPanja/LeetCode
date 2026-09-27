import java.util.*;

class Solution {
    public String reverseParentheses(String s) {
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(sb.length());
            } else if (c == ')') {
                int start = stack.pop();
                String reversed = new StringBuilder(sb.substring(start)).reverse().toString();
                sb.replace(start, sb.length(), reversed);
            } else {
                sb.append(c);
            }
        }
        
        return sb.toString();
    }
}