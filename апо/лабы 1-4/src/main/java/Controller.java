import java.lang.annotation.Annotation;
import java.lang.reflect.*;
public class Controller implements Command{
    View view = new View();
    Model model = Model.getInstance();


    @Override
    public void execute(Double x, Double y, Double height, Double width) {
        ContextStrategy contextStrategy = new ContextStrategy(new StrategyPoint());
        contextStrategy.ActivateAction(x,y,height,width);
        contextStrategy.strategy = new StrategyRectangular();
        contextStrategy.ActivateAction(x,y,height,width);
    }

    class StrategyPoint implements Strategy {
        @Override
        public void Action(Double x, Double y, Double height, Double width) {
            Point PointObject = model.Point(x,y);
            AnnotationMethodInvoke(PointObject,Point.class);
            ToString(PointObject);
            chekModifierClass(Point.class);
            ListConstructOptions(Point.class);
            ListFieldsNameTypeModifierClass(Point.class);
            NameClass(Point.class);
            ListInterface(Point.class);
            ProxyObject(PointObject);

            System.out.println();
            System.out.println("Iterator: ");
            Iterator iterator = model.getIterator("Point");
            while (iterator.hasnext()){
                System.out.println(iterator.next());
            }
        }
    }
    class StrategyRectangular implements Strategy {
        @Override
        public void Action(Double x, Double y, Double height, Double width) {
            Rectangular RectangularObject = model.Rectangular(x, y, height, width);
            AnnotationMethodInvoke(RectangularObject,Rectangular.class);
            ToString(RectangularObject);
            chekModifierClass(Rectangular.class);
            ListConstructOptions(Rectangular.class);
            ListFieldsNameTypeModifierClass(Rectangular.class);
            NameClass(Rectangular.class);
            ListInterface(Rectangular.class);
            ProxyObject(RectangularObject);

            System.out.println();
            Iterator iterator = model.getIterator("Rectangular");
            while (iterator.hasnext()){
                System.out.println(iterator.next());
            }
        }
    }
    class ContextStrategy{
        Strategy strategy;
        public ContextStrategy(Strategy strategy){
            this.strategy = strategy;
        }
        public void ActivateAction(Double x, Double y, Double height, Double width){
            strategy.Action(x,y,height,width);
        }
    }


    void ToString (Object object){
        view.Task1(object);
    }
    void ProxyObject(Object object){
        Virtual proxy = (Virtual) Proxy.newProxyInstance(object);
        view.Task4(proxy);
    }

    double AnnotationMethodInvoke(Object PointObject, Class clazz){
        Method[] methods = clazz.getDeclaredMethods();
        for(Method method: methods){
            Annotation annotation = method.getAnnotation(AnnotationReflectable.class);
            if (annotation != null && annotation.annotationType() == AnnotationReflectable.class){
                Class<?>[] pType  = method.getParameterTypes();
                Object[] params = new Object[pType.length];
                try {
                    view.Task2((double)method.invoke(PointObject, params));
                    return (double)method.invoke(PointObject, params);
                    //method.invoke(PointObject, params);
                } catch (IllegalAccessException e) {
                    System.out.println("Exception 1");
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    System.out.println("Exception 2");
                    e.printStackTrace();
                }
            }
        }
        return 0;
    }
    String NameClass(Class s){
        view.Task3(s.getName());
        return s.getName();
    }
    StringBuffer ListFieldsNameTypeModifierClass(Class clazz){
        StringBuffer stringBuffer = new StringBuffer();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            stringBuffer.append(field.getName() + " " + field.getType().getName() + " ");
            if (Modifier.isPublic(field.getModifiers())) {
                stringBuffer.append("public" + " ");
            }
        }
        view.Exercise4(stringBuffer);
        return stringBuffer;
    }
    String chekModifierClass(Class clazz){
        int mods = clazz.getModifiers();
        if (Modifier.isPublic(mods)) {
            view.Exercise6("public");
            return "public";
        }
        if (Modifier.isAbstract(mods)) {
            view.Exercise6("abstract");
            return "abstract";
        }
        if (Modifier.isFinal(mods)) {
            view.Exercise6("final");
            return "final";
        }
        view.Exercise6("");
        return "";
    }
    StringBuffer ListConstructOptions(Class clazz){
        StringBuffer stringBuffer = new StringBuffer();
        Constructor[] constructors = clazz.getConstructors();
        for (Constructor constructor : constructors) {
            System.out.print(constructor.getName() + ": ");
            stringBuffer.append(constructor.getName() + ": ");
            Class[] parameterTypes = constructor.getParameterTypes();
            for (Class parameterType : parameterTypes) {
                stringBuffer.append(parameterType.getName() + ", ");
            }
            System.out.println();
        }
        view.Exercise1(stringBuffer);
        return stringBuffer;
    }
    StringBuffer ListInterface(Class clazz){
        StringBuffer stringBuffer = new StringBuffer();
        Class[] interfaces = clazz.getInterfaces();
        for (Class inter : interfaces) {
            stringBuffer.append(inter.getName() + " ");
        }
        view.Exercise5(stringBuffer);
        return stringBuffer;
    }


}
