import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class threadPool<Tfun, Targ, Tresult> {
    private final LinkedBlockingQueue<WorkItem<Tfun,Targ,Tresult>> queue;
    private volatile boolean isRunning = true;
    Lock Mainlock = new ReentrantLock();
    Condition Maincondition = Mainlock.newCondition();


    public threadPool(int nThreads) {
        queue = new LinkedBlockingQueue<>();
        List<PoolWorker> poolWorkerList = new ArrayList<>(3);

        for (int i = 0; i < nThreads; i++) {
            poolWorkerList.add(new PoolWorker());
            poolWorkerList.get(i).start();
        }
    }
    public Future execute(function<Tfun> func, Targ arg) {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        WorkItem<Tfun,Targ,Tresult> item = new WorkItem(func,arg, lock, condition);
        if (isRunning){
            synchronized (queue) {
                queue.add(item);
                queue.notify();
            }
        }
        return item.future;
    }

    public ArrayList<Future> map(function<Tfun> func, List args){
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        ArrayList<Future> futures = new ArrayList();
        if (isRunning){
            synchronized (queue) {
                args.forEach(arg->{
                    WorkItem<Tfun,Targ,Tresult> item = new WorkItem(func,arg, lock, condition);
                    futures.add(item.future);
                    queue.add(item);
                    queue.notify();
                });
            }
        }
        return futures;
    }
    public void shutdown() throws InterruptedException {
            if (!queue.isEmpty()) {
                System.out.println("Ждём выполнение: " + queue.size());
                Mainlock.lock();
                Maincondition.await();
                Mainlock.unlock();
                System.out.println("Дождались окончание всех тасков: " + queue.size());
            }
            System.out.println("Метод shutdown!");
            isRunning = false;
    }

    public class PoolWorker extends Thread {
        public void run() {

            WorkItem<Tfun,Targ,Tresult> item;
            while (isRunning) {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        try {
                            Mainlock.lock();
                            Maincondition.signal();
                            Mainlock.unlock();
                            queue.wait();
                        } catch (InterruptedException e) {
                            System.out.println("An error occurred while queue is waiting: " + e.getMessage());
                        }
                    }
                    item = queue.poll();
                }
                try {

                    //Для тестирования программы делаем задержку
                    sleep(1000);


                    item.future.lock.lock();
                    item.future.setRes((Tresult) item.func.func((Tfun) item.arg));
                    item.future.condition.signal();
                    item.future.lock.unlock();
                } catch (RuntimeException | InterruptedException e) {
                    System.out.println("Thread pool is interrupted due to an issue: " + e.getMessage());
                }
            }
        }
    }
}

class Future<Tresult>{
    public boolean hasResult = false;
    public Tresult res;
    Lock lock;
    Condition condition;
    Date date;

    public Future(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    public void setRes(Tresult res) {
        if (!(res==null)){
            this.res = res;
            hasResult = true;
            date = new Date();
        }
    }
    public Tresult result() throws InterruptedException {
        if (res==null){
            lock.lock();
            condition.await();
            lock.unlock();
        }
        return res;
    }
}

class WorkItem <Tfun, Targ, Tresult> {
    function<Tfun> func;
    Targ arg;
    Future<Tresult> future;

    public WorkItem(function<Tfun> func, Targ arg, Lock lock, Condition condition) {
        this.func = func;
        this.arg = arg;
        future = new Future<>(lock, condition);
    }
}