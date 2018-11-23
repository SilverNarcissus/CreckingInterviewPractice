package algorithm;

import java.util.Arrays;
import java.util.Stack;

public class FindTheShortestSuperstring {
    public static void main(String[] args) {
        FindTheShortestSuperstring findTheShortestSuperstring = new FindTheShortestSuperstring();
        String[] input = {"a"};
        System.out.println(findTheShortestSuperstring.shortestSuperstring(input));
    }

    public String shortestSuperstring(String[] A) {
        int n = A.length;
        int[][] graph = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                graph[i][j] = overlap(A[i], A[j]);
                graph[j][i] = overlap(A[j], A[i]);
            }
        }

        int[][] dp = new int[1 << n][n];
        int[][] path = new int[1 << n][n];
        int max = -1;
        int last = -1;
        for (int i = 1; i < (1 << n); i++) {
            Arrays.fill(dp[i], -1);
            for (int j = 0; j < n; j++) {
                if ((i & (1 << j)) > 0) {
                    int prev = i - (1 << j);
                    if (prev == 0) {
                        dp[i][j] = 0;
                    } else {
                        for (int k = 0; k < n; k++) {
                            if (dp[prev][k] != -1 && graph[k][j] + dp[prev][k] > dp[i][j]) {
                                dp[i][j] = graph[k][j] + dp[prev][k];
                                path[i][j] = k;
                            }
                        }
                    }
                }

                if (i == (1 << n) - 1 && dp[i][j] > max) {
                    max = dp[i][j];
                    last = j;
                }
            }
        }

        Stack<Integer> s = new Stack<>();
        int cur = (1 << n) - 1;
        while (cur != 0) {
            s.push(last);
            last = path[cur][s.peek()];
            cur -= (1 << s.peek());
        }

        StringBuilder sb = new StringBuilder();
        int a = s.pop();
        sb.append(A[a]);
        while (!s.isEmpty()) {
            int b = s.pop();
            sb.append(A[b].substring(graph[a][b]));
            a = b;
        }

        return sb.toString();
    }

    private int overlap(String a, String b) {
        for (int i = 0; i < a.length(); i++) {
            if (b.startsWith(a.substring(i))) {
                return a.length() - i;
            }
        }

        return 0;
    }
}
