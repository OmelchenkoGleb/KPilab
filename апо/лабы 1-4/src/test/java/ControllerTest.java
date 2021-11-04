import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ControllerTest {

    Controller controller = Mockito.mock(Controller.class);
    Point point = Mockito.mock(Point.class);
    Rectangular rectangular = Mockito.mock(Rectangular.class);

    @Test
    void proxyObject() {
        Mockito.doThrow(new IllegalAccessException()).when(controller).ProxyObject(point);
        controller.ProxyObject(point);
    }

    @Test
    void annotationMethodInvoke() {
        Point point = new Point(1,1);
        Mockito.when(controller.AnnotationMethodInvoke(point,Point.class)).thenReturn(1.);
        assertEquals(controller.AnnotationMethodInvoke(point,Point.class),1.);
        controller.AnnotationMethodInvoke(rectangular,Rectangular.class);
        Mockito.verify(controller).AnnotationMethodInvoke(rectangular,Rectangular.class);
    }

    @Test
    void nameClass() {
        Mockito.when(controller.NameClass(Point.class)).thenReturn("P");
        assertEquals(controller.NameClass(Point.class),"P");
        assertNotEquals(controller.NameClass(Point.class),"R");
        controller.NameClass(Rectangular.class);
        Mockito.verify(controller).NameClass(Rectangular.class);
    }

    @Test
    void listFieldsNameTypeModifierClass() {
        controller.ListFieldsNameTypeModifierClass(Point.class);
        //Mockito.verify(controller).ListFieldsNameTypeModifierClass(Point.class);
    }
}