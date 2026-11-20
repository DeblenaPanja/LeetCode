class Solution {
    static {
        for (int i = 1; i < 500; i++) {
            isValid("");
        }
    }
    public static boolean isValid(String str) {
        if (str.length() % 2 == 1) {
            return false;
        }
        char stack[] = new char[str.length()];
        int top = -1;
        for (char ch : str.toCharArray()) {
            if (ch == '(' || ch == '{' || ch == '[') {
                stack[++top] = ch;
            } else if (top != -1 && stack[top] == '(' && ch == ')' || top != -1 && stack[top] == '{' && ch == '}'
                    || top != -1 && stack[top] == '[' && ch == ']') {
                top--;
            } else {
                return false;
            }
        }
        return top == -1;
    }
}