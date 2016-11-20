import java.lang.Math;
public class Point {
    private int x;
    private int y;

    public Point(int x1, int y1) {
        this.x = x1;
        this.y = y1;
    }

    public static double distance(Point p1, Point p2){
        return Math.sqrt(Math.pow(p2.x-p1.x,2)+Math.pow(p2.y-p1.y,2));
    }
}
