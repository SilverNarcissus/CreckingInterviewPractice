package algorithm;

import java.util.*;

public class ShortestPathGetAllKeys {
    public static void main(String[] args) {
        ShortestPathGetAllKeys shortestPathGetAllKeys = new ShortestPathGetAllKeys();
        UsingDijkstra usingDijkstra = new UsingDijkstra();
        String[] grid = {"@.a.#", "###.#", "b.A.B"};
        System.out.println(shortestPathGetAllKeys.shortestPathAllKeys(grid));
        System.out.println(usingDijkstra.shortestPathAllKeys(grid));

    }

    private int count = 0;
    private int x_start;
    private int y_start;
    private int n;
    private int m;


    public int shortestPathAllKeys(String[] grid) {
        n = grid.length;
        m = grid[0].length();
        countKey(grid);

        boolean[][][] dp = new boolean[n][m][1 << count];
        return bfs(dp, grid, (1 << count) - 1);
    }

    private int bfs(boolean[][][] dp, String[] grid, int target) {
        Queue<Integer> x_list = new LinkedList<>();
        Queue<Integer> y_list = new LinkedList<>();
        Queue<Integer> key_list = new LinkedList<>();
        int step = 0;
        x_list.offer(x_start);
        y_list.offer(y_start);
        key_list.offer(0);

        while (!x_list.isEmpty()) {
            int size = x_list.size();
            for (int i = 0; i < size; i++) {
                int x = x_list.poll();
                int y = y_list.poll();
                int key = key_list.poll();
                if (check(x, y, key, dp, grid, x_list, y_list, key_list) == target) {
                    return step;
                }
            }
            step++;
        }

        return -1;
    }

    private int check(int x, int y, int keys, boolean[][][] dp, String[] grid, Queue<Integer> x_list, Queue<Integer> y_list, Queue<Integer> key_list) {
        if (!checkRange(x, y)) {
            return -1;
        }

        char cur = grid[x].charAt(y);
        keys = getKey(keys, cur);
        if (dp[x][y][keys]) {
            return -1;
        }


        if (!canEnter(keys, cur)) {
            return -1;
        }

        dp[x][y][keys] = true;

        x_list.offer(x + 1);
        y_list.offer(y);
        key_list.offer(keys);

        x_list.offer(x - 1);
        y_list.offer(y);
        key_list.offer(keys);

        x_list.offer(x);
        y_list.offer(y + 1);
        key_list.offer(keys);

        x_list.offer(x);
        y_list.offer(y - 1);
        key_list.offer(keys);

        return keys;
    }

    private boolean canEnter(int keys, char c) {
        if (c == '#') {
            return false;
        }

        if (c < 'A' || c > 'Z') {
            return true;
        }


        return (keys & (1 << (c - 'A'))) > 0;
    }

    private int getKey(int keys, char c) {
        if (c < 'a' || c > 'z') {
            return keys;
        }

        return keys | (1 << (c - 'a'));
    }

    private boolean checkRange(int x, int y) {
        if (x < 0 || x >= n || y < 0 || y >= m) {
            return false;
        }

        return true;
    }

    private void countKey(String[] grid) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char cur = grid[i].charAt(j);
                if (cur == '@') {
                    x_start = i;
                    y_start = j;
                }
                if (cur >= 'a' && cur <= 'z') {
                    count++;
                }
            }
        }
    }
}

class UsingDijkstra {
    private int n;
    private int m;
    private Map<Character, Point> location = new HashMap<>();
    private String[] grid;


    public int shortestPathAllKeys(String[] grid) {
        n = grid.length;
        m = grid[0].length();
        this.grid = grid;

        // build vertex
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char cur = getLoc(i, j);
                if (cur != '.' && cur != '#') {
                    location.put(cur, new Point(i, j));
                }
            }
        }

        // build edge
        Map<Character, Map<Character, Integer>> graph = new HashMap<>();
        for (Map.Entry<Character, Point> v : location.entrySet()) {
            graph.put(v.getKey(), bfsSearch(v.getKey()));
        }
        int target = (1 << (graph.size() >> 1)) - 1;
        //System.out.println(graph);

        // dijkstra
        PriorityQueue<NodeWithDistance> priorityQueue = new PriorityQueue<>();
        Node startNode = new Node('@', 0);
        priorityQueue.add(new NodeWithDistance(0, startNode));
        Set<Node> found = new HashSet<>();

        while (!priorityQueue.isEmpty()) {
            NodeWithDistance nodeWithDistance = priorityQueue.poll();
            Node cur = nodeWithDistance.node;
            if (!found.add(cur)) {
                continue;
            }

            if (cur.key == target) {
                return nodeWithDistance.distance;
            }

            for (Map.Entry<Character, Integer> next : graph.get(cur.source).entrySet()) {
                char next_source = next.getKey();
                if (tryTheLock(cur.key, next_source)) {
                    int new_key = getKey(cur.key, next_source);
                    priorityQueue.offer(new NodeWithDistance(nodeWithDistance.distance + next.getValue()
                            , new Node(next_source, new_key)));
                }
            }
        }


        return -1;
    }


    private Map<Character, Integer> bfsSearch(char source) {
        int x = location.get(source).x;
        int y = location.get(source).y;
        //System.out.println("~~~~~~~~~~~`");
        boolean[][] seen = new boolean[n][m];


        Queue<Point> points = new LinkedList<>();
        points.offer(new Point(x, y));
        Map<Character, Integer> destination = new HashMap<>();
        int distance = 0;
        while (!points.isEmpty()) {
            int size = points.size();
            for (int i = 0; i < size; i++) {
                Point p = points.poll();
                char cur = getLoc(p.x, p.y);
                if (seen[p.x][p.y] || cur == '#') {
                    continue;
                }

                seen[p.x][p.y] = true;
                if (cur != '.' && cur != source) {
                    destination.put(cur, distance);
                    continue;
                }

                if (checkRange(p.x + 1, p.y)) {
                    points.offer(new Point(p.x + 1, p.y));
                }
                if (checkRange(p.x - 1, p.y)) {
                    points.offer(new Point(p.x - 1, p.y));
                }
                if (checkRange(p.x, p.y + 1)) {
                    points.offer(new Point(p.x, p.y + 1));
                }
                if (checkRange(p.x, p.y - 1)) {
                    points.offer(new Point(p.x, p.y - 1));
                }

            }

            distance++;
        }
        //System.out.println("~~~~~~~~~~~`");


        return destination;
    }

    private char getLoc(int x, int y) {
        return grid[x].charAt(y);
    }

    private boolean checkRange(int x, int y) {
        return x >= 0 && x < n && y >= 0 && y < m;
    }

    private boolean tryTheLock(int keys, char c) {
        if (c < 'A' || c > 'Z') {
            return true;
        }


        return (keys & (1 << (c - 'A'))) > 0;
    }

    private int getKey(int keys, char c) {
        if (c < 'a' || c > 'z') {
            return keys;
        }

        return keys | (1 << (c - 'a'));
    }
}

class NodeWithDistance implements Comparable<NodeWithDistance> {
    int distance;
    Node node;

    public NodeWithDistance(int distance, Node node) {
        this.distance = distance;
        this.node = node;
    }

    @Override
    public int compareTo(NodeWithDistance o) {
        return distance < o.distance ? -1 : 1;
    }
}

class Node {
    char source;
    int key;

    public Node(char source, int key) {
        this.source = source;
        this.key = key;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return source == node.source &&
                key == node.key;
    }

    @Override
    public int hashCode() {
        return Objects.hash(source, key);
    }

}

class Point {
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    int x;
    int y;
}