class Solution {
    public int maxPalindromes(String s, int k) {
        int count = 0;
        int lastEnd = -1;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (i - k + 1 > lastEnd && isPalindrome(s, i - k + 1, i)) {
                count++;
                lastEnd = i;
            } else if (i - k > lastEnd && isPalindrome(s, i - k, i)) {
                count++;
                lastEnd = i;
            }
        }
        return count;
    }

    private boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left++) != s.charAt(right--)) {
                return false;
            }
        }
        return true;
    }
}