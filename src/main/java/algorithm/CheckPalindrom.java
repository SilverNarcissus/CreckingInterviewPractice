package algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CheckPalindrom {
    private int bitRecord = 0;

    public static void main(String[] args) {
        CheckPalindrom checkPalindrom = new CheckPalindrom();
        List<String> input = checkPalindrom.buildPermutation("abcdegf");

        System.out.println(input.size());

        //System.out.println(checkPalindrom.canBePalindrom("gabfcde"));
        for(String s : input) {
//            System.out.println(s);
            if(checkPalindrom.canBePalindrom(s)){
                System.out.println(s);
            }
            //System.out.println(checkPalindrom.canBePalindrom(s));
        }
    }

    public boolean canBePalindrom(String s) {
        bitRecord = 0;
        for(char c : s.toCharArray()){
            setBit(c);
        }

        return (bitRecord & (bitRecord - 1)) == 0;
    }

    private void setBit(char c) {
        if (c >= 'a' && c <= 'z') {
            int loc = 1 << (c - 'a');
            bitRecord = bitRecord ^ loc;
        }
    }

    private List<String> buildPermutation(String s){
        char[] str = s.toCharArray();
        Arrays.sort(str);
        List<String> result = new ArrayList<>();
        permutationHelper(0, str, result);

        return result;
    }

    private void permutationHelper(int curLoc, char[] str, List<String> store){
        if(curLoc == str.length){
            store.add(new String(str));
        }

        for(int i = curLoc; i < str.length; i++){
            if(i != curLoc && str[i] == str[i - 1]){
                continue;
            }
            swap(str, curLoc, i);
            permutationHelper(curLoc + 1, str, store);
            swap(str, curLoc, i);
        }
    }

    private void swap(char[] str, int a, int b){
        char temp = str[a];
        str[a] = str[b];
        str[b] = temp;
    }
}

