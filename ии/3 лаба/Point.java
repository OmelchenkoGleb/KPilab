import static java.lang.Math.pow;

public class Point {
    double x;
    double y;
    String doubleBitsStr;
    public Point(double x) {
        this.x = x;
        this.y = function(x);
        this.doubleBitsStr = Long.toBinaryString(Double.doubleToLongBits(x));
    }
    public double function(double x){return -pow(x,Math.cos(5*x));}
    //иницилизация начальной популяции
    public double getX() {
        return x;
    }
    public void setX(double x) {
        this.x = x;
    }
    public double getY() {
        return y;
    }
    public void setY() {
        this.y = function(x);
    }
    public String getDoubleBitsStr() {
        return doubleBitsStr;
    }
    public void setDoubleBitsStr(String doubleBitsStr) {
        this.doubleBitsStr = doubleBitsStr;
    }
    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
