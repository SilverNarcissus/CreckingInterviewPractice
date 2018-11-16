package moderate;

import java.util.ArrayList;
import java.util.List;

public class PondSize {
    public static void main(String[] args) {
        PondSize pondSize = new PondSize();
        int[][] map = {{0, 2, 1, 0}, {0, 1, 0, 1}, {1, 1, 0, 1}, {0, 1, 0, 1}};

        System.out.println(pondSize.pondSize(map));
    }

    private int n;
    private int m;

    public List<Integer> pondSize(int[][] map) {
        n = map.length;
        m = map[0].length;
        List<Integer> result = new ArrayList<>();
        boolean[][] record = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0 && !record[i][j]) {
                    result.add(count(i, j, map, record));
                }
            }
        }

        return result;
    }

    private int count(int x, int y, int[][] map, boolean[][] record) {
        if (!checkRange(x, y) || record[x][y] || map[x][y] > 0) {
            return 0;
        }

        record[x][y] = true;
        int result = 1;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                result += count(x + i, y + j, map, record);
            }
        }

        return result;
    }

    private boolean checkRange(int x, int y) {
        return x < n && x >= 0 && y < m && y >= 0;
    }
}
