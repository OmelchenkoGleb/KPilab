import static org.junit.jupiter.api.Assertions.*;

class MainTest {
    Point PointObject = new Point(1.0,1.0);
    Rectangular RectangularObject = new Rectangular(2.0, 2.0, 10, 15);
    static Class reflected = Rectangular.class;
    static Class reflectedSuperclass = reflected.getSuperclass();
    Controller controller = new Controller();
    @org.junit.jupiter.api.Test
    void annotationMethodInvoke() {
         assertEquals(controller.AnnotationMethodInvoke(PointObject, reflectedSuperclass), PointObject.getX());
         assertNotEquals(controller.AnnotationMethodInvoke(PointObject, reflectedSuperclass), 0);
    }

    @org.junit.jupiter.api.Test
    void nameClass() {
         assertEquals(controller.NameClass(reflected), "Rectangular");
         assertNotEquals(controller.NameClass(reflectedSuperclass), "Rectangular");
    }

    @org.junit.jupiter.api.Test
    void listFieldsNameTypeModifierClass() {
        assertEquals(controller.ListFieldsNameTypeModifierClass(reflected).toString(), "height double public width double public ");
        assertNotEquals(controller.ListFieldsNameTypeModifierClass(reflectedSuperclass).toString(), "Rectangular");
    }

    @org.junit.jupiter.api.Test
    void chekModifierClass() {
         assertEquals(controller.chekModifierClass(reflected).toString(), "public");
         assertNotEquals(controller.chekModifierClass(reflectedSuperclass).toString(), "private");
    }

    @org.junit.jupiter.api.Test
    void listConstructOptions() {
        assertNotEquals(controller.ListConstructOptions(reflectedSuperclass).toString(), "Point: double, double, point:");
        assertNotEquals(controller.ListConstructOptions(reflected).toString(), "Virtual");
    }

    @org.junit.jupiter.api.Test
    void listInterface() {
         assertEquals(controller.ListInterface(reflected).toString(), "Virtual ");
         assertNotEquals(controller.ListInterface(reflected).toString(), "Virtual");
    }
}