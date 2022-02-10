import static java.lang.Math.*;
public class ex {
    double g = 3 , k =4;
    public double function(double x, double y){return pow(x,2)+pow((y-g),2)-2*k*g*x + k;}
    public double dfx(double x){return 2*x-24;}
    public double dfy(double y){return 2*y-6;}
    public double task1(double e, double x, double y, double h, int count){
        if (count == 1){x = k*g+2; y = g-3; count++;}
        System.out.println("x = " + x + "| y = " + y);
        double dx = dfx(x);
        double dy = dfy(y);
        double Xnew = x-h*dx;
        double Ynew = y-h*dy;
        dx=dfx(Xnew);
        dy=dfy(Ynew);
        if (function(Xnew,Ynew)<function(x,y)) {
            if ((pow(dx, 2) + pow(dy, 2)) <= e) {
                System.out.println("x = " + Xnew + "| y = " + Ynew);
                System.out.println("Значение функции в этой точке: "+function(Xnew,Ynew));
                return function(Xnew,Ynew);
            } else return task1(e,Xnew,Ynew,h/2,count);
        } else return task1(e,Xnew,Ynew,h/2,count);
    }

    public double task2(double e, double x, double y, double h, int count, int M){
        if (count == 1){x = k*g+2; y = g-3; count++;}
        double ekx = Math.random();
        double eky = Math.random();
        if (ekx>=0.5) ekx = 1; else ekx = -1;
        if (eky>=0.5) eky = 1; else eky = -1;
        double Xnew = x-h*ekx;
        double Ynew = y-h*eky;
        if (function(Xnew,Ynew)>=function(x,y)) {
            if (M==3){
                M=0;
                h = h/2;
                if (h<e){
                    System.out.println("Значение функции: "+function(x,y));
                    System.out.println("Дошли заданой точности!");
                    return 0;
                }else {
                    System.out.println("Не удачный шаг - уменьшим h ("+h+").");
                    return task2(e,x,y,h,count,M);
                }
            } else {
                M++;
                System.out.println("Пробуем новую точку.");
                return task2(e,x,y,h,count,M);
            }
        } else {
            System.out.println("!!! Удачный шаг увеличим h ("+h+").");
            return task2(e,Xnew,Ynew,h*2,count,M);
        }
    }
}
