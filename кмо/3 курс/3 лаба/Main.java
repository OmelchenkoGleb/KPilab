public class Main {
    public static void main(String[] args) {
        ex ex = new ex();
        System.out.println("Задание 1 !");
        double res1 = ex.task1(0.01,1,1,1,1);
        System.out.println();
        for (int i=0;i<50;i++) System.out.print("-");
        System.out.println();
        System.out.println();
        System.out.println("Задание 2 !");
        ex.task2(0.01,1,1,1,1,0);
    }
}
