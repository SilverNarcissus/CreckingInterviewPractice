package algorithm;


import util.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class PathWithSum {
    private Map<Integer, Integer> sum = new HashMap();

    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(1);
        root.left.right.right = new TreeNode(2);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);

        root.right = new TreeNode(-3);
        root.right.right = new TreeNode(11);

        PathWithSum pathWithSum = new PathWithSum();
        System.out.println(pathWithSum.countSum(root, 11));
    }


    private int countSum(TreeNode root, int target) {
        if (root == null) {
            return 0;
        }
        sum.put(0, 1);
        return recursive(root, target, 0);
    }


    private int recursive(TreeNode root, int target, int curSum) {
        if (root == null) {
            return 0;
        }

        curSum = curSum + root.val;

        int result = 0;
        if (sum.containsKey(curSum - target)) {
            System.out.println("here!" + curSum + " " + target + " " + sum.get(curSum - target) + " " + root.val);
            result += sum.get(curSum - target);
        }
        sum.put(curSum, sum.getOrDefault(curSum, 0) + 1);
        result += recursive(root.left, target, curSum);
        result += recursive(root.right, target, curSum);
        sum.put(curSum, sum.get(curSum) - 1);


        return result;
    }
}

