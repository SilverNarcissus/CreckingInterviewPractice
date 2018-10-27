package algorithm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TicTacToe {
    private static final int MAX_CALL_TIME = 10000000;
    private int functionCallTimes = 0;
    private int[][] board = new int[3][3];
    private static final int X = 1;
    private static final int O = 2;
    private char[] map = {' ', 'X', 'O'};
    private List<int[]> aiStepTrace = new ArrayList<int[]>();

    public static void main(String[] args) {
        TicTacToe game = new TicTacToe();
        Scanner s = new Scanner(System.in);
        // ai play
        game.aiPlays();
        // game.playWithAi(X);
    }

    public void aiPlays() {
        ai_X(false, true);
        for (int i = aiStepTrace.size() - 1; i >= 0; i--) {
            int[] cur = aiStepTrace.get(i);
            System.out.println("ai " + map[cur[2]] + " choose to move at " + cur[0] + " " + cur[1]);
            move(cur[0], cur[1], cur[2]);
            printBoard();

            if (win(X)) {
                System.out.println(map[X] + " wins!");
                System.exit(0);
            }

            if (win(O)) {
                System.out.println(map[O] + " wins!");
                System.exit(0);
            }

            if (draw()) {
                System.out.println("Draw!");
                System.exit(0);
            }
        }
    }

    public int playWithAi(int who) {
        Scanner s = new Scanner(System.in);

        while (true) {
            functionCallTimes = 0;
            if (who == X) {
                int x = s.nextInt();
                int y = s.nextInt();
                if (move(x, y, X)) {
                    printBoard();
                    if (win(X)) {
                        System.out.println(map[X] + " wins!");
                        System.exit(0);
                    }

                    if (draw()) {
                        System.out.println("Draw!");
                        System.exit(0);
                    }

                    ai_O(true, false);
                    if(functionCallTimes > MAX_CALL_TIME){
                        System.out.println("max call reached!");
                    }
                    if (win(O)) {
                        System.out.println(map[O] + " wins!");
                        System.exit(0);
                    }

                    if (draw()) {
                        System.out.println("Draw!");
                        System.exit(0);
                    }
                } else {
                    System.out.println("Invalid move!");
                }
            } else {
                ai_X(true, false);
                if(functionCallTimes > MAX_CALL_TIME){
                    System.out.println("max call reached!");
                }
                if (win(X)) {
                    System.out.println(map[X] + " wins!");
                    System.exit(0);
                }

                if (draw()) {
                    System.out.println("Draw!");
                    System.exit(0);
                }

                while (true) {
                    int x = s.nextInt();
                    int y = s.nextInt();
                    if (move(x, y, O)) {
                        printBoard();
                        if (win(O)) {
                            System.out.println(map[O] + " wins!");
                            System.exit(0);
                        }

                        if (draw()) {
                            System.out.println("Draw!");
                            System.exit(0);
                        }
                        break;
                    } else {
                        System.out.println("Invalid move!");
                    }
                }
            }
        }
    }

    // want to get less score
    private int ai_O(boolean doMove, boolean isAiPlay) {
        functionCallTimes++;
        //调用次数减枝
        if(functionCallTimes > MAX_CALL_TIME){
            return -1;
        }

        if (win(X)) {
            return 1;
        }

        if (win(O)) {
            return -1;
        }

        if (draw()) {
            return 0;
        }

        int result = 2;
        int x = -1, y = -1;
        List<int[]> step = new ArrayList<int[]>();
        ALREADY_FOUND:
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != 0) {
                    continue;
                }

                board[i][j] = O;
                int score = ai_X(false, isAiPlay);
                board[i][j] = 0;

                if (score < result) {
                    step = new ArrayList<int[]>(aiStepTrace);
                    result = score;
                    x = i;
                    y = j;
                    // 已有最优解
                    if (result == -1){
                        break ALREADY_FOUND;
                    }
                }

            }
        }

        if (doMove) {
            System.out.println("ai O choose to move at " + (x + 1) + " " + (y + 1));
            System.out.println("score: " + result);
            move(x + 1, y + 1, O);
            printBoard();
        }

        if (isAiPlay) {
            step.add(new int[]{x + 1, y + 1, O});
            aiStepTrace = step;
        }


        return result;
    }

    // want to get more score
    private int ai_X(boolean doMove, boolean isAiPlay) {
        functionCallTimes++;

        //调用次数减枝
        if(functionCallTimes > MAX_CALL_TIME){
            return 1;
        }

        if (win(X)) {
            return 1;
        }

        if (win(O)) {
            return -1;
        }

        if (draw()) {
            return 0;
        }


        int result = -2;
        int x = -1, y = -1;
        List<int[]> step = new ArrayList<int[]>();
        ALREADY_FOUND:
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] != 0) {
                    continue;
                }

                board[i][j] = X;
                int score = ai_O(false, isAiPlay);
                board[i][j] = 0;

                if (score > result) {
                    step = new ArrayList<int[]>(aiStepTrace);
                    result = score;
                    x = i;
                    y = j;
                    if (result == 1){
                        break ALREADY_FOUND;
                    }
                }
            }
        }

        if (doMove) {
            System.out.println("ai X choose to move at " + (x + 1) + " " + (y + 1));
            System.out.println("score: " + result);
            move(x + 1, y + 1, X);
            printBoard();
        }

        if (isAiPlay) {
            step.add(new int[]{x + 1, y + 1, X});
            aiStepTrace = step;
        }


        return result;
    }


    private boolean draw() {
        for (int[] row : board) {
            for (int i : row) {
                if (i == 0) {
                    return false;
                }
            }
        }

        return true;
    }

    private boolean win(int who) {
        for (int i = 0; i < board[0].length - 2; i++) {
            for (int j = 0; j < board.length; j++) {
                if (board[j][i] == who && board[j][i + 1] == who && board[j][i + 2] == who) {
                    return true;
                }
            }
        }

        for (int i = 0; i < board.length - 2; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (board[i][j] == who && board[i + 1][j] == who && board[i + 2][j] == who) {
                    return true;
                }
            }
        }

        for (int i = 0; i < board.length - 2; i++) {
            for (int j = 0; j < board[0].length - 2; j++) {
                if (board[i][j] == who && board[i + 1][j + 1] == who && board[i + 2][j + 2] == who) {
                    return true;
                }
            }
        }

        for (int i = 0; i < board.length - 2; i++) {
            for (int j = board[0].length - 1; j > 1; j--) {
                if (board[i][j] == who && board[i + 1][j - 1] == who && board[i + 2][j - 2] == who) {
                    return true;
                }
            }
        }

        return false;
    }


    private boolean move(int x, int y, int who) {
        x--;
        y--;
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] != 0) {
            return false;
        }

        board[x][y] = who;
        return true;
    }

    private void printBoard() {
        for (int[] row : board) {
            StringBuilder sb = new StringBuilder();
            StringBuilder sep = new StringBuilder();
            for (int i : row) {
                sb.append(map[i]);
                sb.append(" | ");
                sep.append("——  ");
            }

            System.out.println(sb.substring(0, sb.length() - 2));
            System.out.println(sep);
        }
    }
}
