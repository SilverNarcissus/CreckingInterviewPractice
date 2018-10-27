package algorithm;

public class PrimePalinfrome {
    public static void main(String[] args) {
        PrimePalinfrome primePalinfrome = new PrimePalinfrome();
        System.out.println(primePalinfrome.primePalindrome(2145));
    }

    public int primePalindrome(int N) {
        if (N <= 2) {
            return 2;
        }
        if (N <= 3) {
            return 3;
        }
        if (N <= 5) {
            return 5;
        }
        if (N <= 7) {
            return 7;
        }
        if (N <= 10) {
            N = 10;
        }

        String s = String.valueOf(N);
        boolean isOdd = (s.length() & 1) == 1;
        int start = (int) Math.pow(10, (s.length() >> 1) - 1);

        System.out.println(start);

        while (true) {
            if (!isOdd) {
                for (int i = start; i < start * 10; i++) {
                    int palindrome = buildPalindromeEven(i);
                    if (palindrome >= N && isPrime(palindrome)) {
                        return palindrome;
                    }
                }
            }


            for (int i = start; i < start * 10; i++) {
                for (int j = 0; j <= 9; j++) {
                    int palindrome = buildPalindromeOdd(i, j);
                    if (palindrome >= N && isPrime(palindrome)) {
                        return palindrome;
                    }
                }
            }
            isOdd = false;
            start *= 10;
        }
    }

    private boolean isPrime(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    private int buildPalindromeEven(int left) {
        int low = 0;
        int high = left;
        while (left > 0) {
            low *= 10;
            low += left % 10;
            left /= 10;
            high *= 10;
        }

        return high + low;
    }

    private int buildPalindromeOdd(int left, int mid) {
        int low = 0;
        int high = left;
        while (left > 0) {
            low *= 10;
            low += left % 10;
            left /= 10;
            high *= 10;
            mid *= 10;
        }

        return high * 10 + mid + low;
    }

}
