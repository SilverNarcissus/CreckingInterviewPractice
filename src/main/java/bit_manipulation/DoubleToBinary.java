package bit_manipulation;
//Binary to String: Given a real number between 0 and 1 (e.g., 0.72) that is passed in as a double, print the binary representation.
// If the number cannot be represented accurately in binary with at most 32 characters, print"ERROR:'
public class DoubleToBinary {
    public static void main(String[] args) {
        DoubleToBinary doubleToBinary = new DoubleToBinary();
        System.out.println(doubleToBinary.toBinary(0.6875));
    }

    private String toBinary(double num){
        if(num >= 1 || num <= 0){
            return "ERROR";
        }

        StringBuilder result = new StringBuilder();
        result.append("0.");
        while(num > 0){
            if(result.length() > 32){
                return "ERROR";
            }

            num = num * 2;

            if(num >= 1){
                result.append('1');
                num -= 1;
            }
            else {
                result.append('0');
            }
        }

        return result.toString();
    }
}
