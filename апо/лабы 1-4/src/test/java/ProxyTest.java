import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProxyTest {
    Point PointObject = new Point(1.0,1.0);
    Rectangular RectangularObject = new Rectangular(2.0, 2.0, 10, 15);
    static Class reflected = Rectangular.class;
    @Test
    void invoke() {
        Virtual proxy = (Virtual) Proxy.newProxyInstance(PointObject);
        assertEquals("Getter", proxy.getInterface());
        assertNotEquals("Not allowed", proxy.getInterface());
        assertNotEquals("Not allowed", proxy.getInterface());
    }
}