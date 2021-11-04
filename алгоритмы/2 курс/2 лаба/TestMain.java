public class TestMain {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        new ex();
        long timeSpent = System.currentTimeMillis() - startTime;
        System.out.println("программа выполнялась " + timeSpent + " миллисекунд");
    }
}
