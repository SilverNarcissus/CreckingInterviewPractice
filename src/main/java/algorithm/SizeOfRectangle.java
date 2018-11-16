package algorithm;


import java.util.*;

public class SizeOfRectangle {
    public static void main(String[] args) {
        int[][] array = {{1, 2}, {3, 4}};
        SizeOfRectangle sizeOfRectangle = new SizeOfRectangle();
        System.out.println(sizeOfRectangle.minAreaRect(array));
    }

    private Map<Integer, Integer> record = new HashMap<>();
    private int result = Integer.MAX_VALUE;

    public int minAreaRect(int[][] points) {
        Arrays.sort(points, Comparator.comparingInt((int[] o) -> o[0]).thenComparingInt(o -> o[1]));


        List<Integer> level = new ArrayList<>();
        int x = -1;
        for (int[] point : points) {
            if (x == -1) {
                level.add(point[1]);
                x = point[0];
            } else {
                if (x != point[0]) {
                    calculate(level, x);
                    level.clear();
                    level.add(point[1]);
                    x = point[0];
                }
            }
        }

        return result == Integer.MAX_VALUE ? 0 : result;
    }

    private void calculate(List<Integer> points, int x) {
        for (int i = 0; i < points.size(); i++) {
            for (int j = i + 1; j < points.size(); j++) {
                int key = buildKey(points.get(i), points.get(j));
                if (record.containsKey(key)) {
                    int distance = points.get(j) - points.get(i);
                    result = Math.min(result, distance * (x - record.get(key)));
                }
                record.put(key, x);
            }
        }
    }

    private int buildKey(int x, int y) {
        return (x << 16) | y;
    }
}
