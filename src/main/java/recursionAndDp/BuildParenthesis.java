package recursionAndDp;

import java.util.HashSet;
import java.util.Set;

public class BuildParenthesis {
    private static int size = 0;
    private static Set<String> result = new HashSet<>();

    public static void main(String[] args) {
        BuildParenthesis buildParenthesis = new BuildParenthesis();
        int count = 15;
        buildParenthesis.solve(count);
//        System.out.println(size);
//        System.out.println(result.size());

        System.out.println(size);

        result.removeAll(buildParenthesis.solveRecursive(count));
        System.out.println(result);
    }

    public void solve(int count){
        StringBuilder stringBuilder = new StringBuilder();
        printParenthesis(count, count, stringBuilder);
    }

    private void printParenthesis(int left, int right, StringBuilder sb) {
        if (left == 0 && right == 0) {
            size++;
            //System.out.println(sb);
            result.add(sb.toString());
        }

        if (left != 0) {
            sb.append('(');
            printParenthesis(left - 1, right, sb);
            sb.deleteCharAt(sb.length() - 1);
        }

        if (right > left) {
            sb.append(')');
            printParenthesis(left, right - 1, sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

    public Set<String> solveRecursive(int count){
        //size = recursiveHelper(count).size();
        //System.out.println(recursiveHelper(count).size());
        //System.out.println(recursiveHelper(count));

        return recursiveHelper(count);
    }

    private Set<String> recursiveHelper(int count){
        Set<String> result = new HashSet<>();
        if(count == 0){
            result.add("");
            return result;
        }

        Set<String> before = recursiveHelper(count - 1);
        for(String b : before){
            result.add("()" + b);
            for(int i = 0; i < b.length(); i++){
                if(b.charAt(i) == '('){
                    result.add(b.substring(0, i + 1) + "()" + b.substring(i + 1));
                }
            }
        }

        return result;
    }
}
