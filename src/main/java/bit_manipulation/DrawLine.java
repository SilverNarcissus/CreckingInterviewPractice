package bit_manipulation;

//Draw Line: A monochrome screen is stored as a single array of bytes, allowing eight consecutive pixels to be stored in one byte.
// The screen has width w, where w is divisible by 8 (that is, no byte will be split across rows).
// The height of the screen, of course, can be derived from the length of the array and the width.
// Implement a function that draws a horizontal line from (xl, y) to ( x2, y).
//        The method signature should look something like:
//        drawline(byte[] screen, int width, int xl, int x2, int y)
public class DrawLine {
    public static void main(String[] args) {
        Screen screen = new Screen(32, 2);
        screen.drawline(4, 6, 1);
        //screen.shadowN(0, 2, true);
        screen.print();
    }
}


class Screen {
    private byte[] screen;
    private int width;
    private int height;

    public Screen(int width, int height) {
        if (width % 8 != 0) {
            throw new IllegalArgumentException("width should be divisible by 8 but is: " + width);
        }
        this.width = width;
        this.height = height;

        screen = new byte[(width >> 3) * height];
    }

    public void drawline(int x1, int x2, int y) {
        int start = getCell(x1, y);
        int end = getCell(x2, y);

        if(start == end){
            shadowRange(7 - (x1 % 8), 7 - (x2 % 8), start);
            return;
        }

        shadowN(start, 7 - (x1 % 8), false);
        for (int i = start + 1; i < end; i++) {
            shadowN(i, 7, false);
        }
        shadowN(end, 7 - (x2 % 8) - 1, true);
    }

    private void shadowRange(int x, int y, int loc){
        int mask = ((1 << (x + 1)) - 1) ^ ((1 << (y)) - 1);
        screen[loc] = (byte) (screen[loc] | (byte) mask);
    }

    private int getCell(int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) {
            throw new IllegalArgumentException("x, y out of bounds! x: " + x + " y: " + y);
        }

        return y * (width >> 3) + (x >> 3);
    }

    public void shadowN(int loc, int n, boolean reverse) {
        if (n >= 8) {
            throw new IllegalArgumentException("shadow out of bounds! n: " + n);
        }

        int mask = (1 << (n + 1)) - 1;
        if(reverse){
            mask = ~ mask;
        }

        screen[loc] = (byte) (screen[loc] | (byte) mask);
    }

    public void print() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int j = 0; j < height; j++) {
            for (int i = 0; i < width >> 3; i++) {
                stringBuilder.append("(");
                byte cell = screen[j * (width >> 3) + i];
                for (int k = 7; k >= 0; k--) {
                    if (((cell >>> k) & 1) == 0) {
                        stringBuilder.append('0');
                    }
                    else {
                        stringBuilder.append('1');
                    }
                }
                stringBuilder.append(")");
            }
            stringBuilder.append("\n");
        }


        System.out.println(stringBuilder.toString());
    }
}