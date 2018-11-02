package sortAndSearch;
//RankfromStream:Imagineyouarereadinginastreamofintegers.
//Periodically,youwishtobeable to look up the rank of a numberx (the number of values less than or equal tox).
// lmplement the data structures and algorithms to support these operations.
// That is, implement the method track (int x), which is called when each number is generated,
// and the method getRankOfNumber(int x), which returns the number of values less than or equal tox (not includingx itself).
// EXAMPLE
// Stream (in order of appearance): 5, getRankOfNumber(l) 0 getRankOfNumber(3) = 1 getRankOfNumber(4) 3

public class RankFromStream {
    public static void main(String[] args) {
        RankFromStream rankFromStream = new RankFromStream();
        int[] stream = {5, 1, 4, 4, 5, 9, 7, 13, 3};
        for(int i : stream){
            rankFromStream.track(i);
        }

        System.out.println(rankFromStream.getRankOfNumber(1));
        System.out.println(rankFromStream.getRankOfNumber(3));
        System.out.println(rankFromStream.getRankOfNumber(4));
        System.out.println(rankFromStream.getRankOfNumber(13));
    }
    RankNode root;

    public void track(int x) {
        if (root == null) {
            root = new RankNode(x);
        } else {
            root.insert(x);
        }
    }

    public int getRankOfNumber(int x) {
        return getRank(x, root);
    }

    private int getRank(int val, RankNode cur) {
        if (cur == null) {
            return 0;
        }

        if (val == cur.val) {
            return cur.leftCount;
        }

        if (val < cur.val) {
            return getRank(val, cur.left);
        }

        return cur.leftCount + 1 + getRank(val, cur.right);
    }
}

class RankNode {
    int val;
    RankNode left;
    RankNode right;
    int leftCount = 0;

    public RankNode(int val) {
        this.val = val;
    }

    void insert(int val) {
        if (val <= this.val) {
            leftCount++;
            if (left == null) {
                left = new RankNode(val);
            } else {
                left.insert(val);
            }
        } else {
            if (right == null) {
                right = new RankNode(val);
            } else {
                right.insert(val);
            }
        }
    }


}
