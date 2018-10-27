package algorithm;

import java.util.Random;

public class RabinKarpSubstringSearch {
    private static Random r = new Random();

    public static void main(String[] args) {
        RabinKarpSubstringSearch s = new RabinKarpSubstringSearch();

        for (int i = 0; i < 10000; i++) {

            String s1 = buildString(100000);
            String s2 = buildString(40);

            if (s.search(s1, s2) != s1.indexOf(s2)){
                System.out.println("wrong!");
            }
//            long time = System.nanoTime();
//            System.out.println(s.search(s1, s2));
//            System.out.println("my time:" + (System.nanoTime() - time) / 1000 / 1000);
//
//            time = System.nanoTime();
//            System.out.println(s1.indexOf(s2));
//            System.out.println("sys time:" + (System.nanoTime() - time) / 1000 / 1000);
        }


    }

    private static String buildString(int len){
        char[] arrays = new char[len];
        for(int i = 0; i < len; i++){
            arrays[i] = (char) (r.nextInt(3) + 'a');
        }

        return new String(arrays);
    }

    private static final int BASE = 100000007;

    public int search(String source, String target) {
        if (target.length() > source.length()) {
            return -1;
        }

        long targetHash = hash(target);
        long sourceHash = hash(source.substring(0, target.length()));
        long power = longPow(BASE, target.length());

        for (int i = 0; i <= source.length() - target.length(); i++) {
            if (targetHash == sourceHash) {
                if (source.substring(i, i + target.length()).equals(target)){
                    return i;
                }
                System.out.println("collision!");
            }

            if (i == source.length() - target.length()) {
                break;
            }
            sourceHash = sourceHash * BASE - source.charAt(i) * power + source.charAt(i + target.length());
        }

        return -1;
    }

    private long longPow(long base, int exp) {
        if (exp == 0) {
            return 1;
        }
        if (exp == 1) {
            return base;
        }

        long half = longPow(base, exp >> 1);
        half = half * half;
        return (exp & 1) == 0 ? half : half * base;
    }

    private long hash(String s) {
        int len = s.length();
        long result = 0;

        long power = 1;
        for (int i = len - 1; i >= 0; i--) {
            result += s.charAt(i) * power;
            power *= BASE;
        }

        return result;
    }
}
