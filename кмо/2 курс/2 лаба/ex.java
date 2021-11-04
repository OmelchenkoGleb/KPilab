import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import javax.swing.*;

import static java.lang.Math.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.*;

public class ex {
    double g = 3;
    double k = 4;

    ex() {
        print();
        printEx1();
        print();
        printEx2();
        print();
        printEx3();
        print();
        printEx4();
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
        System.out.println("f(x) = (x - 12)^2 + sin(x - 12) = 0");
        System.out.println("f(x)` = 2·x + cos(x - 12) - 24");
        System.out.println("f(x)`` = -sin(x - 12) + 2");
        System.out.println("");
        int n = 1;
        double a = 11.6;
        double b = 12.5;
        double e = 0.001;
        System.out.println("Половинного диления: " + bisekcii(a,b,e));
        System.out.println("Хорд: " + hord(a,b,e,1,n));
        System.out.println("Дотичных: " + dot(a,b,e,1,n));
        grafick("ex1");
    }
    public double funct(double x){
        return pow((x-12),2)+sin(Math.toDegrees(x-12));
    }
    public double funct1(double x){
        return 2*x+cos(Math.toDegrees(x-12))-24;
    }
    public double funct11(double x){
        return 2-sin(Math.toDegrees(x-12));
    }
    public double bisekcii(double a, double b, double e){
        double c = (b+a)/2;
        if (c == 0){
            return c;
        }
        double ee = abs(b-a);
        if (ee<e){
            return c;
        }
        if (funct(a)*funct(c) < 0){
            return bisekcii(a,c,e);
        }
        if (funct(c)*funct(b) < 0){
            return bisekcii(c,b,e);
        }
        return 1000;
    }
    public double hord(double a, double b, double e, double xp, int count){
        double x1 = a - (funct(a)*(b-a))/(funct(b)-funct(a));
        if (x1 == 0) {
            return x1;
        }
        if (count > 1){
            if(abs(xp-x1)<e){
                return x1;
            }
        }
        if (funct(a)*funct(x1) < 0) {
            count++;
            return hord(a,x1,e,x1,count);
        }
        if (funct(x1)*funct(b) < 0) {
            count++;
            return hord(x1,b,e,x1,count);
        }
        return 1000;
    }
    public double dot(double a, double b, double e, double xp, int count){
        if (count == 1){
        double x0 = 0;
        if (funct(a)*funct11(a)>0){
            x0 = a;
        } else if (funct(b)*funct11(b)>0){
            x0 = b;
        }
        xp = x0;
        count++;
        }
        double x1 = xp - (funct(xp)/funct1(xp));
        if (abs(x1-xp)<e){
            return x1;
        }
        xp = x1;
        return dot(a,b,e,xp,count);
    }

    // 2 задание

    public void printEx2(){
        System.out.println("f(x) = (4*x - 30) - sin(x - 7.5) = 0");
        System.out.println("Fi(x) = sin(x-7.5)/4 + 7.5");
        System.out.println("");
        double a = 7;
        double b = 8;
        System.out.println("Метод итерации: " + iter(a,b,0.001,1,1));
        grafick("ex2");
    }
    public double f2(double x) {
        return (4*x-30) - sin(Math.toDegrees(x - 7.5));
    }
    public double fi(double x) {
        return (sin(Math.toDegrees(x-7.5))/4)+7.5;
    }
    public double iter(double a, double b, double e, double xp, int count){
        if (count == 1){
        double x0 = (a+b)/2;
        xp = x0;
            count++;
        }
        double xt = fi(xp);
        if (abs(xt-xp)<e){
            return xt;
        }
        xp = xt;
        return iter( a,  b,  e,  xp,  count);
    }

    // 3 задание

    public void printEx3(){
        double [][] A = new double[3][3];
        double [][] B = new double[3][1];
        String [] str = {"[4x+5y+6z=4","|8x+9y-2=5","[12x+3y-3z=6"};
        Pattern p = Pattern.compile("[0-9]+");
        for (int i=0 ; i<A.length; i++) System.out.println(str[i]);
        for (int i =0; i<A.length; i++){
        Matcher m = p.matcher(str[i]);
        ArrayList abc = new ArrayList();
        while (m.find()) {
            Double n = Double.parseDouble(m.group());
            abc.add(n);
        }
        for (int j =0; j<A.length; j++) {
            A[i][j] = (double) abc.get(j);
        }
            B[i][0] = (double) abc.get(3);
        }
        A[1][2] = -2;
        A[2][2] = -3;
        System.out.println("");
        System.out.println("Общая матрица:");
        for (int i =0; i<A.length; i++){
            for (int j =0; j<A.length; j++) {
                System.out.print(A[i][j] + " ");
            }
            System.out.print(B[i][0]);
            System.out.println("");
        }
        System.out.println("");
        Kramer(A,B);
        OpenFile("E:\\КПИ\\2 курс\\2 семестр\\кмо\\2 лаба\\скрины\\3 задание.docx");
    }

    public void Kramer(double [][] A, double [][] B){
        System.out.println("Метод Крамера:");
        if (det(A) == 0) {
            System.out.println("Решений нет!");
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i<A.length; i++){
            arrayList.add(det(matrixSwap(A,B,i))/det(A));
        }
        System.out.println("X = "+arrayList.get(0));
        System.out.println("Y = "+arrayList.get(1));
        System.out.println("Z = "+arrayList.get(2));
    }
    public double [][] matrixSwap (double [][] A, double [][] B, int index){
        double [][] arr = new double [A.length][A[0].length];
        for (int i =0; i<3; i++){
            for (int j =0; j<3; j++) {
                arr[i][j] = A[i][j];
            }
            arr[i][index] = B[i][0];
        }
        return arr;
    }
    public double det(double A[][]){
        int n = A.length;
        if(n == 1) return A[0][0];
        double ans = 0;
        double B[][] = new double[n-1][n-1];
        int l = 1;
        for(int i = 0; i < n; ++i){
            int x = 0, y = 0;
            for(int j = 1; j < n; ++j){
                for(int k = 0; k < n; ++k){
                    if(i == k) continue;
                    B[x][y] = A[j][k];
                    ++y;
                    if(y == n - 1){
                        y = 0;
                        ++x;
                    }
                }
            }
            ans += l * A[0][i] * det(B);
            l *= (-1);
        }
        return ans;
    }

    // 4 задание

    public void printEx4(){
        double e = 0.01;
        System.out.println("[5x - 12 + sin(5x + y - 12)/10 = 0");
        System.out.println("[y - sin(5x + y -12)/40 = 0");
        System.out.println("x = 2.4 - (sin(5 *  + y - 12) / 50)");
        System.out.println("y = sin(5 * x + y - 12) / 40");
        System.out.println("");
        double [] arr = {2,1};
        double [] res = zeidel(arr,e);
        System.out.println("Метод Зейделя: f1(x,y) = "+res[0]+" f2(x,y) = "+res[1]);
        res = Newton(arr,e);
        System.out.println("Метод Ньютона: f1(x,y) = "+res[0]+" f2(x,y) = "+res[1]);
        grafick("ex4");
    }
    double f1(double [] x){
        return 5*x[0]-12+sin(Math.toDegrees(5*x[0]+x[1]-12))/10;
    }
    double f2(double [] x){
        return x[1]-sin(Math.toDegrees(5*x[0]+x[1]-12))/40;
    }
    double fun1(double [] x){
        return 2.4-(sin(Math.toDegrees(5*x[0]+x[1]-12))/50);
    }
    double fun2(double [] x){
        return (sin(Math.toDegrees(5*x[0]+x[1]-12))/40);
    }
    float df1dx(double [] x){ return (float)  ((10+cos(Math.toDegrees(5*x[0]+x[1]-12)))/2); }
    float df1dy(double [] x){
        return (float) (cos(Math.toDegrees(5*x[0]+x[1]-12))/10);
    }
    float df2dx(double [] x){
        return (float) (-cos(Math.toDegrees(5*x[0]+x[1]-12))/8);
    }
    float df2dy(double [] x){ return (float) (1-cos(Math.toDegrees(5*x[0]+x[1]-12))/40); }
    public double [] zeidel(double [] arr, double e){
        double [] res = new double[2];
        res[0] = fun1(arr);
        res[1] = fun2(arr);
        if (0.2903225806451613*(abs(res[0]-arr[0])+abs(res[1]-arr[1]))<=e){
            return res;
        } else return zeidel(res,e);
    }
    public double [] Newton(double [] arr, double e){
        double [] exception = {0,0};
        double [] res = new double[2];
        double [][] W = new double[2][2];
        W[0][0] = df1dx(arr);
        W[0][1] = df1dy(arr);
        W[1][0] = df2dx(arr);
        W[1][1] = df2dy(arr);
        float dW = (float) det(W)*10000;
        if (dW == 0){
            System.out.println("Решений нет!");
            return exception;
        } else {
            res[0] = arr[0] - (f1(arr)*df2dy(arr)-f2(arr)*df1dy(arr))/(df1dx(arr)*df2dy(arr)-df2dx(arr)*df1dy(arr));
            res[1] = arr[1] - (f2(arr)*df1dx(arr)-f1(arr)*df2dx(arr))/(df1dx(arr)*df2dy(arr)-df2dx(arr)*df1dy(arr));
        }
        if ((0.2903225806451613*abs(res[0]-arr[0])+abs(res[1]-arr[1]))<=e){
            return res;
        } else return Newton(res,e);

    }

    //

    public void grafick(String name){
        XYSeries series;
        XYSeries series2;
        if (name == "ex1") {
            series = new XYSeries("Методами ділення навпіл, хорд та дотичних (Ньютона).");
            for(double i = -1; i < 0; i+=0.001){
                series.add(i, funct(i));
            }
            XYDataset xyDataset = new XYSeriesCollection(series);
            JFreeChart chart = ChartFactory.createXYLineChart("Методами ділення навпіл, хорд та дотичних (Ньютона).", "x", "y", xyDataset, PlotOrientation.VERTICAL, true, true, true);
            chart.setBackgroundPaint(Color.YELLOW);
            JFrame frame = new JFrame("Наименьших квадратов");
            frame.getContentPane().add(new ChartPanel(chart));
            frame.setBounds(450,40,1000,1000);
            frame.show();
            Desktop desktop = null;

            OpenFile("E:\\КПИ\\2 курс\\2 семестр\\кмо\\2 лаба\\скрины\\задание 1.png");
        } else if (name == "ex2") {
            series  = new XYSeries("Метод ітерацій(1).");
            series2 = new XYSeries("Метод ітерацій(2).");
            for(double i = -1; i < 0; i+=0.001){
                series.add(i, f2(i));
            }
            for(double i = -1; i < 0; i+=0.001){
                series2.add(i, fi(i));
            }
            XYDataset xyDataset = new XYSeriesCollection(series);
            JFreeChart chart = ChartFactory.createXYLineChart("Метод ітерацій(1).", "x", "y", xyDataset, PlotOrientation.VERTICAL, true, true, true);
            chart.setBackgroundPaint(Color.YELLOW);
            JFrame frame = new JFrame("Наименьших квадратов");
            frame.getContentPane().add(new ChartPanel(chart));
            frame.setBounds(450,40,1000,1000);
            frame.show();

            XYDataset xyDataset2 = new XYSeriesCollection(series2);
            JFreeChart chart2 = ChartFactory.createXYLineChart("Метод ітерацій(2).", "x", "y", xyDataset2, PlotOrientation.VERTICAL, true, true, true);
            chart.setBackgroundPaint(Color.YELLOW);
            JFrame frame2 = new JFrame("Наименьших квадратов");
            frame2.getContentPane().add(new ChartPanel(chart2));
            frame2.setBounds(450,40,1000,1000);
            frame2.show();
            OpenFile("E:\\КПИ\\2 курс\\2 семестр\\кмо\\2 лаба\\скрины\\задание 2.png");
        } else if (name == "ex4") {
            series = new XYSeries("Методи Ньютона та Зейделя(1).");
            series2 = new XYSeries("Методи Ньютона та Зейделя(2).");
            double [] arr = {0,0};
            for(double i = -1; i < 0; i+=0.001){
                series.add(i, f1(arr));
                arr[0]++;
                arr[1]++;
            }
            arr[0] = 0;
            arr[1] = 1;
            for(double i = -1; i < 0; i+=0.001){
                series2.add(i, f2(arr));
                arr[0]++;
                arr[1]++;
            }
            XYDataset xyDataset = new XYSeriesCollection(series);
            JFreeChart chart = ChartFactory.createXYLineChart("Методи Ньютона та Зейделя(1)", "x", "y", xyDataset, PlotOrientation.VERTICAL, true, true, true);
            chart.setBackgroundPaint(Color.YELLOW);
            JFrame frame = new JFrame("Наименьших квадратов");
            frame.getContentPane().add(new ChartPanel(chart));
            frame.setBounds(450,40,1000,1000);
            frame.show();

            XYDataset xyDataset2 = new XYSeriesCollection(series2);
            JFreeChart chart2 = ChartFactory.createXYLineChart("Методи Ньютона та Зейделя(2).", "x", "y", xyDataset2, PlotOrientation.VERTICAL, true, true, true);
            chart.setBackgroundPaint(Color.YELLOW);
            JFrame frame2 = new JFrame("Наименьших квадратов");
            frame2.getContentPane().add(new ChartPanel(chart2));
            frame2.setBounds(450,40,1000,1000);
            frame2.show();
            OpenFile("E:\\КПИ\\2 курс\\2 семестр\\кмо\\2 лаба\\скрины\\задание 4.png");
        } else {
            System.out.println("Такого задания небыло!");
            return;
        }
    }
    public void OpenFile(String name){
        Desktop desktop = null;
        if (Desktop.isDesktopSupported()) {
            desktop = Desktop.getDesktop();
        }
        try {
            String fileName = name;
            desktop.open(new File(fileName));
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }
}
