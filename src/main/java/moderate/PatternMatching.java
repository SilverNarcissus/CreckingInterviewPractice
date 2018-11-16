package moderate;

//You are given two strings, pattern and value.
// The pattern string consists of just the letters a and b, describing a pattern within a string.
// For example, the string catcatgocatgo matches the pattern aabab (where cat is a and go is b).
// It also matches patterns like a, ab, and b.
// Write a method to determine if value matches pattern.
import java.util.Arrays;

public class PatternMatching {
    public static void main(String[] args) {
        PatternMatching patternMatching = new PatternMatching();
        System.out.println(patternMatching.match("abcac", "catcatgocatgo"));
        System.out.println(Arrays.toString(patternMatching.map));
    }

    private String[] map = new String[3];


    public boolean match(String pattern, String value) {
        return match(pattern, value, 0, 0);
    }

    private boolean match(String pattern, String value, int index_p, int index_v) {
        if (index_p == pattern.length() && index_v == value.length()) {
            return true;
        }

        if (index_p == pattern.length() || index_v == value.length()) {
            return false;
        }

        String next = map[pattern.charAt(index_p) - 'a'];
        if (next == null) {
            for (int len = 1; index_v + len <= value.length(); len++) {
                String p = value.substring(index_v, index_v + len);
                map[pattern.charAt(index_p) - 'a'] = p;
                //System.out.println(pattern.charAt(index_p) + "  " + p);
                if (match(pattern, value, index_p + 1, index_v + len)) {
                    return true;
                }
            }

            map[pattern.charAt(index_p) - 'a'] = null;
            return false;
        }

        int new_index_v = next.length() + index_v;
        return new_index_v <= value.length() &&
                next.equals(value.substring(index_v, new_index_v)) &&
                match(pattern, value, index_p + 1, new_index_v);
    }
}
