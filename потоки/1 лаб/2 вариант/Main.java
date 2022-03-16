import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;
public class Main {
    static Integer i = 0;
    public static void main(String[] args) throws FileNotFoundException {
        long time = System.currentTimeMillis();
        Scanner sc = new Scanner(System.in);
        System.out.println("Здравствуйте, введите путь к каталогу: ");
        String namedir = sc.nextLine();
        File dir = new File(namedir);
        if(dir.exists()) {
            HashMap<Integer, ArrayList<word>> map = map(word -> word.count++ , Objects.requireNonNull(dir.listFiles()));
            ArrayList<word> result = reduce(((word1, word2) -> word1.count += word2.count), map);
            System.out.println("\nРезультат: ");
            result.forEach(System.out::println);
        } else System.out.println("Такого каталога не существует");
        System.out.println("Время работы программы в милисекундах: " + (System.currentTimeMillis() - time));
    }
    public static HashMap<Integer, ArrayList<word>> map (counter counter,  File[] filelist) throws FileNotFoundException {
        HashMap<Integer, ArrayList<word>> map = new HashMap<>();
        System.out.println("Обработка таких файлов: ");
        for(File item : filelist){
            if(item.isFile() && item.getName().endsWith(".txt")) {
                ArrayList<word> arrayList = new ArrayList<>();
                FileReader fileReader = new FileReader(item);
                Scanner scanFile = new Scanner(fileReader);
                System.out.println("Название файла: " + item.getName());
                while (scanFile.hasNextLine()) {
                    String arg = scanFile.nextLine();
                    if(arrayList.stream().noneMatch(word -> word.name.equals(arg))) arrayList.add(new word(arg));
                    else arrayList.forEach(word -> {if(word.name.equals(arg)) counter.count(word);});
                }
                map.put(i++,arrayList);
            }
        }
        return map;
    }
    public static ArrayList<word> reduce(redu redu,HashMap<Integer, ArrayList<word>> map){
        ArrayList<word> first = map.get(0);
        for(int k=1; k<i; k++) {
            ArrayList<word> next = map.get(k);
            for (int j = 0; j< next.size() ; j++){
                for(int p = 0; p< first.size(); p++){
                    if(next.get(j).name.equals(first.get(p).name)){
                        redu.red(first.get(p), next.get(j));
                        break;
                    }
                    if(p == first.size()-1) first.add(next.get(j));
                }
            }
        }
        return first;
    }
}
