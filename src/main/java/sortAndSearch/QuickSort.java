package sortAndSearch;

import java.util.Arrays;
import java.util.Random;

public class QuickSort {

    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();

        for (int i = 0; i < 1; i++) {
            int[] input = quickSort.generator(1000);
            int[] another_input = Arrays.copyOf(input, input.length);
            quickSort.sort(input);
            Arrays.sort(another_input);

            if(!Arrays.equals(input, another_input)){
                System.out.println("error!");
                System.out.println(Arrays.toString(input));
            }
        }

        try {
            System.out.println("in");
            return;
        }
        catch (Exception e){

        }
        finally {
            System.out.println("here");
        }
    }

    private int[] generator(int len){
        int[] result = new int[len];
        Random random = new Random();
        for (int i = 0; i < len; i++) {
            result[i] = random.nextInt();
        }

        return result;
    }

    public void sort(int[] array){
        sort(array, 0, array.length - 1);
    }

    public void sort(int[] array, int left, int right){
        if(left >= right){
            return;
        }

        int mid = partition(array, left, right);
        sort(array, left, mid - 1);
        sort(array, mid + 1, right);
    }

    private int partition(int[] array, int left, int right){
        while(left < right){
            while (array[left] < array[right]){
                left++;
            }

            if(left == right){
                break;
            }
            swap(array, left, right);
            right--;

            while (array[left] < array[right]){
                right--;
            }

            if(left == right){
                break;
            }
            swap(array, left, right);
            left++;
        }

        return left;
    }

    private void swap(int[] array, int a, int b){
        int temp = array[a];
        array[a] = array[b];
        array[b] = temp;
    }
}

