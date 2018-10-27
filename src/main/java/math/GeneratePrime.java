package math;

import java.util.ArrayList;
import java.util.Arrays;

public class GeneratePrime {
    public static void main(String[] args) {
        GeneratePrime generatePrime = new GeneratePrime();
        int range = 10000000;
        //System.out.println(generatePrime.naiveMethod(range));
        //System.out.println(generatePrime.sieveOfEratosthenes(range));

        long time = System.nanoTime();
        System.out.println(generatePrime.sieveOfEratosthenes(range).size());
        System.out.println("sieveOfEratosthenes time:" + (System.nanoTime() - time) / 1000 / 1000);

        time = System.nanoTime();
        System.out.println(generatePrime.naiveMethod(range).size());
        System.out.println("naiveMethod time:" + (System.nanoTime() - time) / 1000 / 1000);
    }

    private ArrayList<Integer> naiveMethod(int range) {
        ArrayList<Integer> result = new ArrayList<>(range >> 3);
        for (int i = 2; i < range; i++) {
            if (checkPrime(i)) {
                result.add(i);
            }
        }

        return result;
    }

    private boolean checkPrime(int n) {
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if ((n % i) == 0) {
                return false;
            }
        }

        return true;
    }


    private ArrayList<Integer> sieveOfEratosthenes(int range) {
        boolean[] flags = new boolean[range + 1];
        Arrays.fill(flags, true);

        int prime = 2;
        while (prime <= Math.sqrt(range)) {
            crossOff(flags, prime);
            prime = nextPrime(flags, prime);
        }

        ArrayList<Integer> result = new ArrayList<>(range >> 3);
        for (int i = 2; i <= range; i++) {
            if(flags[i]){
                result.add(i);
            }
        }
        return result;
    }

    private void crossOff(boolean[] flags, int prime){
        /* Cross off remaining multiples of prime. We can start with (prime*prime),
         * because if we have a k * prime, where k < prime, this value would have
         * already been crossed off in a prior iteration. */
        for (int i = prime * prime; i < flags.length; i += prime) {
            flags[i] = false;
        }
    }

    private int nextPrime(boolean[] flags, int cur) {
        int i = cur + 1;
        while (i < flags.length && !flags[i]) {
            i++;
        }

        return i;
    }
}
