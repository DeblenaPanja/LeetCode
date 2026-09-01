class Solution {
    public List<String> letterCombinations(String digits) {
        java.util.LinkedList<String> res = new java.util.LinkedList<>();
        if(digits.isEmpty()) return res;
        res.add("");
        String[] map = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        for(int i = 0; i < digits.length(); i++){
            int x = digits.charAt(i) - '0';
            while(res.peek().length() == i){
                String t = res.remove();
                for(char s : map[x].toCharArray()) res.add(t + s);
            }
        }
        return res;
    }
}