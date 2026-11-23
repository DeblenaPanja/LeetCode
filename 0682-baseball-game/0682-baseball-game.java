class Solution {
    public int calPoints(String[] operations) {
        int[] stack = new int[operations.length];
        int top = -1;
        int sum = 0;

        for (String op : operations) {
            char c = op.charAt(0);
            if (c == '+') {
                int score = stack[top] + stack[top - 1];
                stack[++top] = score;
                sum += score;
            } else if (c == 'D') {
                int score = stack[top] * 2;
                stack[++top] = score;
                sum += score;
            } else if (c == 'C') {
                sum -= stack[top--];
            } else {
                int score = Integer.parseInt(op);
                stack[++top] = score;
                sum += score;
            }
        }

        return sum;
    }
}