package algorithm;

public class Millionaire {
    private static final double MILLION = 1000000;

    public static void main(String[] args) {
        System.out.println(calculate(2, 0.5, 300000));
    }

    public static double calculate(int m, double p, double x) {
        int n = (2 << (m - 1)) + 1;
        double[][] dp = new double[m + 1][n];

        dp[m][n - 1] = 1;

        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                int mostMoney = Math.min(j, n - j - 1);
                for (int k = 0; k <= mostMoney; k++) {
                    dp[i][j] = Math.max(dp[i][j], (1 - p) * dp[i + 1][j - k] + p * dp[i + 1][j + k]);
                }
            }
        }

        int index = Math.min(n - 1, (int) (x / MILLION * (n - 1)));
//        System.out.println(index);
//
//        System.out.println(Arrays.toString(dp[1]));
//        System.out.println(Arrays.toString(dp[0]));

        return dp[0][index];
    }
}
