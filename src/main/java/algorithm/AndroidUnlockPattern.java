package algorithm;

public class AndroidUnlockPattern {

    public static void main(String[] args) {
        AndroidUnlockPattern androidUnlockPattern = new AndroidUnlockPattern();
        Solution_I s = new Solution_I();

        long time = System.nanoTime();
        System.out.println(androidUnlockPattern.solve(1,9));
        System.out.println(s.numberOfPatterns(1, 9));
        System.out.println((System.nanoTime() - time) / 1000 / 1000);
    }

    int[][] map = new int[9][9];

    public AndroidUnlockPattern() {
        map[0][2] = 1;
        map[0][6] = 3;
        map[0][8] = 4;

        map[1][7] = 4;

        map[2][6] = 4;
        map[2][8] = 5;

        map[3][5] = 4;

        map[6][8] = 7;

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (map[i][j] > 0 && map[j][i] == 0) {
                    map[j][i] = map[i][j];
                }
            }
        }
    }


    public int solve(int n, int m) {
        int result = 0;
        for (int i = 0; i < 9; i++) {
            result += dfs(i, new boolean[9], n, m, 1);
        }

        return result;
    }

    private int dfs(int cur, boolean[] record, int n, int m, int now) {
        if (now > m) {
            return 0;
        }
        if (now == m) {
            return 1;
        }

        int result = 0;
        if (now >= n) {
            result = 1;
        }

        record[cur] = true;
        for (int i = 0; i < 9; i++) {
            if (map[cur][i] == 0) {
                if (!record[i]) {
                    result += dfs(i, record, n, m, now + 1);
                }
            } else {
                if (record[map[cur][i]] && !record[i]) {
                    result += dfs(i, record, n, m, now + 1);
                }
            }
        }
        record[cur] = false;

        return result;
    }
}


class Solution_I {
    private int patterns;

    private boolean valid(boolean[] keypad, int from, int to) {
        if (from == to) return false;
        int i = Math.min(from, to), j = Math.max(from, to);
        if ((i == 1 && j == 9) || (i == 3 && j == 7)) return keypad[5] && !keypad[to];
        if ((i == 1 || i == 4 || i == 7) && i + 2 == j) return keypad[i + 1] && !keypad[to];
        if (i <= 3 && i + 6 == j) return keypad[i + 3] && !keypad[to];
        return !keypad[to];
    }

    private void find(boolean[] keypad, int from, int step, int m, int n) {
        if (step == n) {
            patterns++;
            return;
        }
        if (step >= m) patterns++;
        for (int i = 1; i <= 9; i++) {
            if (valid(keypad, from, i)) {
                keypad[i] = true;
                find(keypad, i, step + 1, m, n);
                keypad[i] = false;
            }
        }
    }

    public int numberOfPatterns(int m, int n) {
        boolean[] keypad = new boolean[10];
        for (int i = 1; i <= 9; i++) {
            keypad[i] = true;
            find(keypad, i, 1, m, n);
            keypad[i] = false;
        }
        return patterns;
    }
}