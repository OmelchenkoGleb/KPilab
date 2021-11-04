import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class rectangularTest {
    Point PointObject = new Point(1.0,1.0);
    Rectangular RectangularObject = new Rectangular(2.0, 2.0, 10, 15);
    static Class reflected = Rectangular.class;
    @Test
    void getHeight() {
        assertEquals(10, RectangularObject.height);
        assertNotEquals(1, RectangularObject.height);
    }

    @Test
    void getWidth() {
        assertEquals(15, RectangularObject.width);
        assertNotEquals(1, RectangularObject.width);
    }

    @Test
    void testToString() {
        assertEquals("rectangular{x=2.0, y=2.0, height=10.0, width=15.0}", RectangularObject.toString());
        assertNotEquals("rectangular{=2.0, y=2.0, height=10.0, width=15.0}", RectangularObject.toString());
    }

    @Test
    void testEquals() {
        assertEquals(true, RectangularObject.equals(RectangularObject));
        assertNotEquals(true, RectangularObject.equals(PointObject));
    }

}