import java.util.ArrayList;

import static java.lang.Math.*;

public class ex {
    static double g = 3, k = 4;
     double dx1dt(double t,  double x1,  double x2) { return k*t + x1 - x2 + g; }
     double dx2dt(double t,  double x1,  double x2) { return -x1 + k*x2; }
     double[] KvPohibka(double x, double y, double t, double max_t, double h1, double h2, String name) {
        double[] res = new double[3];
        double[] res2 = new double[3];
        double max_iter, d = 0, dd = 0;
        double iter1 = (max_t - t) / h1;
        double iter2 = (max_t - t) / h2;
        max_iter = Math.min(iter1, iter2);
        if (h1<h2){
            double k = h1;
            h1 = h2;
            h2 = k;
        }
        ArrayList<Double> arrayListH1X = new ArrayList<>((int) max_iter);
        ArrayList<Double> arrayListH1Y = new ArrayList<>((int) max_iter);
        ArrayList<Double> arrayListH2X = new ArrayList<>((int) max_iter);
        ArrayList<Double> arrayListH2Y = new ArrayList<>((int) max_iter);
        for (int i = 0; i <= max_iter; i++) {
            if (i==0){
                switch (name) {
                    case "rungeKutta" -> {
                        res = rungeKutta(x, y, t, h1);
                        res2 = rungeKutta(x, y, t, h2);
                        arrayListH1X.add(res[0]);
                        arrayListH1Y.add(res[1]);
                        arrayListH2X.add(res2[0]);
                        arrayListH2Y.add(res2[1]);
                    }
                    case "Euler" -> {
                        res = Euler(x, y, t, h1);
                        res2 = Euler(x, y, t, h2);
                        arrayListH1X.add(res[0]);
                        arrayListH1Y.add(res[1]);
                        arrayListH2X.add(res2[0]);
                        arrayListH2Y.add(res2[1]);
                    }
                }

            } else {
                switch (name) {
                    case "rungeKutta" -> {
                        res = rungeKutta(res[0], res[1], res[2] + h1, h1);
                        res2 = rungeKutta(res2[0], res2[1], res2[2] + h2, h2);
                        while ((float) res2[2] != (float) res[2]) {
                            res2 = rungeKutta(res2[0], res2[1], res2[2] + h2, h2);
                        }
                        arrayListH1X.add(res[0]);
                        arrayListH1Y.add(res[1]);
                        arrayListH2X.add(res2[0]);
                        arrayListH2Y.add(res2[1]);
                    }
                    case "Euler" -> {
                        res = Euler(res[0], res[1], res[2] + h1, h1);
                        res2 = Euler(res2[0], res2[1], res2[2] + h2, h2);
                        while ((float) res2[2] != (float) res[2]) {
                            res2 = Euler(res2[0], res2[1], res2[2] + h2, h2);
                        }
                        arrayListH1X.add(res[0]);
                        arrayListH1Y.add(res[1]);
                        arrayListH2X.add(res2[0]);
                        arrayListH2Y.add(res2[1]);
                    }
                }
            }
        }

        for (int i=0; i<arrayListH1X.size(); i++){
            d += pow((arrayListH1Y.get(i)-arrayListH2Y.get(i)),2);
            dd += pow((arrayListH1X.get(i)-arrayListH2X.get(i)),2);
            d = max(d,dd);
        }
        if (sqrt((d/(arrayListH1X.size()-1)))<=0.1) return res2;
        else return KvPohibka(x, y, t, max_t, h1/2, h2/2, name);
    }

     double[] rungeKutta(double x, double y, double t, double h){
        double[] res = new double[3];
        double[] k1 = new double[2];
        double[] k2 = new double[2];
        double[] k3 = new double[2];
        double[] k4 = new double[2];
        double x2 = x;
        double y2 = y;

        k1[0] = h*dx1dt(t, x2, y2);
        k1[1] = h*dx2dt(t, x2, y2);

        k2[0] = h*dx1dt(t + 0.5*h, x2 + 0.5*k1[0], y2 + 0.5*k1[1]);
        k2[1] = h*dx2dt(t + 0.5*h, x2 + 0.5*k1[0], y2 + 0.5*k1[1]);

        k3[0] = h*dx1dt(t + 0.5*h, x2 + 0.5*k2[0], y2 + 0.5*k2[1]);
        k3[1] = h*dx2dt(t + 0.5*h, x2 + 0.5*k2[0], y2 + 0.5*k2[1]);

        k4[0] = h*dx1dt(t + h    , x2 +     k3[0] ,y2 +     k3[1]);
        k4[1] = h*dx2dt(t + h    , x2 +     k3[0] ,y2 +     k3[1]);

        x = x2 + (k1[0] + 2 * k2[0] + 2 * k3[0] + k4[0]) / 6;
        y = y2 + (k1[1] + 2 * k2[1] + 2 * k3[1] + k4[1]) / 6;

        res[0] = x;
        res[1] = y;
        res[2] = t;
        return res;
    }

     double[] Euler (double x, double y, double t, double h) {
        double[] res = new double[3];
        double x2 = x;
        double y2 = y;


        x = x2 + h * dx1dt(t,x2,y2);
        y = y2 + h * dx2dt(t,x2,y2);

        res[0] = x;
        res[1] = y;
        res[2] = t;
        return res;
    }
}
