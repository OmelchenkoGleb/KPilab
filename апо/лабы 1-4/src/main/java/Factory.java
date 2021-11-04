public class Factory {
    public Virtual create(String name, Double x, Double y, Double height, Double width){
        switch (name){
            case "Point": return new Point(x,y);
            case "Rectangular": return new Rectangular(x,y,height,width);
            default: return null;
        }
    }
}
