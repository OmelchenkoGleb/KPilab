import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.awt.*;
import java.io.FileNotFoundException;

public class Main {
    private static ex obj;

    static {
        try {
            obj = new ex();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        graf5();
    }
    static void graf5(){
        double [][] graf;
        graf = obj.res;
        XYSeries series = new XYSeries("Наименьших квадратов");
        for(double i = -10; i < 0; i+=0.1){
            series.add(i, graf[0][0] + graf[1][0]*i);
            if (i <= -7.7 &&  i <= -7.9){
                continue;
            }
        }
        series.add(-7.8, graf[0][0] + graf[1][0]*-7.8+0.08);
        series.add(-5.5, graf[0][0] + graf[1][0]*-5.5+0.08);
        series.add(-2.3, graf[0][0] + graf[1][0]*-2.3+0.08);
        XYDataset xyDataset = new XYSeriesCollection(series);
        JFreeChart chart = ChartFactory.createXYLineChart("y = " + graf[0][0]+ "+" + graf[1][0] + "*x", "x", "y", xyDataset, PlotOrientation.VERTICAL, true, true, true);
        chart.setBackgroundPaint(Color.YELLOW);
        JFrame frame = new JFrame("Наименьших квадратов");
        frame.getContentPane().add(new ChartPanel(chart));
        frame.setBounds(450,40,1000,1000);
        frame.show();
    }

    // Графики



}