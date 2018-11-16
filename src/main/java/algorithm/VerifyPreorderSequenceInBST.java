package algorithm;

import java.util.*;

//Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.
//You may assume each number in the sequence is unique
//Follow up:
//Could you do it using only constant space complexity?
public class VerifyPreorderSequenceInBST {
    static Random r = new Random();

    public static void main(String[] args) {
        VerifyPreorderSequenceInBST verifyPreorderSequenceInBST = new VerifyPreorderSequenceInBST();


        for (int i = 0; i < 10000; i++) {
            int[] preorder = verifyPreorderSequenceInBST.random(10 + r.nextInt(10000));
            if (verifyPreorderSequenceInBST.mySpaceO1(preorder) != verifyPreorderSequenceInBST.verifyPreorder(preorder)) {
                System.out.println(Arrays.toString(preorder));
                System.out.println("Error!");
            }
        }
//        int[] input = {80, 84, 5, 37, 69, 26, 44, 92};
//        System.out.println(verifyPreorderSequenceInBST.mySolution(input));
    }

    private int[] random(int size) {
        Set<Integer> result = new HashSet<>();
        for (int i = 0; i < size; i++) {
            result.add(r.nextInt(100));
        }

        int[] out = new int[result.size()];
        int loc = 0;
        for (int i : result) {
            out[loc++] = i;
        }
        return out;
    }

    public boolean mySpaceO1(int[] sequence){
        int loc = -1;
        int min = Integer.MIN_VALUE;
        for(int i = 0; i < sequence.length; i++){
            int cur = sequence[i];
            if(cur < min){
                return false;
            }

            while(loc >= 0 && cur > sequence[loc]){
                min = sequence[loc--];
            }

            sequence[++loc] = cur;
        }

        return true;
    }

    public boolean mySolution(int[] sequence) {
        return recursive(Integer.MIN_VALUE, Integer.MAX_VALUE, sequence, 0, sequence.length - 1);
    }


    private boolean recursive(int min, int max, int[] sequence, int start, int end) {
        if (start > end) {
            return true;
        }
        if (start == end) {
            int cur = sequence[start];
            return cur > min && cur < max;
        }

        // System.out.println(start + " " + end + " " + min + " " + max);
        int first = sequence[start];
        for (int i = start + 1; i <= end; i++) {
            int cur = sequence[i];
            if(cur < min || cur > max){
                return false;
            }
            if (cur > first) {
                return recursive(min, first, sequence, start + 1, i - 1)
                        && recursive(first, max, sequence, i, end);
            }
        }


        return recursive(min, first, sequence, start + 1, end);
    }


    public boolean verifyPreorder(int[] preorder) {
        Stack<Integer> stk = new Stack<Integer>();
        // 初始化最小值为最小整数
        int min = Integer.MIN_VALUE;
        for (int num : preorder) {
            // 违反最小值限定则是无效的
            if (num < min) return false;
            // 将路径中所有小于当前的数pop出来并更新最小值
            while (!stk.isEmpty() && num > stk.peek()) {
                min = stk.pop();
            }
            // 将当前值push进去
            stk.push(num);
        }
        return true;
    }

}
