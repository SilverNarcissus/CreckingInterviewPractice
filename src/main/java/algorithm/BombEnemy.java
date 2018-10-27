package algorithm;

import java.util.Arrays;

public class BombEnemy {

    public static void main(String[] args) {
        char[][] board = {{'0', 'X', '0', '0'},
                {'X', '0', 'Y', 'X'},
                {'0', 'X', '0', '0'}};
        BombEnemy bombEnemy = new BombEnemy();

        System.out.println(bombEnemy.bomb(board));
    }

    private static final int UP = 0;
    private static final int DOWN = 1;
    private static final int LEFT = 2;
    private static final int RIGHT = 3;

    public int bomb(char[][] board) {
        int n = board.length;
        if (n == 0) {
            return 0;
        }

        int m = board[0].length;
        int[][][] dp = new int[n][m][4];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                switch (board[i][j]) {
                    case '0':
                        dp[i][j][UP] = getLoc(i - 1, j, UP, n, m, dp);

                        dp[i][j][LEFT] = getLoc(i, j - 1, LEFT, n, m, dp);

                        break;
                    case 'X':
                        dp[i][j][UP] = getLoc(i - 1, j, UP, n, m, dp) + 1;
                        dp[i][j][LEFT] = getLoc(i, j - 1, LEFT, n, m, dp) + 1;

                        break;
                    case 'Y':
                        dp[i][j][UP] = 0;
                        dp[i][j][LEFT] = 0;
                        break;
                    default:
                        throw new IllegalArgumentException("illegal char at " + i + " " + j);

                }
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                switch (board[i][j]) {
                    case '0':
                        dp[i][j][DOWN] = getLoc(i + 1, j, DOWN, n, m, dp);
                        dp[i][j][RIGHT] = getLoc(i, j + 1, RIGHT, n, m, dp);
                        break;
                    case 'X':
                        dp[i][j][DOWN] = getLoc(i + 1, j, DOWN, n, m, dp) + 1;
                        dp[i][j][RIGHT] = getLoc(i, j + 1, RIGHT, n, m, dp) + 1;
                        break;
                    case 'Y':
                        dp[i][j][DOWN] = 0;
                        dp[i][j][RIGHT] = 0;
                        break;
                    default:
                        throw new IllegalArgumentException("illegal char at " + i + " " + j);
                }
            }
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == '0') {
                    int cur = 0;
                    for (int k = 0; k < 4; k++) {
                        cur += dp[i][j][k];
                    }
                    result = Math.max(cur, result);
                }
            }
        }

        printDp(dp, n, m);
        return result;
    }

    private int getLoc(int i, int j, int direct, int n, int m, int[][][] dp) {
        if (i < 0 || i >= n || j < 0 || j >= m) {
            return 0;
        }
        return dp[i][j][direct];
    }

    private void printDp(int[][][] dp, int n, int m) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                System.out.print(Arrays.toString(dp[i][j]) + " ");
            }
            System.out.println();
        }
    }
}
