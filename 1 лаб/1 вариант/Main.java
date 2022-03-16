import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        long time = System.currentTimeMillis();
        Scanner sc = new Scanner(System.in);
        System.out.println("Здравствуйте, введите путь к каталогу: ");
        String namedir = sc.nextLine();
        File dir = new File(namedir);
        if(dir.exists()) {
            for(File item : dir.listFiles()){
                if(item.isFile() && item.getName().endsWith(".txt")) {
                    ArrayList<word> arrayList = new ArrayList<>();
                    FileReader fileReader = new FileReader(item);
                    Scanner scanFile = new Scanner(fileReader);
                    while (scanFile.hasNextLine()) {
                        String arg = scanFile.nextLine();
                        if(arrayList.stream().noneMatch(word -> word.name.equals(arg))){
                            arrayList.add(new word(arg));
                        } else {
                            arrayList.forEach(word -> {
                                if(word.name.equals(arg)) word.count++;
                            });
                        }
                    }
                    arrayList.forEach(System.out::println);
//                    System.out.println(arrayList.stream().mapToInt(x -> x.count).sum());
                }
                System.out.println("");
            }
        } else System.out.println("Такого каталога не существует");
        System.out.println("Время работы программы в милисекундах: " + (System.currentTimeMillis() - time));
    }
}
