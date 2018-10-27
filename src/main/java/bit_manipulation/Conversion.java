package bit_manipulation;

//Write a function to determine the number of bits you would need to flip to convert integer A to integer B.
//        EXAMPLE
//        Input: 29 (or: 11101), 15 (or: 01111) Output: 2
public class Conversion {
    public static void main(String[] args) {
        Conversion conversion = new Conversion();
        System.out.println(conversion.bitSwapRequired(Integer.valueOf("00010", 2), Integer.valueOf("11101", 2)));
    }

    public int bitSwapRequired(int a, int b) {
        int diff = a ^ b;
        int result = 0;
        while (diff != 0) {
            result++;
            diff = diff & (diff - 1);
        }

        return result;
    }
}
