public class Main {
    public static void main(String[] args) {
        print();
        System.out.println("Перше завдання:");
        ex1 ex1 = new ex1();
        ex1.ex1();
        print();
        System.out.println("Друге завдання:");
        ex2 ex2 = new ex2();
        ex2.ex2();
        print();
        System.out.println("Третьє завдання:");
        ex3 ex3 = new ex3();
        ex3.ex3();
    }
    static void print(){
        for (int i = 0; i<100; i++){
            System.out.print("-");
        }
        System.out.println("");
    }
}
