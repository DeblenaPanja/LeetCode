class Solution {
    public int secondHighest(String s) {
        int l= -1;
        int sl= -1;
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                int v= c - '0'; 
                if (v>l) {
                    sl=l;
                    l=v;
                } else if (v<l && v>sl) {
                    sl=v;
                }
            }
        }
        
        return sl;
    }
}