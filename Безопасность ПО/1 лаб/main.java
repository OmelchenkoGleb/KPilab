import java.util.*;


public class main {
    static Scanner sc;

    public static void main(String[] args) {
        if (!check()) {
            System.out.println("По условию использывания, вы проштрафили время.");
            System.exit(0);
        }
        String q = "";
        while(!Objects.equals(q, "q")){
            sc = new Scanner(System.in);
            System.out.println("Введите, пожалуйста, текст шифровки");
            String text = sc.nextLine();
            System.out.println("Введите, пожалуйста, слово-ключ шифровки");
            String key = sc.nextLine();
            int v = menu();
            new Vizhinera(text, key, v);
            System.out.println("Если желаете закончить процесс работы введите q");
            q = sc.next();
            System.out.println("-------------");
        }
    }

    public static int menu(){
        sc = new Scanner(System.in);
        System.out.println("Выберите, что вам нужно.");
        System.out.println("1. Шифровка");
        System.out.println("2. Дешифровка");
        int i = sc.nextInt();
        while(i != 1 && i != 2){
            System.out.println("Ведите, номер выбора.");
            i = sc.nextInt();
        }
        return i;
    }

    public static boolean check(){
        //return new GregorianCalendar(2022, Calendar.MARCH,14).getTime().before(new GregorianCalendar(2022, Calendar.FEBRUARY,28).getTime());
        return new Date().before(new GregorianCalendar(2022, Calendar.FEBRUARY,28).getTime());
    }
}
