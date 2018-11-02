package sortAndSearch;
//PeaksandValleys:In an array of integers, a"peak" is an element which is greater than or equal to the adjacent integers
//and a "valley" is an element which is less than or equal to the adjacent integers.
//For example, in the array {5, 8, 6, 2, 3, 4, 6}, {8, 6} are peaks and {5, 2} are valleys.
//Given an array of integers, sort the array into an alternating sequence of peaks and valleys.
//EXAMPLE
//Input: {5, 3, 1, 2, 3}
//Output: {5, 1, 3, 2, 3}

// this implement assume that the peak is the first element of the array
import java.util.Arrays;

public class PeaksAndValleys {

    public static void main(String[] args) {
        int[] input = {5, 1, 4, 3, 5, 9, 7, 4, 13};
        PeaksAndValleys peaksAndValleys = new PeaksAndValleys();
        peaksAndValleys.sort(input);
        System.out.println(Arrays.toString(input));
    }


    public void sort(int[] array) {
        optimizeMethod(array);
    }

    private void optimizeMethod(int[] array){
        if(array.length <= 1){
            return;
        }

        if(array[0] < array[1]){
            swap(array, 0, 1);
        }

        for(int i = 1; i < array.length - 2; i += 2){
            if(array[i + 1] >= array[i] && array[i + 1] >= array[i + 2]){
                continue;
            }

            if(array[i] >= array[i + 2]){
                swap(array, i, i + 1);
            }
            else {
                swap(array, i + 1, i + 2);
            }
        }

        if((array.length & 1) == 1){
            if(array[array.length - 2] > array[array.length - 1]){
                swap(array, array.length - 2, array.length - 1);
            }
        }
    }

    private void naiveMethod(int[] array) {
        Arrays.sort(array);
        for (int i = 0; i < array.length - 1; i += 2) {
            swap(array, i, i + 1);
        }
    }

    private void swap(int[] array, int a, int b) {
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}
