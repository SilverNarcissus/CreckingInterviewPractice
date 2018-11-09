package moderate;

import java.util.Objects;

public class Line {
    boolean isVertical;
    double slope;
    double b;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;
        return isVertical == line.isVertical &&
                Double.compare(line.slope, slope) == 0 &&
                Double.compare(line.b, b) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(isVertical, slope, b);
    }

    public Line(Point p1, Point p2) {
        if (p1.equals(p2)) {
            throw new IllegalArgumentException("two points are identical");
        }
        if (p1.y == p2.y) {
            isVertical = true;
        } else {
            slope = (double) ((p1.y - p2.y)) / (p1.x - p2.x);
            b = p1.y / (slope * p1.x);
        }
    }


}

class Point {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x &&
                y == point.y;
    }

    @Override
    public int hashCode() {

        return Objects.hash(x, y);
    }

    public Point(int x, int y) {

        this.x = x;
        this.y = y;
    }

    int x;
    int y;
}
