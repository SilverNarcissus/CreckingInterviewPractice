package algorithm;

// give a sequence like 'IDI' length = N,
// count how many permutations of [0, N] can fit this sequence
// example 'IDI', 5
public class IDISequence {
    public static void main(String[] args) {
        IDISequence idiSequence = new IDISequence();
        System.out.println(idiSequence.solve("IDID"));
    }

    public int solve(String s) {
        int n = s.length();
        int[] dp = new int[n + 1];

        dp[0] = 1;
        int len = 2;
        for (int i = s.length() - 1; i >= 0; i--) {
            int[] cur = new int[n + 1];
            if (s.charAt(i) == 'I') {
                int sum = 0;
                for (int j = len - 2; j >= 0; j--) {
                    sum += dp[j];
                    cur[j] = sum;
                }
            } else {
                int sum = 0;
                for (int j = 1; j < len; j++) {
                    sum += dp[j - 1];
                    cur[j] = sum;
                }
            }

            len++;
            dp = cur;
        }

        return sum(dp);
    }

    private int sum(int[] array) {
        int result = 0;
        for (int i : array) {
            result += i;
        }

        return result;
    }
}
