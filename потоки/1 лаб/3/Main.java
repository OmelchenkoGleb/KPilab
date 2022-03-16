import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class Main {
    static volatile AtomicInteger i = new AtomicInteger(0);
    static volatile HashMap<Integer, ArrayList<word>> map = new HashMap<>();
    public static void main(String[] args) throws FileNotFoundException, InterruptedException {
        long time = System.currentTimeMillis();
        Scanner sc = new Scanner(System.in);
        System.out.println("Здравствуйте, введите путь к каталогу: ");
        String namedir = sc.nextLine();
        File dir = new File(namedir);
        if(dir.exists()) {
//   C:\Users\Gleb\IdeaProjects\potokilab1\src\main\resources\docs-1000-10000
            HashMap<Integer, ArrayList<word>> map = map(word -> word.count++ , Objects.requireNonNull(dir.listFiles()));
            Thread.sleep(10000);
            ArrayList<word> result = reduce(((word1, word2) -> word1.count += word2.count), map);
            System.out.println("\nРезультат: ");
            result.forEach(System.out::println);
            System.out.println(result.stream().collect(Collectors.summarizingInt(x-> x.count)));
        } else System.out.println("Такого каталога не существует");
        System.out.println("Время работы программы в милисекундах: " + (System.currentTimeMillis() - time));
    }


    public static HashMap<Integer, ArrayList<word>> map (counter counter,  File[] filelist) throws FileNotFoundException, InterruptedException {
        System.out.println("Обработка таких файлов: ");
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
        for(File item : filelist){
            if(item.isFile() && item.getName().endsWith(".txt")) {
                MyThread myThread1 = new MyThread();
                myThread1.item = item;
                myThread1.counter = counter;
                executor.execute(myThread1);
            }
        }
        executor.shutdown();
        return map;
    }


    static class MyThread implements Runnable {
        File item;
        counter counter;
        @Override
        public void run() {
                ArrayList<word> arrayList = new ArrayList<>();
                FileReader fileReader = null;
                try {
                    fileReader = new FileReader(item);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                Scanner scanFile = new Scanner(fileReader);
                while (scanFile.hasNextLine()) {
                    String arg = scanFile.nextLine();
                    if(arrayList.stream().noneMatch(word -> word.name.equals(arg))) arrayList.add(new word(arg));
                    else arrayList.forEach(word -> {if(word.name.equals(arg)) counter.count(word);});
                }
                map.put(i.get(),arrayList);
                i.incrementAndGet();
                System.out.println("Файл обработан: " + item.getName());
        }
    }


    public static ArrayList<word> reduce(redu redu,HashMap<Integer, ArrayList<word>> map){
        ArrayList<word> first = map.get(0);
        for(int k=1; k<i.get(); k++) {
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
