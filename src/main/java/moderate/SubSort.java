package moderate;

//  Given an array of integers, write a method to find indices m and n such that
//  if you sorted elements m through n, the entire array would be sorted.
//  find the smallest such sequence
//  EXAMPLE
//  input:1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19
//  Output: (3, 9)

import java.util.Arrays;

public class SubSort {
    public static void main(String[] args) {
        SubSort subSort = new SubSort();
        int[] input = {1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19};
        System.out.println(Arrays.toString(subSort.subSort(input)));
    }

    public int[] subSort(int[] array) {
        int n = array.length;

        int left = n - 1;
        int min = array[n - 1];
        for (int i = n - 1; i >= 0; i--) {
            min = Math.min(array[i], min);
            if (min < array[i]) {
                left = i;
            }
        }

        int right = 0;
        int max = array[0];
        for (int i = 0; i < n; i++) {
            max = Math.max(array[i], max);
            if (max > array[i]) {
                right = i;
            }
        }

        int[] out = {left, right};
        return out;
    }
}
