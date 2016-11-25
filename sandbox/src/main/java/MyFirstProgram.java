public class MyFirstProgram {
    public static void main(String[] args) throws Exception {
        Point p1 = new Point(-12,-35);
        Point p2 = new Point(-11,-78);
        System.out.println(Point.distance(p1,p2));
    }
}