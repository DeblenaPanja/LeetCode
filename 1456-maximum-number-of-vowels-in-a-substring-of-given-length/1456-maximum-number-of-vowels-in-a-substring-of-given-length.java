class Solution {
    public int maxVowels(String s, int k) {
        int max = 0, cur = 0;
        for (int i = 0; i < s.length(); i++) {
            if (isVowel(s.charAt(i))) cur++;
            if (i >= k && isVowel(s.charAt(i - k))) cur--;
            max = Math.max(max, cur);
        }
        return max;
    }

    private boolean isVowel(char c) {
        return c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u';
    }
}