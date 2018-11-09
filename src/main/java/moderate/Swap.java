package moderate;

import java.util.Arrays;

public class Swap {
    public static void main(String[] args) {
        int[] array = {1, 2, 3};
        swap(array, 1, 2);
        System.out.println(Arrays.toString(array));
    }

    private static void swap(int[] array, int a, int b) {
        array[a] = array[a] + array[b];
        array[b] = array[a] - array[b];
        array[a] = array[a] - array[b];
    }
}
