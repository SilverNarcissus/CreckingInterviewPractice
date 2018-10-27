package bit_manipulation;
//Insertion: You are given two 32-bit numbers, N and M, and two bit positions, i and j.
// Write a method to insert M into N such that M starts at bit j and ends at bit i.
// You can assume that the bits j through i have enough space to fit all of M.
// That is, if M = 10011, you can assume that there are at least 5 bits between j and i.
// You would not, for example, have j = 3 and i = 2, because M could not fully fit between bit 3 and bit 2.
// EXAMPLE
// Input: N 10000000000, M 10011, i 2, j 6 Output:N = 10001001100
public class Insertion {
    public static void main(String[] args) {
        Insertion insertion = new Insertion();
        int N = Integer.valueOf("11111111111", 2);
        int M = Integer.valueOf("1", 2);

        System.out.println(Integer.toBinaryString(insertion.insert(N, M, 2, 7)));
    }

    public int insert(int N, int M, int i, int j){
        return (N & buildMask(i, j)) | (M << i);
    }

    private int buildMask(int i, int j){
        j++;
        int left = -1 << j;
        int right = (1 << i) - 1;
        System.out.println(Integer.toBinaryString(left | right));
        return left | right;
    }
}
