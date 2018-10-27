package algorithm;

import java.util.LinkedList;
import java.util.Queue;

public class FindTheCelebrity {
    public static void main(String[] args) {
        FindTheCelebrity findTheCelebrity = new FindTheCelebrity();

        System.out.println(findTheCelebrity.findCelebrity(3));
        System.out.println(findTheCelebrity.findCelebrityStandrad(3));
    }

    int[][] relation = {{0, 1, 1, 0}, {1, 0, 1, 1}, {0, 0, 0, 0}, {1, 1, 1, 0}};

    private boolean know(int a, int b) {
        return relation[a][b] == 1;
    }

    public int findCelebrity(int n) {
        if (n <= 1) {
            return -1;
        }

        Queue<Integer> candidate = new LinkedList<>();
        if ((n & 1) == 1) {
            candidate.offer(n - 1);
        }

        for (int i = 0; i < n - 1; i += 2) {
            if (know(i, i + 1)) {
                candidate.offer(i + 1);
            } else {
                candidate.offer(i);
            }
        }

        while (candidate.size() >= 2) {
            int a = candidate.poll();
            int b = candidate.poll();

            if (know(a, b)) {
                candidate.offer(b);
            } else {
                candidate.offer(a);
            }
        }

        int who = candidate.poll();
        for (int i = 0; i < n; i++) {
            if (i != who) {
                if (!know(i, who) || know(who, i)) {
                    return -1;
                }
            }
        }

        return who;
    }

    public int findCelebrityStandrad(int n) {
        int celebrity = 0;
        for (int i = 1; i < n; i++) {
            if (know(celebrity, i)) celebrity = i;
        }
        for (int i = 0; i < n; i++) {
            if (celebrity != i && know(celebrity, i)) return -1;
            if (celebrity != i && !know(i, celebrity)) return -1;
        }
        return celebrity;
    }

}
