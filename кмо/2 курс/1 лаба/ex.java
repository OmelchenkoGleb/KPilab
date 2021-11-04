import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Math.*;

public class ex {
    double g = 3;
    double k = 4;
    double [] x = new double[4];
    double [] y = new double[4];
    double [] X = new double[3];
    double [][] res;
    ex() throws FileNotFoundException {
        ex1();
        ex2();
        ex3();
        ex4();
        ex5();
        ex6();
    }
    void ex1(){

        System.out.println("Ex1:");
        x[0] = g-2*k-3;
        x[1] = g-2*k-1;
        x[2] = g-2*k;
        x[3] = g-2*k+2;
        for (int i=0; i<4; i++){
            y[i] = sin(x[i]*PI/180) + x[i];
            System.out.println("X="+x[i]+" Y="+y[i]);
        }
        X[0] = g-2*k-2.8;
        X[1] = g-2*k-0.5;
        X[2] = g-2*k+2.7;
        for (int i=0; i<3; i++) System.out.println("X: "+X[i]+", ");
        printLagrange(y);
        printNewton(y);
    }
    void ex2() {
        x[0] = g-2*k-3;
        x[1] = g-2*k-1;
        x[2] = g-2*k;
        x[3] = g-2*k+2;
        for (int i=0; i<4; i++){
            y[i] = sin(x[i]*PI/180) + x[i];
        }
        double [] xCheb = new double[x.length];
        double [] yCheb = new double[x.length];
        System.out.println();
        System.out.println("Ex2:");
        double a,b;
        a = g-2*k-3;
        b = g-2*k + 2;
        System.out.println("a = "+a+" "+"b = "+b);
        xCheb = ChebeshevMinMax(a,b,xCheb,yCheb);
        X[0] = g-2*k-2.8;
        X[1] = g-2*k-0.5;
        X[2] = g-2*k+2.7;
        for (int i=0; i<3; i++) System.out.println("X: "+X[i]+", ");
        printChebeshev(xCheb,yCheb);
    }
    void ex3(){
        System.out.println();
        System.out.println("Ex3:");
        x[0] = g-2*k-2;
        x[1] = g-2*k-1;
        x[2] = g-2*k;
        x[3] = g-2*k+1;
        for (int i=0; i<4; i++){
            y[i] = sin(x[i]*PI/180) + x[i];
            System.out.println("X="+x[i]+" Y="+y[i]);
        }
        X[0] = g-2*k-1.7;
        X[1] = g-2*k-0.5;
        X[2] = g-2*k + 1.8;
        for (int i=0; i<3; i++) System.out.println("X: "+X[i]+", ");
        printNewton_2(y);
    }
    void ex4(){
        System.out.println();
        System.out.println("Ex4:");
        x[0] = g-2*k-3;
        x[1] = g-2*k-1;
        x[2] = g-2*k;
        x[3] = g-2*k+2;
        for (int i=0; i<4; i++){
            y[i] = sin(x[i]*PI/180) + x[i];
            System.out.println("X="+x[i]+" Y="+y[i]);
        }
        X[0] = g-2*k-1.6;
        X[1] = g-2*k-0.5;
        X[2] = g-2*k + 0.9;
        for (int i=0; i<3; i++) System.out.println("X: "+X[i]+", ");
        double [] splain = Splain(x,y,X);
        printSplain(splain);
    }
    void ex5(){
        x[0] = g-2*k-2;
        x[1] = g-2*k-1;
        x[2] = g-2*k;
        x[3] = g-2*k+1;
        for (int i=0; i<4; i++){
            y[i] = sin(x[i]*PI/180) + x[i];
        }
        double [][] xx = new double[4][2];
        double [][] yy = new double[4][1];
        System.out.println();
        System.out.println("Ex5:");
        xx[0][0] = 1;
        xx[1][0] = 1;
        xx[2][0] = 1;
        xx[3][0] = 1;
        xx[0][1] = g-2*k-2;
        xx[1][1] = g-2*k-1;
        xx[2][1] = g-2*k;
        xx[3][1] = g-2*k+1;
        for (int i=0; i<4; i++){
            yy[i][0] = sin(xx[i][1] *PI/180) + xx[i][1];
            System.out.println("X="+xx[i][1]+" Y="+yy[i][0]);
        }

        X[0] = g-2*k-2.8;
        X[1] = g-2*k-0.5;
        X[2] = g-2*k+2.7;
        for (int i=0; i<3; i++) System.out.println("X: "+X[i]+", ");
        printMinKv(xx,yy);
    }
    void ex6() throws FileNotFoundException {
        List<double[]> XX = new ArrayList<double[]>();
        List<double[]> YY = new ArrayList<double[]>();
        System.out.println();
        System.out.println("Ex6:");
        File file = new File("C:\\Users\\Gleb\\IdeaProjects\\untitled1\\src\\main\\java\\Данные.txt");
        Scanner scanner = new Scanner(file);
        while (scanner.hasNextLine()) {
            String s = scanner.nextLine();
            s = s.replaceAll(",", ".");
            s = s.replaceAll("-", "");
            String[] arr = s.split("\t");
            XX.add(new double[] {1,Double.parseDouble(arr[1]), Double.parseDouble(arr[2]), Double.parseDouble(arr[3])});
            YY.add(new double[] {Double.parseDouble(arr[4])});
        }
        scanner.close();

        double[][] xx = new double[XX.size()][4];
        double[][] yy = new double[YY.size()][1];
        for (int i = 0; i != XX.size(); i++) {
            for (int j = 0 ; j<4; j++) {
                xx[i][j] = XX.get(i)[j];
            }
        }
        for (int i = 0; i != XX.size(); i++) {
            for (int j = 0 ; j<1; j++) {
                yy[i][j] = YY.get(i)[j];
            }
        }

        printMinKv1(xx,yy);
    }

    void printLagrange (double[] yy) {
        System.out.println("LagrangeInterpolation:");
        for (int i=0; i<X.length; i++){
            double result = LagrangeInterpolation(x,y,X[i]);
            yy[i] = sin(X[i]*PI/180) + X[i];
            System.out.println("Lagrange: "+result+ " Function: "+yy[i] + " Difference: "+ (abs(yy[i])-abs(result)));
        }
    }
    void printNewton(double[] yy) {
        for (int i=0; i<4; i++){
            y[i] = sin(x[i]*PI/180) + x[i];
        }
        System.out.println("NewtonInterpolation:");
        for (int i=0; i<X.length; i++){
            double result = Newton(X[i]);
            yy[i] = sin(X[i]*PI/180) + X[i];
            System.out.println("Newton: "+result+ " Function: "+yy[i] + " Difference: "+ (abs(yy[i])-abs(result)));
        }
    }
    void printChebeshev(double [] xCheb, double[] yCheb) {
        System.out.println("ChebeshevInterpolation:");
        for (int i=0; i<X.length; i++){
            double result1 = LagrangeInterpolation(xCheb,yCheb,X[i]);
            double result2 = LagrangeInterpolation(x,y,X[i]);
            y[i] = sin(X[i]*PI/180) + X[i];
            System.out.println("Chebeshev: "+result1+ " Точность: "+(abs(y[i])-abs(result1)) + " Lagrange: "+result2 +" Точность: "+(abs(y[i])-abs(result2)) + " Difference: "+ (abs(result1)-abs(result2))+" Разница точностей: "+ (abs(abs(y[i])-abs(result2))-abs(abs(y[i])-abs(result1))));
        }
    }
    void printNewton_2(double[] yy) {
        System.out.println("NewtonInterpolation 2:");
        for (int i=0; i<X.length; i++){
            double result = Newton_2(x,y,X[i]);
            yy[i] = sin(X[i]*PI/180) + X[i];
            System.out.println("Newton 2: "+ result+ " Function: "+yy[i] + " Difference: "+ (abs(y[i])-abs(result)));
        }
    }
    void printSplain(double [] splain) {
        System.out.println("LinalSplain:");
        for (int i=0; i<X.length; i++){
            y[i] = sin(X[i]*PI/180) + X[i];
            System.out.println("Splain: "+splain[i]+ " Function: "+y[i] + " Difference: "+ (abs(y[i])-abs(splain[i])));
        }
    }
    double [][] printMinKv(double [][] xx,double[][] yy) {
        System.out.println("MinKv:");
        double Function , Newton2;
        res = MinKv(xx,yy);
        for (int i=0; i<X.length; i++){
            Function = sin(X[i]*PI/180) + X[i];
            Newton2 = Newton_2(x,y,X[i]);
            System.out.println("MinKv: "+ (res[0][0] + res[1][0]*X[i]) + " Function: "+ Function + " Difference: "+ (abs(Function)-abs((res[0][0] + res[1][0]*X[i]))) + " Newton2: "+ Newton2+ " Function: "+ Function + " Difference: "+ (abs(Function)-abs(Newton2))+ " Разница : "+ (abs(Newton2)-abs((res[0][0] + res[1][0]*X[i]))));
        }
        return res;
    }
    void printMinKv1(double [][] xx,double[][] yy) {
//        for (int i = 0; i < xx.length; i++) {
//            for (int j = 0; j < xx[i].length; j++) {
//                System.out.print(xx[i][j] + "\t");
//            }
//            System.out.println();
//        }
//        for (int i = 0; i < yy.length; i++) {
//            for (int j = 0; j < yy[i].length; j++) {
//                System.out.print(yy[i][j] + "\t");
//            }
//            System.out.println();
//        }
        System.out.println("MinKv2:");
        double [][] result = MinKv(xx,yy);
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[i].length; j++) {
                System.out.print(result[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("MinKv: "+ (result[0][0] + result[1][0]*xx[0][1] + result[2][0]*xx[0][2] + result[3][0]*xx[0][3]));

    }
    double LagrangeInterpolation(double [] x, double [] y , double X){
        double result = 0.0;
        for (int i=0; i<y.length; i++) {
            double arg = 1.0;
            for (int j=0; j<y.length; j++) {
                if(i != j){
                    arg *= (X - x[j]) / (x[i]-x[j]);
                }
            }
            result += arg * y[i];
        }
        return result;
    }
    double Newton(double X){
        double res = y[0];
        for(int i = 1; i < y.length; ++i){
            double arg = 0;
            for(int j = 0; j <= i; ++j){
                double d = 1;
                for(int k = 0; k <= i; ++k)
                    if (k != j)
                        d *= (x[j] - x[k]);
                arg += y[j]/d;
            }
            for(int k = 0; k < i; ++k)
                arg *= (X - x[k]);
            res += arg;
        }
        return res;
    }
    double [] ChebeshevMinMax(double a, double b,double[] xCheb, double[] yCheb) {
        double m = 0;
        for (int i = 0; i<x.length; i++) {
            double koef = cos ((2*m+1)/(2*x.length+2)*PI*PI/180);
            m++;
            xCheb[i] = ((a+b)/2)+(b-a)*koef/2;
            yCheb[i] = sin(xCheb[i]*PI/180)+xCheb[i];
            System.out.println("xCheb="+xCheb[i]+" yCheb="+yCheb[i]);
        }
        return xCheb;
    }
    double Newton_2(double[] x, double[] y, double X) {
        double res = y[0];
        double buf=1,q = X - x[0];
        for (int i = 1;i<x.length;i++){
            buf *= (q-i+1)/i;
            res += buf*NewtonRec(x,y,i,0);
        }
        return res;
    }
    double NewtonRec(double[] x, double[] y, int num, int index) {
        if (num > 1) {
            return (NewtonRec(y,x,num-1,index+1)-NewtonRec(y,x,num-1,index));
        }
        else if (num == 1){
            return (y[index+1] - y[index]);
        }
        else {
            return 0;
        }
    }
    double [] Splain(double[] x, double[] y, double[] X){
        double [] a = new double[x.length-1];
        double [] b = new double[x.length-1];
        double [] splain = new double[x.length-1];
        for (int i=0; i<x.length-1;i++){
            b[i] = (y[i]-y[i+1])/(x[i+1]-x[i]);
            a[i] = y[i];
            if (X[i]>y[i] || X[i] < y[i+1]) {
                splain[i] = b[i]*(x[i]-X[i])+a[i];
            }
        }
        return splain;
    }
    double [][] MinKv(double [][] x,double[][] y){
        double[][] trans_x = trans(x);
        double[][] trans_xANDx = multiplication(trans_x,x);
        double[][] trans_xANDy = multiplication(trans_x,y);
        trans_xANDx = inversion(trans_xANDx,trans_xANDx.length);
        double[][] fun = multiplication(trans_xANDx,trans_xANDy );
        return fun;
    }
    double[][] trans(double[][] array1){
        double [][] result = new double[array1[array1.length-1].length][array1.length];
        for (int i = 0; i < array1.length; i++) {
            for (int j = 0; j < array1[array1.length-1].length; j++) {
                result[j][i] = array1[i][j];
            }
        }
        return  result;
    }
    double[][] multiplication(double[][] array1, double[][] array2)
    {
        int r1, r2, c1, c2;
        r1 = array1.length;
        c1 = array1[0].length;
        r2 = array2.length;
        c2 = array2[0].length;
        double[][] result;
        if (c1 != r2) {
            System.out.println("Error!");
            result = new double[0][0];
        } else {
            result = new double[r1][c2];
            for (int i = 0; i < r1; i++){
                for (int j = 0; j < c2; j++) {
                    for (int k = 0; k < c1; k++) {
                        result[i][j] += array1[i][k] * array2[k][j];
                    } } } }
        return result;
    }
    double[][] inversion(double [][]A, int N) {
        double temp;
        double [][] E = new double [N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++) {
                E[i][j] = 0f;
                if (i == j)
                    E[i][j] = 1f;
            }
        for (int k = 0; k < N; k++) {
            temp = A[k][k];
            for (int j = 0; j < N; j++) {
                A[k][j] /= temp;
                E[k][j] /= temp; }
            for (int i = k + 1; i < N; i++) {
                temp = A[i][k];
                for (int j = 0; j < N; j++) {
                    A[i][j] -= A[k][j] * temp;
                    E[i][j] -= E[k][j] * temp; } } }
        for (int k = N - 1; k > 0; k--) {
            for (int i = k - 1; i >= 0; i--) {
                temp = A[i][k];
                for (int j = 0; j < N; j++) {
                    A[i][j] -= A[k][j] * temp;
                    E[i][j] -= E[k][j] * temp; } } }
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                A[i][j] = E[i][j];
        return A;
    }

}
