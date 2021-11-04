import java.util.ArrayList;

public class Model implements Container{
    ArrayList<Point> ListPoint= new ArrayList<>();
    ArrayList<Rectangular> ListRectangular= new ArrayList<>();
    private static Model instance;
    Factory factory = new Factory();

    private Model(){}
    public static Model getInstance(){
        return (instance == null) ? (instance = new Model()) : instance;
    }

    class ModelPoint{

    }
    public Point Point(double x, double y){
        Point point = (Point) factory.create("Point", x,y,1.,1.);
        ListPoint.add(point);
        return point;
    }
    public Rectangular Rectangular(double x, double y, double height, double width){
        Rectangular rectangular = (Rectangular) factory.create("Rectangular",x,y,height,width);
        ListRectangular.add(rectangular);
        return rectangular;
    }
    @Override
    public Iterator getIterator(String name) {
        if (name == "Point") {
            return new ArrayListPointIterator();
        } else return new ArrayListRectangularIterator();
    }
    class ArrayListPointIterator implements Iterator{
        int index;
        @Override
        public boolean hasnext() {
            return index < ListPoint.size();
        }
        @Override
        public Object next() {
            if (hasnext()){
                return ListPoint.get(index++);
            }
            return null;
        }
    }
    class ArrayListRectangularIterator implements Iterator{
        int index;
        @Override
        public boolean hasnext() {
            return index < ListRectangular.size();
        }
        @Override
        public Object next() {
            if (hasnext()){
                return ListRectangular.get(index++);
            }
            return null;
        }
    }
}
