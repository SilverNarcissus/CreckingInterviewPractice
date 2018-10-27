package bit_manipulation;

public class NextNumber {
    public static void main(String[] args) {
        NextNumber nextNumber = new NextNumber();
        int N = Integer.valueOf("11011001111100", 2);
        int M = Integer.valueOf("10011110000011", 2);

        System.out.println(Integer.toBinaryString(nextNumber.getNext(N)));
        System.out.println(Integer.toBinaryString(nextNumber.getPrev(M)));

    }

    public int getNext(int now) {
        boolean isTrailing = true;
        int p;
        int count_1 = 0;
        for (p = 0; p < 32; p++) {
            if (((now >> p) & 1) == 0){
                if(!isTrailing){
                    break;
                }
            }
            else{
                count_1++;
                isTrailing = false;
            }
        }
        //System.out.println(p + " " + count_1);
        int result = (now | (1 << p)) & (-1 << p);
        //System.out.println(Integer.toBinaryString(result));
        int right = (1 << (count_1 - 1)) - 1;
        return result | right;
    }

    public int getPrev(int now) {
        boolean isTrailing = true;
        int p;
        int count_1 = 0;
        for (p = 0; p < 32; p++) {
            if (((now >> p) & 1) == 1){
                count_1++;
                if(!isTrailing){
                    break;
                }
            }
            else{
                isTrailing = false;
            }
        }


        int result = now & (-1 << (p + 1));
        int mask = (1 << p) - 1;
        int right = ~((1 << (p - count_1)) - 1);
        return (result | mask) & right;
    }
}
