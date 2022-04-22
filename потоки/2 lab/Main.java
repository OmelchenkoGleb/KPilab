import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] ar) throws InterruptedException {
        List<Integer> args = List.of(2,3,4,5);
        function<Integer> function = x -> x *= 2;
        threadPool<Integer,Integer,Integer> threadPool = new threadPool<>(2);
        ArrayList<Future> futures = threadPool.map(function,args);
        threadPool.shutdown();
        System.out.println();
        futures.forEach(f ->{
            try {
                System.out.println(f.date+"  -  "+f.result());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
}