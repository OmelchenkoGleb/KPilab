public class Main {
    public static void main(String[] args) {
        ex ex = new ex();
        double res1 = ex.task1(0.01,1,1,1);
        System.out.println();
        System.out.println("["+res1+" | "+ex.function(res1)+"]");
        for (int i =0; i<45;i++) System.out.print("-");
        System.out.println();
        ex.task2(100);
    }
}
