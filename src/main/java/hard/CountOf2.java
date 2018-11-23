package hard;

import java.util.Random;

public class CountOf2 {
    public static void main(String[] args) {
        Random random = new Random();
        CountOf2 countOf2 = new CountOf2();
        for (int i = 0; i < 10000; i++) {
            int r = random.nextInt(1000000);
            if(countOf2.countOf2(r) != countOf2.bruteForce(r)){
                System.out.println("Error! " + r);
            }
        }
//        int n = 10
//        System.out.println(countOf2.countOf2(n));
//        System.out.println(countOf2.bruteForce(n));
    }

    public int countOf2(int n) {
        return countOf2(String.valueOf(n));
    }

    private int countOf2(String s) {
        if (s.length() == 1) {
            return s.charAt(0) >= '2' ? 1 : 0;
        }

        int result = 0;
        int full = buildFull(s.length() - 1);
        for (char c = '0'; c <= '9'; c++) {
            if (c == s.charAt(0)) {
                break;
            }

            result += full;
            if (c == '2') {
                result += Math.pow(10, s.length() - 1);
            }
        }

        if (s.charAt(0) == '2') {
            result += Integer.parseInt(s.substring(1)) + 1;
        }

        return result + countOf2(s.substring(1));
    }

    private int buildFull(int n) {
        int result = 0;
        int mul = 1;
        while (n != 0) {
            result = 10 * result + mul;
            mul *= 10;
            n--;
        }

        return result;
    }


    public int bruteForce(int n){
        int result = 0;
        for (int i = 1; i <= n; i++) {
            String s = String.valueOf(i);
            for(char c : s.toCharArray()){
                if(c == '2'){
                    result++;
                }
            }
        }

        return result;
    }
}
