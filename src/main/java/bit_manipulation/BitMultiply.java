package bit_manipulation;

public class BitMultiply {
    public static void main(String[] args) {
        BitMultiply bitMultiply = new BitMultiply();
        System.out.println(bitMultiply.mul(2145, 5));
        System.out.println(2145 * 5);
        System.out.println(bitMultiply.halfMul(5, 2145));
    }

    public int mul(int a, int b) {
        int result = 0;
        for (int i = 0; i < 32; i++) {
            if (((b >> i) & 1) == 1) {
                result += (a << i);
            }
        }

        return result;
    }

    public int halfMul(int a, int b){
        int small = Math.min(a, b);
        int big = Math.max(a, b);
        if(small == 0){
            return 0;
        }

        int half = halfMul(small >> 1, big);
        if((small & 1) == 1){
            return half + half + big;
        }

        return half + half;
    }
}
