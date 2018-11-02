package recursionAndDp;

public class BooleanEvaluation {
    public static void main(String[] args) {
        BooleanEvaluation booleanEvaluation = new BooleanEvaluation();

        System.out.println(booleanEvaluation.getCount("0&0&0&1^1|0"));
    }

    public Result getCount(String expression) {
        int len = (expression.length() >> 1) + 1;
        Result[][] dp = new Result[len][len];
        for (int i = 0; i < len; i++) {
            char bit = getNumber(i, expression);
            if (bit == '0') {
                dp[i][i] = new Result(1, 0);
            } else {
                dp[i][i] = new Result(0, 1);
            }
        }

        return helper(dp, 0, len - 1, expression);
    }

    private Result helper(Result[][] dp, int left, int right, String expression) {
        if (left > right) {
            return new Result();
        }

        if (dp[left][right] != null) {
            return dp[left][right];
        }

        Result cur = new Result();
        for (int i = left; i < right; i++) {
            Result next = calculate(getSymbol(i, expression), helper(dp, left, i, expression), helper(dp, i + 1, right, expression));
            cur.count_0 += next.count_0;
            cur.count_1 += next.count_1;
        }

        dp[left][right] = cur;

        return cur;
    }

    private char getNumber(int loc, String expression) {
        return expression.charAt(loc << 1);
    }

    private char getSymbol(int loc, String expression) {
        return expression.charAt((loc << 1) + 1);
    }

    private Result calculate(char c, Result left, Result right) {
        Result result = new Result();

        switch (c) {
            case '&':
                result.count_0 = left.count_0 * (right.count_0 + right.count_1) + left.count_1 * right.count_0;
                result.count_1 = left.count_1 * right.count_1;
                break;
            case '|':
                result.count_0 = left.count_0 * right.count_0;
                result.count_1 = left.count_1 * (right.count_0 + right.count_1) + left.count_0 * right.count_1;
                break;
            case '^':
                result.count_0 = left.count_0 * right.count_0 + left.count_1 * right.count_1;
                result.count_1 = left.count_0 * right.count_1 + left.count_1 * right.count_0;
                break;
        }

        return result;
    }
}

class Result {
    int count_0 = 0;
    int count_1 = 0;


    public Result() {
    }

    public Result(int count_0, int count_1) {
        this.count_0 = count_0;
        this.count_1 = count_1;
    }

    @Override
    public String toString() {
        return "Result{" +
                "count_0=" + count_0 +
                ", count_1=" + count_1 +
                '}';
    }
}
