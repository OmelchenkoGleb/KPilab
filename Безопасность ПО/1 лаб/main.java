import java.io.IOException;
import java.util.*;

public class main {
    static Scanner sc;
    public static void main(String[] args) throws IOException {
        if (!check()) {
            sc = new Scanner(System.in);
            String activation = "y snp"; // world
            System.out.println("По условию использывания, вы проштрафили время.");
            System.out.println("Введите ключ для разблокировки: ");
            String key = sc.nextLine();
            Vizhinera v = new Vizhinera();
            StringBuilder action = v.encryption(key,"bla");
            if (!action.toString().equals(activation)){
                System.out.println("Ключ неверный");
                System.exit(0);
            }
        }
        String q = "";
        while(!Objects.equals(q, "q")){
            int v = menu();
            if(v != 1 && v != 2) {
                sc = new Scanner(System.in);
                Filework filework = new Filework();
                if(v == 3) {
                    filework.newFile();
                }
                if (v == 4) {
                    filework.openFile();
                }
                if (v == 5) {
                    System.out.println("Ведите название файла");
                    String txt = sc.nextLine();
                    filework.deleteFile(txt);
                }
                if (v == 6){
                    System.out.println("Ведите название файла");
                    String txt = sc.nextLine();
                    filework.renameFile(txt);
                }
            }
            else {
                sc = new Scanner(System.in);
                System.out.println("Введите, пожалуйста, текст шифровки");
                String text = sc.nextLine();
                System.out.println("Введите, пожалуйста, слово-ключ шифровки");
                String key = sc.nextLine();
                new Vizhinera(text, key, v);
            }
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
        System.out.println("3. Создать новый файл");
        System.out.println("4. Открыть файл");
        System.out.println("5. Удалить файл");
        System.out.println("6. Переименовать файл");
        int i = sc.nextInt();
        while(i != 1 && i != 2 && i != 3 && i != 4 && i != 5 && i != 6){
            System.out.println("Ведите, номер выбора.");
            i = sc.nextInt();
        }
        return i;
    }

    public static boolean check(){
        return new GregorianCalendar(2022, Calendar.MARCH,14).getTime().before(new GregorianCalendar(2022, Calendar.FEBRUARY,28).getTime());
        //return new Date().before(new GregorianCalendar(2022, Calendar.FEBRUARY,28).getTime());
    }
}
