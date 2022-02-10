import java.util.*;
import java.util.stream.Collectors;
public class genetic {
    Random random = new Random();
    double Xmax = 10;
    double Xmin = 0;
    int N = 6;
    int iteration = 1000000;
    int le = 2;
    ArrayList<Point> ListPoint = new ArrayList<>();
    ArrayList<Point> Ticher;
    ArrayList<Point> Ticher2;
    genetic(){
        init();
        for (int k=0;k<=iteration;k++) {
            selection();
            System.out.print("Iteration: "+k);
            baptism();
        }
    }
    //иницилизация начальной популяции
    public void init(){
        for (int i =0; i<N; i++) {
            double x = Math.random()*Xmax+Xmin;
            ListPoint.add(new Point(x));
        }
    }
    //Отбор
    public void selection(){
        Ticher = new ArrayList<>();
        ListPoint = (ArrayList<Point>) ListPoint.stream().sorted(Comparator.comparing(Point::getY)).collect(Collectors.toList());
        for (int i=0; i<4; i++) Ticher.add(ListPoint.get(i));
    }
    //скрещивание
    public void baptism(){
        System.out.println(" "+Ticher.get(0));
        Ticher2 = new ArrayList<>();
        for (int i=0; i<le; i++){
            Ticher2.add(Ticher.get(i));
        }
        for (int i=0; i<le; i++){
            Ticher.remove(i);
        }
        for (int i=0; i<Ticher.size();i++){
            CrossoverAndMutation(i);
        }
        for (int v=0; v<le;v++)Ticher.add(Ticher2.get(v));
        NewChild();
        ListPoint = Ticher;
    }
    //Новые дети
    public void NewChild(){
        for (int i = 0;i<N-le;i++){
            double x = Math.random()*Xmax;
            Ticher.add(new Point(x));
        }
    }
    //Кроссовер
    public void CrossoverAndMutation(int i){
        ArrayList<String> arrayListString = new ArrayList<>();
        String s1 = Ticher.get(i).doubleBitsStr;
        String s2 = Ticher2.get(i).doubleBitsStr;
        String znak = s1.substring(0,1);
        String celoe1 = s1.substring(1,12);
        String drob1 = s1.substring(12,s1.length());
        String celoe2 = s2.substring(1,12);
        String drob2 = s2.substring(12,s2.length());
        int del1 = random.nextInt(10);
        int del2 = random.nextInt(30 - 20 + 1) + 20;
        arrayListString.add(celoe1.substring(0,del1)+celoe2.substring(del1,celoe2.length()));
        arrayListString.add(celoe2.substring(0,del1)+celoe1.substring(del1,celoe1.length()));
        arrayListString.add(drob1.substring(0,del2)+drob2.substring(del2,drob2.length()));
        arrayListString.add(drob2.substring(0,del2)+drob1.substring(del2,drob1.length()));
        arrayListString = mutation(arrayListString);
        String s1new = znak+arrayListString.get(0)+arrayListString.get(2);
        String s2new = znak+arrayListString.get(1)+arrayListString.get(3);
        Ticher.get(i).doubleBitsStr = s1new;
        Ticher2.get(i).doubleBitsStr = s2new;
        //Вычисление нового икса
        double res1 = Double.longBitsToDouble(Long.parseLong(s1new, 2));
        double res2 = Double.longBitsToDouble(Long.parseLong(s2new, 2));
        if( res1 < 0 || res1>10) ; else {
            Ticher.get(i).x = res1;
            Ticher.get(i).setY();
        }
        if( res2 < 0 || res2>10) ; else {
            Ticher2.get(i).x = res2;
            Ticher2.get(i).setY();
        }
    }
    //Мутация
    public ArrayList mutation(ArrayList<String> arrayListString){
        int del1 = random.nextInt(arrayListString.get(0).length() - 1 - 1 + 1) + 1;
        int del2 = random.nextInt(arrayListString.get(1).length() - 5 - 5 + 1) + 5;
        String celoe1 = arrayListString.get(0);
        String celoe1sub = celoe1.substring(del1-1,del1).replace("0","1");
        arrayListString.set(0,celoe1.substring(0,del1-1)+celoe1sub+celoe1.substring(del1,celoe1.length()));
        String celoe2 = arrayListString.get(0);
        String celoe2sub = celoe2.substring(del1-1,del1).replace("0","1");
        arrayListString.set(0,celoe2.substring(0,del1-1)+celoe2sub+celoe2.substring(del1,celoe2.length()));
        String drob1 = arrayListString.get(0);
        String drob1sub = drob1.substring(del2-1,del2).replace("0","1");
        arrayListString.set(0,drob1.substring(0,del2-1)+drob1sub+drob1.substring(del2,drob1.length()));
        String drob2 = arrayListString.get(0);
        String drob2sub = drob2.substring(del2-1,del2).replace("0","1");
        arrayListString.set(0,drob2.substring(0,del2-1)+drob2sub+drob2.substring(del2,drob2.length()));
        return arrayListString;
    }
}
