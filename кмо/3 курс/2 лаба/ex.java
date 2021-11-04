import java.util.*;
import static java.lang.Math.*;
public class ex {
    double g = 3 , k =4;
    public double function(double x){return pow(x,2)+2*k*g*x+k;}
    public double task1(double e, int count, double a, double b){
        if (count==1){
             a = -k*g-2;
             b = k*g+1;
             count++;
            System.out.println("["+a+" | "+b+"]");
        }
        double Xm = (a+b)/2;
        double L = b-a;
        double WXm = function(Xm);
        double X1 = a+L/4;
        double X2 = b - L/4;
        double WX1 = function(X1);
        double WX2 = function(X2);
        if (WX1<WX2){
            b = Xm;
            Xm = X1;
            L = b-a;
            System.out.println("["+a+" | "+b+"]");
            if (abs(L)<e){
                System.out.println("["+a+" | "+b+"]");
                return a;
            } else return task1(e,count,a,b);
        } else {
            if (WX2 < WXm) {
                a = Xm;
                Xm = X2;
                L = b - a;
                System.out.println("["+a+" | "+b+"]");
                if (abs(L) < e) {
                    System.out.println("["+a+" | "+b+"]");
                    return a;
                } else return task1(e,count,a,b);
            } else {
                a = X1;
                b = X2;
                System.out.println("["+a+" | "+b+"]");
                if (abs(L)<e){
                    System.out.println("["+a+" | "+b+"]");
                    return a;
                } else return task1(e,count,a,b);
            }
        }
    }
    public void task2(double countpoint){
        double A = -k*g-2;
        double B = k*g+1;
        ArrayList<Double> arrayList = new ArrayList<>();
        HashMap<Double,Double> map = new HashMap<>();
        for (int i=0;i<countpoint;i++) arrayList.add(((Math.random()*(B-A))+A));
        arrayList.forEach(x-> map.put(x,function(x)));
        map.entrySet().stream().sorted(Map.Entry.comparingByValue()).forEach(e->System.out.println("["+e.getKey()+" | "+e.getValue()+"]"));
    }
}
