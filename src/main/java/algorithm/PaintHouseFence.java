package algorithm;

public class PaintHouseFence {


    public static void main(String[] args) {
        Solution solution = new Solution();
        StandardSolution s = new StandardSolution();

        System.out.println("Fence!");
        System.out.println(solution.paintFence(12, 13));
        System.out.println(s.paintFence(12, 13));
        System.out.println();

        int[][] money = {{4, 2, 5, 5}, {24, 18, 15, 18}, {25, 15, 17, 36}, {24, 32, 25, 15}, {24, 29, 25, 5}};
        System.out.println("House!");
        System.out.println(solution.paintHouse(5, 4, money));
        System.out.println(s.paintHouse(money));
        System.out.println();
    }
}


class StandardSolution {
    public int paintHouse(int[][] costs) {
        if (costs != null && costs.length == 0) return 0;
        int prevMin = 0, prevSec = 0, prevIdx = -1;
        for (int i = 0; i < costs.length; i++) {
            int currMin = Integer.MAX_VALUE, currSec = Integer.MAX_VALUE, currIdx = -1;
            for (int j = 0; j < costs[0].length; j++) {
                costs[i][j] = costs[i][j] + (prevIdx == j ? prevSec : prevMin);
                // 找出最小和次小的，最小的要记录下标，方便下一轮判断
                if (costs[i][j] < currMin) {
                    currSec = currMin;
                    currMin = costs[i][j];
                    currIdx = j;
                } else if (costs[i][j] < currSec) {
                    currSec = costs[i][j];
                }
            }
            prevMin = currMin;
            prevSec = currSec;
            prevIdx = currIdx;
        }
        return prevMin;
    }

    public int paintFence(int n, int k) {
        // 当n=0时返回0
        int dp[] = {0, k, k * k, 0};
        if (n <= 2) {
            return dp[n];
        }
        for (int i = 2; i < n; i++) {
            // 递推式：第三根柱子要么根第一个柱子不是一个颜色，要么跟第二根柱子不是一个颜色
            dp[3] = (k - 1) * (dp[1] + dp[2]);
            dp[1] = dp[2];
            dp[2] = dp[3];
        }
        return dp[3];
    }
}

class Solution {
    public int paintFence(int n, int k) {
        if (n == 1) {
            return k;
        }

        if (n == 2) {
            return k * k;
        }

        int same = k;
        int notSame = k * (k - 1);

        for (int i = 2; i < n; i++) {
            int newSame = notSame;
            int newNotSame = (same + notSame) * (k - 1);
            same = newSame;
            notSame = newNotSame;
        }

        return same + notSame;
    }

    public int paintHouse(int n, int k, int[][] money) {
        int[] dp = new int[k];
        for (int i = 0; i < k; i++) {
            dp[i] = money[0][i];
        }

        for (int i = 1; i < n; i++) {
            int[][] minRange = new int[k][2];
            int leftMin = Integer.MAX_VALUE;
            int rightMin = Integer.MAX_VALUE;
            for (int j = 0; j < k; j++) {
                leftMin = Math.min(leftMin, dp[j]);
                rightMin = Math.min(rightMin, dp[k - j - 1]);
                minRange[j][0] = leftMin;
                minRange[k - j - 1][1] = rightMin;
            }

            for (int j = 0; j < k; j++) {
                dp[j] = money[i][j] + findMinWithOutIt(minRange, j);
            }
        }


        int min = Integer.MAX_VALUE;
        for (int i : dp) {
            min = Math.min(min, i);
        }

        return min;
    }

    private int findMinWithOutIt(int[][] minRange, int loc) {
        if (loc == minRange.length - 1) {
            return minRange[loc - 1][0];
        }

        if (loc == 0) {
            return minRange[loc + 1][1];
        }

        return Math.min(minRange[loc - 1][0], minRange[loc + 1][1]);
    }
}
