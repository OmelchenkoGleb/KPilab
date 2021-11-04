import java.util.ArrayList;

import static java.lang.Math.*;


public class main {

    static public void main(String[] args) {
        print();
    }



    static void print(){
        ex ex = new ex();
        for (int i = 0; i<48;i++) System.out.print("-");
        System.out.println();
        double [] res = ex.KvPohibka(0,0,0,1,0.5, 0.5/2, "rungeKutta");
        double [] res2 = ex.KvPohibka(0,0,0,1,0.5, 0.5/2, "Euler");
        System.out.println("| RungeKutta | X = " + (float)res[0] +  " | Y = " + (float)res[1]  + "  |");
        for (int i = 0; i<48;i++) System.out.print("-");
        System.out.println();
        System.out.println("| Euler      | X = " + (float)res2[0] + " | Y = " + (float)res2[1] + "  |");
        for (int i = 0; i<48;i++) System.out.print("-");
        System.out.println();
        System.out.println("| Difference | X = " + abs((float)res[0]-(float)res2[0]) + " | Y = " + abs((float)res[1]-(float)res2[1]) + " |");
        for (int i = 0; i<48;i++) System.out.print("-");
    }

}