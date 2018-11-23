package algorithm;

public class FractionAddition {
    public static void main(String[] args) {
        FractionAddition fractionAddition = new FractionAddition();
        String out = fractionAddition.fractionAddition("-5/2+10/3+7/9");
        System.out.println(out);
    }

    public String fractionAddition(String expression) {
        if (expression.isEmpty()) {
            return "0 / 1";
        }

        expression = expression.replaceAll("\\+", " +").replaceAll("-", " -");
        String[] parts = expression.split(" ");
        Fraction result = new Fraction(0, 1);
        for (String part : parts) {
            if (part.isEmpty()){
                continue;
            }
            result = result.add(buildFraction(part));
        }

        return result.toString();
    }

    private Fraction buildFraction(String expression) {
        String[] part = expression.split("/");
        return new Fraction(Integer.parseInt(part[0]), Integer.parseInt(part[1]));
    }
}


class Fraction {
    int numerator;
    int denominator;

    public Fraction(int up, int down) {
        if (up == 0) {
            numerator = up;
            denominator = 1;
        } else {
            int gcd = Math.abs(gcd(Math.min(up, down), Math.max(up, down)));
            numerator = up / gcd;
            denominator = down / gcd;
        }
    }

    public Fraction add(Fraction another) {
        return new Fraction(numerator * another.denominator + another.numerator * denominator,
                denominator * another.denominator);
    }

    private int gcd(int small, int big) {
        return big % small == 0 ? small : gcd(big % small, small);
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }
}