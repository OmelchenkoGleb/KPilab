import java.util.Objects;

public class Rectangular extends Point implements Virtual{
    public double height;
    public double width;

    public Rectangular(double x, double y, double height, double width) {
        super(x, y);
        this.height = height;
        this.width = width;
    }

    public Rectangular(double height, double width) {
        super();
        this.height = height;
        this.width = width;
    }

    public Rectangular() {
        super();
        this.height = 0;
        this.width = 0;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        System.out.println(height);
        this.height = height;
    }

    @AnnotationReflectable(name = "Вывод ширины")
    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }


    @Override
    public String toString() {
        return "rectangular{" +
                "x=" + x +
                ", y=" + y +
                ", height=" + height +
                ", width=" + width +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Rectangular that = (Rectangular) o;
        return Double.compare(that.height, height) == 0 && Double.compare(that.width, width) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), height, width);
    }


    @Override
    public void moving(double x, double y) {
        this.x += x;
        this.y += y;
        System.out.println("moving");
    }
}
