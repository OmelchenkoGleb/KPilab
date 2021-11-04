import static java.lang.Math.*;

public class ex {
    double g = 3;
    double k = 4;
    public double function (double x) { return pow(x,3)+x;}
    public double integral (double a, double b) {return (pow(b,4)/4+pow(b,2)/2)-(pow(a,4)/4+pow(a,2)/2);}
    public double pohidna  (double x) {return 3*pow(x,2)+1;}

    ex() {
        print();
        printEx1();
        print();
        printEx2();
        print();
        printEx3();
        print();
    }
    public void print(){
        for (int i = 0; i<100; i++){
            System.out.print("-");
        }
        System.out.println("");
    }
    // 1 задание


    public void printEx1(){
        double x = g - 2*k - 2;
        System.out.println("Послідовних приростів: " + poslodprirost(x,1,0,0.001)  + "  Порівняння с точним: " + abs(poslodprirost(x,1,0,0.001) - pohidna(x)));
        System.out.println("Центрована різниця: " + centrriznuci(x,1,0,0.001)  + "  Порівняння с точним: " + abs(centrriznuci(x,1,0,0.001) - pohidna(x)));
    }
    public double poslodprirost(double x, int k, double res, double e){
        if (k == 1){
            double d = (function(x+pow(0.5,k))-function(x))/pow(0.5,k);
            k++;
            return poslodprirost(x,k,d,e);
        } else {
            double d = (function(x+pow(0.5,k))-function(x))/pow(0.5,k);
            if (abs(d-res)<e){
                return  d;
            } else {
                k++;
                return poslodprirost(x,k,d,e);
            }
        }
    }

    public double centrriznuci(double x, int k, double res, double e){
        if (k == 1){
            double d = (function(x+pow(0.5,k))-function(x-pow(0.5,k)))/(2.0*pow(0.5,k));
            k++;
            return centrriznuci(x,k,d,e);
        } else {
            double d = (function(x+pow(0.5,k))-function(x-pow(0.5,k)))/(2.0*pow(0.5,k));
            if (abs(d-res)<e){
                return  d;
            } else {
                k++;
                return centrriznuci(x,k,d,e);
            }
        }
    }

    // 2 задание

    public void printEx2(){
        double x = g - 2*k - 2;
        double [] X = { g - 2*k - 3, g - 2*k - 1.5, g - 2*k};
        double [] Y = { function(X[0]), function(X[1]), function(X[2])};
        System.out.println("Квадратна інтерполяція: " + kvadratinter(x, X, Y)  + "  Порівняння с точним: " + abs(kvadratinter(x, X, Y) - pohidna(x)));
    }

    public double kvadratinter(double x, double [] X, double [] Y){
        double dy0 = Y[1] - Y[0];
        double dy1 = Y[2] - Y[1];
        double d2y = dy1 - dy0;
        double h = 1;
        double q = (x-X[0])/h;
        return 1/h*(dy0+((2*q-1)/2)*d2y);
    }

    // 3 задание


    public void printEx3(){
        int n = 5;
        double b = g - 2*k;
        double a = g - 2*k - 3;
        double h = (b-a)/n;
        System.out.println("Лівие прямокутники: "+runge(a,n,1,0,h, 0.001,"Лівих прямокутників") + "  Порівняння с точним: " + abs(runge(a,n,1,0,h,0.001,"Лівих прямокутників")-integral(a,b)) );
        System.out.println("Трапеций: "+runge(a,n,1,0,h, 0.001,"Трапецій") + "  Порівняння с точним: " + abs(runge(a,n,1,0,h, 0.001,"Трапецій")-integral(a,b)));
        System.out.println("Симпсона: "+runge(a,n,1,0,h, 0.001,"Сімпсона") + "  Порівняння с точним: " + abs(runge(a,n,1,0,h, 0.001,"Сімпсона")-integral(a,b)));
    }

    public double runge (double a, int n, int count, double integ, double h, double e, String str) {
        double [] x = new double[n+1];
        for (int i=0; i<=n ; i++){
            x[i] = a + i*h;
        }
        if (count == 1) {
            double res = metod(x,n,h,str);
            count++;
            return runge(a, 2*n, count, res, h / 2, e,str);
        } else {
            double res = metod(x,n,h,str);
            count++;
            if (abs(res - integ) <= e) {
                return res;
            } else return runge(a, 2*n, count, res, h / 2, e,str);
        }
    }

    public double metod (double [] x, int n, double h, String str) {
        switch (str){
            case "Лівих прямокутників":
                return livuhpramokytnukiv(x,n,h);
            case "Трапецій":
                return trapecii(x,n,h);
            case "Сімпсона":
                return simpsona(x,n,h);
            default:
                System.out.println("Вы ввели неправильное название метода либо такого метода не предусмотрено !");
                return 0;
        }
    }

    public double livuhpramokytnukiv (double [] x, int n, double h) {
        double sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += function(x[i - 1]);
        }
        double res = h * sum;
        return res;
    }

    public double trapecii (double [] x, int n, double h){
        double sum = 0;
        for (int i = 1; i <= n - 1; i++) {
            sum += function(x[i]);
        }
        sum += (function(x[0]) + function(x[n])) / 2;
        double res = h * sum;
        return res;
    }

    public double simpsona (double [] x, int n, double h){
        double sum2x = 0, sum4x = 0;
        for (int i = 1; i < n; i += 2) {
            sum4x += function(x[i]);
        }
        for (int i = 2; i < n - 1; i += 2) {
            sum2x += function(x[i]);
        }
        double res = h / 3 * (function(x[0]) + function(x[n]) + 2 * sum2x + 4 * sum4x);
        return res;
    }


}

