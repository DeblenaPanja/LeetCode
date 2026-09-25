import java.util.*;

class Solution {
    public List<String> braceExpansionII(String expression) {
        Set<String> set = parse(expression);
        List<String> result = new ArrayList<>(set);
        Collections.sort(result);
        return result;
    }

    private Set<String> parse(String s) {
        Set<String> res = new HashSet<>();
        List<Set<String>> groups = new ArrayList<>();
        int n = s.length(), i = 0;

        while (i < n) {
            if (s.charAt(i) == '{') {
                int j = i, level = 0;
                while (j < n) {
                    if (s.charAt(j) == '{') level++;
                    if (s.charAt(j) == '}') level--;
                    if (level == 0) break;
                    j++;
                }
                groups.add(parse(s.substring(i + 1, j)));
                i = j + 1;
            } else if (Character.isLetter(s.charAt(i))) {
                int j = i;
                while (j < n && Character.isLetter(s.charAt(j))) j++;
                groups.add(Collections.singleton(s.substring(i, j)));
                i = j;
            } else if (s.charAt(i) == ',') {
                res.addAll(combine(groups));
                groups.clear();
                i++;
            }
        }
        res.addAll(combine(groups));
        return res;
    }

    private Set<String> combine(List<Set<String>> groups) {
        Set<String> res = new HashSet<>();
        res.add("");
        for (Set<String> group : groups) {
            Set<String> next = new HashSet<>();
            for (String a : res) {
                for (String b : group) {
                    next.add(a + b);
                }
            }
            res = next;
        }
        return res;
    }
}