import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class pointTest {
    Point PointObject = new Point(1.0,1.0);
    Rectangular RectangularObject = new Rectangular(2.0, 2.0, 10, 15);
    static Class reflected = Rectangular.class;

    @Test
    void getX() {
        assertEquals(1, PointObject.getX());
        assertNotEquals(2, PointObject.getX());
    }

    @Test
    void getY() {
        assertEquals(1, PointObject.getX());
        assertNotEquals(2, PointObject.getX());
    }

    @Test
    void testToString() {
        assertEquals("point{x=1.0, y=1.0}", PointObject.toString());
        assertNotEquals("poit{x=1.0, y=1.0}", PointObject.toString());
    }

    @Test
    void testEquals() {
        assertEquals(true, PointObject.equals(PointObject));
        assertNotEquals(true, PointObject.equals(RectangularObject));
    }
}