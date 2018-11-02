package recursionAndDp;

// A magic index in an array A[ 0, n -1] is defined to be an index such that A[ i] = i.
// Given a sorted array of distinct integers, write a method to find a magic index, if one exists, in array A.
// ps : What if the values are not distinct?
public class MagicIndex {
    public static void main(String[] args) {
        MagicIndex magicIndex = new MagicIndex();
        int[] input = {-1, 1, 1, 3, 8};

        System.out.println(magicIndex.withoutDuplicate(input));
        System.out.println(magicIndex.withDuplicate(input));
    }

    public int withoutDuplicate(int[] array) {
        int l = 0;
        int r = array.length - 1;

        while (l <= r) {
            int mid = (l + r) >> 1;
            if (array[mid] == mid) {
                return mid;
            } else if (array[mid] < mid) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return -1;
    }


    public int withDuplicate(int[] array) {
        return helper(array, 0, array.length - 1);
    }

    private int helper(int[] array, int left, int right) {
        if (left > right) {
            return -1;
        }

        int mid = (left + right) >> 1;
        int value = array[mid];

        if (mid == value) {
            return value;
        }

        return Math.max(helper(array, left, Math.min(mid - 1, value)), helper(array, Math.max(mid + 1, value), right));
    }
}
