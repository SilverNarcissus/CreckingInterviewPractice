package bit_manipulation;
//Write a program to swap odd and even bits in an integer with as few instructions as
//        possible (e.g., bit 0 and bit 1 are swapped, bit 2 and bit 3 are swapped, and so on).
public class PairwiseSwap {
    public static void main(String[] args) {
        PairwiseSwap swap = new PairwiseSwap();
        System.out.println(Integer.toBinaryString((-1) >>> 30));

        System.out.println(Integer.toBinaryString(swap.swap(Integer.valueOf("0110011001", 2))));
    }

    public int swap(int i){
        int odd = i << 1;
        int even = i >>> 1;

        int odd_mask = 0xaaaaaaaa;
        int even_mask = 0x55555555;

        return (odd & odd_mask) | (even & even_mask);
    }
}
