package algorithm;

public class EggDrop {
    public static void main(String[] args) {
        EggDrop eggDrop = new EggDrop();
        System.out.println(eggDrop.drop(3, 2));
    }

    public int drop(int floor, int n) {
        int[] dp = new int[floor + 1];
        for (int i = 1; i <= floor; i++) {
            dp[i] = i;
        }

        int[] new_dp = new int[floor + 1];
        for (int i = 2; i <= n; i++) {
            int cur = 1;
            for (int j = 1; j <= floor; j++) {
                while (cur < j && Math.max(dp[cur - 1], new_dp[j - cur]) > Math.max(dp[cur], new_dp[j - cur - 1])) {
                    cur++;
                }

                new_dp[j] = Math.max(dp[cur - 1], new_dp[j - cur]) + 1;;
            }

            int[] temp = dp;
            dp = new_dp;
            new_dp = temp;
        }

        return dp[floor];
    }
}
