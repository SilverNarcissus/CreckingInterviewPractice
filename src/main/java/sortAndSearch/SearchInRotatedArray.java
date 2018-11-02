package sortAndSearch;

import java.util.Arrays;

public class SearchInRotatedArray {
    public static void main(String[] args) {
        int[] array = {15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14};
        SearchInRotatedArray searchInRotatedArray = new SearchInRotatedArray();
        System.out.println(searchInRotatedArray.searchWithDup(array, 5));
    }

    public int search(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            int mid = (left + right) >> 1;

            if (array[mid] > array[left]) {
                if (array[mid] >= target && array[left] <= target) {
                    return normalBin(array, left, mid, target);
                } else {
                    left = mid + 1;
                }
            } else {
                if (array[mid] <= target && array[right] >= target) {
                    return normalBin(array, mid, right, target);
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }


    private int normalBin(int[] array, int left, int right, int target) {
        while (left <= right) {
            int mid = (left + right) >> 1;

            if (array[mid] == target) {
                return mid;
            }

            if (array[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }

    public int searchWithDup(int[] array, int target) {
        int left = 0;
        int right = array.length - 1;

        return bin(array, left, right, target);
    }

    private int bin(int[] array, int left, int right, int target) {
        if (left > right) {
            return -1;
        }

        int mid = (left + right) >> 1;
        if (array[mid] == target) {
            return mid;
        }
        if (array[mid] > array[left]) {
            if (array[mid] > target && array[left] <= target) {
                return normalBin(array, left, mid, target);
            } else {
                return bin(array, mid + 1, right, target);
            }
        } else if (array[mid] < array[right]) {
            if (array[mid] < target && array[right] >= target) {
                return normalBin(array, mid, right, target);
            } else {
                return bin(array, left, mid - 1, target);
            }
        }
        if (array[mid] == array[left]) {
            if (array[mid] == array[right]) {
                return bin(array, left, mid - 1, target) > 0 ? bin(array, left, mid - 1, target) : bin(array, mid + 1, right, target);
            }
            return bin(array, mid + 1, right, target);
        }

        return bin(array, left, mid - 1, target) > 0 ? bin(array, left, mid - 1, target) : bin(array, mid + 1, right, target);
    }
}