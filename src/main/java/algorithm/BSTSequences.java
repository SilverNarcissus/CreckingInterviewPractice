package algorithm;

import util.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;


public class BSTSequences {
    private int[][] record = new int[1001][1001];

    public static void main(String[] args) {
        LinkedList<Integer> a = new LinkedList<>();
        a.add(1);
        a.add(2);
        a.add(3);
        a.add(4);
        a.add(5);
//        for (int i = 0; i < 2; i++) {
//            a.add(0);
//        }

        LinkedList<Integer> b = new LinkedList<>();
        b.add(10);
        b.add(11);
        b.add(12);
        b.add(13);
        b.add(14);
        b.add(15);
        b.add(16);
        b.add(17);
//        for (int i = 0; i < 2; i++) {
//            b.add(0);
//        }

        List<LinkedList<Integer>> result = new ArrayList<>();
        BSTSequences bstSequences = new BSTSequences();
        bstSequences.recursiveWave(a, b, result, new LinkedList<>());
        System.out.println(result.size());

        System.out.println(bstSequences.countWave(5, 16));

        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(0);
        root.left.left = new TreeNode(-1);
        root.left.right = new TreeNode(1);
        root.right = new TreeNode(5);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(4);

        result = bstSequences.getSequence(root);
        HashSet<LinkedList<Integer>> s = new HashSet<>();
        for (LinkedList<Integer> l : result) {
            if (!s.add(l)) {
                System.out.println("error!!!");
            }
        }

        System.out.println(result);
        System.out.println(result.size());
    }

    public List<LinkedList<Integer>> getSequence(TreeNode root) {
        return helper(root);
    }

    private List<LinkedList<Integer>> helper(TreeNode root) {
        if (root == null) {
            List<LinkedList<Integer>> result = new ArrayList<>();
            result.add(new LinkedList<>());
            return result;
        }

        List<LinkedList<Integer>> result = wave(helper(root.left), helper(root.right));
        for (LinkedList<Integer> list : result) {
            list.addFirst(root.val);
        }

        return result;
    }

    private List<LinkedList<Integer>> wave(List<LinkedList<Integer>> left, List<LinkedList<Integer>> right) {
        List<LinkedList<Integer>> result = new ArrayList<>();
        for (LinkedList<Integer> l : left) {
            for (LinkedList<Integer> r : right) {
                List<LinkedList<Integer>> cur_result = new ArrayList<>();
                recursiveWave(l, r, cur_result, new LinkedList<>());
                result.addAll(cur_result);
            }
        }

        return result;
    }

    private void recursiveWave(LinkedList<Integer> first, LinkedList<Integer> second, List<LinkedList<Integer>> result, LinkedList<Integer> cur) {
        if (first.size() == 0 || second.size() == 0) {
            LinkedList<Integer> now = new LinkedList<>(cur);
            now.addAll(first);
            now.addAll(second);
            result.add(new LinkedList<>(now));
            return;
        }

        int firstElement = first.removeFirst();
        cur.addLast(firstElement);
        recursiveWave(first, second, result, cur);
        cur.removeLast();
        first.addFirst(firstElement);

        int secondElement = second.removeFirst();
        cur.addLast(secondElement);
        recursiveWave(first, second, result, cur);
        cur.removeLast();
        second.addFirst(secondElement);
    }

    private int countWave(int a, int b) {
        if (a == 0 || b == 0) {
            return 1;
        }

        if (record[a][b] == 0) {
            record[a][b] = countWave(a, b - 1) + countWave(a - 1, b);
        }

        return record[a][b];
    }
}
