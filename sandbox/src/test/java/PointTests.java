import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by kshyniakov on 25.11.2016.
 */
public class PointTests {

    @Test
    public void positiveValues()
    {
        Point p1 = new Point(12,35);
        Point p2 = new Point(11,78);
        Assert.assertEquals(Point.distance(p1,p2),43.01162633521314);
    }

    @Test
    public void negativeValues()
    {
        Point p1 = new Point(-12,-35);
        Point p2 = new Point(-11,-78);
        Assert.assertEquals(Point.distance(p1,p2),43.01162633521314);
    }
}
