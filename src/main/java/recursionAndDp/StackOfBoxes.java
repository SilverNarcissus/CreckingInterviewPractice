package recursionAndDp;
//Stack of Boxes: You have a stack of n boxes, with widths wi, heights hi, and depths di.
// The boxes cannot be rotated and can only be stacked on top of one another \
// if each box in the stack is strictly larger than the box above it in width, height, and depth.

// Implement a method to compute the height of the tallest possible stack. The height of a stack is the sum of the heights of each box.

import java.util.*;

public class StackOfBoxes {
    public static void main(String[] args) {
        StackOfBoxes stackOfBoxes = new StackOfBoxes();
        int[] widths = {1, 2, 3};
        int[] heights = {1, 2, 3};
        int[] depths = {1, 2, 2};

        System.out.println(stackOfBoxes.solve(widths, heights, depths));
    }


    public int solve(int[] widths, int[] heights, int[] depths) {
        List<Box> boxes = new ArrayList<>(widths.length);
        for (int i = 0; i < widths.length; i++) {
            boxes.add(new Box(widths[i], heights[i], depths[i]));
        }
        boxes.sort(new Comparator<Box>() {
            @Override
            public int compare(Box o1, Box o2) {
                return Integer.compare(o1.h, o2.h);
            }
        }.reversed());


        int[] dp = new int[widths.length];
        dp[0] = boxes.get(0).h;

        for (int i = 1; i < boxes.size(); i++) {
            Box cur = boxes.get(i);
            dp[i] = cur.h;
            for (int j = 0; j < i; j++) {
                Box below = boxes.get(j);
                if (cur.canAbove(below)) {
                    dp[i] = Math.max(dp[i], cur.h + dp[j]);
                }
            }
        }

        int result = 0;
        for (int i : dp) {
            result = Math.max(result, i);
        }

        return result;
    }

}

class Box {
    int w;
    int h;
    int d;

    public Box(int w, int h, int d) {
        this.w = w;
        this.h = h;
        this.d = d;
    }

    public boolean canAbove(Box below) {
        return w < below.w && h < below.h && d < below.d;
    }
}
